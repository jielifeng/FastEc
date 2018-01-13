package com.example.hasee.festec.exaple.event;

import android.webkit.WebView;
import android.widget.Toast;

import com.example.hasee.festec.exaple.delegate.web.event.Event;

/**
 * Created by asee on 2017-12-09.
 */

public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(),getAction(),Toast.LENGTH_LONG).show();
        if (getAction().equals("test")){
            final WebView webView = getWebView();
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.evaluateJavascript("nativeCall();",null);
                }
            });
        }
        return null;
    }
}
