package com.example.hasee.festec.exaple.app;

import com.example.hasee.festec.exaple.util.storage.LattePreference;

/**
 * Created by hasee on 2017-10-18.
 */

public class AccountManager {
    private enum SignTag{
        SIGN_TAG;
    }

    //保存用户登陆状态，登陆后调用
    public static void setSignState(boolean state) {
        //创建一个SharedPreferences，把状态保存到SharedPreferences中
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }

    //判断是否已经登陆
    private static boolean isSignIn(){
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker){
        if (isSignIn()){
            checker.onSignIn();
        }else {
            checker.onNotSignIn();
        }
    }
}
