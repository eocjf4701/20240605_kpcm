package com.example.Jasypt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;



public class JaysptMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String url = "jdbc:log4jdbc:oracle:thin:@122.34.228.67:32771/xe?serverTimezone=UTC&characterEncoding=UTF8";
        String username = "com";
        String password = "com123";
        String mailid = "sample@sample.co.kr";
        String mailpwd = "sample";

        System.out.println(jasyptEnc(url));
        System.out.println(jasyptEnc(username));
        System.out.println(jasyptEnc(password));
        System.out.println(jasyptEnc(mailid));
        System.out.println(jasyptEnc(mailpwd));
    }

    public static String jasyptEnc(String VALUE) {
        String key = "komsco@20241!";

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        config.setPassword(key);
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations(1000);
        config.setPoolSize(1);
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");

        encryptor.setConfig(config);
        return encryptor.encrypt(VALUE);
    }

    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();

        encryptor.setProvider(new BouncyCastleProvider());
        encryptor.setPoolSize(2); // 암호화 요청의 pool 크기. 2를 권장
        encryptor.setPassword("komsco@20241!"); // 암호화 키
        encryptor.setAlgorithm("PBEWithSHA256And128BitAES-CBC-BC"); // 사용 알고리즘

        return encryptor;
    }

}
