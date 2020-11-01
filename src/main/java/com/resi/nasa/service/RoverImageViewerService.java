package com.resi.nasa.service;

import com.resi.nasa.model.entity.RoverImage;
import com.resi.nasa.model.repository.RoverImageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class RoverImageViewerService
{
    private RoverImageRepository roverImageRepository;

    public RoverImageViewerService(RoverImageRepository roverImageRepository)
    {
        this.roverImageRepository = roverImageRepository;
    }

    public List<RoverImage> getAllImages()
    {
        List<RoverImage> imagesList = new ArrayList<RoverImage>();
        final Iterable<RoverImage> images = roverImageRepository.findAll();
        for (RoverImage image : images)
        {
            imagesList.add(image);
        }
        return imagesList;
    }
}
