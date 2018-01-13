package com.example.hasee.festec.exaple.net.calback;

import android.os.Handler;

import com.example.hasee.festec.exaple.ui.loader.LatteLoader;
import com.example.hasee.festec.exaple.ui.loader.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hasee on 2017-08-17.
 */

public class RequestCalBacks implements Callback<String> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final LoaderStyle LOADER_STYLE;
    private static final Handler HANDLER = new Handler();

    public RequestCalBacks(IRequest request, ISuccess success, IError error, IFailure failure,LoaderStyle style) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
        this.LOADER_STYLE = style;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()){
            if (call.isExecuted()){
                if (SUCCESS != null){
                    SUCCESS.OnSuccess(response.body());
                }
            }
        }else {
            if (ERROR != null){
                ERROR.OnError(response.code(),response.message());
            }
        }

        if (LOADER_STYLE != null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            },1000);

        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null){
            FAILURE.OnFailure();
        }

        if (REQUEST != null){
            REQUEST.onRequestEnd();
        }
    }
}
