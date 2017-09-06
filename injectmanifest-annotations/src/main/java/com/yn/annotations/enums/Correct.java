package com.yn.annotations.enums;

/**
 * Created by Whyn on 2017/9/6.
 */

public enum Correct {
    NONE(""),
    TRUE("true"),
    FALSE("false");
    private String correct;

    Correct(String correct) {
        this.correct = correct;
    }

    public String getResult() {
        return correct;
    }
}
