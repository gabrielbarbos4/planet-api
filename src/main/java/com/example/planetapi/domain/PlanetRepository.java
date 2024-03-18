package com.example.planetapi.domain;

import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetRepository extends CrudRepository<Planet, Long>, QueryByExampleExecutor<Planet> {
    @NonNull
    @Override
    <S extends Planet> List<S> findAll(@Nonnull Example<S> example);
}
