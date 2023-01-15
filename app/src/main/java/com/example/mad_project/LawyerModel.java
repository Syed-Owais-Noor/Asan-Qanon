package com.example.mad_project;

public class LawyerModel {
    String name, email, phoneNo, whatsAppNo, experience, cases;

    public LawyerModel() { }

    public LawyerModel(String name, String email, String phoneNo, String whatsAppNo, String experience, String cases) {
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.whatsAppNo = whatsAppNo;
        this.experience = experience;
        this.cases = cases;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getWhatsAppNo() {
        return whatsAppNo;
    }

    public void setWhatsAppNo(String whatsAppNo) {
        this.whatsAppNo = whatsAppNo;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }
}
