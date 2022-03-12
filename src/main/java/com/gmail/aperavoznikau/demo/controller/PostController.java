package com.gmail.aperavoznikau.demo.controller;

import com.gmail.aperavoznikau.demo.repository.PostService;
import com.gmail.aperavoznikau.demo.repository.model.Post;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(
        value = "/api/posts",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Post> getPost() {
        return postService.getPosts();
    }

}


