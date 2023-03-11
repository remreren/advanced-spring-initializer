package com.remreren;

import com.remreren.model.ClassModel;
import com.remreren.model.Keyword;
import com.remreren.model.MethodModel;
import com.remreren.model.expression.dyn.MethodInvocationExpression;
import com.remreren.model.expression.dyn.NewInstanceAssignmentExpression;
import com.remreren.model.expression.stat.StringAssignmentExpression;
import com.remreren.model.statement.ParameterModel;
import com.remreren.model.statement.ReturnStatement;
import com.remreren.model.statement.VariableAssignmentStatement;
import lombok.AllArgsConstructor;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        var greetClass = new ClassModel().setName("Greet")
                .addAnnotation(AllArgsConstructor.class);

        var classInstantiation = new NewInstanceAssignmentExpression().setClazz(greetClass);
        var statement = new VariableAssignmentStatement().setName("greet").setExpression(classInstantiation);

        var mainMethod = new MethodModel().setName("main").addStatement(statement).addModifiers(Keyword.PUBLIC, Keyword.STATIC, Keyword.VOID);

        var greetMethodNameParameter = new ParameterModel().setName("name").setClazz(ClassModel.of(String.class));
        var greetMethod = new MethodModel().setName("greetings").addModifiers(Keyword.PUBLIC).setType(ClassModel.of(String.class)).setParameters(List.of(greetMethodNameParameter));

        var nameStatement = new VariableAssignmentStatement().setName("name").setExpression(new StringAssignmentExpression("Emre"));

        mainMethod.addStatement(nameStatement);

        var greetInvocationExpression = new MethodInvocationExpression().setMethod(greetMethod).setParameters(List.of(nameStatement.toVariable())).setVariable(statement.toVariable());
        var greetStatement = new VariableAssignmentStatement().setName("greeted").setExpression(greetInvocationExpression);

        mainMethod.addStatement(greetStatement);

        var concatenation = new MethodModel().setName("concat").setParameters(List.of(new ParameterModel()));
        var greetingsVariable = new VariableAssignmentStatement().setName("greetingString").setExpression(new StringAssignmentExpression("Greetings, "));
        var concatGreetStatement = new ReturnStatement(new MethodInvocationExpression().setMethod(concatenation).setVariable(greetingsVariable.toVariable()).setParameters(List.of(greetMethodNameParameter.toVariable())));

        greetMethod.addStatement(greetingsVariable);
        greetMethod.addStatement(concatGreetStatement);

        greetClass.addMethod(mainMethod);
        greetClass.addMethod(greetMethod);

        var interpolated = greetClass.interpolate();

        System.out.println(interpolated);
    }
}