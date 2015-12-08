package me.vilsol.adventofcode.day8;

import me.vilsol.adventofcode.day6.Day6Main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day8Main {

    private static String[] input;

    public static void main(String[] args) throws IOException {
        input = new String(Files.readAllBytes(Paths.get(Day6Main.class.getResource("/day8input.txt").getPath()))).split("\n");

        partOne();
        partTwo();
    }

    private static void partOne() {
        int diff = 0;
        for(String s : input){
            int start = s.length();
            diff += start - decode(s).length();
        }
        System.out.println("Difference for first part is " + diff);
    }

    private static void partTwo() {
        int diff = 0;

        for(String s : input){
            int start = s.length();
            diff += encode(s).length() - start;
        }
        System.out.println("Difference for second part is " + diff);
    }

    private static String decode(String s){
        return s.substring(1, s.length() - 1).replace("\\\"", ".").replaceAll("\\\\x[a-f0-9]{2}", ".").replace("\\\\", "\\");
    }

    private static String encode(String s){
        return "\"" + s.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
    }

}
