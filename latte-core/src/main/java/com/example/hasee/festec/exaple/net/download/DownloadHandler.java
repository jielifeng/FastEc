package com.example.hasee.festec.exaple.net.download;

import android.content.Intent;
import android.os.AsyncTask;

import com.example.hasee.festec.exaple.net.RestCreator;
import com.example.hasee.festec.exaple.net.calback.IError;
import com.example.hasee.festec.exaple.net.calback.IFailure;
import com.example.hasee.festec.exaple.net.calback.IRequest;
import com.example.hasee.festec.exaple.net.calback.ISuccess;
import com.example.hasee.festec.exaple.util.file.FileUtil;

import java.io.File;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hasee on 2017-08-30.
 */

public class DownloadHandler {

    private final String URL;
    private final Map<String,Object> PARAMS;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;

    public DownloadHandler(String url,
                           Map<String, Object> params,
                           String download_dir,
                           String extension,
                           String name,
                           IRequest request,
                           ISuccess success,
                           IError error,
                           IFailure failure) {
        this.URL = url;
        this.PARAMS = params;
        this.DOWNLOAD_DIR = download_dir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
    }

    public final void handlerDwonload(){
        if (REQUEST != null){
            REQUEST.onRequestStart();
        }

        RestCreator.getRestService().download(URL,PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        if (response.isSuccessful()){
                            final ResponseBody responseBody = response.body();

                            final SaveFileTask task = new SaveFileTask(REQUEST,SUCCESS);
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,DOWNLOAD_DIR,EXTENSION,responseBody,NAME);

                            //注意是否下载完成
                            if (task.isCancelled()){
                                if (REQUEST != null){
                                    REQUEST.onRequestEnd();
                                }
                            }
                        }else {
                            if (ERROR != null){
                                ERROR.OnError(response.code(),response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                            if (FAILURE != null){
                                FAILURE.OnFailure();
                            }
                    }
                });
    }

    private void autoInstallApk(File file){
        if (FileUtil.getExtension(file.getPath()).equals("apk")){
            final Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);

        }
    }
}
