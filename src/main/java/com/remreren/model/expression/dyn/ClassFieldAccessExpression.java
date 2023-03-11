package com.remreren.model.expression.dyn;

import com.remreren.model.ClassModel;
import com.remreren.model.field.FieldModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Getter
@Accessors(chain = true)
public class ClassFieldAccessExpression extends DynamicAssignmentExpression {

    private ClassModel clazz;

    private FieldModel field;

    @Override
    public String interpolate() {
        var interpolated = "%s.%s".formatted(clazz.getName(), field.getName());

        if (expression != null) {
            interpolated = "%s.%s".formatted(interpolated, expression.interpolate());
        }

        return interpolated;
    }

    @Override
    public List<String> getImports() {
        return List.of("%s.%s".formatted(clazz.getPkg(), clazz.getName()));
    }
}
