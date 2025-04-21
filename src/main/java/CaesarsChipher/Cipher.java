package CaesarsChipher;

public class Cipher {

    private static char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и','к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    public Cipher(char[] alphabet) {
        this.ALPHABET = alphabet;
    }
//шифровка
    public String encrypt(String text, int shift) {
        StringBuilder resultText = new StringBuilder();
        for (char c : text.toCharArray()){
            int index = findCharIndex(c);
            if (index == -1){
                resultText.append(c);
            } else {
                int newIndex = (index + shift) % ALPHABET.length;
                resultText.append(newIndex);
            }
        }
        return resultText.toString();
    }
//ищем индекс символа
    private int findCharIndex(char c) {
        for (int i = 0; i < ALPHABET.length; i++) {
            if(c == ALPHABET[i]) return i;
        }
        return -1;
    }
//расшифровка
    public String decrypt(String encryptedText, int shift) {
        return encrypt(encryptedText, -shift);
    }

}
