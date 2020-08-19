package com.example.schoolforproducer.HandleRequest;

public class QueryKeywords {
    private static final String PLAYLIST_ITEM_QUERY = "playlistItems?";
    private static final String PART = "part=";
    private static final String PLAYLIST_ID = "&playlistId=";
    private static final String KEY = "&key=";
    private static final String MAX ="&maxResults=";
    private static final String KEY_API = "AIzaSyBbVnpAsMDicrzefKcKuk6gVVl_A5ZxI84";

    public static String getPlaylistItemQuery() {
        return PLAYLIST_ITEM_QUERY;
    }

    public static String getPART() {
        return PART;
    }

    public static String getPlaylistId() {
        return PLAYLIST_ID;
    }

    public static String getKeyApi() {
        return KEY_API;
    }

    public static String getKEY() {
        return KEY;
    }

    public static String getMAX() {
        return MAX;
    }
}
