package com.example.demo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author huangrongchao on 2018/4/28.
 * @version 1.0
 */
public class DonwloadTest {
    public static void main(String[] args) {
        String fileName = "/test/Fopw9jj2qcAv4-7N8LaSFL4yrtHs";
        String domainOfBucket = "http://puhui-rik";
        String encodedFileName = null;
        try {
            encodedFileName = URLEncoder.encode(fileName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String finalUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
        System.out.println(finalUrl);
    }
}
