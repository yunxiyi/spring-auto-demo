package com.example.demo;

import java.net.URLEncoder;

/**
 * @author huangrongchao on 2018/5/3.
 * @version 1.0
 */
public class DownloadFile {
    public static void main(String[] args) throws Exception {
        String fileName = "/Fopw9jj2qcAv4-7N8LaSFL4yrtHs";
        String domainOfBucket = "http://test.com.1234.cn/";
        String encodedFileName = URLEncoder.encode(fileName, "utf-8");
        String finalUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
        System.out.println(finalUrl);

        //http://io-qos.qiniu.beta/api/bucket/5ae45113164546531f5cb866/download?key=Fri-Ggc80SAU_ZqYtY2qHLT68Ut8&mimeType=text/plain
        //Fopw9jj2qcAv4-7N8LaSFL4yrtHs
    }
}
