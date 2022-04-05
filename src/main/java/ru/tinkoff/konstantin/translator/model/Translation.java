package ru.tinkoff.konstantin.translator.model;

import java.util.Objects;

public class Translation {
    private String text;
    private String to;

    public Translation(String text, String to) {
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
        Translation that = (Translation) o;
        return Objects.equals(text, that.text) && Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, to);
    }

    @Override
    public String toString() {
        return "Translation{" +
                "text='" + text + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
