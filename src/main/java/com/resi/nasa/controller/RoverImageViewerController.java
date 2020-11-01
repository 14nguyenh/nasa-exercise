package com.resi.nasa.controller;

import com.resi.nasa.service.RoverImageViewerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RoverImageViewerController
{
    private RoverImageViewerService roverImageViewerService;

    public RoverImageViewerController(RoverImageViewerService roverImageViewerService)
    {
        this.roverImageViewerService = roverImageViewerService;
    }

    @GetMapping("roverimageviewer")
    private String getRoverImageViewer(Model model)
    {
        model.addAttribute("images", roverImageViewerService.getAllImages());
        model.addAttribute("imageUtil", roverImageViewerService.getImageUtil());
        return "roverimageviewer";
    }

    @PostMapping("roverimageviewer")
    private String postRoverImageViewer()
    {
        roverImageViewerService.deleteAllImages();
        return "redirect:roverimageviewer";
    }
}
