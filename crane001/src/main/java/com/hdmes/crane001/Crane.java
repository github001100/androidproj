package com.hdmes.crane001;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/21.
 */

public class Crane implements Serializable {
    private String Fu_Id;
    private String Equi_Num;
    private String CraneIP;
    private String Capacity;
    private String CurrentRating;
    private String OverLoadTips;
    private String OverLoadNum;
    private String OverCurrentNum;
    private String OverLoadWarming;
    private String WorkHour;
    private String WorkSecond;
    private String MotorStartTimes;
    private String CraneAres;
    private String CraneType;

    public byte[] getImgData() {
        return imgData;
    }

    public void setImgData(byte[] imgData) {
        this.imgData = imgData;
    }

    private byte imgData [];

    public String getCranePath() {
        return CranePath;
    }

    public void setCranePath(String cranePath) {
        CranePath = cranePath;
    }

    private  String CranePath;
    public String getFu_Id() {
        return Fu_Id;
    }

    public void setFu_Id(String fu_Id) {
        Fu_Id = fu_Id;
    }

    public String getEqui_Num() {
        return Equi_Num;
    }

    public void setEqui_Num(String equi_Num) {
        Equi_Num = equi_Num;
    }

    public String getCraneIP() {
        return CraneIP;
    }

    public void setCraneIP(String craneIP) {
        CraneIP = craneIP;
    }

    public String getCapacity() {
        return Capacity;
    }

    public void setCapacity(String capacity) {
        Capacity = capacity;
    }

    public String getCurrentRating() {
        return CurrentRating;
    }

    public void setCurrentRating(String currentRating) {
        CurrentRating = currentRating;
    }

    public String getOverLoadTips() {
        return OverLoadTips;
    }

    public void setOverLoadTips(String overLoadTips) {
        OverLoadTips = overLoadTips;
    }

    public String getOverLoadNum() {
        return OverLoadNum;
    }

    public void setOverLoadNum(String overLoadNum) {
        OverLoadNum = overLoadNum;
    }

    public String getOverCurrentNum() {
        return OverCurrentNum;
    }

    public void setOverCurrentNum(String overCurrentNum) {
        OverCurrentNum = overCurrentNum;
    }

    public String getOverLoadWarming() {
        return OverLoadWarming;
    }

    public void setOverLoadWarming(String overLoadWarming) {
        OverLoadWarming = overLoadWarming;
    }

    public String getWorkHour() {
        return WorkHour;
    }

    public void setWorkHour(String workHour) {
        WorkHour = workHour;
    }

    public String getWorkSecond() {
        return WorkSecond;
    }

    public void setWorkSecond(String workSecond) {
        WorkSecond = workSecond;
    }

    public String getMotorStartTimes() {
        return MotorStartTimes;
    }

    public void setMotorStartTimes(String motorStartTimes) {
        MotorStartTimes = motorStartTimes;
    }

    public String getCraneAres() {
        return CraneAres;
    }

    public void setCraneAres(String craneAres) {
        CraneAres = craneAres;
    }

    public String getCraneType() {
        return CraneType;
    }

    public void setCraneType(String craneType) {
        CraneType = craneType;
    }


}
