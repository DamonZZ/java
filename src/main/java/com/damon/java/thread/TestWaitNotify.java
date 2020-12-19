package com.damon.java.thread;

public class TestWaitNotify {

    public static void main(String[] args) {
        SyncContainer syncContainer = new SyncContainer();
        new Producer(syncContainer).start();
        new Consumer(syncContainer).start();
    }


}


class Producer extends Thread {

    SyncContainer syncContainer;

    public Producer(SyncContainer syncContainer) {
        this.syncContainer = syncContainer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Produced chicken: " + i);
            this.syncContainer.push(new Chicken(i));
        }
    }

}

class Consumer extends Thread {

    SyncContainer syncContainer;

    public Consumer(SyncContainer syncContainer) {
        this.syncContainer = syncContainer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Consumed chicken: " + this.syncContainer.pop().id);
        }
    }

}

class Chicken {
    int id;

    public Chicken(int id) {
        this.id = id;
    }
}

class SyncContainer {
    Chicken[] chickens = new Chicken[10];

    int count = 0;

    public synchronized void push(Chicken chicken) {
        if (count == this.chickens.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.chickens[this.count] = chicken;
        this.count++;
        this.notifyAll();
    }

    public synchronized Chicken pop() {
        if (this.count == 0) {
            try {
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.count--;
        Chicken chicken = this.chickens[this.count];
        this.notifyAll();
        return chicken;
    }

}