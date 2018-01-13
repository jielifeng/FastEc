package com.example.hasee.festec.exaple.net;

import android.content.Context;

import com.example.hasee.festec.exaple.net.calback.IError;
import com.example.hasee.festec.exaple.net.calback.IFailure;
import com.example.hasee.festec.exaple.net.calback.IRequest;
import com.example.hasee.festec.exaple.net.calback.ISuccess;
import com.example.hasee.festec.exaple.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by hasee on 2017-08-17.
 * RestClient的建造者
 */

public class RestClientBuilder {

    private String mUrl;
    private final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    private IRequest mIRequest;
    private ISuccess mISuccess;
    private IError mIError;
    private IFailure mIFailure;
    private RequestBody mBody;
    private LoaderStyle mLoaderStyle;
    private Context mContext;
    private File mFile;
    private String mDownload_dir;
    private String mExtension;
    private String mName;

    RestClientBuilder(){

    }

    public final RestClientBuilder url(String url){
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder map(Map<String,Object> params){
        this.PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key,Object value){
        this.PARAMS.put(key,value);
        return this;
    }

    public final RestClientBuilder file(File file){
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String file){
        this.mFile = new File(file);
        return this;
    }
    public final RestClientBuilder download_dir(String download_dir){
        this.mDownload_dir = download_dir;
        return this;
    }

    public final RestClientBuilder extension(String extension){
        this.mExtension = extension;
        return this;
    }

    public final RestClientBuilder name(String name){
        this.mName = name;
        return this;
    }

    public final RestClientBuilder raw(String raw){
        this.mBody = RequestBody.create(MediaType.parse("applecation/json;charset=UTF-8"),raw);
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess){
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder error(IError iError){
        this.mIError= iError;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure){
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest){
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder loader(Context context,LoaderStyle loaderStyle){
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;
        return this;
    }

    public final RestClientBuilder loader(Context context){
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RestClient build()
    {
        return new RestClient(mUrl,PARAMS,mDownload_dir,mExtension,mName,mIRequest,mISuccess,mIError,mIFailure,mBody,mFile,mLoaderStyle,mContext);
    }


}
