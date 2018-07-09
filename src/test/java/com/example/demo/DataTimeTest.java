package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author huangrongchao on 2018/5/1.
 * @version 1.0
 */
public class DataTimeTest {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.parse("2018-10-10");
        System.out.println(localDateTime);
    }
}
