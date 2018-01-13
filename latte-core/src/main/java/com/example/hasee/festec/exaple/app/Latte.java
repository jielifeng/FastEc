package com.example.hasee.festec.exaple.app;

import android.content.Context;
import android.os.Handler;

/**
 * Created by hasee on 2017-07-31.
 *通过Latte来对Configurator对象进行操作
 */

public final class Latte {
    public static Configurator init(Context context)
    {
        Configurator.getInstance()
                .getLatteConfigs()
                .put(ConfigType.APPLICATION_CONTEXT,context.getApplicationContext());

        return Configurator.getInstance();
    }

    //获取Configurator对象
    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    //获取LATTE_CONFIGS中的值
    public static <T>T getConfigurations(Object key){
        return getConfigurator().getConfiguration(key);
    }

    public static Handler getHandler(){
        return getConfigurations(ConfigType.HANDLER);
    }

    //获取ApplicationContext对象
    public static Context getApplicationContext(){
        return getConfigurations(ConfigType.APPLICATION_CONTEXT);
    }
}
