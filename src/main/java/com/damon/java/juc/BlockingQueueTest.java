package com.damon.java.juc;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockingQueueTest {

    public static void main(String[] args) {
        // 1.
//        test1();

        test2();
    }

    private static void test1() {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        // Queue full
//        System.out.println(blockingQueue.add("d"));
        System.out.println(blockingQueue.remove("a"));
        System.out.println(blockingQueue.remove("b"));
        System.out.println(blockingQueue.remove("c"));

        //
        System.out.println(blockingQueue.remove(""));
    }


    private static void test2() {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

}
