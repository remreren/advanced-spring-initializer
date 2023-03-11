package com.remreren.model.expression.dyn;

import com.remreren.model.ClassModel;
import com.remreren.model.statement.ParameterModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@Accessors(chain = true)
public class NewInstanceAssignmentExpression extends DynamicAssignmentExpression {

    private ClassModel clazz;

    private List<ParameterModel> parameters = new ArrayList<>();

    @Override
    public List<String> getImports() {
        if (clazz.getPkg() == null) {
            return List.of();
        }

        return List.of(clazz.getPkg().concat(".").concat(clazz.getName()));
    }

    @Override
    public String interpolate() {
        return "new ".concat(clazz.getName()).concat("(").concat(getParametersInterpolated()).concat(")");
    }

    private String getParametersInterpolated() {
        return parameters.stream().map(ParameterModel::interpolate).collect(Collectors.joining(", "));
    }
}
