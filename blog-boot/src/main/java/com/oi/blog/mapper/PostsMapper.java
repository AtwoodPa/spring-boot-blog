package com.oi.blog.mapper;

import com.oi.blog.domain.Post;

import java.util.List;


public interface PostsMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);

    List<Post> findByUserId(Long userId);
}
