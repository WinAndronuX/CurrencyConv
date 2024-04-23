package com.winandronux.utils;

public class Utils {

    public static boolean checkIfIsDouble(String num) {
        try {
            Double.parseDouble(num);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

}
