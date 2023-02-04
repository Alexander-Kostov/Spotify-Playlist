package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dtos.StyleSongDTO;
import com.example.spotifyplaylistapp.model.entity.Song;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {
    private SongService songService;

    private UserService userService;

    public HomeService(SongService songService, UserService userService) {
        this.songService = songService;
        this.userService = userService;
    }

    public void addSong(Long songId, Long userId) {
        Song song = this.songService.findSongById(songId);
        this.userService.addSongToUser(song, userId);
    }

    public void removeSong(Long songId, Long userId) {
        this.userService.removeFromPlaylist(songId, userId);
    }

    public void removeAllSongs(long userId) {
        this.userService.emptyPlaylist(userId);
    }

    public String getFormattedDurationTime(List<StyleSongDTO> playlist) {
        int sum = 0;

        for (StyleSongDTO styleSongDTO : playlist) {
            sum+= styleSongDTO.getDuration();
        }

        int min = sum / 60;
        int sec = sum - (min * 60);

        String result;

        if(sec < 9){
            result = min + ":" + "0" + sec;
        }else {
            result = min + ":" + sec;
        }
        return result;
    }
}
