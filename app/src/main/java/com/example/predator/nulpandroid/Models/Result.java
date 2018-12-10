package com.example.predator.nulpandroid.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Predator on 08.11.2018.
 */
public class Result {
    @SerializedName("results")
    private List<Hero> results = null;

    public List<Hero> getResults() {
        return results;
    }
}