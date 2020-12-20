package com.damon.java.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 6; i++) {
            String temp = i + "";
            new Thread(() -> myCache.put(temp, UUID.randomUUID().toString().substring(0, 5))).start();
        }

        for (int i = 0; i < 6; i++) {
            String temp = i + "";
            new Thread(() -> myCache.get(temp)).start();
        }
    }


}


class MyCache {

    private volatile Map<String, String> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, String value) {
        try {
            this.readWriteLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + " start write " + key);
            this.map.put(key, value);
            System.out.println(Thread.currentThread().getName() + " end write " + key);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            this.readWriteLock.writeLock().unlock();
        }

    }

    public String get(String key) {
        String result = "";
        try {
            this.readWriteLock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + " start read " + key);
            result = this.map.get(key);
            System.out.println(Thread.currentThread().getName() + " end read " + key);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            this.readWriteLock.readLock().unlock();
            return result;
        }

    }

}