package com.example.hasee.festec.exaple.delegate.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.example.hasee.festec.exaple.delegate.web.event.Event;
import com.example.hasee.festec.exaple.delegate.web.event.EventManager;

/**
 * Created by hasee on 2017-12-05.
 * 这个类是映射到js中使用的,这个类的实例函数都可以在js中直接调用
 */

final class LatteWebViewInterface {
    private final WebDelegate DELEGATE;

    private LatteWebViewInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static LatteWebViewInterface create(WebDelegate delegate) {
        return new LatteWebViewInterface(delegate);
    }

    @JavascriptInterface
    public String event(String params) {
        //action代表是哪个Event响应这个事件
        final String action = JSON.parseObject(params).getString("action");
        //取出对应的event对象，并设置重新设置相关的属性
        final Event event = EventManager.getInstance().createEvent(action);
        if (event != null) {
            event.setAction(action);
            event.setContext(DELEGATE.getContext());
            event.setDelegate(DELEGATE);
            event.setUrl(DELEGATE.getUrl());
            event.setWebView(DELEGATE.getWebView());
            return event.execute(params);
        }
        return null;
    }

}
