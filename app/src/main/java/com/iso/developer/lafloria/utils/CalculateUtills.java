package com.iso.developer.lafloria.utils;

/**
 * Created by developer on 21.11.2016.
 */

public class CalculateUtills {
    public static String ammountWithProbels(String ammount){
        String newAmmount = "";
        int lenght = ammount.length();
        int count =  0;
        for(int i = lenght-1; i>=0;i--){
            count++;
             newAmmount = newAmmount + ammount.charAt(i);
            if(count%3==0){
                newAmmount = newAmmount +" ";
                count = 0;
            }
        }
         String new2 = "";
        for(int i = newAmmount.length()-1; i>=0;i--){
            new2 = new2 + newAmmount.charAt(i);
        }

        return new2;
    }

}
