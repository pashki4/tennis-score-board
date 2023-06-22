package com.vyshniakov.util;

import com.vyshniakov.service.Game;
import com.vyshniakov.service.Match;
import com.vyshniakov.service.Set;

public class Utils {

    private Utils() {
        throw new IllegalArgumentException("Util class");
    }

    public static Game createNewGame(Set set) {
        return new Game(set);
    }

    public static Set createNewSet(Match match) {
        return new Set(match);
    }

}
