package ru.tinkoff.konstantin.translator.model;

import java.util.Objects;

public class Translate {
    private String text;
    private String to;

    public Translate() {
    }

    public Translate(String text, String to) {
        this.text = text;
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Translate that = (Translate) o;
        return Objects.equals(text, that.text) && Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, to);
    }

    @Override
    public String toString() {
        return "Translate{" +
                "text='" + text + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
