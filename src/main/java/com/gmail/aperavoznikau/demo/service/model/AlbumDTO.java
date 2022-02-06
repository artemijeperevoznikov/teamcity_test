package com.gmail.aperavoznikau.demo.service.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AlbumDTO {

    private String name;
    private String description;
    private List<TrackDTO> tracks = new ArrayList<>();
}
