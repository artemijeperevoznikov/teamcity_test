package com.gmail.aperavoznikau.demo.repository;

import com.gmail.aperavoznikau.demo.repository.model.Post;
import com.gmail.aperavoznikau.demo.repository.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "${feign.name}", url = "${feign.url}")
public interface PostService {

        @GetMapping(value = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
        List<Post> getPosts();

        @GetMapping(value = "/posts/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
        Post getPostById(@PathVariable("postId") Long postId);

        @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
        User addUser(User user);
}
