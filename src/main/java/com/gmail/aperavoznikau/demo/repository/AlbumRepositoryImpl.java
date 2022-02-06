package com.gmail.aperavoznikau.demo.repository;

import com.gmail.aperavoznikau.demo.repository.config.GenerationConfig;
import com.gmail.aperavoznikau.demo.repository.model.Album;
import com.gmail.aperavoznikau.demo.repository.model.Track;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
@RequiredArgsConstructor
public class AlbumRepositoryImpl implements AlbumRepository {

    private Random random = new Random();
    private final GenerationConfig generationConfig;

    @Override
    public List<Album> getAll() {
        return IntStream.range(0, generationConfig.getCountOfAlbums()).boxed()
                .map(this::createAlbum)
                .collect(Collectors.toList());
    }

    private Album createAlbum(Integer element) {
        Album album = new Album();
        album.setName("Album" + element);
        album.setName("Description " + element);

        album.getTracks().addAll(
                IntStream.range(0, generationConfig.getCountOfTracks()).boxed()
                        .map(this::createTrack)
                        .collect(Collectors.toList())
        );
        return album;
    }

    private Track createTrack(Integer index) {
        Track track = new Track();
        track.setName("Track" + index);
        track.setLength(random.nextInt(generationConfig.getTrackMaxLengthValue()));
        return track;
    }
}
