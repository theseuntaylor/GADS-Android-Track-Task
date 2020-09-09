package com.theseuntaylor.aadproject.model;

import com.google.gson.annotations.SerializedName;

public class LearningLeadersResponse {
    @SerializedName("name")
    private String name;
    @SerializedName("hours")
    private int hours;
    @SerializedName("country")
    private String country;
    @SerializedName("badgeUrl")
    private String badgeUrl;;

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
