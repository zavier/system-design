package com.zavier.systemdesign.pastebin.service;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class QiniuService {

    private static final String ACCESS_KEY = "";
    private static final String SECRET_KEY = "";

    public void upload(byte[] body) {
        Configuration cfg = new Configuration(Region.region1());
        UploadManager uploadManager = new UploadManager(cfg);
        String bucket = "zavier";
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(body, null, upToken);
            log.info("上传七牛云结果:{}", response.bodyString());
        } catch (QiniuException ex) {
            log.error("上传失败:{}", ex.response.toString());
            throw new RuntimeException("上传异常" + ex.getMessage());
        }
    }
}
