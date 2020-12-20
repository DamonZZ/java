package com.damon.java.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callable callable = new MyThread();
        FutureTask<String> futureTask = new FutureTask(callable);
        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();
        String result = futureTask.get();
        System.out.println("result: " + result);
    }

}

class MyThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("call");
        return "1234";
    }
}
