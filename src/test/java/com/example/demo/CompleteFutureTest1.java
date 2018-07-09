package com.example.demo;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @author huangrongchao on 2018/5/1.
 * @version 1.0
 */
public class CompleteFutureTest1 {
    public static Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            double price = 0;
            try {
                price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception e) {
                futurePrice.complete(0D);
            }

        }).start();
        return futurePrice;
    }

    private static double calculatePrice(String product) {
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void doSomethingElse() {
        delay();
        delay();
    }

    public static void main(String[] args) throws IOException {
        long start = System.nanoTime();
        Future<Double> futurePrice = getPriceAsync("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime
                + " msecs");
        doSomethingElse();
        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");


        invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime
                + " msecs");
        List<String> strings = find("macboook");
        System.out.println(strings);
        retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");

        CompletableFuture<Double> show = CompletableFuture.supplyAsync(() -> getPrice("show"));
        show.join();
    }

    public static List<String> find(String product) {
        List<String> shops = Arrays.asList("test", "apple", "iphone", "macbook");
        List<CompletableFuture<String>> collect = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop + " price is " + getPrice(shop)))
                .collect(Collectors.toList());
        return collect.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

    }

    private static double getPrice(String shop) {
        return calculatePrice(shop);
    }

}
