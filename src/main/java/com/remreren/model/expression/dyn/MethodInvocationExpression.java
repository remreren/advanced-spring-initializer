package com.remreren.model.expression.dyn;

import com.remreren.model.MethodModel;
import com.remreren.model.expression.SubExpression;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class MethodInvocationExpression extends DynamicAssignmentExpression {

    private SubExpression field;

    private List<SubExpression> parameters;

    private MethodModel method;

    @Override
    public String interpolate() {
        var invocation = method.invokeInterpolate().formatted(parameters.stream().map(SubExpression::interpolate).toArray());

        if (field != null) {
            invocation = "%s.%s".formatted(field.interpolate(), invocation);
        }

        if (expression != null) {
            invocation = "%s.%s".formatted(invocation, expression.interpolate());
        }

        return invocation;
    }

    @Override
    public List<String> getImports() {
        return List.of();
    }
}
