package com.qiny.ycwl.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @ Author     ：Qiny.
 * @ Date       ：Created in 10:48 2018/11/23
 * @ Description：
 */
public class MyBCryptPasswordEncoder extends BCryptPasswordEncoder {
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String presentedPassword = passwordEncoder.encode(encodedPassword);
        return passwordEncoder.matches(rawPassword, presentedPassword);
    }
}
