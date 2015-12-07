package me.vilsol.adventofcode.day7;

import me.vilsol.adventofcode.day6.Day6Main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7Main {

    private static String[] input = null;
    private static final Pattern pattern = Pattern.compile("([a-z0-9]+)?\\s?(AND|LSHIFT|RSHIFT|OR|NOT)?\\s?([a-z0-9]+)?\\s->\\s([a-z]+)");

    public static void main(String[] args) throws IOException {
        input = new String(Files.readAllBytes(Paths.get(Day6Main.class.getResource("/day7input.txt").getPath()))).split("\n");
        //16076
        partOne();
        partTwo();
    }

    private static void partOne() {
        HashMap<String, Calculation> connections = new HashMap<>();

        for(String s : input){
            Matcher m = pattern.matcher(s);
            if(m.find()){
                String base = m.group(1);
                String operator = m.group(2);
                String modifier = m.group(3);
                String name = m.group(4);

                connections.put(name, new Calculation(new Connection(base, operator, modifier)));
            }
        }

        System.out.println("Output of part one: " + connections.get("a").getValue(connections));
    }

    private static void partTwo() {
        HashMap<String, Calculation> connections = new HashMap<>();

        for(String s : input){
            Matcher m = pattern.matcher(s);
            if(m.find()){
                String base = m.group(1);
                String operator = m.group(2);
                String modifier = m.group(3);
                String name = m.group(4);

                connections.put(name, new Calculation(new Connection(base, operator, modifier)));
            }
        }

        connections.put("b", new Calculation(new Connection("16076", null, null)));

        System.out.println("Output of part two: " + connections.get("a").getValue(connections));
    }

    private static class Connection {

        private String base;
        private String operator;
        private String modifier;

        public Connection(String base, String operator, String modifier) {
            this.base = base;
            this.operator = operator;
            this.modifier = modifier;
        }

        public int getValue(HashMap<String, Calculation> connections) {
            try {
                int baseValue = connections.containsKey(base) ? connections.get(base).getValue(connections) : (isInteger(base) ? Integer.parseInt(base) : 0);
                int modifierValue = connections.containsKey(modifier) ? connections.get(modifier).getValue(connections) : (isInteger(modifier) ? Integer.parseInt(modifier) : 0);

                if(operator == null){
                    if(base == null){
                        return modifierValue;
                    }

                    return baseValue;
                }

                switch(operator){
                    case "OR":
                        return baseValue | modifierValue;
                    case "NOT":
                        if(base == null){
                            return +~ modifierValue;
                        }else if(modifier == null){
                            return +~ baseValue;
                        }

                        return baseValue +~ modifierValue;
                    case "AND":
                        return baseValue & modifierValue;
                    case "RSHIFT":
                        return baseValue >> modifierValue;
                    case "LSHIFT":
                        return baseValue << modifierValue;
                }
            } catch (NumberFormatException e){
                System.out.println(base + " or " + modifier + " is not a number!");
            }

            return 0;
        }

    }

    private static class Calculation {

        private Connection connection;
        boolean calculated = false;
        private int result;

        public Calculation(Connection connection) {
            this.connection = connection;
        }

        public int getValue(HashMap<String, Calculation> connections){
            if(calculated){
                return result;
            }

            result = connection.getValue(connections);
            calculated = true;

            return result;
        }
    }

    private static boolean isInteger(String s){
        try{
            Integer.parseInt(s);
            return true;
        }catch (Exception ignore){
        }
        return false;
    }

}
