package com.resi.nasa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Photo
{
    private String id;
    private String sol;
    private Camera camera;
    @JsonProperty("img_src")
    private String imgSrc;
    @JsonProperty("earth_date")
    private String earthDate;
    private Rover rover;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getSol()
    {
        return sol;
    }

    public void setSol(String sol)
    {
        this.sol = sol;
    }

    public Camera getCamera()
    {
        return camera;
    }

    public void setCamera(Camera camera)
    {
        this.camera = camera;
    }

    public String getImgSrc()
    {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc)
    {
        this.imgSrc = imgSrc;
    }

    public String getEarthDate()
    {
        return earthDate;
    }

    public void setEarthDate(String earthDate)
    {
        this.earthDate = earthDate;
    }

    public Rover getRover()
    {
        return rover;
    }

    public void setRover(Rover rover)
    {
        this.rover = rover;
    }
}
