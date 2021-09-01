package com.ownproject.ServiceOrganizer.Model;

import java.sql.Time;
import java.time.LocalDate;

public class ScheduleForm {
    private Integer scheduleId;
    private String mechanic;
    private Time beginningTime;
    private Time endTime;
    private LocalDate reparationDate;
    private Integer repReqId;

    public ScheduleForm(){};

    public String getMechanic() {
        return mechanic;
    }

    public void setMechanic(String mechanic) {
        this.mechanic = mechanic;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
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

    public Integer getRepReqId() {
        return repReqId;
    }

    public void setRepReqId(Integer repReqId) {
        this.repReqId = repReqId;
    }
}
