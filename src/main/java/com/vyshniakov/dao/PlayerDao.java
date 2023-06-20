package com.vyshniakov.dao;

import com.vyshniakov.model.Player;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PlayerDao {
    private DataSource dataSource;
    private Map<Integer, Player> players = new HashMap<>();

    public PlayerDao() {
        init();
    }

    private void init() {
        players.put(1, new Player("player1"));
        players.put(2, new Player("player2"));
        players.put(3, new Player("player3"));
    }

    public Optional<Player> findPlayerByName(String name) {
        return players.values().stream()
                .filter(player -> player.getName().equals(name))
                .findAny();
    }

    public void addPlayer(Player player) {
        players.put(player.getId(), player);
    }
}
