package com.vyshniakov.tennis;

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
    private boolean isTiebreak = false;
    private OngoingMatch ongoingMatch;
    private Game currentGame;

    @Setter(AccessLevel.NONE)
    private List<Game> games = new ArrayList<>();

    public Set(OngoingMatch ongoingMatch) {
        this.player1 = ongoingMatch.getPlayer1();
        this.player2 = ongoingMatch.getPlayer2();
        this.ongoingMatch = ongoingMatch;
        this.currentGame = Utils.createNewGame(this);
    }

    public void addPlayer1GamePoint() {
        if (winner == null) {
            ifCurrentGameGotWinnerCreateNew();
            currentGame.addPlayer1Point();
            if (currentGame.getWinner() != null) {
                player1Score++;
                games.add(currentGame);
            }
            checkSetWinCondition();
        }
    }

    public void addPlayer2GamePoint() {
        if (winner == null) {
            ifCurrentGameGotWinnerCreateNew();
            currentGame.addPlayer2Point();
            if (currentGame.getWinner() != null) {
                player2Score++;
                games.add(currentGame);
            }
            checkSetWinCondition();
        }
    }

    public int player1WinGamesCount() {
        return (int) games.stream()
                .filter(game -> game.getWinner().equals(player1))
                .count();
    }

    public int player2WinGamesCount() {
        return (int) games.stream()
                .filter(game -> game.getWinner().equals(player2))
                .count();
    }

    private void ifCurrentGameGotWinnerCreateNew() {
        if (currentGame.getWinner() != null && !isTiebreak) {
            currentGame = Utils.createNewGame(this);
        } else if (currentGame.getWinner() != null) {
            currentGame = Utils.createTiebreakGame(this);
        }
    }

    private void checkSetWinCondition() {
        checkTiebreak();
        checkSetWinner();
    }

    private void checkTiebreak() {
        if ((player1Score == player2Score) && (player1Score == 6)) {
            isTiebreak = true;
        }
    }

    private void checkSetWinner() {
        if (!isTiebreak) {
            if ((player1Score == 6 && player2Score <= 4) || (player1Score == 7 && player2Score == 5)) {
                winner = player1;
            } else if ((player2Score == 6 && player1Score <= 4) || (player2Score == 7 && player1Score == 5)) {
                winner = player2;
            }
        } else {
            if (player1Score == 7) {
                winner = player1;
            } else if (player2Score == 7) {
                winner = player2;
            }
        }
    }
}
