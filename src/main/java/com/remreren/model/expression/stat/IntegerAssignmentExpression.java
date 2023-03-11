package com.remreren.model.expression.stat;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IntegerAssignmentExpression implements StaticAssignmentExpression {

    private final int value;

    @Override
    public String interpolate() {
        return String.valueOf(value);
    }
}
