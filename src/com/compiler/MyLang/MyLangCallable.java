package com.compiler.MyLang;

import java.util.List;

interface MyLangCallable {
    int arity();
    Object call(Interpreter interpreter, List<Object> arguments);
}
