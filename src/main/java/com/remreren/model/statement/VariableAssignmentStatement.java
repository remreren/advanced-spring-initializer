package com.remreren.model.statement;


import com.remreren.model.expression.Expression;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class VariableAssignmentStatement implements StatementModel {

    private String name;

    private Expression expression;

    @Override
    public String interpolate() {
        return "var ".concat(name).concat(" = ").concat(expression.interpolate());
    }

    public VariableModel toVariable() {
        return new VariableModel(null, name);
    }
}
