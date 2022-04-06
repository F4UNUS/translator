package ru.tinkoff.konstantin.translator.service;

import ru.tinkoff.konstantin.translator.model.Text;
import ru.tinkoff.konstantin.translator.model.TranslationWrapper;

import java.util.List;

public interface TranslationService {
    List<TranslationWrapper> translate(List<Text> words, String from, String to);
}
