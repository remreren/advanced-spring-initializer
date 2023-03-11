package com.remreren.model.statement;

import com.remreren.model.expression.Expression;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public final class ReturnStatement implements Statement {

    private Expression expression;

    @Override
    public String interpolate() {
        return "return ".concat(expression.interpolate());
    }
}
