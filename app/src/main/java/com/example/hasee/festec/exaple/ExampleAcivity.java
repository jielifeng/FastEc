package com.example.hasee.festec.exaple;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.hasee.festec.exaple.activityes.ProxyActivity;
import com.example.hasee.festec.exaple.delegate.LatteDelegate;
import com.example.latte.launcher.ILauncherListener;
import com.example.latte.launcher.OnLauncherFinishTag;
import com.example.latte_ec2.deltest.launcher.LauncherDelegate;
import com.example.latte_ec2.deltest.main.EcButtomDeleagte;
import com.example.latte_ec2.deltest.sign.ISignListener;
import com.example.latte_ec2.deltest.sign.SignInDelegate;

import com.example.hasee.festec.exaple.app.Latte;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by hasee on 2017-08-07.
 * 这个软件是单Activity架构，所以所有的页面都是通过Fragment来显示的
 *
 */

public class ExampleAcivity extends ProxyActivity implements
        ISignListener,
        ILauncherListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Latte.getConfigurator().withaActivity(this);
        StatusBarCompat.translucentStatusBar(this, true);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登陆成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                //Toast.makeText(this, "启动结束，用户登陆", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new EcButtomDeleagte());
                break;
            case NOT_SIGNED:
                //Toast.makeText(this, "启动结束，用户没登陆", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
