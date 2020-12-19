package com.damon.java.thread;

import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock implements Runnable {

    private int ticketNum = 100;
    private boolean flag = true;
    private final ReentrantLock lock = new ReentrantLock();


    @Override
    public void run() {
        while (flag) {
            try {
                this.buy();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void buy() throws InterruptedException {
        this.lock.lock();
        try {
            if (this.ticketNum < 0) {
                this.flag = false;
                return;
            }
            Thread.sleep(10);
            System.out.println(Thread.currentThread().getName() + ": get " + this.ticketNum--);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            this.lock.unlock();
        }
    }

    public static void main(String[] args) {
        TestReentrantLock testThreadSync = new TestReentrantLock();
        new Thread(testThreadSync).start();
        new Thread(testThreadSync).start();
        new Thread(testThreadSync).start();
    }

}
