package com.remreren.model.statement;

import com.remreren.model.expression.Expression;
import com.remreren.model.expression.dyn.BooleanExpression;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Accessors(chain = true)
public class IfStatement extends ConditionalStatement {

    private BooleanExpression expression;

    @Override
    public String interpolate() {
        var statementDeclaration = "if (%s) {%n".formatted(expression.interpolate());
        var statementsInterpolation = statements.stream().map(Expression::interpolate).map("\t\t\t"::concat).collect(Collectors.joining("\n"));

        return statementDeclaration
                .concat(statementsInterpolation)
                .concat("\t\t}");
    }

    @Override
    public List<String> getImports() {
        return List.of();
    }
}
