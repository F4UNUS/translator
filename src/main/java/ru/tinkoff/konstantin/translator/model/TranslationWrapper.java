package ru.tinkoff.konstantin.translator.model;

import java.util.List;
import java.util.Objects;

public class TranslationWrapper {
    private List<Translation> translations;

    public TranslationWrapper(List<Translation> translations) {
        this.translations = translations;
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TranslationWrapper that = (TranslationWrapper) o;
        return Objects.equals(translations, that.translations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(translations);
    }

    @Override
    public String toString() {
        return "TranslationWrapper{" +
                "translations=" + translations +
                '}';
    }
}
