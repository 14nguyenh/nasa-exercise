package com.resi.nasa.controller;

import com.resi.nasa.model.entity.RoverImage;
import com.resi.nasa.model.repository.RoverImageRepository;
import com.resi.nasa.service.RoverImageViewerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class RoverImageViewerController
{
    private RoverImageRepository roverImageRepository;
    private RoverImageViewerService roverImageViewerService;

    public RoverImageViewerController(RoverImageRepository roverImageRepository, RoverImageViewerService roverImageViewerService)
    {
        this.roverImageRepository = roverImageRepository;
        this.roverImageViewerService = roverImageViewerService;
    }

    @GetMapping("roverimageviewer")
    private String getRoverImageViewer(Model model)
    {
        List<RoverImage> imagesList = new ArrayList<RoverImage>();
        final Iterable<RoverImage> images = roverImageRepository.findAll();
        for (RoverImage image : images)
        {
            imagesList.add(image);
        }
        model.addAttribute("images", imagesList);
        model.addAttribute("roverImageViewerService", roverImageViewerService);
        return "roverimageviewer";
    }

    @PostMapping("roverimageviewer")
    private String postRoverImageViewer()
    {
        roverImageRepository.deleteAll();
        return "redirect:roverimageviewer";
    }
}
