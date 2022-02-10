package com.gmail.aperavoznikau.demo.service.converters;

import com.gmail.aperavoznikau.demo.repository.model.Album;
import com.gmail.aperavoznikau.demo.service.model.AlbumDTO;

public interface AlbumConverter {

    AlbumDTO convertToDTO(Album album);

}
