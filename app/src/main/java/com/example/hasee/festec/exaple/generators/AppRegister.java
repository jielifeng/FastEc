package com.example.hasee.festec.exaple.generators;

import com.example.annotations.AppRegisterGenerator;
import com.example.hasee.festec.exaple.wechat.template.AppRegisterTemplate;

/**
 * Created by hasee on 2017-10-23.
 */
@AppRegisterGenerator(
        packageName = "com.example.hasee.festec.exaple",
        registerTemplete = AppRegisterTemplate.class

)
public interface AppRegister {
}
