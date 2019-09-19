package com.tap2up.utils;

/**
 * Created with IntelliJ IDEA.
 * User: 张雪彬
 * Date: 2019/09/12 14:24
 * Description:
 * Version: V1.0
 */
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

// 加密
public class EncryptUtils {

    // 加密
    public static String Encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

        return new Base64().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    // 解密
    public static String Decrypt(String sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = new Base64().decode(sSrc);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        /*
         * 此处使用AES-128-ECB加密模式，key需要为16位。
         */
        String cKey = "fbe47880b9171706";
        // 需要加密的字串
        String cSrc = "{\"account\":\"3309\", \"deviceSn\":\"1028006153360776\", \"passWord\":\"121314\", \"timestamp\":1557817725858}";
        System.out.println(cSrc);
        // 加密
        String enString = EncryptUtils.Encrypt(cSrc,cKey);
        System.out.println("加密后的字串是：" + enString);

        // 解密
        String str = "MNtmGhTsvX1SOKYKBGvIWcfpBeAdU5AnOAVU8tmd1jebPg3hUYSKBPJd+biXalTngnDAVWfGQOOFRjTVWwT4SUG0LzfVUoE1yrwy55Q6zHLBg8fHSthMSRYbyE3sE/pFSkRADVBpxexxr9UcHWCLYg==";
        String DeString = EncryptUtils.Decrypt(str, cKey);
        System.out.println("解密后的字串是：" + DeString);
    }
}