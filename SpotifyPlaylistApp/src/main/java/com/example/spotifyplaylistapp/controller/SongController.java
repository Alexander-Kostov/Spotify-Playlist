package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dtos.AddSongDTO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class SongController {

    @ModelAttribute("addSongDTO")
    public AddSongDTO addSongDTO(){
        return new AddSongDTO();
    }

    @GetMapping("/songs/add-song")
    public String addSong(){
        return "song-add";
    }

    @PostMapping("/songs/add-song")
    public String addSong(@Valid AddSongDTO addSongDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addSongDTO", addSongDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addSongDTO", bindingResult);

            return "redirect:/songs/add-song";
        }

        return "redirect:/home";

    }
}
