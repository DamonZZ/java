package com.damon.java.juc;

import java.util.concurrent.TimeUnit;

public class DeadLockTest {

    public static void main(String[] args) {

        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new DeadLockThread(lockA, lockB), "A").start();
        new Thread(new DeadLockThread(lockB, lockA), "A").start();

    }

}


class DeadLockThread implements Runnable {

    private String lockA;
    private String lockB;

    public DeadLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {

        synchronized (this.lockA) {
            System.out.println(Thread.currentThread().getName() + " lock: " + this.lockA + "=>get " + this.lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this.lockB) {
                System.out.println(Thread.currentThread().getName() + " lock: " + this.lockB + "=>get " + this.lockA);
            }
        }

    }
}