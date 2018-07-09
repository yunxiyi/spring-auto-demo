package com.example.demo;

/**
 * @author huangrongchao on 2018/5/1.
 * @version 1.0
 */
public class RunnableTest {
    int b = 20;
    public void run() {
        int a = 10;
        Runnable executor = () -> {
            System.out.println("hello world");
            this.b = 30;
            System.out.println(a);
        };
    }
}
