package com.example.demo;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author huangrongchao on 2018/4/30.
 * @version 1.0
 */
public class CompleteFutureTest {
    static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Void> futureA = CompletableFuture.runAsync(() -> work("A"));
        CompletableFuture<Void> futureB = CompletableFuture.runAsync(() -> work("B"));
        CompletableFuture<Void> futureC = futureA.runAfterEither(futureB, () -> work("C"));

        while (!(futureA.isDone() && futureB.isDone() && futureC.isDone())){

        }
    }

    public static Void work(String name) {
        System.out.println(name + " starts at " + LocalTime.now());
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " ends at " + LocalTime.now());
        return null;
    }
}
