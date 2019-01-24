package com.gameracer.mussa.shuta.provider;

public class Academic {
    String academicID;
    String Year, Name;

    public Academic(String academicID, String year, String name) {
        this.academicID = academicID;
        Year = year;
        Name = name;
    }

    public Academic() {
    }

    public String getAcademicID() {
        return academicID;
    }

    public void setAcademicID(String academicID) {
        this.academicID = academicID;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
