package com.remreren.model.expression.dyn;

import com.remreren.model.expression.SubExpression;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public abstract class DynamicAssignmentExpression implements SubExpression {

    protected DynamicAssignmentExpression expression;

}
