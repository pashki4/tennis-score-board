package com.vyshniakov.tennis;

import com.vyshniakov.model.Player;
import lombok.Getter;

import static com.vyshniakov.tennis.GamePoints.*;

@Getter
public class Game {
    private GamePoints player1Points = LOVE;
    private GamePoints player2Points = LOVE;
    private final Player player1;
    private final Player player2;
    private final Set currentSet;
    private Player winner;
    private boolean isDeuce = false;

    public Game(Set currentSet) {
        this.player1 = currentSet.getPlayer1();
        this.player2 = currentSet.getPlayer2();
        this.currentSet = currentSet;
    }

    public void addPlayer1Point() {
        if (winner == null) {
            if (!isDeuce) {
                notDeucePlayer1Logic();
                checkDeuce();
            } else {
                deucePlayer1Logic();
            }
            checkWinCondition();
        }
    }

    private void notDeucePlayer1Logic() {
        if (player1Points != FORTY) {
            player1Points = GamePoints.values()[player1Points.ordinal() + 1];
        } else {
            player1Points = GAME;
        }
    }

    private void deucePlayer1Logic() {
        if (player1Points == FORTY && player2Points == FORTY) {
            player1Points = AD;
        } else if (player1Points == AD && player2Points == FORTY) {
            player1Points = GAME;
        } else {
            player1Points = player2Points = FORTY;
        }
    }

    private void checkWinCondition() {
        if (player1Points == GAME) {
            winner = player1;
        } else if (player2Points == GAME) {
            winner = player2;
        }
    }

    public void addPlayer2Point() {
        if (winner == null) {
            if (!isDeuce) {
                notDeucePlayer2Logic();
                checkDeuce();
            } else {
                deucePlayer2Logic();
            }
            checkWinCondition();
        }
    }

    private void deucePlayer2Logic() {
        if (player1Points == FORTY && player2Points == FORTY) {
            player2Points = AD;
        } else if (player2Points == AD && player1Points == FORTY) {
            player2Points = GAME;
        } else {
            player1Points = player2Points = FORTY;
        }
    }

    private void notDeucePlayer2Logic() {
        if (player2Points != FORTY) {
            player2Points = GamePoints.values()[player2Points.ordinal() + 1];
        } else {
            player2Points = GAME;
        }
    }

    private void checkDeuce() {
        if ((player1Points == FORTY) && (player2Points == FORTY)) {
            isDeuce = true;
        }
    }
}
