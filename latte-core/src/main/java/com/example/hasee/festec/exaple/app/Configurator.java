package com.example.hasee.festec.exaple.app;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.Utils;
import com.example.hasee.festec.exaple.delegate.web.event.Event;
import com.example.hasee.festec.exaple.delegate.web.event.EventManager;
import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by hasee on 2017-07-31.
 */

public class Configurator {

    private static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final Handler HANDLER = new Handler();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private Configurator(){
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
        LATTE_CONFIGS.put(ConfigType.HANDLER,HANDLER);
    }

    //对外暴露Configurator对象
    public static Configurator getInstance()
    {
        return Holder.INSTANCE;
    }

    final HashMap<Object,Object> getLatteConfigs()
    {
        return LATTE_CONFIGS;
    }

    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    //执行初始化
    public final void configure(){
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY,true);
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
        Utils.init((Application) Latte.getApplicationContext());
    }

    public final Configurator withApiHost(String host)
    {
        LATTE_CONFIGS.put(ConfigType.API_HOST,host);
        return this;
    }

    private void checkConfiguration()
    {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("");
        }
    }

    public final Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigType.INTERCEPTORS,INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors){
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(Configurator.INTERCEPTORS,INTERCEPTORS);
        return this;
    }

    public final Configurator withWeChatAppId(String appId){
        LATTE_CONFIGS.put(ConfigType.WE_CHAT_APP_ID,appId);
        return this;
    }

    public final Configurator withWeChatAppSecret(String secret){
        LATTE_CONFIGS.put(ConfigType.WE_CHAT_APP_SECRET,secret);
        return this;
    }

    public final Configurator withaActivity(Activity activity){
        LATTE_CONFIGS.put(ConfigType.ACTIVITY,activity);
        return this;
    }

    public final Configurator withEvent(@NonNull String name, @NonNull Event event){
        EventManager manager = EventManager.getInstance();
        manager.addEvent(name,event);
        return this;
    }

    public final Configurator WithJavascriptInterface(@NonNull String name){
        LATTE_CONFIGS.put(ConfigType.JAVASCRIPT_INTERFACE,name);
        return this;
    }

    public final Configurator WithWebHost(@NonNull String host){
        LATTE_CONFIGS.put(ConfigType.WEB_HOST,host);
        return this;
    }

    final <T>T getConfiguration(Object key){
        checkConfiguration();
        final Object value = LATTE_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) LATTE_CONFIGS.get(key);
    }

    //初始化图标库
    private void initIcons()
    {
        if (ICONS.size()>0)
        {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i<ICONS.size(); i++)
            {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor)
    {
        ICONS.add(descriptor);
        return this;
    }
}
