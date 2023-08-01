package com.vyshniakov.tennis;

import com.vyshniakov.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TiebreakGameTest {

    private final Player player1 = new Player("player1");
    private final Player player2 = new Player("player2");
    private final OngoingMatch ongoingMatch = new OngoingMatch(player1, player2, true);
    private Game game;

    @BeforeEach
    void setup() {
        Set set = new Set(ongoingMatch);
        game = new TiebreakGame(set);
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
    @DisplayName("player1 win")
    void player1Win() {
        assertNull(game.getWinner());
        game.addPlayer1Point();
        game.addPlayer1Point();
        game.addPlayer1Point();
        game.addPlayer1Point();
        game.addPlayer1Point();
        game.addPlayer1Point();
        game.addPlayer1Point();
        assertEquals(player1, game.getWinner());
    }

    @Test
    @DisplayName("player2 win")
    void player2Win() {
        assertNull(game.getWinner());
        game.addPlayer2Point();
        game.addPlayer2Point();
        game.addPlayer2Point();
        game.addPlayer2Point();
        game.addPlayer2Point();
        game.addPlayer2Point();
        game.addPlayer2Point();
        assertEquals(player2, game.getWinner());
    }

    @Test
    @DisplayName("player1 win after 7-7 score")
    void player1WinAfter7Both() {
        assertNull(game.getWinner());
        game.addPlayer2Point();
        game.addPlayer2Point();
        game.addPlayer2Point();
        game.addPlayer2Point();
        game.addPlayer2Point();
        game.addPlayer2Point();

        game.addPlayer1Point();
        game.addPlayer1Point();
        game.addPlayer1Point();
        game.addPlayer1Point();
        game.addPlayer1Point();
        game.addPlayer1Point();
        game.addPlayer1Point();
        assertNull(game.getWinner());

        game.addPlayer1Point();
        assertEquals(player1, game.getWinner());
    }

    @Test
    @DisplayName("player2 win tiebreak workflow")
    void player2WinTiebreakWorkflow() {
        assertNull(game.getWinner());
        game.addPlayer2Point();
        game.addPlayer2Point();
        game.addPlayer2Point();
        game.addPlayer2Point();
        game.addPlayer2Point();
        game.addPlayer2Point();

        game.addPlayer1Point();
        game.addPlayer1Point();
        game.addPlayer1Point();
        game.addPlayer1Point();
        game.addPlayer1Point();
        game.addPlayer1Point();
        game.addPlayer1Point();
        assertNull(game.getWinner());

        //7 - 7
        game.addPlayer2Point();

        //8 - 7
        game.addPlayer1Point();
        assertNull(game.getWinner());

        //9 - 7
        game.addPlayer1Point();
        assertEquals(player1, game.getWinner());
    }

}