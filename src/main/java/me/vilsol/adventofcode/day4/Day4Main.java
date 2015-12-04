package me.vilsol.adventofcode.day4;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day4Main {

    private static final String input = "bgvyzdsv";

    public static void main(String[] args) {
        int max = 1000000000;

        boolean foundFive = false;
        for (int i = 0; i <= max; i++) {
            String md5Hash = getMD5Hash(input + i);

            if(!foundFive && md5Hash.startsWith("00000")){
                System.out.println("Lowest number with five 0s: " + i);
                foundFive = true;
            }

            if(md5Hash.startsWith("000000")){
                System.out.println("Lowest number with six 0s: " + i);
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
