package com.example.voyage_travel.Domains;

import java.io.Serializable;

public class PopularDomain implements Serializable {

    private String title;
    private String location;
    private String desc;
    private String topPlaces;
    private String src;

    private int price;
    private double Score;
    private int bed;
    private String pic;
    private boolean wifi;
    private boolean guide;


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

    public void setSrc(String src) {this.src = src;
    }

    public boolean getGuide() { return guide; }

    public void setGuide(boolean guide) { this.guide = guide;}


    public boolean getWifi() { return wifi; }

    public void setWifi(boolean wifi) { this.wifi = wifi;}


    public String getPic() { return pic; }

    public void setPic(String pic) { this.pic = pic;}

    public int getBed() { return bed; }

    public void setBed(int bed) { this.bed = bed;}

    public double getScore() { return Score; }

    public void setScore(double score) { this.Score = score;}


    public int getPrice() { return price; }

    public void setPrice(int price) { this.price = price;}

    public PopularDomain(String title, String location, String desc, String topPlaces, String src) {
        this.title = title;
        this.location = location;
        this.desc = desc;
        this.topPlaces = topPlaces;
        this.src = src;

        this.price = price;
        this.Score = Score;
        this.bed = bed;
        this.pic = pic;
        this.wifi = wifi;
        this.guide = guide;


    }
}
