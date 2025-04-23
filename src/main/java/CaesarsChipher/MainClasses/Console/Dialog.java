package CaesarsChipher.MainClasses.Console;

import CaesarsChipher.MainClasses.Caesars.Coder;
import CaesarsChipher.MainClasses.FileManager.exception.FileProcessingException;

import java.util.Scanner;

public class Dialog implements DialogInterface {

    private static final String WELCOME_MESSAGE =
            """
                    *************
                    ** Welcome **
                    *************
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

    private void showMenu() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println("Choose next option to continue:");
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
                System.out.println("Operation number is wrong");
                String input = readString();

                if(TRY_AGAIN_COMMAND.equalsIgnoreCase(input)){
                    shouldTryAgain = true;
                }
            }
        } while (shouldTryAgain);
        return Operations.EXIT;
    }

    private void processOperations(Operations operations) {
        switch (operations) {
            case EXIT -> processExit();
            case ENCRYPTION -> prosessEncryptionOperation();
            case DECRYPTION -> processDecryptionOperation();
            case BRUTEFORCE -> processBruteOperation();
        }
    }

    private void prosessEncryptionOperation() {
        System.out.println("Enter fileName which contains original text: ");
        String inputFileName = readString();
        System.out.println("Enter fileName which will be used for result saving: ");
        String outputFileName = readString();
        System.out.println("Enter key: ");
        int key = readInt();

        try {
            coder.encrypt(inputFileName, outputFileName, key);
            System.out.println("Done");
        } catch (FileProcessingException e) {
            System.err.println("Error(");
            e.printStackTrace();
        }
    }

    private void processDecryptionOperation() {
        System.out.println("Enter fileName which contains original text: ");
        String inputFileName = readString();
        System.out.println("Enter fileName which will be used for result saving: ");
        String outputFileName = readString();
        System.out.println("Enter key: ");
        int key = readInt();

        try {
            coder.decrypt(inputFileName, outputFileName, key);
            System.out.println("Done");
        } catch (FileProcessingException e) {
            System.err.println("Error(");
            e.printStackTrace();
        }
    }

    private void processBruteOperation() {
        System.out.println("Enter fileName which contains encrypted text: ");
        String inputFileName = readString();
        System.out.println("Enter fileName which will be used for result saving: ");
        String outputFileName = readString();

        try {
            coder.bruteForce(inputFileName, outputFileName);
            System.out.println("Brute force completed. Check the output file.");
        } catch (FileProcessingException e) {
            System.err.println("Error(");
            e.printStackTrace();
        }
    }

    private void processExit() {
        System.out.println("Bye");
        System.exit(0);
    }

    private int readInt() {
        String input = in.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid number format");
        }
    }

    private String readString() {
        return in.nextLine();
    }
}