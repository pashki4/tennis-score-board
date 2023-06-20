package com.vyshniakov.service;

import com.vyshniakov.model.Player;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        Match match = new Match(player1, player2);
        Set set = new Set(match);
        set.play();
    }
}
