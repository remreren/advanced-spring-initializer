package com.remreren.model.expression.dyn;

import com.remreren.model.expression.SubExpression;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class BooleanExpression implements SubExpression {

    private SubExpression left;

    private SubExpression right;

    private Sign sign;

    @Override
    public String interpolate() {
        return "%s %s %s".formatted(left.interpolate(), sign.getValue(), right.interpolate());
    }

    @Override
    public List<String> getImports() {
        return SubExpression.super.getImports();
    }

    @Getter
    @RequiredArgsConstructor
    public enum Sign {
        LESS("<"),
        EQUALS("=="),
        MORE(">"),
        LESS_EQUALS("<="),
        MORE_EQUALS(">=");

        private final String value;
    }
}
