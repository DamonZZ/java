package com.damon.java.juc;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {

    public static void main(String[] args) {
        // java.util.ConcurrentModificationException
//        Map<String, String> map = new HashMap<>();
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
//                System.out.println(map);
//            }, i + "").start();
//        }

        // 1. synchronizedSet
//        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
//                System.out.println(map);
//            }, i + "").start();
//        }


        // 2. ConcurrentHashMap
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
                System.out.println(map);
            }, i + "").start();
        }
    }

}
