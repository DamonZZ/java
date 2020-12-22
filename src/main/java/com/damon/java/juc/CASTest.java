package com.damon.java.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class CASTest {

    public static void main(String[] args) {

        // ABA
        AtomicInteger atomicInteger = new AtomicInteger(2000);

        atomicInteger.compareAndSet(2000, 2001);
        System.out.println(atomicInteger.get());

        atomicInteger.compareAndSet(2001, 2000);
        System.out.println(atomicInteger.get());

        atomicInteger.compareAndSet(2000, 2002);
        System.out.println(atomicInteger.get());


        //
        System.out.println("-----");
        AtomicStampedReference<Integer> atomicReference = new AtomicStampedReference<Integer>(1, 1);
        new Thread(() -> {
            System.out.println("a1=>" + atomicReference.getStamp());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicReference.compareAndSet(1, 2, atomicReference.getStamp(), atomicReference.getStamp() + 1);
            System.out.println("a2=>" + atomicReference.getStamp());

            atomicReference.compareAndSet(2, 1, atomicReference.getStamp(), atomicReference.getStamp() + 1);
            System.out.println("a3=>" + atomicReference.getStamp());
        }, "a").start();

        new Thread(() -> {
            int stamp = atomicReference.getStamp();
            atomicReference.compareAndSet(1, 3, stamp, stamp + 1);
            System.out.println("b1=>" + atomicReference.getStamp());
        }, "b").start();
    }
}
