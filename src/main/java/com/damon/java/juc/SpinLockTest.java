package com.damon.java.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLockTest {

    private static AtomicReference<Thread> atomicReference = new AtomicReference();

    public static void main(String[] args) {

        SpinLockTest lock = new SpinLockTest();

        new Thread(() -> {
            try {
                lock.lock();
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "a").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            try {
                lock.lock();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "b").start();

    }

    public void lock() {
        Thread thread = Thread.currentThread();

        System.out.println(Thread.currentThread().getName() + " ==> lock");

        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void unlock() {
        Thread thread = Thread.currentThread();

        System.out.println(Thread.currentThread().getName() + " ==> unlock");

        atomicReference.compareAndSet(thread, null);
    }

}
