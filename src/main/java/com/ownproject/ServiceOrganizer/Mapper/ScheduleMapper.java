package com.ownproject.ServiceOrganizer.Mapper;

import com.ownproject.ServiceOrganizer.Model.Mechanic;
import com.ownproject.ServiceOrganizer.Model.Schedule;
import org.apache.ibatis.annotations.*;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ScheduleMapper {
    @Select("SELECT * FROM SCHEDULE")
    List<Schedule> getAllSchedules();

    @Select("SELECT * FROM SCHEDULE where scheduleid=#{scheduleId}")
    Schedule getScheduleById(Integer scheduleId);

    @Select("SELECT * FROM SCHEDULE where repreqid=#{repReqId}")
    Schedule getScheduleByRepReqId(Integer repReqId);

    @Select("SELECT * FROM SCHEDULE where beginningTime=#{beginningtime}")
    List<Schedule>getScheduleByDate(Instant beginningTime);

    @Select("SELECT * FROM MECHANICS")
    List<Mechanic>getAllMechanics();

    @Insert("INSERT into SCHEDULE (scheduleid, mechanic, beginningtime, endtime,  repreqid) VALUES(#{scheduleId}, #{mechanic}, #{beginningTime}, #{endTime},  #{repreqId})")
    @Options(useGeneratedKeys = true, keyProperty="scheduleId")
    int insert(Schedule schedule);

    @Delete("DELETE FROM SCHEDULE where scheduleid=#{scheduleId} and mechanic=#{mechanic}")
    int delete(Integer scheduleId,String mechanic);

    @Update ("UPDATE SCHEDULE SET  mechanic=#{mechanic}, beginningtime=#{beginningTime}, endtime=#{endTime},  repreqid=#{repReqId} WHERE scheduleid=#{scheduleId}")
    void updateSchedule(Integer scheduleId, String mechanic, Instant beginningTime, Instant endTime, Integer repreqId);


}
