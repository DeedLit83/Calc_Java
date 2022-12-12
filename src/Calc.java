import java.util.Scanner;

public class Calc {

    public static void main(String[] args) {
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};

        System.out.println("Input math action:");
        String income = (new Scanner(System.in)).nextLine();

        int position = -1;
        for (int i=0; i < actions.length; i++) {

            if (income.contains(actions[i])) {
                position = i;
                break;
            }
        }

        if (position == -1) {
            System.out.println("Action is incorrect.");
            return;
        }

        String[] data = income.split(regexActions[position]);
        System.out.println();


    }

}
