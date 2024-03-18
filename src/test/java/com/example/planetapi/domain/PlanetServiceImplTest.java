package com.example.planetapi.domain;


import com.example.planetapi.exception.PlanetNotFoundException;
import com.example.planetapi.web.response.PlanetResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.planetapi.common.ObjectMapper.jsonMapper;
import static com.example.planetapi.common.PlanetConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class PlanetServiceImplTest {

    @InjectMocks
    private PlanetServiceImpl planetService;

    @Mock
    private PlanetRepository planetRepository;

    @Test
    public void create_WithValidData_ReturnsPlanetResponse() throws Exception {
        // Arrange
        lenient().when(planetRepository.save(PLANET)).thenReturn(PLANET);

        // Act
        PlanetResponse sut = planetService.create(CREATE_PLANET_REQUEST);

        // Assert
        assertThat(jsonMapper.writeValueAsString(sut)).isEqualTo(jsonMapper.writeValueAsString(PLANET_RESPONSE));
    }

    @Test
    public void createPlanet_WithInvalidData_ThrowsException() {
        // Arrange
        when(planetRepository.save(INVALID_PLANET)).thenThrow(RuntimeException.class);

        // Act / Assert
        assertThatThrownBy(() -> planetService.create(INVALID_CREATE_PLANET_REQUEST)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void getPlanet_WithExistentId_ReturnsPlanet() throws Exception {
        // Arrange
        when(planetRepository.findById(anyLong())).thenReturn(Optional.of(PLANET));

        // Act
        PlanetResponse sut = planetService.get(1L);

        // Assert
        assertThat(jsonMapper.writeValueAsString(sut)).isEqualTo(jsonMapper.writeValueAsString(PLANET_RESPONSE));
    }

    @Test
    public void getPlanet_WithNoExistentId_ThrowsException() {
        // Arrange
        when(planetRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act / Assert
        assertThatThrownBy(() -> planetService.get(100L)).isInstanceOf(PlanetNotFoundException.class);
    }

    @Test
    public void getPlanetList_withFilters_ReturnsFilteredPlanetList() {
        // Arrange
        List<Planet> planets = new ArrayList<>() {
            {
                add(PLANET);
            }
        };

        Planet planetExample = new Planet();
        planetExample.setClimate(PLANET.getClimate());
        planetExample.setTerrain(PLANET.getTerrain());

        Example<Planet> example = Example.of(planetExample, ExampleMatcher.matchingAll().withIgnoreCase().withIgnoreNullValues());

        when(
            planetRepository.findAll(
                argThat(exampleItem ->
                    exampleItem.getProbe().getClimate().equals(PLANET.getClimate()) &&
                        exampleItem.getProbe().getTerrain().equals(PLANET.getTerrain())
                )
            )
        ).thenReturn(planets);

        // Act
        List<PlanetResponse> sut = planetService.list(PLANET.getTerrain(), PLANET.getClimate());

        // Assert
        assertThat(sut).isNotEmpty();
        assertThat(sut).hasSize(1);
        assertThat(sut.get(0)).isInstanceOf(PlanetResponse.class);
    }
}
