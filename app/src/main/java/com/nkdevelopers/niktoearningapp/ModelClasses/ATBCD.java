package com.nkdevelopers.niktoearningapp.ModelClasses;


import androidx.annotation.Keep;
@Keep

public class ATBCD {

    @Keep

    String NAME;
    String EMAIL;
    String UPIC;

    String JOIN_DATE,notice;
    int coins, scratch, spins ,diceroll ,withdraw , REWARD_AD,INTER_AD;
    @Keep

    public ATBCD() {
    }
    @Keep

    public ATBCD(String NAME, String EMAIL, String UPIC, String JOIN_DATE, String notice, int coins, int scratch, int spins, int diceroll, int withdraw, int REWARD_AD, int INTER_AD) {
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
    }
    @Keep

    public String getNAME() {
        return NAME;
    }
    @Keep

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }
    @Keep

    public String getEMAIL() {
        return EMAIL;
    }
    @Keep

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }
    @Keep

    public String getUPIC() {
        return UPIC;
    }
    @Keep

    public void setUPIC(String UPIC) {
        this.UPIC = UPIC;
    }
    @Keep

    public String getJOIN_DATE() {
        return JOIN_DATE;
    }
    @Keep

    public void setJOIN_DATE(String JOIN_DATE) {
        this.JOIN_DATE = JOIN_DATE;
    }
    @Keep

    public String getNotice() {
        return notice;
    }
    @Keep

    public void setNotice(String notice) {
        this.notice = notice;
    }
    @Keep

    public int getCoins() {
        return coins;
    }
    @Keep

    public void setCoins(int coins) {
        this.coins = coins;
    }
    @Keep

    public int getScratch() {
        return scratch;
    }
    @Keep

    public void setScratch(int scratch) {
        this.scratch = scratch;
    }
    @Keep

    public int getSpins() {
        return spins;
    }
    @Keep

    public void setSpins(int spins) {
        this.spins = spins;
    }
    @Keep

    public int getDiceroll() {
        return diceroll;
    }
    @Keep

    public void setDiceroll(int diceroll) {
        this.diceroll = diceroll;
    }
    @Keep

    public int getWithdraw() {
        return withdraw;
    }
    @Keep

    public void setWithdraw(int withdraw) {
        this.withdraw = withdraw;
    }
    @Keep

    public int getREWARD_AD() {
        return REWARD_AD;
    }
    @Keep

    public void setREWARD_AD(int REWARD_AD) {
        this.REWARD_AD = REWARD_AD;
    }
    @Keep

    public int getINTER_AD() {
        return INTER_AD;
    }
    @Keep

    public void setINTER_AD(int INTER_AD) {
        this.INTER_AD = INTER_AD;
    }
}




