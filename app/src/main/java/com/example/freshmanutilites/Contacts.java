package com.example.freshmanutilites;

public class Contacts {

    private String tDesignation, tName, tMobNo, tEmail, tRoomNo;

    private boolean expandable;

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public Contacts(String tDesignation, String tName, String tMobNo, String tEmail, String tRoomNo) {
        this.tDesignation = tDesignation;
        this.tName = tName;
        this.tMobNo = tMobNo;
        this.tEmail = tEmail;
        this.tRoomNo = tRoomNo;
        this.expandable = false;
    }

    public String gettDesignation() {
        return tDesignation;
    }

    public String gettName() {
        return tName;
    }

    public String gettMobNo() {
        return tMobNo;
    }

    public String gettEmail() {
        return tEmail;
    }

    public String gettRoomNo() {
        return tRoomNo;
    }

    public void settDesignation(String tDesignation) {
        this.tDesignation = tDesignation;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public void settMobNo(String tMobNo) {
        this.tMobNo = tMobNo;
    }

    public void settEmail(String tEmail) {
        this.tEmail = tEmail;
    }

    public void settRoomNo(String tRoomNo) {
        this.tRoomNo = tRoomNo;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "tDesignation='" + tDesignation + '\'' +
                ", tName='" + tName + '\'' +
                ", tMobNo='" + tMobNo + '\'' +
                ", tEmail='" + tEmail + '\'' +
                ", tRoomNo='" + tRoomNo + '\'' +
                '}';
    }
}
