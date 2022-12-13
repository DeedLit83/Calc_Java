import java.util.Arrays;
import java.util.Scanner;

enum RomanNumeral {
    I(1), II(2), III(3), IV(4),
    V(5), VI(6), VII(7), VIII(8),
    IX(9), X(10);

    private int value;

    RomanNumeral(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

public class Calc {

    public static void main(String[] args) {
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        String[] allowedChars = {"+", "-", "/", "*", "I", "V", "X", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};


        System.out.println("Input math operand:");
        String income = (new Scanner(System.in)).nextLine();

        int position = -1;
        int count = 0;
        for (int i=0; i < actions.length; i++) {

            if (income.contains(actions[i])) {
                position = i;
                count++;
            }
        }

        if (position == -1) {
            System.out.println("Incorrect operand.");
            return;
        }

        if (count > 1) {
            System.out.println("To march operand.");
            return;
        }

        String[] data = income.split(regexActions[position]);

        if ((isRoman(data[0])) && (isRoman(data[1]))) {
            if (actions[position] == "-") {
                if (calculate(RomanNumeral.valueOf(data[0]).getValue(), RomanNumeral.valueOf(data[1]).getValue(), actions[position]) < 1) {
                    System.out.println("Result < 1.");
                    return;
                }
            }

            if (actions[position] == "/") {
                if (calculate(RomanNumeral.valueOf(data[0]).getValue(), RomanNumeral.valueOf(data[1]).getValue(), actions[position]) < 1) {
                    System.out.println("Result < 1.");
                    return;
                }
            }

            int result = calculate(RomanNumeral.valueOf(data[0]).getValue(), RomanNumeral.valueOf(data[1]).getValue(), actions[position]);
            System.out.println(result);
            return;
        }

        if ((isDigit(data[0])) && (isDigit(data[1]))) {
            System.out.println(calculate(Integer.parseInt(data[0]), Integer.parseInt(data[1]), actions[position]));
            return;
        }

        //System.out.println("I'm here");


    }

    public static boolean isRoman(String data) {
        String romanNumber = Arrays.toString(RomanNumeral.values());
        return romanNumber.contains(data);
    }

    public static boolean isDigit(String data) {
        boolean result = false;
        int a = 0;
        try {
            a = Integer.parseInt(data);
        } catch (NumberFormatException e) {
            System.out.println("Incorrect input: " + e);
            a = 0;
        }

        result = (a < 1) | (a > 10);
        if (result) {
            throw new IllegalArgumentException(a + " is not in range [1,10]");
        }

        return !result;
    }

    public static int calculate(int a, int b, String operand) {
        int c = 0;
        switch (operand) {
            case "+":
                c = a + b;
                break;
            case "-":
                c = a - b;
                break;
            case "/":
                c = a / b;
                break;
            case "*":
                c = a * b;
                break;
        }
        return c;
    }

}
