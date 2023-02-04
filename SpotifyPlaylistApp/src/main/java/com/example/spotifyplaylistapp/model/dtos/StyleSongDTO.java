package com.example.spotifyplaylistapp.model.dtos;

public class StyleSongDTO {

    private Long id;
    private String performer;

    private String title;

    private int duration;

    public Long getId() {
        return id;
    }

    public StyleSongDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPerformer() {
        return performer;
    }

    public StyleSongDTO setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public StyleSongDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public StyleSongDTO setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public String durationInMin(){
        int durationInSec = getDuration();

        int min = durationInSec / 60;

        int seconds = durationInSec - (min * 60);

        return min + ":" + seconds + " min";
    }

}
