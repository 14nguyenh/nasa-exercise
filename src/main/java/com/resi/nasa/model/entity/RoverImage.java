package com.resi.nasa.model.entity;

import javax.persistence.*;
import java.awt.*;
import java.util.Date;

@Entity
public class RoverImage
{
    @Id
    @GeneratedValue
    private Long id;

    private String fileType;

    @Lob
    private byte[] image;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getFileType()
    {
        return fileType;
    }

    public void setFileType(String fileType)
    {
        this.fileType = fileType;
    }


    public byte[] getImage()
    {
        return image;
    }

    public void setImage(byte[] image)
    {
        this.image = image;
    }
}
