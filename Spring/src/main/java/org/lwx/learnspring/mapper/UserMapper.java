package org.lwx.learnspring.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.lwx.learnspring.dao.UserDO;

@Mapper
public interface UserMapper {
    UserDO getUserById(@Param("id") Integer id);
}
