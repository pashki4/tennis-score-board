package com.vyshniakov.service;

import com.vyshniakov.model.Player;
import com.vyshniakov.util.Utils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Set {

    private Player player1;
    private Player player2;
    private Player winner;
    private int player1Score;
    private int player2Score;
    private Match match;

    @Setter(AccessLevel.NONE)
    private List<Game> games = new ArrayList<>();

    public Set(Match match) {
        this.player1 = match.getPlayer1();
        this.player2 = match.getPlayer2();
    }

    public void addGame(Game game) {
        this.games.add(game);
    }

    public void play() {
        while(winner == null) {
            Game game = Game.createGame(this);
            game.play();
        }
    }
}
