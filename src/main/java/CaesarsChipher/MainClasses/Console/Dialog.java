package CaesarsChipher.MainClasses.Console;

import CaesarsChipher.MainClasses.Caesars.Coder;
import CaesarsChipher.MainClasses.FileManager.exception.FileProcessingException;

import java.util.Scanner;

public class Dialog implements DialogInterface {

    private static final String WELCOME_MESSAGE =
            """
                    *************************************************************************
                    *************** Добро пожаловать в шифр Цезаря, Искатель! ***************
                    *************************************************************************
                    """;
    private static final String OPERATION_PATTERN = "%d - %s";
    private static final String TRY_AGAIN_COMMAND = "again";
    private final Scanner in;
    private final Coder coder;

    public Dialog() {
        in = new Scanner(System.in);
        coder = new Coder();
    }

    @Override
    public void start() {
        while (true) {
            showMenu();
            Operations operations = readOperatons();
            processOperations(operations);
        }
    }

    private void processOperations(Operations operations) {
        switch (operations) {
            case EXIT -> processExit();
            case ENCRYPTION -> prosessEncryptionOperation();
            case DECRYPTION -> processDecryptionOperation();
            case BRUTEFORCE -> processBruteOperation();
        }
    }

    private void showMenu() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println("Выбери действие, чтобы продолжить:");
        for (Operations operation : Operations.values()) {
            String message = String.format(OPERATION_PATTERN, operation.getNumber(), operation.getDescription());
            System.out.println(message);
        }
    }

    private Operations readOperatons() {
        boolean shouldTryAgain = false;
        do {
            try {
                int option = readInt();
                return Operations.getByNumber(option);
            } catch (IllegalArgumentException ex) {
                System.out.println("Номер операции выбран неверно...");
                String input = readString();

                if(TRY_AGAIN_COMMAND.equalsIgnoreCase(input)){
                    shouldTryAgain = true;
                }
            }
        } while (shouldTryAgain);
        return Operations.EXIT;
    }

    private void prosessEncryptionOperation() {
        System.out.println("Введите путь файла, хранящем текст, над которым Вы хотите провести выбранную операцию: ");
        String inputFileName = readString();
        System.out.println("Введите путь файла, в который Вы хотите сохранить результат: ");
        String outputFileName = readString();
        System.out.println("Введите ключ: ");
        int key = readInt();

        try {
            coder.encrypt(inputFileName, outputFileName, key);
            System.out.println("Готово!");
        } catch (FileProcessingException e) {
            System.err.println("Ошибка...(");
            e.printStackTrace();
        }
    }

    private void processDecryptionOperation() {
        System.out.println("Введите путь файла, хранящем текст, над которым Вы хотите провести выбранную операцию: ");
        String inputFileName = readString();
        System.out.println("Введите путь файла, в который Вы хотите сохранить результат: ");
        String outputFileName = readString();
        System.out.println("Введите ключ: ");
        int key = readInt();

        try {
            coder.decrypt(inputFileName, outputFileName, key);
            System.out.println("Готово!");
        } catch (FileProcessingException e) {
            System.err.println("Ошибка...(");
            e.printStackTrace();
        }
    }

    private void processBruteOperation() {
        System.out.println("Введите путь файла, хранящем текст, над которым Вы хотите провести выбранную операцию: ");
        String inputFileName = readString();
        System.out.println("Введите путь файла, в который Вы хотите сохранить результат: ");
        String outputFileName = readString();

        try {
            coder.bruteForce(inputFileName, outputFileName);
            System.out.println("Взлом завершён, посмотрите файл.");
        } catch (FileProcessingException e) {
            System.err.println("Ошибка...(");
            e.printStackTrace();
        }
    }

    private void processExit() {
        System.out.println("Хорошего дня!");
        System.exit(0);
    }

    private int readInt() {
        String input = in.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Неправильный формат чисел.");
        }
    }

    private String readString() {
        return in.nextLine();
    }
}