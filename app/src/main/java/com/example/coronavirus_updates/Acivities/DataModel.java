package com.example.coronavirus_updates.Acivities;

public class DataModel {

    private String active,confirmed,deaths,deltaconfirmed,deltadeaths,deltarecovered
            ,lastupdatedtime,recovered,state,statecode,statenotes;

    public DataModel() {
    }

    public DataModel(String active, String confirmed, String deaths, String deltaconfirmed, String deltadeaths, String deltarecovered, String lastupdatedtime, String recovered, String state, String statecode, String statenotes) {
        this.active = active;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.deltaconfirmed = deltaconfirmed;
        this.deltadeaths = deltadeaths;
        this.deltarecovered = deltarecovered;
        this.lastupdatedtime = lastupdatedtime;
        this.recovered = recovered;
        this.state = state;
        this.statecode = statecode;
        this.statenotes = statenotes;
    }

    public String getActive() {
        return active;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getDeltaconfirmed() {
        return deltaconfirmed;
    }

    public String getDeltadeaths() {
        return deltadeaths;
    }

    public String getDeltarecovered() {
        return deltarecovered;
    }

    public String getLastupdatedtime() {
        return lastupdatedtime;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getState() {
        return state;
    }

    public String getStatecode() {
        return statecode;
    }

    public String getStatenotes() {
        return statenotes;
    }
}
