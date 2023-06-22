package com.vyshniakov.service;

import com.vyshniakov.model.Player;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Match {
    private Player player1;
    private Player player2;
    private int player1Score;
    private int player2Score;
    private Player winner;

    @Setter(AccessLevel.NONE)
    private List<Set> sets = new ArrayList<>();

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

//    public void play() {
//        while (winner == null) {
//            Set currentSet = Set.createSet(this);
//            currentSet.play();
//        }
//    }

    public void addSet(Set set) {
        sets.add(set);
    }
}
