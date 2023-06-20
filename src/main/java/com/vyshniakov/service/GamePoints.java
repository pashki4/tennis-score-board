package com.vyshniakov.service;

public enum GamePoints {
    LOVE("0"), FIFTEEN("15"), THIRTY("30"), FORTY("40"),
    AD("ADVANTAGE"), GAME("GAME");

    private String description;

    GamePoints(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
