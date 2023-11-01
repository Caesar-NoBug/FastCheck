package com.caesar.check.handler;


import com.caesar.check.info.CheckMethodInfo;
import com.caesar.check.checker.ObjectChecker;
import com.caesar.check.method.Checkable;
import sun.security.validator.ValidatorException;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class ObjectCheckHandler extends CheckHandler{

    public static String ILLEGAL_OBJECT_MESSAGE = "参数为空";

    public final Map<Class<? extends Checkable>, CheckMethodInfo> CHECKER_METHOD_MAP = new ConcurrentHashMap<>();

    @Override
    public boolean match(Annotation checker) {
        return checker instanceof ObjectChecker;
    }

    @Override
    public void doCheck(Object attribute, Annotation checker) {
        try {

            ObjectChecker objectChecker = (ObjectChecker) checker;

            CheckMethodInfo info = getCheckMethodInfo(objectChecker.method());

            if (!(boolean) info.getMethod().invoke(info.getInstance(), attribute))
                throw new ValidatorException(objectChecker.name() + ILLEGAL_OBJECT_MESSAGE);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private CheckMethodInfo getCheckMethodInfo(Class<? extends Checkable> clazz) {
        if (Objects.isNull(CHECKER_METHOD_MAP.get(clazz)))
            CHECKER_METHOD_MAP.put(clazz, new CheckMethodInfo(clazz));

        return CHECKER_METHOD_MAP.get(clazz);
    }

}
