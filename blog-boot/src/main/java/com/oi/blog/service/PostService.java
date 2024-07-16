package com.oi.blog.service;

import com.oi.blog.domain.Post;
import com.oi.blog.domain.dto.PostRequest;
import com.oi.blog.mapper.PostsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author supanpan
 * @date 2024/07/16
 */
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostsMapper postMapper;

    /**
     * 创建帖子
     * @param postRequest
     * @return
     */
    public int createPost(PostRequest postRequest) {
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setUserId(postRequest.getUser().getUserId());
        post.setCreated(new Date());
        post.setLastModified(new Date());
        return postMapper.insert(post);
    }

    /**
     * 获取帖子 - 根据用户id
     * @param userId
     * @return
     */
    public List<Post> getPosts(Long userId) {
        return postMapper.findByUserId(userId);
    }

    /**
     * 获取帖子 - 根据id
     * @param id
     * @return
     */
    public Post getPost(Long id) {
        return postMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新帖子
     * @param id
     * @param postRequest
     * @return
     */
    public int updatePost(Long id, PostRequest postRequest) {
        Post post = getPost(id);
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setLastModified(new Date());
        return postMapper.updateByPrimaryKeySelective(post);
    }

    /**
     * 删除帖子
     * @param id
     */
    public int deletePost(Long id) {
        return postMapper.deleteByPrimaryKey(id);
    }
}
