package ui.input;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This class used to interaction with user by scanner.
 * This class created ass singleton, and contains static method for
 * user input, warp answers and close scanner.
 * This service class for Button class.
 * */
public class InputScanner {

    private static InputScanner instance = InputScanner.getInstance();

    private final Scanner scanner;

    public InputScanner(){
        scanner = new Scanner(System.in);
    }

    public static InputScanner getInstance(){
        if (instance == null){
            instance = new InputScanner();
        }
        return instance;
    }

    public static String processInput(String patternSample, String wrongInput){
        String input = "";
        if (instance.scanner.hasNext()){
            input = instance.scanner.next();
        }
        if (!isProperInput(input, patternSample)){
            System.out.println(warpMsg(wrongInput));
            input = "";
        }

        return input;
    }

    public static String processInput(){
        String input = "";
        if (instance.scanner.hasNext()){
            input = instance.scanner.next();
        }
        return input;
    }

    public static void closeScanner(){
        instance.scanner.close();
    }

    public static Boolean isProperInput(String str, String patternSample){
        Pattern pattern = Pattern.compile(patternSample);
        return pattern.matcher(str).matches();
    }

    public static String warpMsg(String msg){
        StringBuilder warpMsg = new StringBuilder();

        for (int i=1; i <=3; i++ ) {
            warpMsg.append(buildString(msg, i));
        }

        return warpMsg.toString();
    }

    private static String buildString(String msg, Integer option){
        Character start = null;
        Character end = null;
        Character position = null;
        String nextString = "";

        switch (option) {
            case 1 -> {
                start = '┌';
                end = '┐';
                position = '─';
                nextString = "\n";
            }
            case 2 -> {
                start = '│';
                end = '│';
                nextString = "\n";
            }
            case 3 -> {
                start = '└';
                end = '┘';
                position = '─';
            }
        }
        var length = msg.length() + 2;

        StringBuilder builder = new StringBuilder();
        for (int i = 1; length >= i; i++){
            if(i == 1){
                builder.append(start);
            }
            else if (i == length){
                if (option == 2)
                    builder.append(msg).append(end);
                else
                    builder.append(end);
            }
            else
            {
                if (option != 2){
                    builder.append(position);
                }
            }
        }
        return builder + nextString;
    }

}
