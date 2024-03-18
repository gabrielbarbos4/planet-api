package com.example.planetapi.web.response;

import com.example.planetapi.domain.Planet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlanetResponse {
    private String name;
    private String climate;
    private String terrain;

    public PlanetResponse(Planet request) {
        this.name = request.getName();
        this.climate = request.getClimate();
        this.terrain = request.getTerrain();
    }
}
