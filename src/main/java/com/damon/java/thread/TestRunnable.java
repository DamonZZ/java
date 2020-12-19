package com.damon.java.thread;

public class TestRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ": I am learning " + i);
        }
    }

    public static void main(String[] args) {

        Runnable runnable = new TestRunnable();
        Thread thread = new Thread(runnable);
        thread.start();

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ": I am main " + i);
        }

    }

}
