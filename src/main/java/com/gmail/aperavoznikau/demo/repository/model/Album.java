package com.gmail.aperavoznikau.demo.repository.model;

import com.gmail.aperavoznikau.demo.service.model.TrackDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Album {
    private String name;
    private String description;
    private List<Track> tracks = new ArrayList<>();
}
