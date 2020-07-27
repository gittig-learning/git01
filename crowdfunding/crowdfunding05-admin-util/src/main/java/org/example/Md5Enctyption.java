package org.example;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Enctyption {
    /**
     *对明文进行md5加密
     * @param source 加密的明文
     * @return 加密结果
     */
    public static String md5(String source){
        if (source==null||source.length()==0){
            throw new RuntimeException("字符串不能为空");
        }
        // 获得messageDigest加密对象
        String algorithm="md5";
        try {
            MessageDigest messageDigest=MessageDigest.getInstance(algorithm);
            // 获取明文字符串对应的字节数组
            byte[] input=source.getBytes();
            // 执行加密
            byte[] output=messageDigest.digest(input);
            //将byte数组转换16进制字符串
            BigInteger bigInteger=new BigInteger(1,output);
            int radix=16;
            String encoded=bigInteger.toString(radix).toUpperCase();
            return encoded;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
