package com.ownproject.ServiceOrganizer.Services;

import com.ownproject.ServiceOrganizer.Mapper.ScheduleMapper;
import com.ownproject.ServiceOrganizer.Model.Mechanic;
import com.ownproject.ServiceOrganizer.Model.Schedule;
import com.ownproject.ServiceOrganizer.Model.ScheduleForm;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.util.List;

import static java.time.temporal.ChronoUnit.HOURS;

@Service
public class ScheduleService {
    private ScheduleMapper scheduleMapper;

    public ScheduleService(ScheduleMapper scheduleMapper) {
        this.scheduleMapper = scheduleMapper;
    }

    public List<Schedule> getAllSchedules() {
        return this.scheduleMapper.getAllSchedules();
    }

    public Schedule getScheduleById(Integer scheduleId) {
        return this.scheduleMapper.getScheduleById(scheduleId);
    }

    public Schedule getScheduleByRepReqId(Integer repReqId) {
        return this.scheduleMapper.getScheduleByRepReqId(repReqId);
    }

    public List<Schedule> getSchedulesByDate(Instant reparationDate) {
        return this.scheduleMapper.getScheduleByDate(reparationDate);
    }

    public List<Mechanic> allMechanics() {
        return this.scheduleMapper.getAllMechanics();
    }

    public Integer addSchedule(ScheduleForm scheduleForm) {
        Schedule schedule = new Schedule();
        schedule.setBeginningTime(Instant.parse(scheduleForm.getBeginningTime()));
        schedule.setEndTime(Instant.parse(scheduleForm.getBeginningTime()).plus(scheduleForm.getDuration(), HOURS));
        schedule.setRepreqId(scheduleForm.getRepReqId());
        return this.scheduleMapper.insert(schedule);
    }

    public Integer deleteSchedule(Integer scheduleId, String mechanic) {
        return this.scheduleMapper.delete(scheduleId, mechanic);
    }

    public void updateSchedule(ScheduleForm sch) {
        this.scheduleMapper.updateSchedule(sch.getScheduleId(), sch.getMechanic(), Instant.parse(sch.getBeginningTime()), Instant.parse(sch.getEndTime()), sch.getRepReqId());
    }

    public Instant convertToInstant(String date) {
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd'T'HH:mm");
        Instant localDate = Instant.parse(date);
        return localDate;
    }
}
