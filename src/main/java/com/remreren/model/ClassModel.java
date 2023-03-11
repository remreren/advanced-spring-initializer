package com.remreren.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@Accessors(chain = true)
public final class ClassModel implements Interpolation {

    private String name;

    private List<MethodModel> methods = new ArrayList<>();

    private List<Class<? extends Annotation>> annotations = new ArrayList<>();

    private Set<Keyword> modifiers = new HashSet<>();

    private String pkg;

    @Setter(AccessLevel.NONE)
    private Set<String> importStatements = new HashSet<>();

    public static ClassModel of(Class<?> clazz) {
        return new ClassModel()
                .setName(clazz.getSimpleName())
                .setPkg(clazz.getPackageName());
    }

    @Override
    public String interpolate() {
        var annotationsInterpolated = interpolateAnnotations();
        var methodsInterpolated = interpolateMethods();
        var importsInterpolated = String.join("\n", importStatements);
        var classDefinition = modifiers.stream().map(Keyword::getValue).collect(Collectors.joining(" ")).concat(" class ").concat(name).concat(" {");

        var interpolated = "";
        if (pkg != null) {
            interpolated = interpolated.concat("package %s;%n%n".formatted(pkg));
        }

        interpolated = interpolated
                .concat(importsInterpolated).concat("\n\n")
                .concat(annotationsInterpolated).concat("\n")
                .concat(classDefinition).concat("\n")
                .concat(methodsInterpolated).concat("\n")
                .concat("}\n");

        return interpolated;
    }

    private String interpolateAnnotations() {
        this.importStatements.addAll(annotations.stream().map(Class::getName).map(clazzName -> "import ".concat(clazzName).concat(";")).toList());
        return annotations.stream().map(Class::getSimpleName).map("@"::concat).collect(Collectors.joining("\n"));
    }

    private String interpolateMethods() {
        return methods.stream().map(method -> method.interpolate(importStatements)).collect(Collectors.joining("\n\n"));
    }

    public ClassModel addAnnotation(Class<? extends Annotation> annotation) {
        annotations.add(annotation);
        return this;
    }

    public ClassModel addMethod(MethodModel method) {
        methods.add(method);
        return this;
    }

    public ClassModel addModifiers(Keyword... modifiers) {
        this.modifiers.addAll(List.of(modifiers));
        return this;
    }
}
