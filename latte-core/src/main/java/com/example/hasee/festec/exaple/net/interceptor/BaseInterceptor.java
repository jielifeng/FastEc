package com.example.hasee.festec.exaple.net.interceptor;

import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * Created by hasee on 2017-09-10.
 * 拦截器负责拦截请求，通过请求的类型来返回相应的数据
 */

public abstract class BaseInterceptor implements Interceptor {


    //获取get请求的参数
    protected LinkedHashMap<String,String> getUrlParameters(Chain chain){
       final HttpUrl url = chain.request().url();
        int size = url.querySize();
        final LinkedHashMap<String,String> params = new LinkedHashMap<>();
        for (int i = 0; i<size; i++){
            params.put(url.queryParameterName(i),url.queryParameterValue(i));
        }
        return params;
    }

    protected String getUrlParameters(Chain chain,String key){
        final Request request = chain.request();
        return request.url().queryParameter(key);
    }

    protected LinkedHashMap<String,String> getBodyParameter(Chain chain){
        final FormBody formBody = (FormBody) chain.request().body();
        final LinkedHashMap<String,String> params = new LinkedHashMap<>();
        int size = formBody.size();
        for (int i=0; i<size; i++){
            params.put(formBody.name(i),formBody.value(i));
        }
        return params;
    }

    protected String getBodyParameter(Chain chain, String key){
        return getBodyParameter(chain).get(key);
    }
}
