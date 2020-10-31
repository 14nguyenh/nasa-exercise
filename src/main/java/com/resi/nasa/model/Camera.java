package com.resi.nasa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Camera
{
    private String id;
    private String name;
    @JsonProperty("rover_name")
    private String roverId;
    @JsonProperty("full_name")
    private String fullName;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getRoverId()
    {
        return roverId;
    }

    public void setRoverId(String roverId)
    {
        this.roverId = roverId;
    }

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }
}
