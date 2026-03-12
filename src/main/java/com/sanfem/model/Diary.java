package com.sanfem.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 日记模型类
 */
public class Diary implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String title;
    private String content;
    private String mood;
    private String weather;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Diary() {}

    public Diary(String title, String content, String mood, String weather) {
        this.title = title;
        this.content = content;
        this.mood = mood;
        this.weather = weather;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getMood() { return mood; }
    public void setMood(String mood) { this.mood = mood; }

    public String getWeather() { return weather; }
    public void setWeather(String weather) { this.weather = weather; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }
}
