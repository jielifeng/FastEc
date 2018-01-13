package com.example.hasee.festec.exaple.net.rx;

import com.example.hasee.festec.exaple.util.storage.LattePreference;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hasee on 2017-12-10.
 */

public class AddCookieInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        final Request.Builder builder = chain.request().newBuilder();
        Observable
                .just(LattePreference.getCustomAppProfile("cookie"))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String cookie) throws Exception {
                        //给原生API请求时带上WebView拦截下来的Cookie
                        builder.addHeader("Cookie",cookie);
                    }
                });

        return chain.proceed(builder.build());
    }
}
