package com.example.hasee.festec.exaple.delegate.web.event;

import android.content.Context;
import android.webkit.WebView;

import com.example.hasee.festec.exaple.delegate.LatteDelegate;

/**
 * Created by hasee on 2017-12-09.
 * 在js调用原生的框架中，InterFace的evernt函数中会根据传进的action来判断具体由那个event对象来响应这次调用
 * 在js调用InterFace的evernt函数时一定要传入一个字符串说明你要调用的Event
 * 这些属性是方便子类编写代码的
 */

public abstract class Event implements IEvent {

    private Context mContext = null;
    private String mAction = null;
    private LatteDelegate mDelegate = null;
    private String mUrl = null;
    private WebView mWebView = null;


    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String mAction) {
        this.mAction = mAction;
    }

    public LatteDelegate getDelegate() {
        return mDelegate;
    }

    public void setDelegate(LatteDelegate mDelegate) {
        this.mDelegate = mDelegate;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public WebView getWebView() {
        return mWebView;
    }

    public void setWebView(WebView mWebView) {
        this.mWebView = mWebView;
    }
}
