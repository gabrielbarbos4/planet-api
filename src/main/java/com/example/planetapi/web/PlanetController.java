package com.example.planetapi.web;

import com.example.planetapi.domain.PlanetService;
import com.example.planetapi.web.request.CreatePlanetRequest;
import com.example.planetapi.web.response.PlanetResponse;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planet")
public class PlanetController {

    private final PlanetService planetService;

    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }

    @PostMapping
    public ResponseEntity<PlanetResponse> create(@RequestBody CreatePlanetRequest request) {
        return new ResponseEntity<>(this.planetService.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanetResponse> getPlanet(@PathVariable Long id) {
        return new ResponseEntity<>(this.planetService.get(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PlanetResponse>> list(
        @RequestParam(required = false) String terrain,
        @RequestParam(required = false) String climate
    ) {
        return new ResponseEntity<>(this.planetService.list(terrain, climate), HttpStatus.OK);
    }
}
