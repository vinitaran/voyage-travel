package com.example.voyage_travel.Domains;

import java.io.Serializable;

public class PopularDomain implements Serializable {

    private String title;
    private String location;
    private String desc;
    private String topPlaces;
    private String src;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTopPlaces() {
        return topPlaces;
    }

    public void setTopPlaces(String topPlaces) {
        this.topPlaces = topPlaces;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public PopularDomain(String title, String location, String desc, String topPlaces, String src){
        this.title = title;
        this.location = location;
        this.desc = desc;
        this.topPlaces = topPlaces;
        this.src = src;

    }




}
