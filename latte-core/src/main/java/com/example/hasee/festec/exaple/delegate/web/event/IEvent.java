package com.example.hasee.festec.exaple.delegate.web.event;

/**
 * Created by hasee on 2017-12-09.
 * 在js调用原生的框架中，InterFace的evernt函数中会根据传进的action来判断具体由那个event对象来响应这次调用
 */

public interface IEvent {
    String execute(String params);
}
