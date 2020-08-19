package com.example.schoolforproducer;

import com.example.schoolforproducer.HandleRequest.QueryKeywords;
import com.example.schoolforproducer.HandleRequest.YoutubeConfig;

public class Constant {
    public static final String YOUTUBE_LINK= "https://www.youtube.com/watch?v=";
    public static final String URL = YoutubeConfig.getSCHEME()+ QueryKeywords.getPlaylistItemQuery()
            +QueryKeywords.getPART()+"snippet"
            +QueryKeywords.getPlaylistId()+"PLDfKAXSi6kUZnATwAUfN6tg1dULU-7XcD"
            +QueryKeywords.getKEY()
            +QueryKeywords.getKeyApi()
            +QueryKeywords.getMAX()+"50";
}
