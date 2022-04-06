package ru.tinkoff.konstantin.translator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tinkoff.konstantin.translator.entity.RequestEntity;
import ru.tinkoff.konstantin.translator.entity.TranslatedWordEntity;
import ru.tinkoff.konstantin.translator.model.Translation;
import ru.tinkoff.konstantin.translator.model.TranslationWrapper;
import ru.tinkoff.konstantin.translator.repository.TranslatedWordRepository;

import java.util.List;

@Service
public class TranslatedWordsService {
    TranslatedWordRepository translatedWordRepository;

    @Autowired
    public TranslatedWordsService(TranslatedWordRepository translatedWordRepository) {
        this.translatedWordRepository = translatedWordRepository;
    }

    public void create(List<TranslationWrapper> translatedWords,
                       RequestEntity requestEntity) {
        for (TranslationWrapper word : translatedWords) {
            for (Translation translation : word.getTranslations()) {
                TranslatedWordEntity translatedWord =
                        new TranslatedWordEntity();
                translatedWord.setRequest(requestEntity);
                translatedWord.setContent(translation.getText());
                translatedWord.setTargetLanguage(translation.getTo());
                translatedWordRepository.save(translatedWord);
            }
        }
    }
}
