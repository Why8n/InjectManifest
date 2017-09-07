package com.yn.annotations.enums;

/**
 * Created by Whyn on 2017/9/7.
 */

public enum UiOptions {
    NULL(""),
    NONE("none"),
    SPLIT_ACTIONBAR_WHEN_NARROW("splitActionBarWhenNarrow");

    private String uiOptions;

    UiOptions(String uiOptions) {
        this.uiOptions = uiOptions;
    }

    public String getUiOptions() {
        return uiOptions;
    }
}