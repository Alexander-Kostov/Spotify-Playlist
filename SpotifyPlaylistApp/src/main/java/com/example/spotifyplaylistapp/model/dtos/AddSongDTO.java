package com.example.spotifyplaylistapp.model.dtos;

import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.enums.StyleName;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class AddSongDTO {

    @Size(min = 3, max = 20)
    @NotBlank
    private String performer;

    @Size(min = 2, max = 20)
    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent()
    private LocalDate releaseDate;

    @Positive
    private int duration;

    @NotNull
    private StyleName style;

    public String getPerformer() {
        return performer;
    }

    public AddSongDTO setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public AddSongDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public AddSongDTO setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public AddSongDTO setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public StyleName getStyle() {
        return style;
    }

    public AddSongDTO setStyle(StyleName style) {
        this.style = style;
        return this;
    }
}
