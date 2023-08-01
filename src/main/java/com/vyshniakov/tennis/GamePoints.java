package com.vyshniakov.tennis;

public enum GamePoints {
    LOVE("0"), FIFTEEN("15"), THIRTY("30"), FORTY("40"),
    AD("AD"), GAME("GAME");

    private final String description;

    GamePoints(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
