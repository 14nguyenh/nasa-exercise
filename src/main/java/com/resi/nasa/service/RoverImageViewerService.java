package com.resi.nasa.service;

import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class RoverImageViewerService
{
    public String getImgData(byte[] image)
    {
        return Base64.getMimeEncoder().encodeToString(image);
    }
}
