package CaesarsChipher;

public class BruteForce {
    private final char[] alphabet;

    public BruteForce(char[] alphabet) {
        this.alphabet = alphabet;
    }

    public String decryptByBruteForce(String encryptedText) {
        for (int key = 1; key < alphabet.length; key++) {
            String decryptedAttempt = decryptWithKey(encryptedText, key);
            if (isMeaningful(decryptedAttempt)) {  // Проверка на осмысленность
                return decryptedAttempt;
            }
        }
        return "Не удалось подобрать ключ. Возможные варианты:\n" + getAllDecryptionAttempts(encryptedText);
    }

    private String decryptWithKey(String text, int key) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            int index = findCharIndex(c);
            if (index != -1) {
                int newIndex = (index - key + alphabet.length) % alphabet.length;
                result.append(alphabet[newIndex]);
            } else {
                result.append(c);  // Символы не из алфавита не меняем
            }
        }
        return result.toString();
    }

    private int findCharIndex(char c) {
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] == c) {
                return i;
            }
        }
        return -1;
    }

    private boolean isMeaningful(String text) {
        // Пример: проверяем наличие гласных и пробелов
        return text.matches(".*[аеёиоуыэюя ]+.*");  // Для русского алфавита
    }

    private String getAllDecryptionAttempts(String encryptedText) {
        StringBuilder allResults = new StringBuilder();
        for (int key = 1; key < alphabet.length; key++) {
            allResults.append("Ключ ")
                    .append(key)
                    .append(": ")
                    .append(decryptWithKey(encryptedText, key))
                    .append("\n");
        }
        return allResults.toString();
    }
}
