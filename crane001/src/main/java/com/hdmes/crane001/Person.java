package com.hdmes.crane001;

/**
 * Created by Administrator on 2017/9/11 0011.
 */

public class Person  {
    public int fu_id ;

    public Person(String name, String address, int age) {
    }

    public int getFu_id() {
        return fu_id;
    }

    public void setFu_id(int fu_id) {
        this.fu_id = fu_id;
    }

    public String getClientIP() {
        return ClientIP;
    }

    public void setClientIP(String clientIP) {
        ClientIP = clientIP;
    }

    public String getOverLoadHour() {
        return OverLoadHour;
    }

    public void setOverLoadHour(String overLoadHour) {
        OverLoadHour = overLoadHour;
    }

    public String getOverLoadMinute() {
        return OverLoadMinute;
    }

    public void setOverLoadMinute(String overLoadMinute) {
        OverLoadMinute = overLoadMinute;
    }

    public String getOverLoadSecond() {
        return OverLoadSecond;
    }

    public void setOverLoadSecond(String overLoadSecond) {
        OverLoadSecond = overLoadSecond;
    }

    public String ClientIP ;
    public String OverLoadHour ;
    public String OverLoadMinute ;
    public String OverLoadSecond ;

    public String getOverLoadDay() {
        return OverLoadDay;
    }

    public void setOverLoadDay(String overLoadDay) {
        OverLoadDay = overLoadDay;
    }

    public String getOverLoad() {
        return OverLoad;
    }

    public void setOverLoad(String overLoad) {
        OverLoad = overLoad;
    }

    public String getStartHour() {
        return StartHour;
    }

    public void setStartHour(String startHour) {
        StartHour = startHour;
    }

    public String getStartMinute() {
        return StartMinute;
    }

    public void setStartMinute(String startMinute) {
        StartMinute = startMinute;
    }

    public String getStartSecond() {
        return StartSecond;
    }

    public void setStartSecond(String startSecond) {
        StartSecond = startSecond;
    }

    public String getStartYear() {
        return StartYear;
    }

    public void setStartYear(String startYear) {
        StartYear = startYear;
    }

    public String getStartMonth() {
        return StartMonth;
    }

    public void setStartMonth(String startMonth) {
        StartMonth = startMonth;
    }

    public String getStartDay() {
        return StartDay;
    }

    public void setStartDay(String startDay) {
        StartDay = startDay;
    }

    public String getStopHour() {
        return StopHour;
    }

    public void setStopHour(String stopHour) {
        StopHour = stopHour;
    }

    public String getStopMinute() {
        return StopMinute;
    }

    public void setStopMinute(String stopMinute) {
        StopMinute = stopMinute;
    }

    public String getStopSecond() {
        return StopSecond;
    }

    public void setStopSecond(String stopSecond) {
        StopSecond = stopSecond;
    }

    public String getStopYear() {
        return StopYear;
    }

    public void setStopYear(String stopYear) {
        StopYear = stopYear;
    }

    public String getStopMonth() {
        return StopMonth;
    }

    public void setStopMonth(String stopMonth) {
        StopMonth = stopMonth;
    }

    public String getStopDay() {
        return StopDay;
    }

    public void setStopDay(String stopDay) {
        StopDay = stopDay;
    }

    public String getLiftingCapacity() {
        return LiftingCapacity;
    }

    public void setLiftingCapacity(String liftingCapacity) {
        LiftingCapacity = liftingCapacity;
    }

    public String getEquipmentNum() {
        return EquipmentNum;
    }

    public void setEquipmentNum(String equipmentNum) {
        EquipmentNum = equipmentNum;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getStopTime() {
        return StopTime;
    }

    public void setStopTime(String stopTime) {
        StopTime = stopTime;
    }

    public String getOverLoadYear() {
        return OverLoadYear;
    }

    public void setOverLoadYear(String overLoadYear) {
        OverLoadYear = overLoadYear;
    }

    public String getOverLoadMonth() {
        return OverLoadMonth;
    }

    public void setOverLoadMonth(String overLoadMonth) {
        OverLoadMonth = overLoadMonth;
    }

    public String getStopDate() {
        return StopDate;
    }

    public void setStopDate(String stopDate) {
        StopDate = stopDate;
    }

    public String getKp() {
        return Kp;
    }

    public void setKp(String kp) {
        Kp = kp;
    }

    public String OverLoadYear ;
    public String OverLoadMonth ;
    public String OverLoadDay ;
    public String OverLoad ;
    //10
    public String StartHour ;
    public String StartMinute;
    public String StartSecond;
    public String StartYear ;
    public String StartMonth ;
    public String StartDay ;
    public String StopHour ;
    public String StopMinute ;
    public String StopSecond ;
    public String StopYear;
    //20
    public String StopMonth;
    public String StopDay ;
    public String LiftingCapacity ;
    public String EquipmentNum ;//设备识别码
    public String StartTime ;
    public String StartDate;
    public String StopTime ;
    public String StopDate ;
    //public string numm { get; set; }////////////////////////////////////////////////////数据库必须随之新加的一列

    public String Kp ;////////////////////////////////////////////////////数据库必须随之新加的一列

}
