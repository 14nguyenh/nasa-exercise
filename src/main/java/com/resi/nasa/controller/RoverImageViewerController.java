package com.resi.nasa.controller;

import com.resi.nasa.model.entity.RoverImage;
import com.resi.nasa.model.repository.RoverImageRepository;
import com.resi.nasa.service.RoverImageViewerService;
import com.resi.nasa.util.ImageUtil;
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
    private ImageUtil imageUtil;

    public RoverImageViewerController(RoverImageRepository roverImageRepository, RoverImageViewerService roverImageViewerService, ImageUtil imageUtil)
    {
        this.roverImageRepository = roverImageRepository;
        this.roverImageViewerService = roverImageViewerService;
        this.imageUtil = imageUtil;
    }

    @GetMapping("roverimageviewer")
    private String getRoverImageViewer(Model model)
    {
        model.addAttribute("images", roverImageViewerService.getAllImages());
        model.addAttribute("imageUtil", imageUtil);
        return "roverimageviewer";
    }

    @PostMapping("roverimageviewer")
    private String postRoverImageViewer()
    {
        roverImageRepository.deleteAll();
        return "redirect:roverimageviewer";
    }
}
