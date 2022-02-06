package com.gmail.aperavoznikau.demo.service.impl;

import com.gmail.aperavoznikau.demo.repository.AlbumRepository;
import com.gmail.aperavoznikau.demo.repository.model.Album;
import com.gmail.aperavoznikau.demo.service.AlbumService;
import com.gmail.aperavoznikau.demo.service.converters.AlbumConverter;
import com.gmail.aperavoznikau.demo.service.model.AlbumDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlbumDatabaseServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final AlbumConverter albumConverter;

    @Override
    public List<AlbumDTO> getGeneratedAlbums() {
        List<Album> albums = albumRepository.getAll();
        return albums.stream()
                .map(albumConverter::convertToDTO)
                .collect(Collectors.toList());
    }
}
