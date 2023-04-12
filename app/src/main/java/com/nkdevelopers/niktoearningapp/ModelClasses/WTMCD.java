package com.nkdevelopers.niktoearningapp.ModelClasses;



public class WTMCD {

    String UserID, Email, Currency,COINS,W_Date,T_INT,T_RWD;



    public WTMCD() {
    }

    public WTMCD(String userID, String email, String currency, String COINS, String w_Date, String t_INT, String t_RWD) {
        UserID = userID;
        Email = email;
        Currency = currency;
        this.COINS = COINS;
        W_Date = w_Date;
        T_INT = t_INT;
        T_RWD = t_RWD;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public String getCOINS() {
        return COINS;
    }

    public void setCOINS(String COINS) {
        this.COINS = COINS;
    }

    public String getW_Date() {
        return W_Date;
    }

    public void setW_Date(String w_Date) {
        W_Date = w_Date;
    }

    public String getT_INT() {
        return T_INT;
    }

    public void setT_INT(String t_INT) {
        T_INT = t_INT;
    }

    public String getT_RWD() {
        return T_RWD;
    }

    public void setT_RWD(String t_RWD) {
        T_RWD = t_RWD;
    }
}