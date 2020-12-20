package com.damon.java.juc;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetTest {

    public static void main(String[] args) {

        // java.util.ConcurrentModificationException
//        Set<String> set = new HashSet<>();
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                set.add(UUID.randomUUID().toString());
//                System.out.println(Thread.currentThread().getName() + ": " + set);
//            }, i + "").start();
//        }

        // 1. synchronizedSet
//        Set<String> set = Collections.synchronizedSet(new HashSet<>());
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                set.add(UUID.randomUUID().toString());
//                System.out.println(Thread.currentThread().getName() + ": " + set);
//            }, i + "").start();
//        }

        // 2. CopyOnWriteArraySet
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString());
                System.out.println(Thread.currentThread().getName() + ": " + set);
            }, i + "").start();
        }
    }

}
