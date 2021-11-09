package com.ownproject.ServiceOrganizer.Mapper;

import com.ownproject.ServiceOrganizer.Model.Mechanic;
import com.ownproject.ServiceOrganizer.Model.Schedule;
import org.apache.ibatis.annotations.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;


@Mapper
public interface ScheduleMapper {
    @Select("SELECT * FROM SCHEDULE")
    List<Schedule> getAllSchedules();

    @Select("SELECT * FROM serviceorganizer.schedule where mechanic=#{mechanic} and DATE(beginningtime)=#{date}")
    List<Schedule> getAllSchedulesOfMechanicByDate(String mechanic, LocalDate date);

    @Select("SELECT * FROM SCHEDULE where scheduleid=#{scheduleId}")
    Schedule getScheduleById(Integer scheduleId);

    @Select("SELECT * FROM SCHEDULE where repreqid=#{repReqId}")
    Schedule getScheduleByRepReqId(Integer repReqId);

    @Select("Select * from serviceorganizer.schedule where DATE(beginningtime)=#{date};")
    List<Schedule> getScheduleByDate(LocalDate date);

    @Select("select distinct cast(serviceorganizer.schedule.beginningtime AS DATE) from schedule;")
    List<LocalDate> getAllScheduledDates();

    @Select("SELECT * FROM MECHANICS")
    List<Mechanic> getAllMechanics();

    @Insert("INSERT into SCHEDULE (scheduleid, mechanic, beginningtime, duration, endtime,  repreqid) VALUES(#{scheduleId}, #{mechanic}, #{beginningTime},#{duration}, #{endTime},  #{repreqId}})")
    @Options(useGeneratedKeys = true, keyProperty = "scheduleId")
    int insert(Schedule schedule);

    @Delete("DELETE FROM SCHEDULE where scheduleid=#{scheduleId} and mechanic=#{mechanic}")
    int delete(Integer scheduleId, String mechanic);

    @Update("UPDATE SCHEDULE SET  mechanic=#{mechanic}, beginningtime=#{beginningTime}, duration=#{duration}, endtime=#{endTime},  repreqid=#{repReqId} WHERE scheduleid=#{scheduleId}")
    void updateSchedule(Integer scheduleId, String mechanic, Instant beginningTime, Instant endTime, Integer repreqId);


}
