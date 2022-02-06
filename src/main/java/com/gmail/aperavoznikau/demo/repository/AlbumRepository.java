package com.gmail.aperavoznikau.demo.repository;

import com.gmail.aperavoznikau.demo.repository.model.Album;

import java.util.List;

public interface AlbumRepository {
    List<Album> getAll();
}
