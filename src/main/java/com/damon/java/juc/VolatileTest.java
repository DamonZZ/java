package com.damon.java.juc;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileTest {

    private volatile static int num = 0;

    private volatile static AtomicInteger atomicInteger = new AtomicInteger();

    private static void add() {
        atomicInteger.getAndIncrement();
    }

    public static void main(String[] args) {

        // 1.
//        new Thread(() -> {
//            while (num == 0) {
//
//            }
//        }).start();
//
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        num = 1;
//        System.out.println("num: " + num);

        // 2.
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount() > 2) {

        }
        System.out.println(Thread.currentThread().getName() + " num: " + atomicInteger.get());
    }


}
