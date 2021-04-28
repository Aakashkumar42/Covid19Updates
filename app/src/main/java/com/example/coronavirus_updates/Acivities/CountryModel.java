package com.example.coronavirus_updates.Acivities;

public class CountryModel {

    private String flag,country,cases,todaycases,deaths,todaydeaths,recovered,active,critical
            ,casesPerOneMillion,deathsPerOneMillion,tests,testPerOneMillion;

    public CountryModel() {
    }

    public CountryModel(String flag, String country, String cases, String todaycases, String deaths, String todaydeaths, String recovered, String active, String critical, String casesPerOneMillion, String deathsPerOneMillion, String tests, String testPerOneMillion) {
        this.flag = flag;
        this.country = country;
        this.cases = cases;
        this.todaycases = todaycases;
        this.deaths = deaths;
        this.todaydeaths = todaydeaths;
        this.recovered = recovered;
        this.active = active;
        this.critical = critical;
        this.casesPerOneMillion = casesPerOneMillion;
        this.deathsPerOneMillion = deathsPerOneMillion;
        this.tests = tests;
        this.testPerOneMillion = testPerOneMillion;
    }

    public String getFlag() {
        return flag;
    }

    public String getCountry() {
        return country;
    }

    public String getCases() {
        return cases;
    }

    public String getTodaycases() {
        return todaycases;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getTodaydeaths() {
        return todaydeaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getActive() {
        return active;
    }

    public String getCritical() {
        return critical;
    }

    public String getCasesPerOneMillion() {
        return casesPerOneMillion;
    }

    public String getDeathsPerOneMillion() {
        return deathsPerOneMillion;
    }

    public String getTests() {
        return tests;
    }

    public String getTestPerOneMillion() {
        return testPerOneMillion;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public void setTodaycases(String todaycases) {
        this.todaycases = todaycases;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public void setTodaydeaths(String todaydeaths) {
        this.todaydeaths = todaydeaths;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }

    public void setCasesPerOneMillion(String casesPerOneMillion) {
        this.casesPerOneMillion = casesPerOneMillion;
    }

    public void setDeathsPerOneMillion(String deathsPerOneMillion) {
        this.deathsPerOneMillion = deathsPerOneMillion;
    }

    public void setTests(String tests) {
        this.tests = tests;
    }

    public void setTestPerOneMillion(String testPerOneMillion) {
        this.testPerOneMillion = testPerOneMillion;
    }
}
