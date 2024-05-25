import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите выражение (например, 2 + 2 или II + II): ");
        String input = scanner.nextLine();
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат ввода");
        }

        boolean isRoman = isRoman(parts[0]) && isRoman(parts[2]);
        if (isRoman != (isRoman(parts[0]) && isRoman(parts[2]))) {

            throw new IllegalArgumentException("Оба числа должны быть либо арабскими, либо римскими");
        }


        int a = 0, b = 0;
        if (isRoman) {
            a = RomanToArabic(parts[0]);
            b = RomanToArabic(parts[2]);
        } else {
            a = Integer.parseInt(parts[0]);
            b = Integer.parseInt(parts[2]);
        }

        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new IllegalArgumentException("Числа должны быть от 1 до 10");
        }

        int result = 0;
        if (parts[1].equals("+")) {
            result = a + b;
        } else if (parts[1].equals("-")) {
            result = a - b;
        } else if (parts[1].equals("*")) {
            result = a * b;
        } else if (parts[1].equals("/")) {
            if (b == 0) {

                throw new IllegalArgumentException("Нельзя делить на 0");
            }
            result = a / b;
        } else {

            throw new IllegalArgumentException("Неверная операция. Используйте +,-,*,/");
        }

        if (isRoman) {
            if (result <= 0) {
                throw new IllegalArgumentException("Результат не может быть равен или меньше 0.");
            }
            System.out.println(ArabicToRoman(result));
        } else {
            System.out.println(result);
        }
    }

    public static boolean isRoman(String input) {
        return input.matches("[IVXLC]+");
    }

    public static int RomanToArabic(String input) {
        input = input.toUpperCase();
        int result = 0;
        int prevValue = 0;
        for (int i = input.length() - 1; i >= 0; i--) {
            int value = getRomanValue(input.charAt(i));
            if (value < prevValue) {
                result -= value;
            } else {
                result += value;
            }
            prevValue = value;
        }
        return result;
    }

    public static int getRomanValue(char roman) {
        if (roman == 'I') return 1;
        if (roman == 'V') return 5;
        if (roman == 'X') return 10;
        if (roman == 'L') return 50;
        if (roman == 'C') return 100;
        throw new IllegalArgumentException("Недопустимый символ римской цифры: " + roman);
    }
    public static String ArabicToRoman(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("Римские числа не могут быть меньше 1.");
        }
        String result = "";
        while (number >= 100) { result = result + "C"; number -= 100; }
        while (number >= 90) { result = result + "XC"; number -= 90; }
        while (number >= 50) { result = result + "L"; number -= 50; }
        while (number >= 40) { result = result + "XL"; number -= 40; }
        while (number >= 10) { result = result + "X"; number -= 10; }
        while (number >= 9) { result = result + "IX"; number -= 9; }
        while (number >= 5) { result = result + "V"; number -= 5; }
        while (number >= 4) { result = result + "IV"; number -= 4; }
        while (number >= 1) { result = result + "I"; number -= 1; }
        return result;
    }
}
