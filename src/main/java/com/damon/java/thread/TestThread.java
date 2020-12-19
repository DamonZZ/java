package com.damon.java.thread;

public class TestThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ": I am learning " + i);
        }
    }

    public static void main(String[] args) {

        Thread testThread = new TestThread();
        testThread.start();

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ": I am main " + i);
        }
    }

}
