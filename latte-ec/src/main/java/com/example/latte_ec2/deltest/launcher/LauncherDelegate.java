package com.example.latte_ec2.deltest.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;

import com.example.hasee.festec.exaple.app.AccountManager;
import com.example.hasee.festec.exaple.app.IUserChecker;
import com.example.hasee.festec.exaple.delegate.LatteDelegate;
import com.example.hasee.festec.exaple.util.storage.LattePreference;
import com.example.hasee.festec.exaple.util.timer.BaseTimerTask;
import com.example.hasee.festec.exaple.util.timer.ITimerListener;
import com.example.latte.launcher.ILauncherListener;
import com.example.latte.launcher.OnLauncherFinishTag;
import com.example.latte.launcher.ScrollLauncharFlag;
import com.example.latte_ec2.R;
import com.example.latte_ec2.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hasee on 2017-09-13.
 * 广告页，每次启动都会显示
 */

public class LauncherDelegate extends LatteDelegate implements ITimerListener {

    private Timer mTimer = null;
    private int mCount = 5;
    private ILauncherListener mLauncherListener = null;

    @BindView(R2.id.tv_launcher_timer3)
    AppCompatTextView mTvTimr = null;

    @OnClick(R2.id.tv_launcher_timer3)
    void onClickTimerView(){
        if (mTimer != null){
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    private void inittime(){
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task,0,1000);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener){
            mLauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher2;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceSate, View rootView) {
        inittime();
    }

    //判断是否显示滑动页
    private void checkIsShowScroll(){
        if (!LattePreference.getAppFlag(ScrollLauncharFlag.HAS_FIRST_LAUNCHER_APP.name()))
        {
            getSupportDelegate().start(new LaunecherScrollDelegate(),SINGLETASK);
        }else {
            //检查用户是否登陆了app
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mLauncherListener != null){
                        mLauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }else {
                        Log.i("testLauncher", "mLauncherListener is null");
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mLauncherListener != null){
                        mLauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                        Log.i("testLauncher", "mLauncherListener is null");
                    }else {
                        Log.i("testLauncher", "mLauncherListener is null");
                    }
                }
            });
        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimr != null){
                    Log.i("test", "run: ");
                    mTvTimr.setText(MessageFormat.format("跳过\n{0}s",mCount));
                    mCount--;
                    if (mCount<0){
                        if (mTimer != null){
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}
