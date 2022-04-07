package ru.tinkoff.konstantin.translator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class TranslatedWordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "source_word")
    private String sourceWord;
    @Column(name = "source_language")
    private String sourceLanguage;
    @Column(name = "translated_word")
    private String translatedWord;
    @Column(name = "target_language")
    private String targetLanguage;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private RequestEntity request;

    public TranslatedWordEntity() {
    }

    public TranslatedWordEntity(Long id, String sourceWord,
                                String sourceLanguage, String targetWord,
                                String targetLanguage, RequestEntity request) {
        this.id = id;
        this.sourceWord = sourceWord;
        this.sourceLanguage = sourceLanguage;
        this.translatedWord = targetWord;
        this.targetLanguage = targetLanguage;
        this.request = request;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSourceWord() {
        return sourceWord;
    }

    public void setSourceWord(String sourceWord) {
        this.sourceWord = sourceWord;
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    public String getTranslatedWord() {
        return translatedWord;
    }

    public void setTranslatedWord(String translatedWord) {
        this.translatedWord = translatedWord;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public void setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage;
    }

    public RequestEntity getRequest() {
        return request;
    }

    public void setRequest(RequestEntity request) {
        this.request = request;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TranslatedWordEntity that = (TranslatedWordEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(sourceWord, that.sourceWord) && Objects.equals(sourceLanguage, that.sourceLanguage) && Objects.equals(translatedWord, that.translatedWord) && Objects.equals(targetLanguage, that.targetLanguage) && Objects.equals(request, that.request);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sourceWord, sourceLanguage, translatedWord, targetLanguage, request);
    }

    @Override
    public String toString() {
        return "TranslatedWordEntity{" +
                "id=" + id +
                ", sourceWord='" + sourceWord + '\'' +
                ", sourceLanguage='" + sourceLanguage + '\'' +
                ", targetWord='" + translatedWord + '\'' +
                ", targetLanguage='" + targetLanguage + '\'' +
                ", request=" + request +
                '}';
    }
}
