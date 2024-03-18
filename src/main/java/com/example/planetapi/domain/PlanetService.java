package com.example.planetapi.domain;


import com.example.planetapi.web.request.CreatePlanetRequest;
import com.example.planetapi.web.response.PlanetResponse;

import java.util.List;

public interface PlanetService {
    PlanetResponse create(CreatePlanetRequest request);
    PlanetResponse get(Long id);
    List<PlanetResponse> list(String terrain, String climate);
}
