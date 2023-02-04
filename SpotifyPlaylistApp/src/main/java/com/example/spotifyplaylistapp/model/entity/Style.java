package com.example.spotifyplaylistapp.model.entity;

import com.example.spotifyplaylistapp.model.enums.StyleName;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "styles")
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private StyleName name;

    private String description;

    @OneToMany(mappedBy = "style")
    private Set<Song> songs;

    public Style(StyleName styleName){
        this.name = styleName;
    }

    public Style() {}

    public Long getId() {
        return id;
    }

    public Style setId(Long id) {
        this.id = id;
        return this;
    }

    public StyleName getName() {
        return name;
    }

    public Style setName(StyleName name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Style setDescription(String description) {
        this.description = description;
        return this;
    }
}
