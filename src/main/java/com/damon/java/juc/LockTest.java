package com.damon.java.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    public static void main(String[] args) {

        Phone phone = new Phone();

        new Thread(() -> {
            phone.sms();
        }, "a").start();

        new Thread(() -> {
            phone.sms();
        }, "b").start();


    }

}


class Phone {

//    public synchronized void sms() {
//        System.out.println(Thread.currentThread().getName() + " sms");
//        call(); //*
//    }
//
//    public synchronized void call() {
//        System.out.println(Thread.currentThread().getName() + " call");
//    }

    Lock lock = new ReentrantLock();

    public void sms() {
        try {
            this.lock.lock();
            System.out.println(Thread.currentThread().getName() + " sms");
            call(); //*
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            this.lock.unlock();
        }
    }

    public void call() {
        try {
            this.lock.lock();
            System.out.println(Thread.currentThread().getName() + " call");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            this.lock.unlock();
        }
    }

}