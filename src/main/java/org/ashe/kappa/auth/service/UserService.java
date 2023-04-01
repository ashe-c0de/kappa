package org.ashe.kappa.auth.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.ashe.kappa.auth.mapper.UserMapper;
import org.ashe.kappa.auth.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService extends ServiceImpl<UserMapper, User> {

    public Optional<User> findByEmail(String email){
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getEmail, email);
        User user = baseMapper.selectOne(qw);
        return Optional.ofNullable(user);
    }

    public List<User> userList() {
        return baseMapper.selectList(Wrappers.emptyWrapper());
    }
}
