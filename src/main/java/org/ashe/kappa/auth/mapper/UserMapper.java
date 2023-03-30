package org.ashe.kappa.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.ashe.kappa.auth.model.User;


@Mapper
public interface UserMapper extends BaseMapper<User> {

}
