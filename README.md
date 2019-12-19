# This Repo

I found what I believe to be a compiler bug in the groovy-eclipse compiler (https://github.com/groovy/groovy-eclipse), in that it mismanages parameter names for certain types of lambda functions. Consider this method:

```
public static String getStr(){
    String myString1 = "Hello ";
    String myString2 = "world!";
    Supplier<String> sup = () -> myString1 + myString2;
    return sup.get();
}
```

When this is compiled, two methods are added to its parent class. The first is of course `getStr()`, but the second is an anonymous one for the lambda function that takes two parameters, `myString1` and `myString2`. In a basic Java application the parameter names would be reassigned to simpler names since functionally they don't matter, but in applications that use reflection it's common to need to reference parameters by name. Therefore it would be necessary to compile the function with the `-parameters` flag, which will preserve the parameter names to the bytecode (see the java8 docs https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javac.html). 

As demonstrated by the two basic unit tests, this works for the default javac compiler when it's passed the `-parameters` flag. However, when using the groovy-eclipse compiler, the names are still erased and reassigned. I've tried various combinations of maven-compiler-plugin configurations to try to pass this argument correctly to the compiler, but I haven't achieved any successful results for groovy-eclipse. Also, I unfortunately don't know enough about the bytecode spec or have time to investigate the true bug, so this repo exists. 

