package com.nkdevelopers.niktoearningapp.ModelClasses;


public class WithdrawModel {

    String UserID, Email, Currency;
    int INTAD, RWRDAD;

    public WithdrawModel() {
    }

    public WithdrawModel(String userID, String email, String currency, int INTAD, int RWRDAD) {
        UserID = userID;
        Email = email;
        Currency = currency;
        this.INTAD = INTAD;
        this.RWRDAD = RWRDAD;
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

    public int getINTAD() {
        return INTAD;
    }

    public void setINTAD(int INTAD) {
        this.INTAD = INTAD;
    }

    public int getRWRDAD() {
        return RWRDAD;
    }

    public void setRWRDAD(int RWRDAD) {
        this.RWRDAD = RWRDAD;
    }
}