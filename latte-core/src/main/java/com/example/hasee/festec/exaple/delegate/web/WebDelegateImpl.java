package com.example.hasee.festec.exaple.delegate.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.hasee.festec.exaple.delegate.IPageLoadListener;
import com.example.hasee.festec.exaple.delegate.web.chromeclient.WebChromeClientImpl;
import com.example.hasee.festec.exaple.delegate.web.client.WebViewClientImpl;
import com.example.hasee.festec.exaple.delegate.web.route.RouteKeys;
import com.example.hasee.festec.exaple.delegate.web.route.Router;

/**
 * Created by hasee on 2017-12-05.
 *WebDelegate的实现类
 */

public class WebDelegateImpl extends WebDelegate {

    private IPageLoadListener mIPageLoadListener = null;

    public static WebDelegateImpl create(String url){
        //设置url
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }

    public void setIPageLoadListener(IPageLoadListener iPageLoadListener){
        this.mIPageLoadListener = iPageLoadListener;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceSate, View rootView) {
        if (getUrl() != null){
            //用原生的方式模拟Web跳转并进行加载页面
            Router.getInstance().laodPage(this,getUrl());
        }
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        WebViewClientImpl client = new WebViewClientImpl(this);
        client.setPageLoadListener(mIPageLoadListener);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }
}
