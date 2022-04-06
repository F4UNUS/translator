package ru.tinkoff.konstantin.translator.entity;

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
    private String content;
    private String targetLanguage;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private RequestEntity request;

    public TranslatedWordEntity() {
    }

    public TranslatedWordEntity(Long id, String content, String targetLanguage, RequestEntity request) {
        this.id = id;
        this.content = content;
        this.targetLanguage = targetLanguage;
        this.request = request;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        return Objects.equals(id, that.id) && Objects.equals(content, that.content) && Objects.equals(targetLanguage, that.targetLanguage) && Objects.equals(request, that.request);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, targetLanguage, request);
    }

    @Override
    public String toString() {
        return "TranslatedWordEntity{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", targetLanguage='" + targetLanguage + '\'' +
                ", request=" + request +
                '}';
    }
}
