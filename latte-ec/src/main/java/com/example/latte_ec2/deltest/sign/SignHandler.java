package com.example.latte_ec2.deltest.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.latte_ec2.deltest.database.DatabaseManager;
import com.example.latte_ec2.deltest.database.UserProfile;

import com.example.hasee.festec.exaple.app.AccountManager;

/**
 * Created by hasee on 2017-10-17.
 */

public class SignHandler {

    public static void onSignIn(String response,ISignListener iSignListener){
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final Long id = profileJson.getLong("id");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(id,name, avatar,address, gender);
        DatabaseManager.getInstance().getDao().insert(profile);

        AccountManager.setSignState(true);
        iSignListener.onSignInSuccess();
    }

    public static void onSignUp(String response,ISignListener iSignListener){
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final Long id = profileJson.getLong("id");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(id,name, avatar,address, gender);
        DatabaseManager.getInstance().getDao().insert(profile);

        AccountManager.setSignState(true);
        iSignListener.onSignUpSuccess();
    }
}
