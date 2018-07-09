package com.example.demo;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

/**
 * @author huangrongchao on 2018/4/28.
 * @version 1.0
 */
public class QiniuTest {
    public static void main(String[] args) {
        Zone.Builder jstv = new Zone.Builder()
                .upHttp("http://up-qos.qiniu.beta")
                .upBackupHttp("http://up-qos.qiniu.beta")
                .iovipHttp("http://io-qos.qiniu.beta");


        Configuration cfg = new Configuration(jstv.build());

        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "GAdjD6WE2-COKkiln_fObw5cpO0lEFzJ4b8oLTGg";
        String secretKey = "rEK71fsVrMivcxd__6awKnmRmShar_y8IuPdYXnl";
        String bucket = "test1";
        String key = "file.txt";
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "./file.txt";

        try {
            Auth auth = Auth.create(accessKey, secretKey);

            String upToken = auth.uploadToken(bucket);
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);

        } catch (QiniuException ex) {
            ex.printStackTrace();
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }
}
