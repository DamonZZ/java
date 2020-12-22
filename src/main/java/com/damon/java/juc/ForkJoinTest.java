package com.damon.java.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * ForkJoin, Stream
 */
public class ForkJoinTest extends RecursiveTask<Long> {

    private Long start = 0L;
    private Long end = 1000L;
    private Long temp = 100000000000L;

    public ForkJoinTest(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1.
        long sum = 0;
        long start = System.currentTimeMillis();
        for (long i = 0; i <= 10_0000_0000; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("1. sum: " + sum + ", time: " + (end - start));

        // 2.
        start = System.currentTimeMillis();
        sum = 0L;
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinTest(0L, 10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);
        sum = submit.get();
        end = System.currentTimeMillis();
        System.out.println("2. sum: " + sum + ", time: " + (end - start));

        // 3.
        sum = 0L;
        start = System.currentTimeMillis();
        sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(0, Long::sum);
        end = System.currentTimeMillis();
        System.out.println("3. sum: " + sum + ", time: " + (end - start));

    }

    @Override
    protected Long compute() {
        if ((this.end - this.start) < this.temp) {
            Long sum = 0L;
            for (long i = this.start; i <= this.end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (this.start + this.end) / 2;
            ForkJoinTest task1 = new ForkJoinTest(this.start, middle);
            task1.fork();
            ForkJoinTest task2 = new ForkJoinTest(middle, this.end);
            task2.fork();
            long sum = task1.join() + task2.join();
            return sum;
        }
    }
}
