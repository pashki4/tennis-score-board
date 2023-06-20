package com.vyshniakov.service;

import com.vyshniakov.model.Player;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Match {
    private Player player1;
    private Player player2;
    private int player1Score;
    private int player2Score;
    private Player winner;
    private Set currentSet;

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentSet = new Set(this);
    }

    public void play() {
        while (winner == null) {
            //do something
        }
    }
}
