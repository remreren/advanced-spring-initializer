package com.remreren.model.expression.stat;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StringAssignmentExpression implements StaticAssignmentExpression {

    private final String value;

    @Override
    public String interpolate() {
        return "\"%s\"".formatted(value);
    }
}
