package com.example.predator.nulpandroid.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Predator on 08.11.2018.
 */
public class Hero {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("status")
    private String status;
    @SerializedName("species")
    private String species;
    @SerializedName("type")
    private String type;
    @SerializedName("gender")
    private String gender;
    @SerializedName("image")
    private String image;
    @SerializedName("url")
    private String url;
    @SerializedName("created")
    private String created;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getSpecies() {
        return species;
    }

    public String getType() {
        return type;
    }

    public String getGender() {
        return gender;
    }

    public String getImage() {
        return image;
    }

    public String getCreated() {
        return created;
    }

    public Hero(String name, String species, String created, String image) {
        this.name = name;
        this.species = species;
        this.image = image;
        this.created = created;
    }
}