package com.example.hasee.festec.exaple.net.rx;

import android.content.Context;
import com.example.hasee.festec.exaple.ui.loader.LoaderStyle;
import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;

/**
 * Created by hasee on 2017-09-12.
 */

public class RxRestClientBuilder {

    private String mUrl = null;
    private final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    private RequestBody mBody = null;
    private LoaderStyle mLoaderStyle = null;
    private Context mContext = null;
    private File mFile = null;

    RxRestClientBuilder(){

    }

    public final RxRestClientBuilder url(String url){
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder map(Map<String,Object> params){
        this.PARAMS.putAll(params);
        return this;
    }

    public final RxRestClientBuilder params(String key,Object value){
        this.PARAMS.put(key,value);
        return this;
    }

    public final RxRestClientBuilder file(File file){
        this.mFile = file;
        return this;
    }

    public final RxRestClientBuilder file(String file){
        this.mFile = new File(file);
        return this;
    }

    public final RxRestClientBuilder loader(Context context,LoaderStyle loaderStyle){
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;
        return this;
    }

    public final RxRestClientBuilder loader(Context context){
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RxRestClient build()
    {
        return new RxRestClient(mUrl,PARAMS,mBody,mFile,mLoaderStyle,mContext);
    }

}
