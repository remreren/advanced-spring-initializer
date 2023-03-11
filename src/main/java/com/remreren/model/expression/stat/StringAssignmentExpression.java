package com.remreren.model.expression.stat;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StringAssignmentExpression extends StaticAssignmentExpression {

    private final String value;

    @Override
    public String interpolate() {
        return "\"%s\"".formatted(value);
    }
}
