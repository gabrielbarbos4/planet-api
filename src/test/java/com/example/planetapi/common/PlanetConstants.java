package com.example.planetapi.common;

import com.example.planetapi.domain.Planet;
import com.example.planetapi.web.request.CreatePlanetRequest;
import com.example.planetapi.web.response.PlanetResponse;

public class PlanetConstants {
    public static final Planet PLANET = new Planet("test_name", "test_climate", "test_terrain");
    public static final Planet INVALID_PLANET = new Planet("", "", "");
    public static final CreatePlanetRequest CREATE_PLANET_REQUEST = new CreatePlanetRequest("test_name", "test_climate", "test_terrain");
    public static final CreatePlanetRequest INVALID_CREATE_PLANET_REQUEST = new CreatePlanetRequest("", "", "");
    public static final PlanetResponse PLANET_RESPONSE = new PlanetResponse("test_name", "test_climate", "test_terrain");
    public static final Planet TERRAIN_1_PLANET = new Planet("test_name", "climate-1","terrain-1");
}
