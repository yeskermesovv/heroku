package com.example.heroku.encoder;

import com.example.heroku.util.AesEncryptDecrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncoderServiceImpl implements EncoderService {

    private final AesEncryptDecrypt aesEncryptDecrypt;

    @Autowired
    public EncoderServiceImpl(AesEncryptDecrypt aesEncryptDecrypt) {
        this.aesEncryptDecrypt = aesEncryptDecrypt;
    }

    @Override
    public String encode(String s) {
        return aesEncryptDecrypt.encrypt(s);
    }
}
