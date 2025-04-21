package CaesarsChipher;

public class Cipher {

    private static char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и','к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    public Cipher(char[] alphabet) {
        this.ALPHABET = alphabet;
    }

    public String encrypt(String text, int shift) {

        return text;
    }
    public String decrypt(String encryptedText, int shift) {

        return encryptedText;
    }

}
