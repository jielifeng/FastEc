package com.example.hasee.festec.exaple.net;

import android.content.Context;

import com.example.hasee.festec.exaple.net.calback.IError;
import com.example.hasee.festec.exaple.net.calback.IFailure;
import com.example.hasee.festec.exaple.net.calback.IRequest;
import com.example.hasee.festec.exaple.net.calback.ISuccess;
import com.example.hasee.festec.exaple.net.calback.RequestCalBacks;
import com.example.hasee.festec.exaple.net.download.DownloadHandler;
import com.example.hasee.festec.exaple.ui.loader.LatteLoader;
import com.example.hasee.festec.exaple.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by hasee on 2017-08-14.
 * 这个类获取到网络请求的相关参数之后，就会去请求数据
 */

public class RestClient {

    private final String URL;
    private final Map<String,Object> PARAMS;
    private final RequestBody BODY;
    private final File FILE;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    //回调接口
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEX;


    public RestClient(String URL,
                      Map<String, Object> params,
                      String download_dir,
                      String extension,
                      String name,
                      IRequest request,
                      ISuccess success,
                      IError error,
                      IFailure failure,
                      RequestBody body,
                      File file,
                      LoaderStyle loaderStyle,
                      Context context) {
        this.URL = URL;
        this.PARAMS = params;
        this.DOWNLOAD_DIR = download_dir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
        this.BODY = body;
        this.FILE = file;
        this.CONTEX = context;
        this.LOADER_STYLE = loaderStyle;

    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }

    private void request(HttpMethod method){
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        //回调方法
        if (REQUEST != null)
        {
            REQUEST.onRequestStart();
        }

        //显示加载视图
        if (LOADER_STYLE != null){
            LatteLoader.showLoading(CONTEX,LOADER_STYLE);
        }

        switch (method){
            case GET:
                call = service.get(URL,PARAMS);
                break;
            case POST:
                call = service.post(URL,PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL,BODY);
                break;
            case PUT:
                call = service.put(URL,PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL,BODY);
                break;
            case DELETE:
                call = service.delete(URL,PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file",FILE.getName(),requestBody);
                call = RestCreator.getRestService().upload(URL,body);
                break;
            default:
                break;
        }

        if (call != null){
            //正式发送请求
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback(){
        return new RequestCalBacks(
                REQUEST,
                SUCCESS,
                ERROR,
                FAILURE,
                LOADER_STYLE
        );
    }

    public final void get(){
        request(HttpMethod.GET);
    }

    public final void put(){
        if (BODY == null){
            request(HttpMethod.PUT);
        }else {
            if (!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.PUT_RAW);
        }

    }

    public final void post(){
        if (BODY == null){
            request(HttpMethod.POST);
        }else {
            if (!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void delete(){
        request(HttpMethod.DELETE);
    }

    public final void upload(){
        request(HttpMethod.UPLOAD);
    }

    public final void dwonload(){
        new DownloadHandler(URL,
                            PARAMS,
                            DOWNLOAD_DIR,
                            EXTENSION,
                            NAME,
                            REQUEST,
                            SUCCESS,
                            ERROR,
                            FAILURE)
                .handlerDwonload();
    }

}
