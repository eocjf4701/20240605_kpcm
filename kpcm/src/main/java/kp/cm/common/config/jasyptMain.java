package kp.cm.common.config;

import java.security.SecureRandom;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;

public class jasyptMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //SpringApplication.run(KpcmApplication.class, args);
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setProvider(new BouncyCastleProvider());
        encryptor.setPoolSize(2);
        String rdmKey = getRandomPassword(20);
        encryptor.setPassword(rdmKey); // 암호화 키
        encryptor.setAlgorithm("PBEWithSHA256And128BitAES-CBC-BC");
        System.out.println("key:" + rdmKey);
        System.out.println("jdbc:" + encryptor.encrypt("jdbc:log4jdbc:oracle:thin:@122.34.228.67:32771/xe?serverTimezone=UTC&characterEncoding=UTF8"));
        System.out.println("user: "+ encryptor.encrypt("com"));
        System.out.println("pass: "+ encryptor.encrypt("com123"));
    }

    private static final char[] rndAllCharacters = new char[]{
            //number
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            //uppercase
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            //lowercase
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            //special symbols
            '@', '$', '!', '%', '*', '?', '&'
    };

    public static String getRandomPassword(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();

        int rndAllCharactersLength = rndAllCharacters.length;
        for (int i = 0; i < length; i++) {
            stringBuilder.append(rndAllCharacters[random.nextInt(rndAllCharactersLength)]);
        }

        return stringBuilder.toString();
    }

}
