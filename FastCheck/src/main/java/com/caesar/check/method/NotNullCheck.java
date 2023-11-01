package com.caesar.check.method;

import java.util.Objects;

public class NotNullCheck implements Checkable {

    @Override
    public boolean check(Object attribute) {
        return !Objects.isNull(attribute);
    }

}
