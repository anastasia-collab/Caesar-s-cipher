package CaesarsChipher.MainClasses;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    private static char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и','к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    private static Cipher cipher;

    static {
        try {
            cipher = new Cipher(ALPHABET);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        System.out.println("Добро пожаловать в Шифровальщик!");

        while (true) {
            action(getAction());
        }
    }
    private static void action (int action) throws IOException {
        switch (action) {
            case 1 -> cipher.encrypt("textIn", 3);
            //case 2 -> move();
            //case 3 -> showInventory();
            //case 4 -> useItem();
        }
    }

    private static int getAction() {
        int action;
        while (true) {
            System.out.println("Ваши действия:\n" +
                    "1. Шифровать\n" +
                    "2. Расшифровать\n" +
                    "3. Взломать\n" +
                    "4. Покинуть");
            String input = scanner.nextLine();
            if (isNumeric(input) && (action = Integer.parseInt(input)) >= 1 && action <= 5) {
                return action;
            }
            System.out.println("Некорректный ввод. Введите числа от 1 до 5");
        }
    }

    private static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
}