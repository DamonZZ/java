package com.damon.java.juc;

import java.util.concurrent.*;

public class PoolTest {

    public static void main(String[] args) {

//        singleThreadExecutorTest();

//        fixedThreadPoolTest();

//        cachedThreadPool();

        threadPoolExecutorTest();
    }

    private static void singleThreadExecutorTest() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            for (int i = 0; i < 10; i++) {
                final String temp = i + "";
                executorService.execute(() -> System.out.println(Thread.currentThread().getName() + " " + temp));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            executorService.shutdown();
        }

    }

    private static void fixedThreadPoolTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        try {
            for (int i = 0; i < 10; i++) {
                final String temp = i + "";
                executorService.execute(() -> System.out.println(Thread.currentThread().getName() + " " + temp));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            executorService.shutdown();
        }

    }

    private static void cachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 10; i++) {
                final String temp = i + "";
                executorService.execute(() -> System.out.println(Thread.currentThread().getName() + " " + temp));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    private static void threadPoolExecutorTest() {
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()
        );
        try {
            for (int i = 0; i < 20; i++) {
                final String temp = i + "";
                executorService.execute(() -> System.out.println(Thread.currentThread().getName() + " " + temp));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

}
