package com.example.schoolforproducer.Model;

public class Track {
    private String id;
    private String thumbnail;
    private String trackName;
    private String trackChannel;

    public Track(String id, String thumbnail, String trackName, String trackChannel) {
        this.id = id;
        this.thumbnail = thumbnail;
        this.trackName = trackName;
        this.trackChannel = trackChannel;
    }
    public Track(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getTrackChannel() {
        return trackChannel;
    }

    public void setTrackChannel(String trackChannel) {
        this.trackChannel = trackChannel;
    }
}
