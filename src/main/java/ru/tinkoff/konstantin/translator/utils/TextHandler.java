package ru.tinkoff.konstantin.translator.utils;

import ru.tinkoff.konstantin.translator.model.Text;
import ru.tinkoff.konstantin.translator.model.Translation;
import ru.tinkoff.konstantin.translator.model.Translations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TextHandler {
    private static final String SEPARATOR = "[^\\p{L}\\d]+";
    private static final String WORD = "[\\p{L}\\d]+";

    private String translationFormat;

    public TextHandler() {
        translationFormat = new String();
    }

    public List<Text> parseToWords(Text text) {
        translationFormat = text.getText().replaceAll(WORD, "%s");
        return Arrays.stream(text.getText().split(SEPARATOR)).map(Text::new).
                collect(Collectors.toList());
    }

    public List<Translation> concat( Translations[] translations) {
        List<Translation> translationTexts = new ArrayList<>();
        for (int i = 0; i < translations[0].getTranslations().size(); i++) {
            List<String> words = new ArrayList<>();
            StringBuilder stringBuilder = new StringBuilder();
            for (Translations trans : translations) {
                stringBuilder.append(trans.getTranslations().get(i).getText());
                stringBuilder.append(" ");
                words.add(trans.getTranslations().get(i).getText());
            }
            translationTexts.add(new Translation(String.format(translationFormat, words.toArray()), translations[0].getTranslations().get(i).getTo()));
        }
        return translationTexts;
    }
}
