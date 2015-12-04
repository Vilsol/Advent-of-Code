package me.vilsol.adventofcode.day4;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day4Main {

    private static final String input = "bgvyzdsv";

    public static void main(String[] args) {
        int max = 1000000000;

        for (int i = 0; i <= max; i++) {
            if(getMD5Hash(input + i).startsWith("000000")){
                System.out.println("Lowest number: " + i);
                break;
            }
        }
    }

    private static String getMD5Hash(String input){
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(input.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1,digest);
            String hashtext = bigInt.toString(16);

            while(hashtext.length() < 32 ){
                hashtext = "0"+hashtext;
            }

            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }

}
