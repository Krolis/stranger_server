package com.yeahbunny.stranger.server.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Calendar;

/**
 * Created by kroli on 30.05.2017.
 */
public class AddEventRequest {

    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
    @NotNull
    private Calendar dateStart;
    @NotNull
    private Integer durationHours;
    @NotNull
    @NotEmpty
    private String title;
    @NotNull
    @NotEmpty
    private String place;
    @NotNull
    @NotEmpty
    private String description;

    private int maxAttenders;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Calendar getDateStart() {
        return dateStart;
    }

    public void setDateStart(Calendar dateStart) {
        this.dateStart = dateStart;
    }

    public Integer getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(Integer durationHours) {
        this.durationHours = durationHours;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxAttenders() {
        return maxAttenders;
    }

    public void setMaxAttenders(int maxAttenders) {
        this.maxAttenders = maxAttenders;
    }

    @Override
    public String toString() {
        return "AddEventRequest{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", dateStart=" + dateStart +
                ", durationHours=" + durationHours +
                ", title='" + title + '\'' +
                ", place='" + place + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
