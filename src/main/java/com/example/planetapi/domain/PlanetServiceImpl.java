package com.example.planetapi.domain;

import com.example.planetapi.exception.PlanetNotFoundException;
import com.example.planetapi.web.request.CreatePlanetRequest;
import com.example.planetapi.web.response.PlanetResponse;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanetServiceImpl implements PlanetService {

    private final PlanetRepository repository;

    public PlanetServiceImpl(PlanetRepository repository) {
        this.repository = repository;
    }

    @Override
    public PlanetResponse create(CreatePlanetRequest request) {
        Planet planet = new Planet(request.getName(), request.getClimate(), request.getTerrain());

        this.repository.save(planet);

        return new PlanetResponse(planet);
    }

    @Override
    public PlanetResponse get(Long id) {
        Planet planet = new Planet();

        return this.repository
            .findById(id)
            .map(PlanetResponse::new)
            .orElseThrow(PlanetNotFoundException::new);
    }

    @Override
    public List<PlanetResponse> list(String terrain, String climate) {
        Planet planet = new Planet();
        planet.setTerrain(terrain);
        planet.setClimate(climate);

        Example<Planet> planetExample = Example.of(
            planet,
            ExampleMatcher.matchingAll().withIgnoreCase().withIgnoreNullValues()
        );

        return this.repository
            .findAll(planetExample)
            .stream()
            .map(PlanetResponse::new)
            .collect(Collectors.toList());
    }
}
