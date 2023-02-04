package com.example.spotifyplaylistapp.service;


import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addSongToUser(Song song, Long userId) {
        Optional<User> user = this.userRepository.findById(userId);
        User userRep = user.get();
        if(userRep.getPlaylist().stream().noneMatch(s -> s.getId().equals(song.getId()))){
            userRep.getPlaylist().add(song);
            this.userRepository.save(userRep);
        }
    }

    public void removeFromPlaylist(Long songId, Long userId) {
        Optional<User> userOptional = this.userRepository.findById(userId);
        User user = userOptional.get();
        user.removeSongFromPlaylist(songId);
        this.userRepository.save(user);
    }

    public void emptyPlaylist(long userId) {
        Optional<User> userOpt = this.userRepository.findById(userId);
        User user = userOpt.get();

        user.removeAllSongs();
        this.userRepository.save(user);
    }
}
