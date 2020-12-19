package com.damon.java.thread;

import java.util.concurrent.*;

public class TestCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": I am learning " + i);
        }
        return "";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callable callable = new TestCallable();

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Future<String> f1 = executorService.submit(callable);
        Future<String> f2 = executorService.submit(callable);
        Future<String> f3 = executorService.submit(callable);

        String result1 = f1.get();
        String result2 = f2.get();
        String result3 = f3.get();

        executorService.shutdown();
    }
}
