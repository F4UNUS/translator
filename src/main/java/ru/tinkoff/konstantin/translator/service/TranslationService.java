package ru.tinkoff.konstantin.translator.service;

import ru.tinkoff.konstantin.translator.model.Text;
import ru.tinkoff.konstantin.translator.model.Translation;

import java.util.List;

public interface TranslationService {
    List<Translation> translate(Text text, String from, String to);
}
