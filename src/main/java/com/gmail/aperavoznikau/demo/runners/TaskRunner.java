package com.gmail.aperavoznikau.demo.runners;

import com.gmail.aperavoznikau.demo.service.AlbumService;
import com.gmail.aperavoznikau.demo.service.model.AlbumDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
public class TaskRunner implements ApplicationRunner {

    private final AlbumService albumService;

    public TaskRunner(AlbumService albumService) {
        this.albumService = albumService;
    }

    @Override
    public void run(ApplicationArguments args) {
        List<AlbumDTO> albums = albumService.getGeneratedAlbums();
        log.info(String.valueOf(albums));
    }
}
