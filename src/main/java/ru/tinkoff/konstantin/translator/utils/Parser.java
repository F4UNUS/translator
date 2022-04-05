package ru.tinkoff.konstantin.translator.utils;

import ru.tinkoff.konstantin.translator.model.Text;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {
    private static final String SEPARATOR = "[^\\p{L}\\d]+";

    public static List<Text> parse(Text text) {
        return Arrays.stream(text.getText().split(SEPARATOR)).map(Text::new).
                collect(Collectors.toList());
    }
}
