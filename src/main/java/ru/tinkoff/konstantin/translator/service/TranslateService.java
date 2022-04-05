package ru.tinkoff.konstantin.translator.service;

import ru.tinkoff.konstantin.translator.model.Translate;
import ru.tinkoff.konstantin.translator.model.Text;

import java.util.List;

public interface TranslateService {
    List<Translate> translate(Text text,
                              String from,
                              String to);
}
