package com.gameracer.mussa.shuta.provider;

import android.support.annotation.Nullable;

public class Student {
    String ID;
    String fname, mname,lname,password, gender, pgname,pgPhoneNo,pgPoBox,stClass,location;
    int location_id;

    public Student(String studentID, String fnm, String mnm, String lnm, String pwd, String gender, String pgnm, String pgPhNo, String pgPBox, String stClass, String location, int o) {
    }

    public Student(String ID, String fname, String mname, String lname, String password, String gender, String pgname, String pgPhoneNo, String pgPoBox, @Nullable String stClass, @Nullable String location) {
        this.ID = ID;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.password = password;
        this.gender = gender;
        this.pgname = pgname;
        this.pgPhoneNo = pgPhoneNo;
        this.pgPoBox = pgPoBox;
        this.stClass = stClass;
        this.location = location;
//        this.location_id = location_id;
    }

    public String getPgPoBox() {
        return pgPoBox;
    }

    public void setPgPoBox(String pgPoBox) {
        this.pgPoBox = pgPoBox;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPgname() {
        return pgname;
    }

    public void setPgname(String pgname) {
        this.pgname = pgname;
    }

    public String getPgPhoneNo() {
        return pgPhoneNo;
    }

    public void setPgPhoneNo(String pgPhoneNo) {
        this.pgPhoneNo = pgPhoneNo;
    }


    public String getStClass() {
        return stClass;
    }

    public void setStClass(String stClass) {
        this.stClass = stClass;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }
}
