package com.example.hasee.festec.exaple.delegate;

/**
 * Created by hasee on 2017-08-02.
 */

public abstract class LatteDelegate extends PermissionCheckerDelegate {

    public <T extends LatteDelegate> T getParentDelegate(){
        return (T)getParentFragment();
    }
}
