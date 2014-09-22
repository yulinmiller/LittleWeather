package com.littleweather.app.util;

/**
 * Created by 麟 on 2014/9/20.
 */
public interface HttpCallbackListner {
    void onFinish(String response);
    void onError(Exception e);
}
