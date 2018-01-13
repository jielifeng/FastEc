package com.example.latte_ec2.deltest.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.hasee.festec.exaple.app.AccountManager;
import com.example.hasee.festec.exaple.app.IUserChecker;
import com.example.hasee.festec.exaple.delegate.LatteDelegate;
import com.example.hasee.festec.exaple.util.storage.LattePreference;
import com.example.latte.launcher.ILauncherListener;
import com.example.latte.launcher.LauncherHolderCreater;
import com.example.latte.launcher.OnLauncherFinishTag;
import com.example.latte.launcher.ScrollLauncharFlag;
import com.example.latte_ec2.R;

import java.util.ArrayList;

/**
 * Created by hasee on 2017-09-14.
 * 特性介绍页，第一次打开软件或更新版本后显示
 */

public class LaunecherScrollDelegate extends LatteDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();
    private ILauncherListener mLauncherListener = null;
    private void initBanner(){
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_001);
        mConvenientBanner
                .setPages(new LauncherHolderCreater(),INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<Integer>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceSate, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        if (position == INTEGERS.size() - 1){
            LattePreference.setAppFlag(ScrollLauncharFlag.HAS_FIRST_LAUNCHER_APP.name(),true);
            //检查用户是否已经登陆
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
                    }else {
                        Log.i("testLauncher", "mLauncherListener is null");
                    }
                }
            });
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener){
            mLauncherListener = (ILauncherListener) activity;
        }
    }
}
