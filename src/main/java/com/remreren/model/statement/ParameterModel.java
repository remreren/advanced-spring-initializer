package com.remreren.model.statement;

import com.remreren.model.ClassModel;
import com.remreren.model.Interpolation;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ParameterModel implements Interpolation {

    private ClassModel clazz;

    private String name;

    @Override
    public String interpolate() {
        return clazz.getName().concat(" ").concat(name);
    }
}
