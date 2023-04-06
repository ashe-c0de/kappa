package org.ashe.kappa.auth.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.ashe.kappa.auth.dao.UserRepository;
import org.ashe.kappa.auth.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findByEmail(String email){
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getEmail, email);
        User user = userRepository.getOne(qw);
        return Optional.ofNullable(user);
    }

    public List<User> userList() {
        return userRepository.list(Wrappers.emptyWrapper());
    }
}
