package com.zavier.systemdesign.pastebin.util;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class Md5Utils {

    public static byte[] md5(String data) {
        return md5(data.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] md5(byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            return md.digest(data);
        } catch (NoSuchAlgorithmException e) {
            log.error("md5异常", e);
            throw new RuntimeException("md5异常:" + e.getMessage());
        }
    }
}
