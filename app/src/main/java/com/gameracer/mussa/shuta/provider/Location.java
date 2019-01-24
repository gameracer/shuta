package com.gameracer.mussa.shuta.provider;

public class Location {
    int ID;
    String region,district,word;

    public Location() {
    }

    public Location(int ID, String region, String district, String word) {
        this.ID = ID;
        this.region = region;
        this.district = district;
        this.word = word;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
