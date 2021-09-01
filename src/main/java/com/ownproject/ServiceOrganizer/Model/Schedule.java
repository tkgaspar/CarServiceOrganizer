package com.ownproject.ServiceOrganizer.Model;

import java.sql.Time;
import java.time.LocalDate;

public class Schedule {
    private Integer scheduleId;
    private String mechanic;
    private Time beginningTime;
    private Time endTime;
    private LocalDate reparationDate;
    private Integer repreqId;

    public Schedule() {
    }

    public Schedule(Integer scheduleId, String mechanic, Time beginningTime, Time endTime, LocalDate reparationDate, Integer repreqId) {
        this.scheduleId = scheduleId;
        this.mechanic = mechanic;
        this.beginningTime = beginningTime;
        this.endTime = endTime;
        this.reparationDate = reparationDate;
        this.repreqId = repreqId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getMechanic() {
        return mechanic;
    }

    public void setMechanic(String mechanic) {
        this.mechanic = mechanic;
    }

    public Time getBeginningTime() {
        return beginningTime;
    }

    public void setBeginningTime(Time beginningTime) {
        this.beginningTime = beginningTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public LocalDate getReparationDate() {
        return reparationDate;
    }

    public void setReparationDate(LocalDate reparationDate) {
        this.reparationDate = reparationDate;
    }

    public Integer getRepreqId() {
        return repreqId;
    }

    public void setRepreqId(Integer repreqId) {
        this.repreqId = repreqId;
    }
}
