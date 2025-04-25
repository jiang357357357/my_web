package com.personal.model;

public class TimelineEvent {
    private String date;
    private String title;
    private String description;
    private String url; // 新增URL字段

    public TimelineEvent(String date, String title, String description, String url) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }
}