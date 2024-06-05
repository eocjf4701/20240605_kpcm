package com.example.Jasypt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JasyptConfigTest {

    @Value("${jasypt.encryptor.password}")
    private String key;

    @Test
    public void jasypt_암호화_복호화() {
        // given
        String mysqlURL = "jdbc:mysql://내_DB_URL:3306/내_DB_schema?serverTimezone=Asia/Seoul&characterEncoding=UTF-8&allowMultiQueries=true";
        String mysqlUserName = "myId";
        String mysqlPassword = "myPassword";

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setProvider(new BouncyCastleProvider());
        encryptor.setPoolSize(2);
        encryptor.setPassword("password");
        encryptor.setAlgorithm("PBEWithSHA256And128BitAES-CBC-BC");

        // when
        String encryptedTextMysqlURL = encryptor.encrypt(mysqlURL);
        System.out.println("mysqlURL 암호화 값 : " + encryptedTextMysqlURL);

        String encryptedTextMysqlUserName = encryptor.encrypt(mysqlUserName);
        System.out.println("mysqlUserName 암호화 값 : " + encryptedTextMysqlUserName);

        String encryptedTextMysqlPassword = encryptor.encrypt(mysqlPassword);
        System.out.println("mysqlPassword 암호화 값 : " + encryptedTextMysqlPassword);

//        String decryptedTextMysqlURL = encryptor.decrypt(encryptedTextMysqlURL);
//        String decryptedTextMysqlUserName = encryptor.decrypt(encryptedTextMysqlUserName);
//        String decryptedTextMysqlPassword = encryptor.decrypt(encryptedTextMysqlPassword);

        // then
//        assertThat(mysqlURL).isEqualTo(decryptedTextMysqlURL);
//        assertThat(mysqlUserName).isEqualTo(decryptedTextMysqlUserName);
//        assertThat(mysqlPassword).isEqualTo(decryptedTextMysqlPassword);
    }
}