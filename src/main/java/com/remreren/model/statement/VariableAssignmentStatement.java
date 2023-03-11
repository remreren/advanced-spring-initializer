package com.remreren.model.statement;

import com.remreren.model.expression.SubExpression;
import com.remreren.model.field.FieldModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class VariableAssignmentStatement implements Statement {

    private String name;

    private SubExpression expression;

    @Override
    public String interpolate() {
        return "var ".concat(name).concat(" = ").concat(expression.interpolate());
    }

    public FieldModel toField() {
        return new FieldModel().setName(name);
    }
}
