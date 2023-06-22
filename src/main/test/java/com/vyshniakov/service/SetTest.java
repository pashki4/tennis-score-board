package com.vyshniakov.service;

import com.vyshniakov.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetTest {
    private final Player player1 = new Player("player1");
    private final Player player2 = new Player("player2");
    private final Match match = new Match(player1, player2);

    private Set set;
    private Game game;

    @BeforeEach
    void setup() {
        this.set = new Set(match);
        this.game = new Game(set);
    }

    @Test
    @DisplayName("player1 add one set point")
    void player1Win() {
        assertEquals(0, set.getPlayer1Score());
        addGamePointsToPlayer(true, 4);
        assertEquals(1, set.getPlayer1Score());
    }

    @Test
    @DisplayName("player2 add one set point")
    void player2Win() {
        assertEquals(0, set.getPlayer2Score());
        addGamePointsToPlayer(false, 4);
        assertEquals(1, set.getPlayer2Score());
    }

    @Test
    @DisplayName("player1 win without tiebreak")
    void player1WinWithoutTiebreak() {
        assertNull(set.getWinner());
        addSetPointsToPlayer(true, 6);
        assertEquals(player1, set.getWinner());
    }

    @Test
    @DisplayName("player2 win without tiebreak")
    void player2WinWithoutTiebreak() {
        assertNull(set.getWinner());
        addSetPointsToPlayer(false, 6);
        assertEquals(player2, set.getWinner());
    }

    @Test
    @DisplayName("player1 win after 5-5 score")
    void fiveFive() {
        assertNull(set.getWinner());
        addSetPointsToPlayer(true, 5);
        addSetPointsToPlayer(false, 5);
        assertNull(set.getWinner());
        addSetPointsToPlayer(true, 1);
        assertNull(set.getWinner());
        addSetPointsToPlayer(true, 1);
        assertEquals(player1, set.getWinner());
    }

    @Test
    @DisplayName("tiebreak mode on")
    void tiebreakMode() {
        assertFalse(set.isTiebreak());
        addSetPointsToPlayer(true, 5);
        addSetPointsToPlayer(false, 5);
        assertFalse(set.isTiebreak());
        addSetPointsToPlayer(true, 1);
        assertFalse(set.isTiebreak());
        addSetPointsToPlayer(false, 1);
        assertTrue(set.isTiebreak());
    }

    @Test
    @DisplayName("player2 win after tiebreak")
    void player2WinAfterTiebreak() {
        assertNull(set.getWinner());
        addSetPointsToPlayer(true, 5);
        addSetPointsToPlayer(false, 5);
        addSetPointsToPlayer(true, 1);
        addSetPointsToPlayer(false, 1);
        addSetPointsToPlayer(false, 1);
        assertEquals(player2, set.getWinner());
    }
    @Test
    @DisplayName("player1 win after tiebreak")
    void player1WinAfterTiebreak() {
        assertNull(set.getWinner());
        addSetPointsToPlayer(true, 5);
        addSetPointsToPlayer(false, 5);
        addSetPointsToPlayer(true, 1);
        addSetPointsToPlayer(false, 1);
        addSetPointsToPlayer(true, 1);
        assertEquals(player1, set.getWinner());
    }


    private void addGamePointsToPlayer(boolean firstPlayer, int gamePointsCount) {
        for (int i = 0; i < gamePointsCount; i++) {
            if (firstPlayer) {
                set.addPlayer1GamePoint();
            } else {
                set.addPlayer2GamePoint();
            }
        }
    }

    private void addSetPointsToPlayer(boolean firstPlayer, int setPointsCount) {
        for (int i = 0; i < setPointsCount; i++) {
            addGamePointsToPlayer(firstPlayer, 4);
        }
    }
}