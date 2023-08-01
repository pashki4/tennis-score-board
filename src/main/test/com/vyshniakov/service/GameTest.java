package com.vyshniakov.service;

import com.vyshniakov.model.Player;
import com.vyshniakov.tennis.Game;
import com.vyshniakov.tennis.OngoingMatch;
import com.vyshniakov.tennis.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.vyshniakov.tennis.GamePoints.*;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private final Player player1 = new Player("player1");
    private final Player player2 = new Player("player2");
    private final OngoingMatch ongoingMatch = new OngoingMatch(player1, player2, true);
    private Game game;

    @BeforeEach
    void setup() {
        Set set = new Set(ongoingMatch);
        game = new Game(set);
    }

    @Test
    @DisplayName("add point to player1")
    void addPlayer1Point() {
        assertEquals(0, game.getPlayer1Points());
        game.addPlayer1Point();
        assertEquals(1, game.getPlayer1Points());
    }

    @Test
    @DisplayName("add point to player2")
    void addPlayer2Point() {
        assertEquals(0, game.getPlayer2Points());
        game.addPlayer2Point();
        assertEquals(1, game.getPlayer2Points());
    }

    @Test
    @DisplayName("player1 should win without deuce mode")
    void player1WinWithoutDeuceMode() {
        assertNull(game.getWinner());
        addGamePointsToPlayer(true, 4);
        assertEquals(player1, game.getWinner());
    }

    @Test
    @DisplayName("player2 should win without deuce mode")
    void player2WinWithoutDeuceMode() {
        assertNull(game.getWinner());
        addGamePointsToPlayer(false, 4);
        assertEquals(player2, game.getWinner());
    }

    @Test
    @DisplayName("Deuce mode activated")
    void deuceModeActivated() {
        assertFalse(game.isDeuce());
        deuceModeOn();
        assertTrue(game.isDeuce());
    }

    @Test
    @DisplayName("Deuce mode workflow")
    void deuceMode() {
        deuceModeOn();

        game.addPlayer1Point();
        assertEquals(4, game.getPlayer1Points());
        game.addPlayer2Point();
        assertEquals(3, game.getPlayer1Points());
        assertEquals(3, game.getPlayer2Points());

        game.addPlayer2Point();
        assertEquals(4, game.getPlayer2Points());
        game.addPlayer1Point();
        assertEquals(3, game.getPlayer1Points());
        assertEquals(3, game.getPlayer2Points());
    }

    @Test
    @DisplayName("player1 win after deuce")
    void player1WinAfterDeuce() {
        deuceModeOn();
        game.addPlayer1Point();
        assertEquals(4, game.getPlayer1Points());
        game.addPlayer1Point();
        assertEquals(5, game.getPlayer1Points());
        assertEquals(player1, game.getWinner());
    }
    @Test
    @DisplayName("player2 win after deuce")
    void player2WinAfterDeuce() {
        deuceModeOn();
        game.addPlayer2Point();
        assertEquals(4, game.getPlayer2Points());
        game.addPlayer2Point();
        assertEquals(5, game.getPlayer2Points());
        assertEquals(player2, game.getWinner());
    }

    private void deuceModeOn() {
        addGamePointsToPlayer(true, 3);
        addGamePointsToPlayer(false, 3);
    }

    private void addGamePointsToPlayer(boolean firstPlayer, int playerPointsCount) {
        for (int i = 0; i < playerPointsCount; i++) {
            if (firstPlayer) {
                game.addPlayer1Point();
            } else {
                game.addPlayer2Point();
            }
        }
    }
}