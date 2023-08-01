package com.vyshniakov.tennis;

public class TiebreakGame extends Game {

    public TiebreakGame(Set currentSet) {
        super(currentSet);
    }

    @Override
    public void addPlayer1Point() {
        if (winner == null) {
            player1Points++;
        }
        checkWinCondition();
    }

    @Override
    public void addPlayer2Point() {
        if (winner == null) {
            player2Points++;
        }
        checkWinCondition();
    }

    @Override
    protected void checkWinCondition() {
        if (player1Points >= 7 && player2Points <= (player1Points - 2)) {
            winner = player1;
        } else if (player2Points >= 7 && player1Points <= (player2Points - 2)) {
            winner = player2;
        }
    }
}
