package ru.tinkoff.konstantin.translator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class RequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long id;
    @Column(length = 3000)
    private String input;
    @Column(length = 8000)
    private String output;
    private LocalDateTime time;
    @Column(name = "source_language")
    private String sourceLanguage;
    @Column(name = "target_languages")
    private String targetLanguages;
    private String ip;

    @OneToMany(mappedBy = "request")
    private List<TranslatedWordEntity> translatedWords;

    public RequestEntity() {
    }

    public RequestEntity(Long id, String input, String output, LocalDateTime time, String sourceLanguage, String targetLanguages, String ip) {
        this.id = id;
        this.input = input;
        this.output = output;
        this.time = time;
        this.sourceLanguage = sourceLanguage;
        this.targetLanguages = targetLanguages;
        this.ip = ip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    public String getTargetLanguages() {
        return targetLanguages;
    }

    public void setTargetLanguages(String targetLanguages) {
        this.targetLanguages = targetLanguages;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<TranslatedWordEntity> getTranslatedWords() {
        return translatedWords;
    }

    public void setTranslatedWords(List<TranslatedWordEntity> translatedWords) {
        this.translatedWords = translatedWords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestEntity that = (RequestEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(input, that.input) && Objects.equals(output, that.output) && Objects.equals(time, that.time) && Objects.equals(sourceLanguage, that.sourceLanguage) && Objects.equals(targetLanguages, that.targetLanguages) && Objects.equals(ip, that.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, input, output, time, sourceLanguage, targetLanguages, ip);
    }

    @Override
    public String toString() {
        return "RequestEntity{" +
                "id=" + id +
                ", input='" + input + '\'' +
                ", output='" + output + '\'' +
                ", time=" + time +
                ", sourceLanguage='" + sourceLanguage + '\'' +
                ", targetLanguages='" + targetLanguages + '\'' +
                ", ip='" + ip + '\'' +
                ", translatedWords=" + translatedWords +
                '}';
    }
}
