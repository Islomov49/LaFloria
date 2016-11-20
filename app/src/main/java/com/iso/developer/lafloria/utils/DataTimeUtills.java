package com.iso.developer.lafloria.utils;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by developer on 21.11.2016.
 */

public class DataTimeUtills {
    private static final String TIME_HHMM =
            "^([0-9]|[0-9][0-9]):[0-5][0-9]$";
    private static final String TIME_HHMM_ONEDAY=
            "^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";


    public static long isItCorrectTimeHHMM (String HHMM){
        Pattern pattern = Pattern.compile(TIME_HHMM);
        Matcher matcher= pattern.matcher(HHMM);
        long time=-1;
        if(!matcher.matches()){
            return -1;
        }
        int z = 0;
           try {
                for (String retval : HHMM.split(":")) {
                    if(z==0){
                        z = 1;
                        time = 0;
                        time += Long.parseLong(retval)*1000l*60*60;
                        continue;
                    }
                    else if(z==1){
                        z = 2;
                        time += Long.parseLong(retval)*1000l*60;
                        continue;

                    }
                    else
                    z = 3;
                }
            }
            catch (Exception e){
                return -1;
            }
        if(z == 3){
            return  -1;
        }
        else
        return time;
    }
}
