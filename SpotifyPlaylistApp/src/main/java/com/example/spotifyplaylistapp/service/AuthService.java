package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dtos.LoginDTO;
import com.example.spotifyplaylistapp.model.dtos.RegisterDTO;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class AuthService {
    private UserRepository userRepository;

    private LoggedUser loggedUser;

    public AuthService(UserRepository userRepository, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    public boolean UserLoggedIn(){
        return this.loggedUser.getId() > 0;
    }

    public void logout(){
        this.loggedUser.logout();
    }

    public boolean register(RegisterDTO registerDTO) {
        Optional<User> byUsername = this.userRepository.findByUsername(registerDTO.getUsername());
        if(byUsername.isPresent()){
            return false;
        }

        Optional<User> byEmail = this.userRepository.findByEmail(registerDTO.getEmail());

        if(byEmail.isPresent()){
            return false;
        }

        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        user.setPlaylist(new HashSet<>());

        this.userRepository.save(user);
        return true;
    }

    public boolean login(LoginDTO loginDTO) {
        Optional<User> byUsernameAndPassword = this.userRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        if(byUsernameAndPassword.isEmpty()){
            return false;
        }

        this.loggedUser.login(byUsernameAndPassword.get());

        return true;
    }
}
