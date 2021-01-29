package com.pawelpluta.telldontaskkata.domain;

public class WaterValve {

    private Integer id;
    private Boolean open;

    public WaterValve(Integer id) {
        this.id = id;
        open = false;
    }

    public Integer getId() {
        return id;
    }

    public void open() {
        open = true;
    }

    public void close() {
        open = false;
    }

}
