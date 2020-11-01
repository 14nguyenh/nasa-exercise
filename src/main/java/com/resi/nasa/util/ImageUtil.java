package com.resi.nasa.util;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class ImageUtil
{
    public String getImgData(byte[] image)
    {
        return Base64.getMimeEncoder().encodeToString(image);
    }
}
