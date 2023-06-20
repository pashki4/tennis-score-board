package com.vyshniakov.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Player {
    private Integer id;

    private String name;

    public Player(String name) {
        this.name = name;
    }
}
