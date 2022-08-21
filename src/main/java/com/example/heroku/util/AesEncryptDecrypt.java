package com.example.heroku.util;


import com.google.common.base.*;
import org.apache.commons.lang3.*;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.*;
import javax.xml.datatype.*;
import java.util.*;

@Component
public class AesEncryptDecrypt {

    private static final String CONST_DEFAULT_AES_ENCRYPTION_KEY = "W36z0RDMmBfhuoc5";
    private static final String CONST_ENCRYPTION_TYPE = "AES";
    private static final String CONST_ENCRYPTION_TRANSFORMER = "AES/CBC/PKCS5Padding";
    private static final String CONST_ENCRYPTION_PREFIX = "CpHr1";

    public final String decrypt(String iin){
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(CONST_DEFAULT_AES_ENCRYPTION_KEY.getBytes(Charsets.UTF_8), CONST_ENCRYPTION_TYPE);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(CONST_DEFAULT_AES_ENCRYPTION_KEY.getBytes(Charsets.UTF_8));
            Cipher cipher = Cipher.getInstance(CONST_ENCRYPTION_TRANSFORMER);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            String ev = StringUtils.substringAfter(iin, CONST_ENCRYPTION_PREFIX);

            byte[] decodedValue = Base64.getDecoder().decode(ev);
            byte[] decryptedValue = cipher.doFinal(decodedValue);

            return new String(decryptedValue, Charsets.UTF_8);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public final String encrypt(String iin){
        if (iin == null){
            return null;
        }
        if (iin.startsWith(CONST_ENCRYPTION_PREFIX)){
            return iin;
        } else {
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(CONST_DEFAULT_AES_ENCRYPTION_KEY.getBytes(Charsets.UTF_8), CONST_ENCRYPTION_TYPE);
                IvParameterSpec ivParameterSpec = new IvParameterSpec(CONST_DEFAULT_AES_ENCRYPTION_KEY.getBytes(Charsets.UTF_8));
                Cipher cipher = Cipher.getInstance(CONST_ENCRYPTION_TRANSFORMER);
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
                byte[] encrypted = cipher.doFinal(iin.getBytes(Charsets.UTF_8));
                String ev = new String(Base64.getEncoder().encode(encrypted), Charsets.UTF_8);
                ev = CONST_ENCRYPTION_PREFIX + ev;
                return ev;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}

