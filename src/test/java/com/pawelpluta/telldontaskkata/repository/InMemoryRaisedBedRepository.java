package com.pawelpluta.telldontaskkata.repository;

import java.util.Optional;

import com.pawelpluta.telldontaskkata.domain.RaisedBed;

public class InMemoryRaisedBedRepository implements RaisedBedRepository {

    private RaisedBed savedRaisedBed;

    @Override
    public Optional<RaisedBed> findById(Integer raisedBedId) {
        return Optional.ofNullable(savedRaisedBed);
    }

    @Override
    public void save(RaisedBed bed) {
        savedRaisedBed = bed;
    }
}
