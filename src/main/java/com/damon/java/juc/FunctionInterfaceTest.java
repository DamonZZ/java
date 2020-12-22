package com.damon.java.juc;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionInterfaceTest {

    public static void main(String[] args) {
        Function<String, String> function = (in) -> {
            return in;
        };

        Predicate<String> predicate = (in) -> {
            return in != null;
        };

        Consumer<String> consumer = (in) -> {
            System.out.println("accept: " + in);
        };

        Supplier<String> supplier = () -> {
            return "123";
        };

        System.out.println(function.apply("123"));
        System.out.println(predicate.test("123"));
        consumer.accept("123");
        System.out.println("get: " + supplier.get());
    }

}
