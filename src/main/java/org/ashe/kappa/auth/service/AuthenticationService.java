package org.ashe.kappa.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ashe.kappa.auth.dao.UserRepository;
import org.ashe.kappa.auth.model.AuthenticationRequest;
import org.ashe.kappa.auth.model.RegisterRequest;
import org.ashe.kappa.auth.model.User;
import org.ashe.kappa.auth.model.VerifyCodeRequest;
import org.ashe.kappa.infra.RedisKey;
import org.ashe.kappa.infra.RegexUtil;
import org.ashe.kappa.infra.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserRepository userRepository;
    private final RedisTemplate<String, String> redisTemplate;

    public String register(RegisterRequest request) {
        Optional<User> optional = userService.findByEmail(request.getEmail());
        if (optional.isPresent()) {
            throw new ServiceException("email was taken");
        }
        User user = new User();
        BeanUtils.copyProperties(request, user);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        // register success, issue token
        return jwtService.generateToken(user);
    }

    /**
     * authenticate password
     */
    public String authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userService.findByEmail(request.getEmail())
                .orElseThrow();
        // authenticate success, issue token
        return jwtService.generateToken(user);
    }

    /**
     * authenticate verify code
     */
    public String authenticate(VerifyCodeRequest request) {
        String verifyCode = redisTemplate.opsForValue().get(RedisKey.getRedisKey(RedisKey.VERIFY_CODE, request.getEmail()));
        Assert.isTrue(request.getVerifyCode().equals(verifyCode), "verify code is expired or invalid");
        // email should be registered
        var user = userService.findByEmail(request.getEmail())
                .orElseThrow();
        // authenticate success, issue token
        var jwtToken = jwtService.generateToken(user);
        String username = jwtService.extractUsername(jwtToken);
        log.info(String.format("%s login -->", username));
        return jwtToken;
    }

    public void sendVerifyCode(String email) {
        RegexUtil.assertEmail(email);
        // verify code ------ 6-digit random number
        String str = Integer.toString(UUID.randomUUID().hashCode());
        String verifyCode = str.substring(str.length() - 6);
        log.info(String.format("verify code is --> %s", verifyCode));
        redisTemplate.opsForValue().set(RedisKey.getRedisKey(RedisKey.VERIFY_CODE, email), verifyCode, 5, TimeUnit.MINUTES);
    }
}
