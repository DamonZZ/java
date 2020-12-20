package com.damon.java.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    public static void main(String[] args) {

        final Data data = new Data();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) data.increment();
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) data.increment();
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) data.decreasement();
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) data.decreasement();
        }, "D").start();
    }

}

class Data {

    private int number = 0;

    private Lock lock = new ReentrantLock();

    private Condition condition = this.lock.newCondition();

    public void increment() {

        try {
            this.lock.lock();
            while (this.number != 0) {
                this.condition.await();
            }
            this.number++;
            System.out.println(Thread.currentThread().getName() + " increase " + this.number);
            this.condition.signalAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            this.lock.unlock();
        }
    }

    public void decreasement() {
        try {
            this.lock.lock();
            while (this.number == 0) {
                this.condition.await();
            }
            this.number--;
            System.out.println(Thread.currentThread().getName() + " decrease " + this.number);
            this.condition.signalAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            this.lock.unlock();
        }

    }
}
