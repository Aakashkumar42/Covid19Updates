package com.example.coronavirus_updates.Acivities;

public class DistrictModelClass {

    private String districtdeceased,districtActive,districtConfirmed;

    public DistrictModelClass() {
    }

    public DistrictModelClass(String districtdeceased, String districtActive, String districtConfirmed) {
        this.districtdeceased = districtdeceased;
        this.districtActive = districtActive;
        this.districtConfirmed = districtConfirmed;
    }

    public String getDistrictdeceased() {
        return districtdeceased;
    }

    public String getDistrictActive() {
        return districtActive;
    }

    public String getDistrictConfirmed() {
        return districtConfirmed;
    }
}
