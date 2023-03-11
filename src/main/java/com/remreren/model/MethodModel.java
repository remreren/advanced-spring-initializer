package com.remreren.model;

import com.remreren.model.statement.ParameterModel;
import com.remreren.model.statement.StatementModel;
import com.remreren.model.statement.VariableAssignmentStatement;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Setter
@Getter
@Accessors(chain = true)
public class MethodModel implements Interpolation {

    private List<StatementModel> statements = new ArrayList<>();

    private Set<Keyword> modifiers = new HashSet<>();

    private ClassModel type;

    private String name;

    private List<ParameterModel> parameters = new ArrayList<>();

    @Override
    public String interpolate() {
        return "\t".concat(modifiers.stream().map(Keyword::getValue).collect(Collectors.joining(" ")))

                .concat(modifiers.contains(Keyword.VOID) ? "": " %s".formatted(type.getName())).concat(" ")
                .concat(name)
                .concat("(").concat(interpolateParameters()).concat(")").concat(" {\n")
                .concat(interpolateStatements()).concat("\n")
                .concat("\t}\n");
    }

    public String invokeInterpolate() {
        return name.concat("(").concat(IntStream.range(0, parameters.size()).mapToObj(__ -> "%s").collect(Collectors.joining(", "))).concat(")");
    }

    public String interpolate(Set<String> imports) {
        var importTypes = getImports();
        imports.addAll(importTypes);

        return interpolate();
    }

    private String interpolateStatements() {
        return statements.stream().map(StatementModel::interpolate).map("\t\t%s;"::formatted).collect(Collectors.joining("\n"));
    }

    private List<String> getImports() {
        var classesFiltered = statements.stream().filter(VariableAssignmentStatement.class::isInstance);
        return classesFiltered
                .map(stmt -> (((VariableAssignmentStatement) stmt).getExpression().getImport()))
                .filter(Objects::nonNull)
                .map(imp -> "import ".concat(imp).concat(";")).toList();
    }

    private String interpolateParameters() {
        return parameters.stream().map(ParameterModel::interpolate).collect(Collectors.joining("\n"));
    }

    public MethodModel addStatement(StatementModel statement) {
        statements.add(statement);
        return this;
    }

    public MethodModel addModifiers(Keyword... keyword) {
        modifiers.addAll(Arrays.asList(keyword));
        return this;
    }
}
