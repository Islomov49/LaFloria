package com.iso.developer.lafloria.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by developer on 21.11.2016.
 */

public class PhoneUtills {
    public static final int UZB = 0;
    private static final String PHONE_FORMAT [] = {
            /*0-UZB*/ "^([+]?)(998[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]|[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]|[0-9][0-9][0-9][0-9][0-9][0-9][0-9])$"
    };
    public static boolean isCorrectPhoneFormat(String phoneNumber,int COUNTRY){
        Pattern pattern = Pattern.compile(PHONE_FORMAT[0]);
        Matcher matcher= pattern.matcher(phoneNumber);
        if(matcher.matches()){
            return true;
        }
        else
        return false;
    }
}
