package com.remreren.model.statement;

import com.remreren.model.expression.Expression;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public abstract class ConditionalStatement implements Statement {

    protected List<Expression> statements = new ArrayList<>();

}
