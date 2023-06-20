package com.vyshniakov.service;

import com.vyshniakov.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.vyshniakov.service.GamePoints.*;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private final Player player1 = new Player("player1");
    private final Player player2 = new Player("player2");
    private final Match match = new Match(player1, player2);
    private Game game;

    @BeforeEach
    void setUp() {
        Set set = new Set(match);
        game = new Game(set);
    }

    @Test
    @DisplayName("add point to player1")
    void addPlayer1Point() {
        assertEquals(LOVE, game.getPlayer1Points());
        game.addPlayer1Point();
        assertEquals(FIFTEEN, game.getPlayer1Points());
    }

    @Test
    @DisplayName("add point to player2")
    void addPlayer2Point() {
        assertEquals(LOVE, game.getPlayer2Points());
        game.addPlayer2Point();
        assertEquals(FIFTEEN, game.getPlayer2Points());
    }

    @Test
    @DisplayName("A game should have a winner")
    void play() {
        assertNull(game.getWinner());
        game.play();
        assertNotNull(game.getWinner());
    }

    @Test
    @DisplayName("player 1 should win without deuce mode")
    void player1WinWithoutDeuceMode() {
        assertNull(game.getWinner());
        addPointsToPlayer(true, 4);
        assertEquals("player1", game.getWinner().getName());
    }

    @Test
    @DisplayName("player 2 should win without deuce mode")
    void player2WinWithoutDeuceMode() {
        assertNull(game.getWinner());
        addPointsToPlayer(false, 4);
        assertEquals("player2", game.getWinner().getName());
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
        assertEquals(AD, game.getPlayer1Points());
        game.addPlayer2Point();
        assertEquals(FORTY, game.getPlayer1Points());
        assertEquals(FORTY, game.getPlayer2Points());

        game.addPlayer2Point();
        assertEquals(AD, game.getPlayer2Points());
        game.addPlayer1Point();
        assertEquals(FORTY, game.getPlayer1Points());
        assertEquals(FORTY, game.getPlayer2Points());
    }

    @Test
    @DisplayName("player 1 win after deuce")
    void player1WinAfterDeuce() {
        deuceModeOn();
        game.addPlayer1Point();
        assertEquals(AD, game.getPlayer1Points());
        game.addPlayer1Point();
        assertEquals(GAME, game.getPlayer1Points());
        assertEquals(player1, game.getWinner());
    }
    @Test
    @DisplayName("player 2 win after deuce")
    void player2WinAfterDeuce() {
        deuceModeOn();
        game.addPlayer2Point();
        assertEquals(AD, game.getPlayer2Points());
        game.addPlayer2Point();
        assertEquals(GAME, game.getPlayer2Points());
        assertEquals(player2, game.getWinner());
    }

    private void deuceModeOn() {
        addPointsToPlayer(true, 3);
        addPointsToPlayer(false, 3);
    }

    private void addPointsToPlayer(boolean firstPlayer, int playerPoints) {
        for (int i = 0; i < playerPoints; i++) {
            if (firstPlayer) {
                game.addPlayer1Point();
            } else {
                game.addPlayer2Point();
            }
        }
    }
}