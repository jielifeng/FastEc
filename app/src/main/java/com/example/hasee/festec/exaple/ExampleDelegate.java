package com.example.hasee.festec.exaple;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.hasee.festec.exaple.delegate.LatteDelegate;
import com.example.hasee.festec.exaple.net.RestClient;
import com.example.hasee.festec.exaple.net.RestCreator;
import com.example.hasee.festec.exaple.net.calback.IError;
import com.example.hasee.festec.exaple.net.calback.IFailure;
import com.example.hasee.festec.exaple.net.calback.ISuccess;

import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hasee on 2017-08-07.
 */

public class ExampleDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceSate, View rootView) {
        Log.i("test", "onBinderView: ");
        testRestClient();
        //test();
    }

    //TODO:测试方法，用完就删
    private void testRestClient()
    {
        RestClient.builder()
                .url("http://10.12.163.156/data/goods_detail_data_1.json")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void OnSuccess(String respones) {
                        Toast.makeText(getContext(), respones, Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void OnError(int code, String smg) {
                        Toast.makeText(getContext(), "OnError", Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void OnFailure() {
                        Toast.makeText(getContext(), "OnFailure", Toast.LENGTH_LONG).show();
                    }
                })
                .build()
                .get();
    }

    void test(){
        final String url = "RestServer/api";
        final WeakHashMap<String,Object> pamas = new WeakHashMap<>();

        final Observable<String> observable = RestCreator.getRxRestService().get(url,pamas);
        if (observable!= null){
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull String s) {
                            Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

    }


}
