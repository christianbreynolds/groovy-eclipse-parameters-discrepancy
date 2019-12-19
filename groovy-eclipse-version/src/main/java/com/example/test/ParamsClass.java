package com.example.test;

import java.util.function.Supplier;

public class ParamsClass {

    public static String getStr(){
        String myString1 = "Hello ";
        String myString2 = "world!";
        Supplier<String> sup = () -> myString1 + myString2;
        return sup.get();
    }

}
