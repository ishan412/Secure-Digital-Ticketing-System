package com.example.securedigitalticketingsystemmain.core.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.tomcat.util.codec.binary.Base64;

public class AesUtil {

    private static final String CHARSET = "UTF-8";
    private static final String AES_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String IV_PARAMETER = "0102030405060708"; // 偏移量固定为这个值
    private static final String SECRET_KEY = "Ticket@Team15";

    public static String decrypt(String encryptedData) {
        try {
            byte[] encryptedDataBytes = Base64.decodeBase64(encryptedData.getBytes(CHARSET));
            byte[] secretKeyBytes = SECRET_KEY.getBytes(CHARSET);
            byte[] ivParameterBytes = IV_PARAMETER.getBytes(CHARSET);
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(secretKeyBytes, "AES"), new IvParameterSpec(ivParameterBytes));
            byte[] decryptedDataBytes = cipher.doFinal(encryptedDataBytes);
            return new String(decryptedDataBytes, CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
