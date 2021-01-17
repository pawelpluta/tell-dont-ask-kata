package com.pawelpluta.telldontaskkata.repository;

import java.util.Optional;

import com.pawelpluta.telldontaskkata.domain.RaisedBed;

public interface RaisedBedRepository {

    Optional<RaisedBed> findById(Integer raisedBedId);

    void save(RaisedBed bed);
}
