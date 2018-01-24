package com.example.hasee.festec.exaple.delegate.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.hasee.festec.exaple.app.ConfigType;
import com.example.hasee.festec.exaple.app.Latte;
import com.example.hasee.festec.exaple.delegate.IPageLoadListener;
import com.example.hasee.festec.exaple.delegate.web.WebDelegate;
import com.example.hasee.festec.exaple.delegate.web.route.Router;
import com.example.hasee.festec.exaple.ui.loader.LatteLoader;
import com.example.hasee.festec.exaple.util.storage.LattePreference;

/**
 * Created by hasee on 2017-12-05.
 * WebViewClient的实现类
 */

public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;
    private static final Handler HANDLER = Latte.getHandler();

    public void setPageLoadListener(IPageLoadListener iPageLoadListener) {
        this.mIPageLoadListener = iPageLoadListener;
    }

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    //点击超链接加载url前会调用这个函数，返回true代表不再加载这个url
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return Router.getInstance().handleWebUrl(DELEGATE, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }
        LatteLoader.showLoading(view.getContext());

    }

    //获取浏览器Cookie
    private void syncCookie() {
        final CookieManager manager = CookieManager.getInstance();
        /*
        * 这里的Cookie和API请求的Cookie是不一样的，这个在网页中不可见
        * */
        final String webHost = Latte.getConfigurations(ConfigType.WEB_HOST);
        if (webHost != null) {
            if (manager.hasCookies()) {
                final String cookieString = manager.getCookie(webHost);
                if (cookieString != null && !cookieString.equals("")) {
                    LattePreference.addCustomAppProfile("cookie", cookieString);
                }
            }
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        syncCookie();
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadEnd();
        }
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                LatteLoader.stopLoading();
            }
        }, 1000);

    }
}
