package com.zjamss.model.vo;

import com.sun.javafx.binding.StringFormatter;

/**
* @Program: AutoImportNote
* @Description:
* @Author: ZJamss
* @Create: 2022-05-30 20:23
**/
public class FrontMatter {
    String title;
    String date;
    String updated;
    String slug;

    public FrontMatter() {
    }

    public FrontMatter(String title, String date, String updated, String slug) {
        this.title = title;
        this.date = date;
        this.updated = updated;
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
    @Override
    public String toString() {
        return StringFormatter.format(
                "---\n" +
                        "title: %s\n" +
                        "date: %s\n" +
                        "updated: %s\n" +
                        "url: /archives/%s\n" +
                        "categories: \n" +
                        "- 新笔记\n" +
                        "tags: \n" +
                        "---",
                title,date,updated,slug).getValue();
    }
}
