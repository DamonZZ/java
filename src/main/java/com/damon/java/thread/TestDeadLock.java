package com.damon.java.thread;

public class TestDeadLock implements Runnable {

    private static Obj1 obj1 = new Obj1();
    private static Obj2 obj2 = new Obj2();

    private int choice = 0;
    private String user = "";

    public TestDeadLock(int choice, String user) {
        this.choice = choice;
        this.user = user;
    }


    @Override
    public void run() {
        try {
            this.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void get() throws InterruptedException {
        if (choice == 0) {
            synchronized (obj1) {
                System.out.println(this.user + " get obj1 ...");
                Thread.sleep(1000);
                synchronized (obj2) {
                    System.out.println(this.user + " get obj2 ...");
                }
            }
        } else {
            synchronized (obj2) {
                System.out.println(this.user + " get obj2 ...");
                Thread.sleep(1000);
                synchronized (obj1) {
                    System.out.println(this.user + " get obj1 ...");
                }
            }
        }
    }

    public static void main(String[] args) {
        Runnable runnable1 = new TestDeadLock(0, "user1");
        Runnable runnable2 = new TestDeadLock(1, "user2");
        new Thread(runnable1).start();
        new Thread(runnable2).start();
    }
}


class Obj1 {

}

class Obj2 {

}