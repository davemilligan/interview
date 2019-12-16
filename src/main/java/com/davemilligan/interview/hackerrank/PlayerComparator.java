package com.davemilligan.interview.hackerrank;

import java.util.Comparator;

public class PlayerComparator implements Comparator<Player> {

    @Override
    public int compare(Player o1, Player o2) {
        return o1.score == o2.score ? o1.name.compareTo(o2.name) : (o1.score > o2.score)? -1 : 1;
    }
}

class Player {
    public final String name;
    public final int score;

    Player(String name, int score) {
        this.name = name;
        this.score = score;
    }
}
