package com.pawelpluta.telldontaskkata.domain;

import java.util.List;

public class RaisedBed {

    private Integer id;
    private List<Plant> plants;

    public RaisedBed(Integer id, List<Plant> plants) {
        this.id = id;
        this.plants = plants;
    }

    public Integer getId() {
        return id;
    }

    public List<Plant> getPlants() {
        return plants;
    }
}
