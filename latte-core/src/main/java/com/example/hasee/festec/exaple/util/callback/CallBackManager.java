package com.example.hasee.festec.exaple.util.callback;

import java.util.WeakHashMap;

/**
 * Created by hasee on 2018-01-23.
 */

public class CallBackManager {

    private static final WeakHashMap<Object,IGlobalCallBack> CALLBACKS = new WeakHashMap<>();

    private static class Holder{
        private static final CallBackManager INSTANCE = new CallBackManager();
    }

    public static CallBackManager getInstance(){
        return Holder.INSTANCE;
    }

    public CallBackManager addcallBack(Object tag,IGlobalCallBack callBack){
        CALLBACKS.put(tag,callBack);
        return this;
    }

    public IGlobalCallBack getCallBack(Object tag){
        return CALLBACKS.get(tag);
    }
}
