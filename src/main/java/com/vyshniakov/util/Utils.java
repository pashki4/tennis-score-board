package com.vyshniakov.util;

import com.vyshniakov.dao.PlayerDao;
import com.vyshniakov.model.OngoingMatch;
import com.vyshniakov.model.Player;
import com.vyshniakov.service.Game;
import com.vyshniakov.service.Match;
import com.vyshniakov.service.Set;

import java.util.List;
import java.util.Optional;

public class Utils {

    private Utils() {
        throw new IllegalArgumentException("Util class");
    }

    public static OngoingMatch createMatch(List<String> players) {
        List<Player> list = fetchPlayers(players);
        OngoingMatch ongoingMatch = new OngoingMatch();
        ongoingMatch.setFirstPlayer(list.get(0));
        ongoingMatch.setSecondPlayer(list.get(1));
        return ongoingMatch;
    }

    private static List<Player> fetchPlayers(List<String> players) {
        PlayerDao playerDao = new PlayerDao();
        return players.stream()
                .map(playerDao::findPlayerByName)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    public static Game createNewGame(Set set) {
        return new Game(set);
    }

    public static Set createNewSet(Match match) {
        return new Set(match);
    }

}
