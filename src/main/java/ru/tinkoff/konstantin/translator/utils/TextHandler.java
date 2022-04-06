package ru.tinkoff.konstantin.translator.utils;

import ru.tinkoff.konstantin.translator.model.Text;
import ru.tinkoff.konstantin.translator.model.Translation;
import ru.tinkoff.konstantin.translator.model.TranslationWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TextHandler {
    private static final String SEPARATOR = "[^\\p{L}]+";
    private static final String WORD = "[\\p{L}]+";

    private String translationFormat;

    public TextHandler() {
        translationFormat = new String();
    }

    public List<Text> parseToWords(Text text) {
        translationFormat = text.getText().replaceAll(WORD, "%s");
        return Arrays.stream(text.getText().split(SEPARATOR)).map(Text::new).
                collect(Collectors.toList());
    }

    public List<Translation> concat(TranslationWrapper[] translatedWords) {
        List<Translation> translatedTexts = new ArrayList<>();
        int translationLanguageCount =
                translatedWords[0].getTranslations().size();
        for (int i = 0; i < translationLanguageCount; i++) {
            List<String> oneLanguageWords = new ArrayList<>();
            for (TranslationWrapper translatedWord : translatedWords) {
                oneLanguageWords.add(
                        translatedWord.getTranslations().get(i).getText());
            }
            translatedTexts.add(new Translation(String.format(
                    translationFormat, oneLanguageWords.toArray()),
                    translatedWords[0].getTranslations().get(i).getTo()));
        }
        return translatedTexts;
    }
}
