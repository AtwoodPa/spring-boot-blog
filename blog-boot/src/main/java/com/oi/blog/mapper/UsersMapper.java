package com.oi.blog.mapper;

import com.oi.blog.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;



/**
 * @author panpan
 */
@Mapper
public interface UsersMapper {

    int deleteByPrimaryKey(Long id);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int save(User user);

    User findByUsername(@Param("username") String username);
}
