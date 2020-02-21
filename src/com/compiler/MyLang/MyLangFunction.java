package com.compiler.MyLang;

import java.util.List;

class MyLangFunction implements MyLangCallable{
    private final Stmt.Function declaration;
    private final Environment closure;

    MyLangFunction(Stmt.Function declaration, Environment closure){
        this.declaration = declaration;
        this.closure = closure;
    }

    @Override
    public String toString(){
        return "<fn " + declaration.name.lexeme + ">";
    }

    @Override
    public int arity(){
        return declaration.params.size();
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments){
        Environment environment = new Environment(closure);
        for(int i = 0; i < declaration.params.size(); i++)
            environment.define(declaration.params.get(i).lexeme, arguments.get(i));

        try {
            interpreter.executeBlock(declaration.body, environment);
        } catch (Return returnValue){
            return returnValue.value;
        }

        return null;
    }
}
