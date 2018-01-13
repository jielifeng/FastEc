package com.example.hasee.festec.exaple.generators;

import com.example.annotations.PayEntryGenerator;
import com.example.hasee.festec.exaple.wechat.template.WXPayEntryTemplate;


/**
 * Created by hasee on 2017-10-23.
 */
@PayEntryGenerator(
        packageName = "com.example.hasee.festec.exaple",
        payEntryTemplete = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
