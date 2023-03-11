package com.remreren.model.expression.dyn;

import com.remreren.model.MethodModel;
import com.remreren.model.statement.VariableModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class MethodInvocationExpression extends DynamicAssignmentExpression {

    private VariableModel variable;

    private List<VariableModel> parameters;

    private MethodModel method;

    @Override
    public String interpolate() {
        var invocation = method.invokeInterpolate().formatted(parameters.stream().map(VariableModel::name).toArray());

        if (variable == null) {
            return invocation;
        }

        return variable.name().concat(".").concat(invocation);
    }
}
