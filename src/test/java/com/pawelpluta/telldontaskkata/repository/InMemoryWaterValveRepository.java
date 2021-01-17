package com.pawelpluta.telldontaskkata.repository;

import java.util.Optional;

import com.pawelpluta.telldontaskkata.domain.WaterValve;

public class InMemoryWaterValveRepository implements WaterValveRepository {

    private WaterValve savedWaterValve;
    @Override
    public Optional<WaterValve> findById(Integer valveId) {
        return Optional.ofNullable(savedWaterValve);
    }

    @Override
    public void save(WaterValve waterValve) {
        savedWaterValve = waterValve;
    }
}
