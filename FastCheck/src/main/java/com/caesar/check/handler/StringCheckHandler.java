package com.caesar.check.handler;


import com.caesar.check.checker.StringChecker;

import javax.xml.bind.ValidationException;
import java.lang.annotation.Annotation;

public class StringCheckHandler extends CheckHandler{

    public static final String ILLEGAL_STRING_MESSAGE = "参数为空或长度超过最大限制";

    public static final String ILLEGAL_FORMAT_MESSAGE = "参数格式不符合指定类型";

    @Override
    public boolean match(Annotation checker) {
        return checker instanceof StringChecker;
    }

    @Override
    public void doCheck(Object attribute, Annotation checker) {

        String value = (String) attribute;
        StringChecker stringChecker = (StringChecker) checker;

        if (!StrUtil.checkString(value, stringChecker.maxLength()))
            throw new RuntimeException(stringChecker.name() + ILLEGAL_STRING_MESSAGE);

        if(!StrUtil.checkFormat(value, stringChecker.format()))
            throw new RuntimeException(stringChecker.name() + ILLEGAL_FORMAT_MESSAGE);
    }

}
