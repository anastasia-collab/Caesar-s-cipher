package CaesarsChipher.MainClasses.Caesars;

import CaesarsChipher.MainClasses.Caesars.exception.CaesarAlphaetException;

import java.util.*;

public class Alphabet {
    private static Character[] RU_ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};
    private static Character[] EN_ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
            'z'};

    private final List<Character> alphabet;
    private final Map<Character, Integer> charIndex;

    public Alphabet(){
        List<Character> temporaryAlphabet = new ArrayList<>();

        temporaryAlphabet.addAll(Arrays.asList(RU_ALPHABET));
        temporaryAlphabet.addAll(Arrays.asList(EN_ALPHABET));
        alphabet = List.copyOf(temporaryAlphabet);
        charIndex = new HashMap<>();
        for (int i = 0; i < alphabet.size(); i++) {
            charIndex.put(alphabet.get(i), i);
        }
    }

    public Character[] getCharByIndex(int index) {
        if(index < 0 || index > alphabet.size()){
            throw new CaesarAlphaetException("Invalid index: " + index + ". Valid is from 0 to " + alphabet.size());
        }
        return new Character[]{alphabet.get(index)};
    }

    public int getCharIndex(Character character) {
        if (!charIndex.containsKey(character)) {
            throw new CaesarAlphaetException("Invalid character. Char: " + character);
        }
        return charIndex.get(character);  // Получаем индекс из Map, а не из List!
    }
public int getSize(){return alphabet.size();}

}
