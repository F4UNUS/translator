package ru.tinkoff.konstantin.translator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tinkoff.konstantin.translator.entity.RequestEntity;
import ru.tinkoff.konstantin.translator.entity.TranslatedWordEntity;
import ru.tinkoff.konstantin.translator.model.Text;
import ru.tinkoff.konstantin.translator.model.Translation;
import ru.tinkoff.konstantin.translator.model.TranslationWrapper;
import ru.tinkoff.konstantin.translator.repository.TranslatedWordRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TranslatedWordsService {
    TranslatedWordRepository translatedWordRepository;

    @Autowired
    public TranslatedWordsService(
            TranslatedWordRepository translatedWordRepository) {
        this.translatedWordRepository = translatedWordRepository;
    }

    public void create(List<Text> sourceWords, String sourceLanguage,
                       List<TranslationWrapper> translatedWords,
                       RequestEntity requestEntity) {
        List<TranslatedWordEntity> translatedWordEntities = new ArrayList<>();
        for (int i = 0; i < translatedWords.size(); i++) {
            for (Translation translation :
                    translatedWords.get(i).getTranslations()) {
                TranslatedWordEntity translatedWord =
                        new TranslatedWordEntity();
                translatedWord.setRequest(requestEntity);
                translatedWord.setSourceWord(sourceWords.get(i).getText());
                translatedWord.setSourceLanguage(sourceLanguage);
                translatedWord.setTranslatedWord(translation.getText());
                translatedWord.setTargetLanguage(translation.getTo());
                translatedWordEntities.add(translatedWord);
            }
        }
        translatedWordRepository.saveAll(translatedWordEntities);
    }
}
