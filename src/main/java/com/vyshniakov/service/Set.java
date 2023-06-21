package com.vyshniakov.service;

import com.vyshniakov.model.Player;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Setter
@Getter
public class Set {

    private Player player1;
    private Player player2;
    private Player winner;
    private int player1Score;
    private int player2Score;
    private boolean isTiebreak = false;
    private Match match;

    @Setter(AccessLevel.NONE)
    private List<Game> games = new ArrayList<>();

    public Set(Match match) {
        this.player1 = match.getPlayer1();
        this.player2 = match.getPlayer2();
        this.match = match;
    }

    public static Set createSet(Match match) {
        return new Set(match);
    }

    public void play() {
        while (winner == null) {
            Game game = Game.createGame(this);
            game.play();
            checkWinCondition();
        }
        match.addSet(this);
    }

    private void checkWinCondition() {
        Map<Player, Long> playerWins = games.stream()
                .collect(Collectors.groupingBy(Game::getWinner, Collectors.counting()));

        Long p1WinSets = playerWins.get(player1);
        Long p2WinSets = playerWins.get(player2);

        checkTiebreak(p1WinSets, p2WinSets);

        if (!isTiebreak) {
            playerWins.entrySet().stream()
                    .filter(entry -> entry.getValue().equals(6L))
                    .findAny()
                    .ifPresent(entry -> winner = entry.getKey());
        } else {
            playerWins.entrySet().stream()
                    .filter(entry -> entry.getValue().equals(7L))
                    .findAny()
                    .ifPresent(entry -> winner = entry.getKey());
        }
    }

    private void checkTiebreak(Long p1WinSets, Long p2WinSets) {
        if (p1WinSets.equals(p2WinSets) && p1WinSets.equals(6L)) {
            isTiebreak = true;
        }
    }

    public void addGame(Game game) {
        this.games.add(game);
    }
}
