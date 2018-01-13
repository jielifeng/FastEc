package com.example.hasee.festec.exaple.net.rx;

import android.content.Context;

import com.example.hasee.festec.exaple.net.HttpMethod;
import com.example.hasee.festec.exaple.net.RestCreator;
import com.example.hasee.festec.exaple.ui.loader.LatteLoader;
import com.example.hasee.festec.exaple.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by hasee on 2017-09-12.
 */

public class RxRestClient {

    private final String URL;
    private final Map<String,Object> PARAMS;
    private final RequestBody BODY;
    private final File FILE;
    //回调接口
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEX;


    public RxRestClient(String URL,
                      Map<String, Object> params,
                      RequestBody body,
                      File file,
                      LoaderStyle loaderStyle,
                      Context context) {
        this.URL = URL;
        this.PARAMS = params;
        this.BODY = body;
        this.FILE = file;
        this.CONTEX = context;
        this.LOADER_STYLE = loaderStyle;

    }

    public static RxRestClientBuilder builder(){
        return new RxRestClientBuilder();
    }

    private Observable<String> request(HttpMethod method){
        final RxRestService service = RestCreator.getRxRestService();
        Observable<String> observable = null;


        //显示加载视图
        if (LOADER_STYLE != null){
            LatteLoader.showLoading(CONTEX,LOADER_STYLE);
        }

        switch (method){
            case GET:
                observable = service.get(URL,PARAMS);
                break;
            case POST:
                observable = service.post(URL,PARAMS);
                break;
            case POST_RAW:
                observable = service.postRaw(URL,BODY);
                break;
            case PUT:
                observable = service.put(URL,PARAMS);
                break;
            case PUT_RAW:
                observable = service.putRaw(URL,BODY);
                break;
            case DELETE:
                observable = service.delete(URL,PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file",FILE.getName(),requestBody);
                observable = RestCreator.getRxRestService().upload(URL,body);
                break;
            default:
                break;
        }

        return observable;
    }


    public final Observable<String> get(){
       return request(HttpMethod.GET);
    }

    public final Observable<String> put(){
        if (BODY == null){
            return request(HttpMethod.PUT);
        }else {
            if (!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null");
            }
            return request(HttpMethod.PUT_RAW);
        }

    }

    public final Observable<String> post(){
        if (BODY == null){
            return request(HttpMethod.POST);
        }else {
            if (!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null");
            }
            return request(HttpMethod.POST_RAW);
        }
    }

    public final Observable<String> delete(){
        return request(HttpMethod.DELETE);
    }

    public final Observable<String> upload(){
        return request(HttpMethod.UPLOAD);
    }

    public final Observable<ResponseBody> dwonload(){
        final Observable<ResponseBody> responseBodyObservable = RestCreator.getRxRestService().download(URL,PARAMS);
        return responseBodyObservable;
    }
}
