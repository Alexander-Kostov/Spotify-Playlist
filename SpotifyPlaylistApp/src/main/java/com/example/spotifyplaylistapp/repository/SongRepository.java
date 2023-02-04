package com.example.spotifyplaylistapp.repository;

import com.example.spotifyplaylistapp.model.dtos.StyleSongDTO;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.enums.StyleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByStyleName(StyleName pop);

    @Query("SELECT s, u FROM Song s join s.users u on u.id = :id")
    List<Song> findByUserId(long id);
}
