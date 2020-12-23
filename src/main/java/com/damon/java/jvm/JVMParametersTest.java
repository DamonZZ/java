package com.damon.java.jvm;

import java.util.Random;

public class JVMParametersTest {

    public static void main(String[] args) {
        //
        // -Xms1024m default 1/64
        // -Xmx1024m default 1/4
        // -XX:+PrintGCDetails
        // -XX:+HeapDumpOnOutOfMemoryError
        System.out.println(Runtime.getRuntime().totalMemory() / (1024 * 1024) + "MB");
        System.out.println(Runtime.getRuntime().maxMemory() / (1024 * 1024) + "MB");

        String test = "1234567890";
        while (true) {
            test = test + new Random().nextInt(2000000000) + new Random().nextInt(2000000000);
        }
    }

}
