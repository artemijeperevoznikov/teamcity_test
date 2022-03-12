package com.gmail.aperavoznikau.demo.repository.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Post {
    private Long id;
    private Long userId;
    private String title;
    @JsonProperty("body")
    private String description;
}
