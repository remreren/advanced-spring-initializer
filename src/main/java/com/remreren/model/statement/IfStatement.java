package com.remreren.model.statement;

import java.util.List;

public class IfStatement implements ConditionalStatement {

    @Override
    public String interpolate() {
        return null;
    }

    @Override
    public List<String> getImports() {
        return ConditionalStatement.super.getImports();
    }
}
