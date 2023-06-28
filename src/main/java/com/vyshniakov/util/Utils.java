package com.vyshniakov.util;

import com.vyshniakov.tennis.Game;
import com.vyshniakov.tennis.OngoingMatch;
import com.vyshniakov.tennis.Set;

public class Utils {

    private Utils() {
        throw new IllegalArgumentException("Util class");
    }

    public static Game createNewGame(Set set) {
        return new Game(set);
    }

    public static Set createNewSet(OngoingMatch ongoingMatch) {
        return new Set(ongoingMatch);
    }
}
