package com.tap2up.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author: Han
 * @Date: 2019/9/20 13:38
 * @Description: 签名生成工具
 */
public class SignUtil {

    /**
     * 生成签名
     * @param key 登录后返回的key
     * @param paramsMap 参数map
     * @return sign
     */
    public static String getSign(String key, Map<String, Object> paramsMap){
        //将map放入TreeMap中以便参数自动按字典排序
        TreeMap<String, Object> treeMap =new TreeMap<>(paramsMap);
        StringBuilder stringBuilder = new StringBuilder();

        //用&拼接字符串 例：deviceSn=1028006153360776&timestamp=1557976916084&token=6Q1eu7iTAmHgCU
        for (Map.Entry<String, Object> entry : treeMap.entrySet()){
            stringBuilder.append(entry.getKey());
            stringBuilder.append("=");
            stringBuilder.append(entry.getValue());
            stringBuilder.append("&");
        }
        //去掉最后一个&
        stringBuilder.setLength(stringBuilder.length()-1);
        //用#拼接上key， KEY：登陆成功后返回
        stringBuilder.append("#");
        stringBuilder.append(key);

        //将StringBuilder转换成String并用md5加密
        String str = stringBuilder.toString();
        String md5 = stringToMD5(str);

        return md5;
    }

    public static String stringToMD5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("timestamp", "1557996921861");
        map.put("token","6Q1eu7iTAmHgCU7DkiHb2A==");
        map.put("deviceSn", "1028006153360776");

        String sign = getSign("0333f893b077426482e678ccaf9eab2e",map);
        System.out.println(sign);
    }
}
