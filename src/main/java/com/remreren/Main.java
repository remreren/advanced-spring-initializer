package com.remreren;

import com.remreren.model.ClassModel;
import com.remreren.model.Keyword;
import com.remreren.model.MethodModel;
import com.remreren.model.expression.dyn.ClassFieldAccessExpression;
import com.remreren.model.expression.dyn.MethodInvocationExpression;
import com.remreren.model.expression.dyn.NewInstanceAssignmentExpression;
import com.remreren.model.expression.dyn.ObjectFieldAccessExpression;
import com.remreren.model.expression.stat.StringAssignmentExpression;
import com.remreren.model.field.FieldModel;
import com.remreren.model.statement.ParameterModel;
import com.remreren.model.statement.ReturnStatement;
import com.remreren.model.statement.VariableAssignmentStatement;
import lombok.AllArgsConstructor;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        var greetClass = new ClassModel().setName("Greet")
                .setPkg("com.remreren")
                .addAnnotation(AllArgsConstructor.class)
                .addModifiers(Keyword.PUBLIC);

        var greetMethod = new MethodModel()
                .setName("greet")
                .addModifiers(Keyword.PUBLIC)
                .setParameters(List.of(new ParameterModel().setName("name").setClazz(ClassModel.of(String.class))))
                .setType(ClassModel.of(String.class));

        var concatMethod = new MethodModel().setName("concat").setType(ClassModel.of(String.class)).setParameters(List.of(new ParameterModel()));
        var concatGreetingsStatement = new MethodInvocationExpression()
                .setMethod(concatMethod)
                .setField(new StringAssignmentExpression("Greetings, "))
                .setParameters(
                        List.of(new ObjectFieldAccessExpression().setField(new FieldModel().setName("name"))));
        var returnString = new ReturnStatement().setExpression(concatGreetingsStatement);

        greetMethod.addStatement(returnString);

        var mainMethod = new MethodModel()
                .setName("main")
                .addModifiers(Keyword.PUBLIC, Keyword.STATIC, Keyword.VOID);

        var greetingsClassInstantiation = new VariableAssignmentStatement()
                .setName("greetingService")
                .setExpression(new NewInstanceAssignmentExpression().setClazz(greetClass));

        var greetUser = new VariableAssignmentStatement()
                .setName("greetings")
                .setExpression(new MethodInvocationExpression()
                        .setField(greetingsClassInstantiation.toField())
                        .setMethod(greetMethod)
                        .setParameters(List.of(new StringAssignmentExpression("Emre"))));

        var systemClass = ClassModel.of(System.class);
        var outField = new FieldModel().setClazz(systemClass).setName("out");
        var printlnExpr = new ClassFieldAccessExpression()
                .setClazz(systemClass)
                .setField(outField)
                .setExpression(new MethodInvocationExpression()
                        .setMethod(new MethodModel()
                                .setName("println")
                                .setParameters(List.of(new ParameterModel())))
                        .setParameters(List.of(greetUser.toField())));

        mainMethod.addStatement(greetUser)
                .addStatement(printlnExpr);

        greetClass.addMethod(mainMethod)
                .addMethod(greetMethod);

        var interpolated = greetClass.interpolate();

        System.out.println(interpolated);
    }
}