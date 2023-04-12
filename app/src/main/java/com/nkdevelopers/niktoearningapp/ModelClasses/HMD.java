package com.nkdevelopers.niktoearningapp.ModelClasses;

public class HMD {

    String DATE, TOTAL_MONEY,UPIC;

    public HMD() {

    }

    public HMD(String DATE, String TOTAL_MONEY, String UPIC) {
        this.DATE = DATE;
        this.TOTAL_MONEY = TOTAL_MONEY;
        this.UPIC = UPIC;
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

    public String getUPIC() {
        return UPIC;
    }

    public void setUPIC(String UPIC) {
        this.UPIC = UPIC;
    }
}
