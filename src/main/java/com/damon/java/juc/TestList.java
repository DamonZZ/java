package com.damon.java.juc;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestList {

    public static void main(String[] args) {

        // java.util.ConcurrentModificationException
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                list.add(UUID.randomUUID().toString());
//                System.out.println(Thread.currentThread().getName() + ": " + list);
//            }, i + "").start();
//        }

        // 1. use Vector
//        List<String> list = new Vector<>();
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                list.add(UUID.randomUUID().toString());
//                System.out.println(list);
//            }, i + "").start();
//        }

        // 2. use Collections
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                list.add(UUID.randomUUID().toString());
//                System.out.println(list);
//            }, i + "").start();
//        }

        // 3. use CopyOnWriteArrayList
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString());
                System.out.println(list);
            }, i + "").start();
        }

    }

}
