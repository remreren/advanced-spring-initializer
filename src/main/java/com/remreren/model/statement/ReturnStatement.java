package com.remreren.model.statement;

import com.remreren.model.expression.Expression;

public record ReturnStatement(Expression expression) implements StatementModel {
    @Override
    public String interpolate() {
        return "return ".concat(expression.interpolate());
    }
}
