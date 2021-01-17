package com.pawelpluta.telldontaskkata.repository;

import java.util.Optional;

import com.pawelpluta.telldontaskkata.domain.WaterValve;

public interface WaterValveRepository {

    Optional<WaterValve> findById(Integer valveId);

    void save(WaterValve waterValve);
}
