package ru.tinkoff.konstantin.translator.repository;

import org.springframework.data.repository.CrudRepository;
import ru.tinkoff.konstantin.translator.entity.TranslatedWordEntity;

public interface TranslatedWordRepository extends
        CrudRepository<TranslatedWordEntity, Long> {
}
