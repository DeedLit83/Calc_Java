import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

enum RomanNumeral {
    I(1), IV(4), V(5), IX(9), X(10),
    XL(40), L(50), XC(90), C(100),
    CD(400), D(500), CM(900), M(1000);

    private int value;

    RomanNumeral(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static List<RomanNumeral> getReverseSortedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                .collect(Collectors.toList());
    }
}

public class Calc {

    public static void main(String[] args) {
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        String[] romanString = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] digit = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] allowedChars = {"+", "-", "/", "*",
                "I", "II", "III", "IV","V", "VI", "VII", "VIII", "IX", "X",
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

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
            throw new IllegalArgumentException("Incorrect operand. Try to use: +, -, / or *.");
        }

        if (count > 1) {
            throw new NumberFormatException("To march operand.");
        }

        String[] data = income.split(regexActions[position]);

        if (data.length > 2) {
                throw new NumberFormatException("To march operand.");
        }

        if (isAllowedSymbol(data[0], allowedChars) && isAllowedSymbol(data[1], allowedChars)) {

            if ((isAllowedSymbol(data[0], romanString)) && (isAllowedSymbol(data[1], romanString))) {
                int res = calculate(romanToArabic(data[0]), romanToArabic(data[1]), actions[position]);
                if (res < 1) {
                    throw new NumberFormatException("V rimskoy sisteme net otricatelnih chisel.");
                } else {
                    System.out.println(arabicToRoman(res));
                }
            } else if ((isAllowedSymbol(data[0], digit)) && (isAllowedSymbol(data[1], digit))) {
                    System.out.println(calculate(Integer.parseInt(data[0]), Integer.parseInt(data[1]), actions[position]));
            } else {
                    throw new NumberFormatException("Raznie sistemi ischeslenia");
            }
        } else {
            throw new NumberFormatException("Incorrect symbol: " + data[0] + " or " + data[1] + ".");
        }
    }

    public static boolean isAllowedSymbol(String data, String[] array) {
        boolean result = false;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(data.intern())) {
                result = true;
                break;
            }
        }
        return result;
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

    public static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(input + " cannot be converted to a Roman Numeral");
        }

        return result;
    }

    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " is not in range (0,4000]");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

}
