package com.example.latte_ec2.deltest.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import com.example.hasee.festec.exaple.delegate.LatteDelegate;
import com.example.hasee.festec.exaple.net.RestClient;
import com.example.hasee.festec.exaple.net.calback.ISuccess;
import com.example.latte_ec2.R;
import com.example.latte_ec2.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hasee on 2017-10-11.
 */

public class SignInDelegate extends LatteDelegate {
    boolean isPass = true;
    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_passwork)
    TextInputEditText mPasswork = null;

    @OnClick(R2.id.tv_link_sign_in)
    void onClickLinck(){
        getSupportDelegate().start(new SignUpDelegate());
    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn(){
        if (checkFrom()){
            RestClient.builder()
                    .url("http://192.168.1.110/data/user_profile.json")
                    .params("email",mEmail.getText().toString())
                    .params("passwork",mPasswork.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void OnSuccess(String respones) {
                            Log.i("json", "OnSuccess: " + respones);
                            SignHandler.onSignIn(respones,mSignListener);
                            //Toast.makeText(getContext(),"通过",Toast.LENGTH_LONG).show();
                        }
                    })
                    .build()
                    .post();
            //Toast.makeText(getContext(),"验证通过",Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void OnClickWeChat(){
    }

    private ISignListener mSignListener = null;

    //fragment与activity关联时调用
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener ){
            mSignListener = (ISignListener) activity;
        }
    }

    private boolean checkFrom(){
        final String email = mEmail.getText().toString();
        final String passwork = mPasswork.getText().toString();


        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        }else {
            mEmail.setError(null);
        }

        if (passwork.isEmpty() || passwork.length() < 6){
            mPasswork.setError("请填写至少6为数密码");
            isPass = false;
        }else{
            mPasswork.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceSate, View rootView) {

    }
}
