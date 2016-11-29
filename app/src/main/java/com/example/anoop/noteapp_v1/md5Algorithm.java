package com.example.anoop.noteapp_v1;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by anoop on 22/11/2016.
 */

public class md5Algorithm {

    public md5Algorithm(){}

    public static String md5Algorithm(String password) throws NoSuchAlgorithmException{
        String result = password;
        if(password != null){
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            BigInteger hash = new BigInteger(1, md.digest());
            result = hash.toString(16);
            while(result.length()<32){
                result="0"+result;
            }
        }
        return result;
    }
}
