package com.example.schoolforproducer.Model;

public class YoutubePreview {
   private String ids;
   private String title;



    public YoutubePreview(String ids, String title) {
        this.ids = ids;
        this.title = title;


    }
    public YoutubePreview(){

    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIds(){
       return ids;
   }
}
