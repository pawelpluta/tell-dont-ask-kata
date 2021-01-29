package com.pawelpluta.telldontaskkata.repository;

import java.util.Optional;
import java.util.UUID;

import com.pawelpluta.telldontaskkata.domain.RaisedBed;

public class InMemoryRaisedBedRepository implements RaisedBedRepository {

    private RaisedBed savedRaisedBed;

    @Override
    public Optional<RaisedBed> findById(UUID raisedBedId) {
        return Optional.ofNullable(savedRaisedBed);
    }

    @Override
    public void save(RaisedBed bed) {
        savedRaisedBed = bed;
    }
}
