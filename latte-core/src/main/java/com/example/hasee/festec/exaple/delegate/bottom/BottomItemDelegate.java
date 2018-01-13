package com.example.hasee.festec.exaple.delegate.bottom;

import android.view.View;
import android.widget.Toast;

import com.example.hasee.festec.exaple.delegate.LatteDelegate;
import com.example.latte.R;

/**
 * Created by hasee on 2017-10-30.
 * 上面的fragment的基类
 */

public abstract class BottomItemDelegate extends LatteDelegate {

    private long TOUCH_TIME = 0;
    private long mExitTime = 0;
    private static final long WAIT_TIME = 2000L;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME){
            _mActivity.finish();
        }else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(getContext(), "双击退出" + getString(R.string.app_name), Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        View rootView = getView();
        if (rootView != null){
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
        }
    }
}
