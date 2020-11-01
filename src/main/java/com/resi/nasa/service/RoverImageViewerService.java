package com.resi.nasa.service;

import com.resi.nasa.model.entity.RoverImage;
import com.resi.nasa.model.repository.RoverImageRepository;
import com.resi.nasa.util.ImageUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoverImageViewerService
{
    private RoverImageRepository roverImageRepository;
    private ImageUtil imageUtil;

    public RoverImageViewerService(RoverImageRepository roverImageRepository, ImageUtil imageUtil)
    {
        this.roverImageRepository = roverImageRepository;
        this.imageUtil = imageUtil;
    }

    public ImageUtil getImageUtil()
    {
        return imageUtil;
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

    public void deleteAllImages()
    {
        roverImageRepository.deleteAll();
    }
}
