package com.example.test;

import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GroovyEclipseParamsTest {

    @Test
    public void doTest() {
        Class<ParamsClass> clazz = ParamsClass.class;
        assertEquals(2, clazz.getDeclaredMethods().length);

        Optional<Method> optional = Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.getName().startsWith("lambda"))
                .findFirst();
        assertTrue(optional.isPresent());

        Method lambda = optional.get();
        Parameter[] params = lambda.getParameters();

        assertTrue("Params aren't named", Arrays.stream(params).allMatch(Parameter::isNamePresent));
    }
}
