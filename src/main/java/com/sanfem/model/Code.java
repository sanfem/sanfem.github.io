package com.sanfem.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 代码集模型类
 */
public class Code implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String title;
    private String language;
    private String description;
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Code() {}

    public Code(String title, String language, String description, String content) {
        this.title = title;
        this.language = language;
        this.description = description;
        this.content = content;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }
}
