package com.example.coronavirus_updates.Acivities;

public class helplinelist {
    private String contact_no;
    private String stateName;

    public helplinelist(String contact_no, String stateName) {
        this.contact_no = contact_no;
        this.stateName = stateName;
    }

    public String getContact_no() {
        return contact_no;
    }

    public String getStateName() {
        return stateName;
    }
}
