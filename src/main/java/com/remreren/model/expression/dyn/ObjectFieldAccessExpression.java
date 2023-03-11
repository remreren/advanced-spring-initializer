package com.remreren.model.expression.dyn;

import com.remreren.model.expression.SubExpression;
import com.remreren.model.field.FieldModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Getter
@Accessors(chain = true)
public class ObjectFieldAccessExpression extends DynamicAssignmentExpression {

    private FieldModel object;

    private SubExpression field;

    @Override
    public String interpolate() {

        if (object == null)
            return field.interpolate();

        var interpolated = "%s.%s".formatted(object.interpolate(), field.interpolate());

        if (expression == null)
            return interpolated;

        return interpolated.concat(".%s".formatted(expression.interpolate()));
    }

    @Override
    public List<String> getImports() {
        return List.of();
    }
}
