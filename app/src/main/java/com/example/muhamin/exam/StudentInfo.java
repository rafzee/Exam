package com.example.muhamin.exam;


import java.util.Date;

public class StudentInfo {

    private String name, level, credit, gender, sID;
    private Date dob;

    public StudentInfo() {

    }
    public StudentInfo(String name, String level, String credit, String gender, String sID, Date dob) {
        this.name = name;
        this.level = level;
        this.credit = credit;
        this.gender = gender;
        this.sID = sID;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public Date getDob() { return dob; }

    public void setDob(Date dob) { this.dob = dob; }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getsID() {
        return sID;
    }

    public void setsID(String sID) {
        this.sID = sID;
    }
}
