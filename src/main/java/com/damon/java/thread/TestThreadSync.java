package com.damon.java.thread;

public class TestThreadSync implements Runnable {

    private int ticketNum = 100;
    private boolean flag = true;

    private Object object = new Object();

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

        synchronized (this.object) {
            if (this.ticketNum < 0) {
                this.flag = false;
                return;
            }
            Thread.sleep(10);
            System.out.println(Thread.currentThread().getName() + ": get " + this.ticketNum--);
        }
    }

    public static void main(String[] args) {
        TestThreadSync testThreadSync = new TestThreadSync();
        new Thread(testThreadSync).start();
        new Thread(testThreadSync).start();
        new Thread(testThreadSync).start();
    }
}
