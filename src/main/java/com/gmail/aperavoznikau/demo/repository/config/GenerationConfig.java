package com.gmail.aperavoznikau.demo.repository.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class GenerationConfig {

    @Value("${count.albums}")
    private int countOfTracks;
    @Value("${count.tracks}")
    private int countOfAlbums;
    @Value("${lenght.tracks.min}")
    private int trackMinLengthValue;
    @Value("${lenght.tracks.max}")
    private int trackMaxLengthValue;
}
