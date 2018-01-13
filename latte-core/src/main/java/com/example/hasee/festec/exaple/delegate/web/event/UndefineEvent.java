package com.example.hasee.festec.exaple.delegate.web.event;

import android.util.Log;

/**
 * Created by hasee on 2017-12-09.
 */

public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        Log.e("UndefineEvent",params);
        return null;
    }
}
