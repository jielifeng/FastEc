package com.example.latte_ec2.deltest.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.hasee.festec.exaple.delegate.LatteDelegate;
import com.example.hasee.festec.exaple.net.RestClient;
import com.example.hasee.festec.exaple.net.calback.ISuccess;
import com.example.latte_ec2.R;
import com.example.latte_ec2.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hasee on 2017-09-20.
 */

public class SignUpDelegate extends LatteDelegate {

    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName = null;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_sign_up_passwork)
    TextInputEditText mPasswork = null;
    @BindView(R2.id.edit_sign_up_re_passwork)
    TextInputEditText mRePasswork = null;

    private ISignListener mSignListener = null;

    //fragment与activity关联时调用
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener ){
            mSignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLinck(){
        getSupportDelegate().start(new SignInDelegate());
    }

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp(){
        if (checkFrom()){
            RestClient.builder()
                    .url("http://192.168.1.100/data/user_profile.json")
                    .params("name",mName.getText().toString())
                    .params("email",mEmail.getText().toString())
                    .params("phone",mPhone.getText().toString())
                    .params("passwork",mPasswork.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void OnSuccess(String respones) {
                            Log.i("json", "OnSuccess: " + respones);
                            SignHandler.onSignUp(respones,mSignListener);
                            Toast.makeText(getContext(),"通过", Toast.LENGTH_LONG).show();
                        }
                    })
                    .build()
                    .post();
            //Toast.makeText(getContext(),"验证通过",Toast.LENGTH_LONG).show();
        }
    }

    boolean isPass = true;

    private boolean checkFrom(){
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String passwork = mPasswork.getText().toString();
        final String rePasswork = mRePasswork.getText().toString();

        if (name.isEmpty()){
            mName.setError("请输入姓名");
            isPass = false;
        }else {
            mName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        }else {
            mEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11){
            mPhone.setError("手机号码错误");
            isPass = false;
        }else{
            mPhone.setError(null);
        }

        if (passwork.isEmpty() || passwork.length() < 6){
            mPasswork.setError("请填写至少6为数密码");
            isPass = false;
        }else{
            mPasswork.setError(null);
        }

        if (rePasswork.isEmpty() || rePasswork.length()<6 || !(rePasswork.equals(passwork))){
            mRePasswork.setError("密码验证错误");
            isPass = false;
        }else {
            mRePasswork.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceSate, View rootView) {

    }
}
