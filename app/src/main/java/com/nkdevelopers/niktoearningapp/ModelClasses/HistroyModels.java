package com.nkdevelopers.niktoearningapp.ModelClasses;

public class HistroyModels {
    String DATE, TOTAL_MONEY;

    public HistroyModels() {

    }

    public HistroyModels(String DATE, String TOTAL_MONEY) {
        this.DATE = DATE;
        this.TOTAL_MONEY = TOTAL_MONEY;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getTOTAL_MONEY() {
        return TOTAL_MONEY;
    }

    public void setTOTAL_MONEY(String TOTAL_MONEY) {
        this.TOTAL_MONEY = TOTAL_MONEY;
    }
}
