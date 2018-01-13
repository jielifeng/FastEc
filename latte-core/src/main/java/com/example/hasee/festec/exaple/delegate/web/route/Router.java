package com.example.hasee.festec.exaple.delegate.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.example.hasee.festec.exaple.delegate.LatteDelegate;
import com.example.hasee.festec.exaple.delegate.web.WebDelegate;
import com.example.hasee.festec.exaple.delegate.web.WebDelegateImpl;

/**
 * Created by hasee on 2017-12-06.
 * 控制网页的跳转，需要判断要加载的页面是本地页面还是网络页面
 */

public class Router {

    private Router() {

    }

    private static final class Holder {
        private static final Router INSTANCE = new Router();
    }

    public static Router getInstance() {
        return Holder.INSTANCE;
    }

    //处理url的方法
    public final boolean handleWebUrl(WebDelegate delegate, String url) {

        //如果是电话协议
        if (url.contains("tel:")) {
            callPhone(delegate.getContext(), url);
            return true;
        }

        final LatteDelegate topDelegate = delegate.getTopDelegate();
        final WebDelegateImpl webDelegate = WebDelegateImpl.create(url);
        topDelegate.getSupportDelegate().start(webDelegate);

        return true;
    }

    //为webView加载url
    private void loadWebPage(WebView webView, String url){
        if (webView!=null){
            webView.loadUrl(url);
        }else {
            throw new NullPointerException("webView is null!");
        }
    }

    //webView加载本地页面
    private void loadLocalPage(WebView webView, String url){
        loadWebPage(webView,"file:///android_asset/" + url);
    }

    //判断webView是应该加载本地页面还是网络页面
    private void loadPage(WebView webView, String url){
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)){
            loadWebPage(webView,url);
        }else {
            loadLocalPage(webView,url);
        }
    }

    //对外的入口
    public final  void laodPage(WebDelegate delegate, String url){
        loadPage(delegate.getWebView(),url);
    }

    private void callPhone(Context context, String uri) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
        ContextCompat.startActivity(context, intent, null);
    }
}
