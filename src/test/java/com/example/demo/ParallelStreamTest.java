package com.example.demo;

import java.util.stream.LongStream;

/**
 * @author huangrongchao on 2018/4/30.
 * @version 1.0
 */
public class ParallelStreamTest {

    public static void main(String[] args) {
        long reduce = LongStream.range(1L, 10_000_000L).parallel().reduce(0L, (acc, value) -> {
            acc += value;
            return acc;
        });
        System.out.println(reduce);
    }

    public static class Accumulator {
        private static long total;

        public static void add(long value) {
            total += value;
        }
    }
}
