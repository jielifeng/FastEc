package com.example.hasee.festec.exaple.delegate.web.event;

import android.support.annotation.NonNull;

import java.util.HashMap;

/**
 * Created by hasee on 2017-12-09.
 * 在ExampleApp中配置event对象
 * 具体的event对象是全局唯一的，这个类是管理存储event队列的用的
 */

public class EventManager {
    //存储event对象
    private static final HashMap<String,Event> EVENTS = new HashMap<>();

    private EventManager(){

    }

    private static class Holder{
        private static final EventManager INSTANCE = new EventManager();
    }

    public static EventManager getInstance(){
        return Holder.INSTANCE;
    }

    public EventManager addEvent(@NonNull String name, Event event){
        EVENTS.put(name,event);
        return this;
    }

    public Event createEvent(@NonNull String action){
        Event event = EVENTS.get(action);
        if (event == null){
            return new UndefineEvent();
        }
        return event;
    }
}
