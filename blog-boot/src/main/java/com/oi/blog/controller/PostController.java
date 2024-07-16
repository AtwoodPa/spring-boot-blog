package com.oi.blog.controller;

import com.oi.blog.common.Result;
import com.oi.blog.domain.dto.PostRequest;
import com.oi.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



/**
 * 文章接口
 *
 * @author supanpan
 * @date 2024/07/16
 */
@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public Result createPost(@RequestBody PostRequest postRequest) {
        return Result.ok(postService.createPost(postRequest) == 1 ? "创建成功" : "创建失败");
    }

    @GetMapping
    public Result getPosts(@RequestParam Long uid) {
        return Result.ok(postService.getPosts(uid));
    }

    @GetMapping("/{id}")
    public Result getPost(@PathVariable Long id) {
        return Result.ok(postService.getPost(id));
    }

    @PutMapping("/{id}")
    public Result updatePost(@PathVariable Long id, @RequestBody PostRequest postRequest) {
        return Result.ok(postService.updatePost(id, postRequest) == 1 ? "更新成功" : "更新失败");
    }

    @DeleteMapping("/{id}")
    public Result deletePost(@PathVariable Long id) {
        return Result.ok(postService.deletePost(id) == 1 ? "删除成功" : "删除失败");
    }
}
