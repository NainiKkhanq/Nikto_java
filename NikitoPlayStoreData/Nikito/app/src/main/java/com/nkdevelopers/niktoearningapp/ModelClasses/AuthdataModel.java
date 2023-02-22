package com.nkdevelopers.niktoearningapp.ModelClasses;


public class AuthdataModel {

    String Name, Email, Password, Notice, CDate;
    int Coins,Withdraw;


    public AuthdataModel() {
    }

    public AuthdataModel(String name, String email, String password, String notice, String CDate, int coins, int withdraw) {
        Name = name;
        Email = email;
        Password = password;
        Notice = notice;
        this.CDate = CDate;
        Coins = coins;
        Withdraw = withdraw;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getNotice() {
        return Notice;
    }

    public void setNotice(String notice) {
        Notice = notice;
    }

    public String getCDate() {
        return CDate;
    }

    public void setCDate(String CDate) {
        this.CDate = CDate;
    }

    public int getCoins() {
        return Coins;
    }

    public void setCoins(int coins) {
        Coins = coins;
    }

    public int getWithdraw() {
        return Withdraw;
    }

    public void setWithdraw(int withdraw) {
        Withdraw = withdraw;
    }
}



