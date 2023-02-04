package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dtos.AddSongDTO;
import com.example.spotifyplaylistapp.model.dtos.StyleSongDTO;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.enums.StyleName;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import com.example.spotifyplaylistapp.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongService {

    private StyleRepository styleRepository;
    private SongRepository songRepository;
    private UserService userService;

    private LoggedUser loggedUser;

    public SongService(StyleRepository styleRepository, SongRepository songRepository, UserService userService, LoggedUser loggedUser) {
        this.styleRepository = styleRepository;
        this.songRepository = songRepository;
        this.userService = userService;
        this.loggedUser = loggedUser;
    }

    public boolean addSong(AddSongDTO addSongDTO){
        Song song = new Song();

        Optional<Style> byName = this.styleRepository.findByName(addSongDTO.getStyle());

        if(byName.isEmpty()){
            return false;
        }

        song.setPerformer(addSongDTO.getPerformer());
        song.setTitle(addSongDTO.getTitle());
        song.setDuration(addSongDTO.getDuration());
        song.setReleaseDate(addSongDTO.getReleaseDate());
        song.setStyle(byName.get());

        this.songRepository.save(song);

        return true;

    }

    public List<StyleSongDTO> GetAllPopSongs(StyleName pop) {
        return this.songRepository.findByStyleName(pop).stream().map(this::mapStyleDTOSong).collect(Collectors.toList());
    }

    public List<StyleSongDTO> getAllRockSongs(StyleName rock) {
        return this.songRepository.findByStyleName(rock).stream().map(this::mapStyleDTOSong).collect(Collectors.toList());
    }
    public List<StyleSongDTO> getAllJazzSongs(StyleName jazz) {
        return this.songRepository.findByStyleName(jazz).stream().map(this::mapStyleDTOSong).collect(Collectors.toList());
    }
    public StyleSongDTO mapStyleDTOSong(Song song){
        StyleSongDTO styleSongDTO = new StyleSongDTO();
        styleSongDTO.setId(song.getId());
        styleSongDTO.setPerformer(song.getPerformer());
        styleSongDTO.setTitle(song.getTitle());
        styleSongDTO.setDuration(song.getDuration());

        return styleSongDTO;
    }


    public void addSongToPlaylist(Long id, Long userId) {
        Optional<Song> Song = this.songRepository.findById(id);
        if(Song.isEmpty()) {
            throw new NoSuchElementException();
        }
        this.userService.addSongToUser(Song.get(), userId);
    }


    public List<StyleSongDTO> getPlaylist(long id) {
      return this.songRepository.findByUserId(id).stream().map(this::mapStyleDTOSong).collect(Collectors.toList());
    }

    public Song findSongById(Long songId) {
        return this.songRepository.findById(songId).orElseThrow();
    }
}
