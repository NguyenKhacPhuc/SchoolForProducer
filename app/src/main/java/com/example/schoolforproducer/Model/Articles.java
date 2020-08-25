package com.example.schoolforproducer.Model;

public class Articles {
    private String title;
    private String content;
    private String author;
    private String urlToImage;

    public Articles(String title, String content, String author, String urlToImage) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.urlToImage = urlToImage;
    }
    public Articles(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }
}
