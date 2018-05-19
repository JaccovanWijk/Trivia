package com.example.jacco.trivia;

import android.support.annotation.NonNull;

/**
 * Created by Jacco on 19-5-2018.
 */

public class Highscore implements Comparable {
    private String name;
    private long score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        long compareScore = ((Highscore)o).getScore();

        if(compareScore > score) {
            return 1;
        }
        else if(compareScore < score) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
