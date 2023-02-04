package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dtos.StyleSongDTO;
import com.example.spotifyplaylistapp.model.enums.StyleName;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.service.AuthService;
import com.example.spotifyplaylistapp.service.HomeService;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

    private SongRepository songRepository;
    private SongService songService;

    private LoggedUser loggedUser;
    private HomeService homeService;
    private AuthService authService;

    public HomeController(SongRepository songRepository, SongService songService, LoggedUser loggedUser, HomeService homeService, AuthService authService) {
        this.songRepository = songRepository;
        this.songService = songService;
        this.loggedUser = loggedUser;
        this.homeService = homeService;
        this.authService = authService;
    }

    @GetMapping("/home")
    public String home(Model model){

        if(!this.authService.userLoggedIn()){
            return "redirect:/";
        }

        List<StyleSongDTO> popSongs = this.songService.GetAllPopSongs(StyleName.POP);
        List<StyleSongDTO> rockSongs = this.songService.getAllRockSongs(StyleName.ROCK);
        List<StyleSongDTO> jazzSongs = this.songService.getAllJazzSongs(StyleName.JAZZ);
        List<StyleSongDTO> playlist = this.songService.getPlaylist(loggedUser.getId());
        String formattedDurationTime = this.homeService.getFormattedDurationTime(playlist);

        model.addAttribute("POP", popSongs);
        model.addAttribute("ROCK", rockSongs);
        model.addAttribute("JAZZ", jazzSongs);
        model.addAttribute("playlist", playlist);
        model.addAttribute("result", formattedDurationTime);


        return "home";
    }

    @GetMapping("/home/add-song-to-playlist/{id}")
    public String addSongToPlaylist(@PathVariable("id") Long id){

        if(!this.authService.userLoggedIn()){
            return "redirect:/";
        }

        this.songService.addSongToPlaylist(id, this.loggedUser.getId());

        return "redirect:/home";
    }

    @GetMapping("/home/remove-song-from-playlist/{id}")
    public String removeSongFromPlaylist(@PathVariable("id") Long songId){

        if(!this.authService.userLoggedIn()){
            return "redirect:/";
        }

        this.homeService.removeSong(songId, this.loggedUser.getId());

        return "redirect:/home";
    }

    @GetMapping("/home/remove-all-songs")
    public String removeAllSongsFromPlaylist(){

        if(!this.authService.userLoggedIn()){
            return "redirect:/home";
        }

        this.homeService.removeAllSongs(this.loggedUser.getId());

        return "redirect:/home";
    }





}
