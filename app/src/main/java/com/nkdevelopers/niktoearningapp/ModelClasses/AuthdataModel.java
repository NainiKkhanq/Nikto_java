package com.nkdevelopers.niktoearningapp.ModelClasses;


public class AuthdataModel {


    String NAME;
    String EMAIL;
    String UPIC;

    String JOIN_DATE,notice;
    int coins, scratch, spins ,diceroll ,withdraw , REWARD_AD,INTER_AD,T_PTC,PTC_WATCH;

    public AuthdataModel() {
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getUPIC() {
        return UPIC;
    }

    public void setUPIC(String UPIC) {
        this.UPIC = UPIC;
    }

    public String getJOIN_DATE() {
        return JOIN_DATE;
    }

    public void setJOIN_DATE(String JOIN_DATE) {
        this.JOIN_DATE = JOIN_DATE;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getScratch() {
        return scratch;
    }

    public void setScratch(int scratch) {
        this.scratch = scratch;
    }

    public int getSpins() {
        return spins;
    }

    public void setSpins(int spins) {
        this.spins = spins;
    }

    public int getDiceroll() {
        return diceroll;
    }

    public void setDiceroll(int diceroll) {
        this.diceroll = diceroll;
    }

    public int getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(int withdraw) {
        this.withdraw = withdraw;
    }

    public int getREWARD_AD() {
        return REWARD_AD;
    }

    public void setREWARD_AD(int REWARD_AD) {
        this.REWARD_AD = REWARD_AD;
    }

    public int getINTER_AD() {
        return INTER_AD;
    }

    public void setINTER_AD(int INTER_AD) {
        this.INTER_AD = INTER_AD;
    }

    public int getT_PTC() {
        return T_PTC;
    }

    public void setT_PTC(int t_PTC) {
        T_PTC = t_PTC;
    }

    public int getPTC_WATCH() {
        return PTC_WATCH;
    }

    public void setPTC_WATCH(int PTC_WATCH) {
        this.PTC_WATCH = PTC_WATCH;
    }

    public AuthdataModel(String NAME, String EMAIL, String UPIC, String JOIN_DATE, String notice, int coins, int scratch, int spins, int diceroll, int withdraw, int REWARD_AD, int INTER_AD, int t_PTC, int PTC_WATCH) {
        this.NAME = NAME;
        this.EMAIL = EMAIL;
        this.UPIC = UPIC;
        this.JOIN_DATE = JOIN_DATE;
        this.notice = notice;
        this.coins = coins;
        this.scratch = scratch;
        this.spins = spins;
        this.diceroll = diceroll;
        this.withdraw = withdraw;
        this.REWARD_AD = REWARD_AD;
        this.INTER_AD = INTER_AD;
        T_PTC = t_PTC;
        this.PTC_WATCH = PTC_WATCH;
    }
}




