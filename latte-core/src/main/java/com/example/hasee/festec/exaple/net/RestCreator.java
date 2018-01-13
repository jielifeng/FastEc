package com.example.hasee.festec.exaple.net;

import com.example.hasee.festec.exaple.net.rx.RxRestService;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.example.hasee.festec.exaple.app.ConfigType;
import com.example.hasee.festec.exaple.app.Latte;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
/**
 * Created by hasee on 2017-08-15.
 *单例对象
 */

public class RestCreator {


    private static final class OkHttpHolder{
        private static final int TIME_OUT = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient().newBuilder();
        private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

        private static OkHttpClient.Builder addInterceptor(){
            if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()){
                for (Interceptor interceptor : INTERCEPTORS){
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }

        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();

    }

    /**
     *构建全局的Retrofit客户端
     * */
    private static final class RetrofitHolder{
        private static final String BASE_URL = Latte.getConfigurations(ConfigType.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * service接口
     * */
    private static final class  RestServiceHolder{
        private static final RestService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    public static RestService getRestService()
    {
        return RestServiceHolder.REST_SERVICE;
    }


    /**
     * RxService接口
     * */
    private static final class  RxRestServiceHolder{
        private static final RxRestService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(RxRestService.class);
    }

    public static RxRestService getRxRestService()
    {
        return RxRestServiceHolder.REST_SERVICE;
    }
}
