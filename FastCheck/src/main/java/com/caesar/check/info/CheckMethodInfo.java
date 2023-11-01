package com.caesar.check.info;

import com.caesar.check.method.Checkable;
import lombok.Data;


import java.lang.reflect.Method;

@Data
public class CheckMethodInfo {
    private Checkable instance;
    private Method method;

    public CheckMethodInfo(Class<? extends Checkable> clazz) {
        try {
            this.instance = clazz.getConstructor().newInstance();
            this.method = clazz.getMethod("check", Object.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
