package com.example.hasee.festec.exaple.delegate.bottom;

/**
 * Created by hasee on 2017-10-30.
 *存储底部按钮的数据
 */

public final class BottomBean {
    private final CharSequence ICON;
    private final CharSequence TITLE;

    public BottomBean(CharSequence icon,CharSequence title){
        this.ICON = icon;
        this.TITLE = title;
    }

    public CharSequence getICON() {
        return ICON;
    }

    public CharSequence getTITLE() {
        return TITLE;
    }
}
