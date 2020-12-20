package com.damon.java.juc;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + ": go out");
                countDownLatch.countDown();
            }, i + "").start();
        }
        countDownLatch.await();
        System.out.println("close door");
    }

}
