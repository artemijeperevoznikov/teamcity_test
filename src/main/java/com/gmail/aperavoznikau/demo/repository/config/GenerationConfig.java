package com.gmail.aperavoznikau.demo.repository.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class GenerationConfig {

    @Value("${count.albums}")
    private Integer countOfTracks;
    @Value("${count.tracks}")
    private Integer countOfAlbums;
    @Value("${lenght.tracks.min}")
    private Integer trackMinLengthValue;
    @Value("${lenght.tracks.max}")
    private Integer trackMaxLengthValue;
}
