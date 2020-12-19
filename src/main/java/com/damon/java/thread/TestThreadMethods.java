package com.damon.java.thread;

public class TestThreadMethods implements Runnable {

    // do not use stop/destroy

    private boolean flag = true;

    @Override
    public void run() {

//        int count = 0;
//        while (flag) {
//            System.out.println(Thread.currentThread().getName() + ": " + count++);
//        }

//        System.out.println(Thread.currentThread().getName() + ": Thread start...");
//        Thread.yield();
//        System.out.println(Thread.currentThread().getName() + ": Thread end...");

        for (int i = 0; i < 1000; i++) {
            System.out.println(Thread.currentThread().getName() + ": vip coming " + i);
        }
    }

    public void stop() {
        this.flag = false;
    }

    public static void main(String[] args) throws InterruptedException {
        // test sopt
//        testStop();

        // test yield
//        testYield();

        // test join
        testJoin();

    }

    private static void testStop() throws InterruptedException {
        TestThreadMethods testThreadMethods = new TestThreadMethods();
        new Thread(testThreadMethods).start();
        for (int i = 0; i < 1000; i++) {
            Thread.sleep(1);
            if (i == 99) {
                System.out.println("stop thread");
                testThreadMethods.stop();
            }
        }
    }

    private static void testYield() throws InterruptedException {
        Runnable runnable = new TestThreadMethods();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }

    private static void testJoin() throws InterruptedException {
        Thread thread = new Thread(new TestThreadMethods());
        Thread.State state = thread.getState();
        System.out.println("State : " + state);
        thread.start();
        state = thread.getState();
        System.out.println("State : " + state);
        for (int i = 0; i < 500; i++) {
            if (i == 200) {
                thread.join();
                state = thread.getState();
                System.out.println("State : " + state);
            }
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }


}
