package com.example.hasee.festec.exaple;

import android.app.Application;

import com.example.hasee.festec.exaple.event.TestEvent;
import com.example.hasee.festec.exaple.net.rx.AddCookieInterceptor;
import com.example.latte_ec2.deltest.database.DatabaseManager;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import com.example.hasee.festec.exaple.app.Latte;

/**
 * Created by hasee on 2017-07-31.
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withApiHost("http://192.168.1.110/RestServer/api/")
                .withEvent("test",new TestEvent())
                .WithJavascriptInterface("latte")
                //添加Cookie同步拦截器
                .WithWebHost("https://www.baidu.com/")
                .withInterceptor(new AddCookieInterceptor())
                .configure();
        initStetho();
        DatabaseManager.getInstance().init(this);
    }

    private void initStetho()
    {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}

