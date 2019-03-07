package com.example.adminapp.addPoint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddPointBody {

    @SerializedName("device_id")
    @Expose
    private String diviceId;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("building_id")
    @Expose
    private String buildingId;

    public String getDiviceId() {
        return diviceId;
    }

    public void setDiviceId(String diviceId) {
        this.diviceId = diviceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public AddPointBody(String diviceId, String title, String buildingId) {
        this.diviceId = diviceId;
        this.title = title;
        this.buildingId = buildingId;
    }
}
