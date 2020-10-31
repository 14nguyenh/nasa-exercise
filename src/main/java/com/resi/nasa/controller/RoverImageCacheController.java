package com.resi.nasa.controller;

import com.resi.nasa.service.RoverImageCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class RoverImageCacheController
{
    RoverImageCacheService roverImageCacheService;

    @Autowired
    RoverImageCacheController(RoverImageCacheService roverImageCacheService)
    {
        this.roverImageCacheService = roverImageCacheService;
    }

    @GetMapping("roverimagecache")
    private String getRoverImageCache()
    {
        return "roverimagecache";
    }

    @PostMapping("roverimagecache")
    private String postRoverImageCache(@RequestParam("datefile") MultipartFile file)
    {
        roverImageCacheService.downloadImagesFromFile(file);
        return "redirect:roverimagecache";
    }
}
