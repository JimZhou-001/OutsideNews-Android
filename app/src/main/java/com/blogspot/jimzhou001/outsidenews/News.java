package com.blogspot.jimzhou001.outsidenews;

public class News {

    private String title;

    private int imageId;

    private String content;

    public News(String title, int imageId, String content) {
        this.title = title;
        this.imageId = imageId;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public int getImageId() {
        return imageId;
    }

    public String getContent() {
        return content;
    }

}
