package com.haymorg.drabc.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Treatment {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("treatment")
    @Expose
    private String treatment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
}
