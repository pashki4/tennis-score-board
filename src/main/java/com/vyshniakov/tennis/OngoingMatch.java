package com.vyshniakov.tennis;

import com.vyshniakov.model.Player;
import com.vyshniakov.util.Utils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class OngoingMatch {
    private UUID uuid = UUID.randomUUID();
    private Player player1;
    private Player player2;
    private int player1Score;
    private int player2Score;
    private Player winner;
    private Set currentSet;
    private boolean isBestOfThree;

    @Setter(AccessLevel.NONE)
    @Getter
    private List<Set> sets = new ArrayList<>();

    public OngoingMatch(Player player1, Player player2, boolean isBestOfThree) {
        this.player1 = player1;
        this.player2 = player2;
        this.isBestOfThree = isBestOfThree;
        this.currentSet = Utils.createNewSet(this);
    }

    public void addPlayer1GamePoint() {
        if (winner == null) {
            ifCurrentSetGotWinnerCreateNew();
            currentSet.addPlayer1GamePoint();
            if (currentSet.getWinner() != null) {
                player1Score++;
                sets.add(currentSet);
                checkWinCondition();
            }
        }
    }

    public void addPlayer2GamePoint() {
        if (winner == null) {
            ifCurrentSetGotWinnerCreateNew();
            currentSet.addPlayer2GamePoint();
            if (currentSet.getWinner() != null) {
                player2Score++;
                sets.add(currentSet);
                checkWinCondition();
            }
        }
    }

    private void checkWinCondition() {
        if (isBestOfThree) {
            if (player1Score == 2) {
                winner = player1;
            } else if (player2Score == 2) {
                winner = player2;
            }
        } else {
            if (player1Score == 3) {
                winner = player1;
            } else if (player2Score == 3) {
                winner = player2;
            }
        }
    }

    private void ifCurrentSetGotWinnerCreateNew() {
        if (currentSet.getWinner() != null) {
            currentSet = Utils.createNewSet(this);
        }
    }

    public boolean isEnded() {
        return winner != null;
    }
}
