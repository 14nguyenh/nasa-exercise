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

    public byte[] getImage()
    {
        return image;
    }

    public void setImage(byte[] image)
    {
        this.image = image;
    }
}
