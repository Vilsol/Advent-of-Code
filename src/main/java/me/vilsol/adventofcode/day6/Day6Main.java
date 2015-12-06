package me.vilsol.adventofcode.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day6Main {

    private static String[] input = null;

    public static void main(String[] args) throws IOException {
        input = new String(Files.readAllBytes(Paths.get(Day6Main.class.getResource("/day6input.txt").getPath()))).split("\n");

        partOne();
        partTwo();
    }

    private static void partOne() {
        boolean[][] lights = new boolean[1000][1000];

        for(String s : input){
            Pattern pattern = Pattern.compile("(.*?)\\s([0-9]+),([0-9]+)\\sthrough\\s([0-9]+),([0-9]+)");
            Matcher m = pattern.matcher(s);
            if(m.find()){
                int minX = Integer.parseInt(m.group(2));
                int minY = Integer.parseInt(m.group(3));
                int maxX = Integer.parseInt(m.group(4));
                int maxY = Integer.parseInt(m.group(5));

                switch(m.group(1)){
                    case "turn on":
                        for (int i = minX; i <= maxX; i++) {
                            for (int j = minY; j <= maxY; j++) {
                                lights[i][j] = true;
                            }
                        }
                        break;
                    case "turn off":
                        for (int i = minX; i <= maxX; i++) {
                            for (int j = minY; j <= maxY; j++) {
                                lights[i][j] = false;
                            }
                        }
                        break;
                    case "toggle":
                        for (int i = minX; i <= maxX; i++) {
                            for (int j = minY; j <= maxY; j++) {
                                lights[i][j] = !lights[i][j];
                            }
                        }
                        break;
                }
            }
        }

        int on = 0;
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                if(lights[i][j]){
                    on++;
                }
            }
        }

        System.out.println("There would be " + on + " lights on!");
    }

    private static void partTwo() {
        int[][] lights = new int[1000][1000];

        for(String s : input){
            Pattern pattern = Pattern.compile("(.*?)\\s([0-9]+),([0-9]+)\\sthrough\\s([0-9]+),([0-9]+)");
            Matcher m = pattern.matcher(s);
            if(m.find()){
                int minX = Integer.parseInt(m.group(2));
                int minY = Integer.parseInt(m.group(3));
                int maxX = Integer.parseInt(m.group(4));
                int maxY = Integer.parseInt(m.group(5));

                switch(m.group(1)){
                    case "turn on":
                        for (int i = minX; i <= maxX; i++) {
                            for (int j = minY; j <= maxY; j++) {
                                lights[i][j]++;
                            }
                        }
                        break;
                    case "turn off":
                        for (int i = minX; i <= maxX; i++) {
                            for (int j = minY; j <= maxY; j++) {
                                lights[i][j] = Math.max(lights[i][j] - 1, 0);
                            }
                        }
                        break;
                    case "toggle":
                        for (int i = minX; i <= maxX; i++) {
                            for (int j = minY; j <= maxY; j++) {
                                lights[i][j] += 2;
                            }
                        }
                        break;
                }
            }
        }

        int brightness = 0;
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                brightness += lights[i][j];
            }
        }

        System.out.println("The overall brightness would be " + brightness + "!");
    }

}
