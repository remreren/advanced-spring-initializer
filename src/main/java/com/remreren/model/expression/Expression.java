package com.remreren.model.expression;

import com.remreren.model.Interpolation;

import java.util.List;

public interface Expression extends Interpolation {

    default List<String> getImports() {
        return List.of();
    }

}
