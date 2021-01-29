package com.pawelpluta.telldontaskkata.repository;

import java.util.Optional;
import java.util.UUID;

import com.pawelpluta.telldontaskkata.domain.RaisedBed;

public interface RaisedBedRepository {

    Optional<RaisedBed> findById(UUID raisedBedId);

    void save(RaisedBed bed);
}
