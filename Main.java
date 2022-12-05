import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

    static Scanner scanner = new Scanner(System.in);
    static int number1, number2;
    static char operation;
    static int result;

    public static void main(String[] args) throws Exception {

        System.out.println("Введите два арабских числа от 1 до 10: [2+2] или два римских числа от I до X:[V+V] + Enter ");
        String userInput = scanner.nextLine();
        System.out.println("Результат:");
        String s = calc(userInput);
        System.out.println(s);
    }

    public static String calc(String input){

        String result2 = null;
        char[] chars = new char[9];
        for (int i = 0; i < input.length(); i++) {
            chars[i] = input.charAt(i);
            if (chars[i] == '+') {
                operation = '+';
            }
            if (chars[i] == '-') {
                operation = '-';
            }
            if (chars[i] == '*') {
                operation = '*';
            }
            if (chars[i] == '/') {
                operation = '/';
            }
        }
        if (operation == '0') {
            throw new RuntimeException("Неверный знак!");
        }

        String charsString = String.valueOf(chars);
        String[] blocks = charsString.split("[+-/*]");

        if(blocks.length != 2){
            throw new RuntimeException("Надо 2 операнда!");
        }
        String block1 = blocks[0].trim();
        String block2 = blocks[1].trim();

        if ((block1.equals("I") || block1.equals("II") || block1.equals("III") || block1.equals("IV") || block1.equals("V") ||
                block1.equals("VI") || block1.equals("VII") || block1.equals("VIII") || block1.equals("IX") || block1.equals("X")) &&
                (block2.equals("I") || block2.equals("II") || block2.equals("III") || block2.equals("IV") || block2.equals("V") ||
                        block2.equals("VI") || block2.equals("VII") || block2.equals("VIII") || block2.equals("IX") || block2.equals("X"))) {
            number1 = romanToNumber(block1);
            number2 = romanToNumber(block2);
            if (number1 < 0 && number2 < 0) {
                result = 0;
            } else {
                result = calculated(number1, number2, operation);

                try {
                    return convertNumToRoman(result);
                } catch (Exception e) {
                    throw new RuntimeException("В римской системе нет отрицательных чисел!");
                }
            }
        } else {
            try {
                number1 = Integer.parseInt(block1);
                number2 = Integer.parseInt(block2);
                if (number1 > 10 || number2 > 10){
                    throw new RuntimeException("Числа больше 10");
                }
                result = calculated(number1, number2, operation);
                result2 = Integer.toString(result);

            } catch (NumberFormatException e) {
                throw new RuntimeException("Неверный формат данных!");
            }
        }
        return result2;

    }

    private static String convertNumToRoman(int numArabian) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX",
                "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX",
                "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX",
                "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX",
                "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX",
                "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX",
                "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX",
                "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
        return roman[numArabian];
    }

    private static int romanToNumber(String roman) {
        try {
            switch (roman) {
                case "I":
                    return 1;
                case "II":
                    return 2;
                case "III":
                    return 3;
                case "IV":
                    return 4;
                case "V":
                    return 5;
                case "VI":
                    return 6;
                case "VII":
                    return 7;
                case "VIII":
                    return 8;
                case "IX":
                    return 9;
                case "X":
                    return 10;
            }
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Неверный формат данных");
        }
        return -1;
    }

    public static int calculated(int num1, int num2, char op) {
        int result = 0;
        switch (op) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                try {
                    result = num1 / num2;
                } catch (ArithmeticException | InputMismatchException e) {
                    System.out.println("Нельзя делить на ноль");
                    System.exit(0);
                    break;
                }
                break;

        }
        return result;
    }

}
