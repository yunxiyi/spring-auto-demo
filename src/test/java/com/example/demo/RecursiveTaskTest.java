package com.example.demo;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @author huangrongchao on 2018/4/30.
 * @version 1.0
 */
public class RecursiveTaskTest {

    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1, 10_000_000L).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        Long invoke = new ForkJoinPool().invoke(task);
        System.out.println(invoke);

        int a = 10;
        Runnable run = () -> {
            System.out.println(a);

        };

        run.run();
    }

    public static class ForkJoinSumCalculator extends RecursiveTask<Long> {
        private final long[] numbers;
        private final int start;
        private final int end;

        public static final long THRESHOLD = 10_000;

        public ForkJoinSumCalculator(long[] numbers) {
            this(numbers, 0, numbers.length);
        }

        public ForkJoinSumCalculator(long[] numbers, int start, int end) {
            this.numbers = numbers;
            this.start = start;
            this.end = end;
        }


        @Override
        public Long compute() {

            int length = end - start;
            if (length <= THRESHOLD)
                return computeSequentially();


            ForkJoinSumCalculator leftFork = new ForkJoinSumCalculator(numbers, start, start + length / 2);
            leftFork.fork();

            ForkJoinSumCalculator rightFork = new ForkJoinSumCalculator(numbers, start + length / 2, end);
            Long rightResult = rightFork.compute();
            Long leftResult = leftFork.join();

            long toal = rightResult + leftResult;
            System.out.println(Thread.currentThread().getName()
                    + " start = " + start
                    + " end = " + end
                    + " total = " + toal);
            return toal;
        }

        private long computeSequentially() {
            long sum = 0;
            for (int i = start; i < end; i++)
                sum += numbers[i];

            return sum;
        }
    }
}
