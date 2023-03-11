package com.remreren.model.field;

import com.remreren.model.ClassModel;
import com.remreren.model.expression.SubExpression;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Getter
@Accessors(chain = true)
public class FieldModel implements SubExpression {

    private ClassModel clazz;

    private String name;

    @Override
    public String interpolate() {
        return name;
    }

    @Override
    public List<String> getImports() {
        return List.of();
    }
}
