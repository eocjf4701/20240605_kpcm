package kp.cm.common.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESFunction {

    public static String byte2hex (byte []b) {
        String hs ="";
        String stmp ="";
        for (int n =0 ;n <b.length ;n ++) {
            stmp =(java.lang.Integer.toHexString (b [n ]&0XFF ));
            if (stmp.length ()==1 ) {
                hs =hs +"0"+stmp ;
            } else {
                hs =hs +stmp ;
            }
            if (n <b.length -1 ) {
                hs = hs;
            }
        }
        return hs;
    }

    public static Key makeAESKey(String sKey) {
        final byte[] key = sKey.getBytes();
        return new SecretKeySpec(key, "AES");
    }

    public static byte[] aesEncode(byte[] src, Key skey) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            return cipher.doFinal(src);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] aesDecode(byte[] src, Key skey) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, skey);
            return cipher.doFinal(src);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] hexToByteArray(String hex) {
        if (hex == null || hex.length() == 0) {
            return null;
        }

        byte[] ba = new byte[hex.length() / 2];
        for (int i = 0; i < ba.length; i++) {
            ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return ba;
    }

    /**
    암호화하려는 문자열 데이터의 길이가 16배수가 아닐 경우 '&&' 구분자후에 '0'으로 채움.
    */
    public static String padding16(String strValue) {

        int iNum1 = strValue.length();
        int iNum2 = iNum1 % 16;

        if(iNum2 != 0){
            strValue = strValue + "&&";
            int iNum3 = 14 - iNum2;
            for(int i=0; i<iNum3;i++){
                strValue = strValue + "0";
            }
        }

        return strValue;
    }
}
