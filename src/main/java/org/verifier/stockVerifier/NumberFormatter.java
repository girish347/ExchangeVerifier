package org.verifier.stockVerifier;

public class NumberFormatter {
    public static String formatQuantity(long number) {
        String numberString = String.valueOf(number);
        int digits = numberString.length();
        char value[] = new char[11];
        for (int i=0; i<=10;i++) {
            value[i] = ' ';
        }
        value[3] = ',';
        value[7] = ',';
        int index = 10;
        while(digits > 0) {
            if (value[index] == ',' ) index--;
            value[index] = numberString.charAt(digits-1);
            index--;
            digits--;
        }
        if (value[2] == ' ' && value[3] == ',') value[3] = ' ';
        if (value[6] == ' ' && value[7] == ',') value[7] = ' ';


        return new String(value);
    }
    public static String formatPrice(long number) {
        String numberString = String.valueOf(number);
        int digits = numberString.length();
        char ans[] = new char[6];
        for (int i=0; i<=5;i++) {
            ans[i] = ' ';
        }
        int index = 5;
        while(digits > 0) {
            ans[index] = numberString.charAt(digits-1);
            index--;
            digits--;
        }
        return new String(ans);
    }
}