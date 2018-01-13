package com.example.hasee.festec.exaple.wechat;

import com.example.hasee.festec.exaple.app.ConfigType;
import com.example.hasee.festec.exaple.app.Latte;

/**
 * Created by hasee on 2017-10-24.
 */

public class LatteWeChat {
    static final String APP_ID = Latte.getConfigurations(ConfigType.WE_CHAT_APP_ID);
    static final String APP_SECRET = Latte.getConfigurations(ConfigType.WE_CHAT_APP_SECRET);
}
