package com.example.hasee.festec.exaple.generators;

import com.example.annotations.EntryGenerator;
import com.example.hasee.festec.exaple.wechat.template.WXEntryTemplate;


/**
 * Created by hasee on 2017-10-23.
 */
@EntryGenerator(
        packageName = "com.example.hasee.festec.exaple",
        entryTemplete = WXEntryTemplate.class
)
public interface WeChatEntry {
}
