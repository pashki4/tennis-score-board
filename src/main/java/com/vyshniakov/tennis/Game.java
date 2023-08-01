package com.vyshniakov.tennis;

import com.vyshniakov.model.Player;
import lombok.Getter;

import static com.vyshniakov.tennis.GamePoints.*;

@Getter
public class Game {
    private int player1Points;
    private int player2Points;
    protected final Player player1;
    protected final Player player2;
    protected final Set currentSet;
    protected Player winner;
    protected boolean isDeuce = false;

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
        if (player1Points != 3) {
            player1Points++;
        } else {
            player1Points = 5;
        }
    }

    private void deucePlayer1Logic() {
        if (player1Points == 3 && player2Points == 3) {
            player1Points = 4;
        } else if (player1Points == 4 && player2Points == 3) {
            player1Points = 5;
        } else {
            player1Points = player2Points = 3;
        }
    }

    private void checkWinCondition() {
        if (player1Points == 5) {
            winner = player1;
        } else if (player2Points == 5) {
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
        if (player1Points == 3 && player2Points == 3) {
            player2Points = 4;
        } else if (player2Points == 4 && player1Points == 3) {
            player2Points = 5;
        } else {
            player1Points = player2Points = 3;
        }
    }

    private void notDeucePlayer2Logic() {
        if (player2Points != 3) {
            player2Points++;
        } else {
            player2Points = 5;
        }
    }

    private void checkDeuce() {
        if ((player1Points == 3) && (player2Points == 3)) {
            isDeuce = true;
        }
    }
}
