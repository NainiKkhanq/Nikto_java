package com.nkdevelopers.niktoearningapp.ModelClasses;


public class WithdrawModel {

    String UserID, Email, RequestedBy;


    public WithdrawModel() {
    }

    public WithdrawModel(String userID, String email, String requestedBy) {
        UserID = userID;
        Email = email;
        RequestedBy = requestedBy;
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

    public String getRequestedBy() {
        return RequestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        RequestedBy = requestedBy;
    }
}

