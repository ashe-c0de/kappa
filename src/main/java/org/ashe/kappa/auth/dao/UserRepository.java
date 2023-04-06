package org.ashe.kappa.auth.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ashe.kappa.auth.model.User;
import org.springframework.stereotype.Repository;

/**
 * DAO Layer
 * DAO----Data Access Object
 */
@Repository
public class UserRepository extends ServiceImpl<UserMapper, User> {
}
