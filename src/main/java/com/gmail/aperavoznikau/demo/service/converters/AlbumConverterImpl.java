package com.gmail.aperavoznikau.demo.service.converters;

import com.gmail.aperavoznikau.demo.repository.model.Album;
import com.gmail.aperavoznikau.demo.repository.model.Track;
import com.gmail.aperavoznikau.demo.service.model.AlbumDTO;
import com.gmail.aperavoznikau.demo.service.model.TrackDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AlbumConverterImpl implements AlbumConverter {

    @Override
    public AlbumDTO convertToDTO(Album album) {
        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setName(album.getName());
        albumDTO.setDescription(album.getDescription());

        albumDTO.getTracks()
                .addAll(
                        album.getTracks()
                                .stream()
                                .map(this::convertTrack)
                                .collect(Collectors.toList())
                );

        return albumDTO;
    }

    private TrackDTO convertTrack(Track track) {

        TrackDTO trackDTO = new TrackDTO();
        trackDTO.setName(track.getName());
        trackDTO.setLength(track.getLength());

        return trackDTO;
    }
}
