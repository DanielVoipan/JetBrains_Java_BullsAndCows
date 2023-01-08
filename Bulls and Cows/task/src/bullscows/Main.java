package bullscows;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static int foundBulls = 0;
    static int foundCows = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean start = false;
        int turn = 0;
        String[] out = new String[2];
        String secret = "";
        System.out.println("Please, enter the secret code's length:");
        String lengthString = scanner.next();
        // if the length isn't a number, return
        if (!lengthString.matches("\\d+")) {
            System.out.printf("Error: \"%s\" isn't a valid number.", lengthString);
            return;
        }
        System.out.println("Input the number of possible symbols in the code:");
        String symbolsString = scanner.next();
        // if the number of symbols isn't a number, return
        if (!symbolsString.matches("\\d+")) {
            System.out.printf("Error: \"%s\" isn't a valid number.", symbolsString);
            return;
        }
        int symbols = Integer.parseInt(symbolsString);
        int length = Integer.parseInt(lengthString);
        // if there are more the 36 posibile symbols, return.
        if (symbols > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        }
        if (length < 1) {
            System.out.println("Error: the length of the code must be bigger than 0.");
            return;
        }
        // if the length is bigger than number of symbols, return.
        if (symbols < length) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.\n", length, symbols);
            return;
        }
        for (int i = 0; i < length; i++) {
            secret += "*";
        }
        randomCode(length, symbols, out);
        // get the randomCode;
        String randomCode = out[0];
        // get the last letter
        String lastLetter = out[1];
        System.out.printf("The secret is prepared: %s (0-9, a-%s).\n", secret, lastLetter);
        System.out.println("Okay, let's start a game!");
        // everything is ok, start the game.
        start = true;
        while (start) {
            foundBulls = 0;
            foundCows = 0;
            String[] test = new String[0];
            String[] s = Arrays.copyOf(test, test.length);
            System.out.printf("Turn %d:\n", ++turn);
            String getNumber = scanner.next();

            boolean find = analizeCowsAndBulls(getNumber, randomCode, length);
            if (find) {
                System.out.printf("Grade: None\n");
            } else {
                if (foundBulls == length) {
                start = false;
                }
                output(length);
            }
        }
    }

    // output messages
    static void output(int length) {
            String bullsText = foundBulls == 1 ? "bull" : "bulls";
            String cowsText = foundCows == 1 ? "cow" : "cows";
            if (foundBulls == length) {
                System.out.printf("Grade: %s %s\n", foundBulls, bullsText);
                System.out.println("Congratulations! You guessed the secret code.");
            } else if (foundBulls != 0 && foundCows != 0) {
                System.out.printf("Grade: %d %s and %d %s\n", foundBulls, bullsText, foundCows, cowsText);
            } else if (foundBulls != 0) {
                System.out.printf("Grade: %d %s\n", foundBulls, bullsText);
            } else {
                System.out.printf("Grade: %d %s\n", foundCows, cowsText);
            }
    }

    static boolean analizeCowsAndBulls(String number, String randomNumber, int length) {
        StringBuilder foundPositions = new StringBuilder();
        StringBuilder notFoundDigits = new StringBuilder();
        try {
            for (int i = 0; i < length; i++) {
                if (!number.contains(String.valueOf(randomNumber.charAt(i)))) {
                    continue;
                }
                boolean b = !foundPositions.toString().contains(String.valueOf(i));
                if (number.charAt(i) == randomNumber.charAt(i) && b) {
                    foundBulls++;
                    foundPositions.append(i);
                    continue;
                }
                if (b && !notFoundDigits.toString().contains(String.valueOf(i))) {
                    foundCows++;
                    notFoundDigits.append(number.charAt(i));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return foundBulls == 0 && foundCows == 0 ? true : false;
    }

    // generate randomCode
    static void randomCode(int numberOfDigits, int symbols, String[] out) {
        StringBuilder code = new StringBuilder();
        String[] sb = new String[symbols];
        int lastPos = 0;
        char lastDigit = 0;
        // add digits to symbols list
        for (int i = 0; i < 10; i++) {
            sb[i] = String.valueOf(i);
            lastPos = i;
        }
        // add letters to symbols list
        int dif = symbols - 10;
        if (dif > 0) {
            for (int i = 97; i < 97 + dif; i++) {
                lastPos++;
                String codeToLetter = String.valueOf((char) i);
                sb[lastPos] = codeToLetter;
                lastDigit = codeToLetter.charAt(0);
            }
        }
        // getting the unique number of digits required.
        Random r = new Random();
            for (int i = 0; i < numberOfDigits; i++) {
                int randPos = r.nextInt(symbols);
                boolean stop = false;
                while (!stop) {
                    if (!code.toString().contains(String.valueOf(sb[randPos]))) {
                        code.append(sb[randPos]);
                        stop = true;
                    }
                    randPos = r.nextInt(symbols);
                }
            }
            out[0] = code.toString();
            out[1] = String.valueOf(lastDigit);
    }
}
