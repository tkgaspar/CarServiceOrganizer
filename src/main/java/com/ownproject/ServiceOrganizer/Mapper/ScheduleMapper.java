package com.ownproject.ServiceOrganizer.Mapper;

import com.ownproject.ServiceOrganizer.Model.Schedule;
import org.apache.ibatis.annotations.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ScheduleMapper {
    @Select("SELECT * FROM SCHEDULE")
    List<Schedule> getAllSchedules();

    @Insert("INSERT into SCHEDULE (scheduleid, mechanic, beginningtime, endtime, reparationdate, repreqid) VALUES(#{scheduleId}, #{mechanic}, #{beginningTime}, #{endTime}, #{reparationDate}, #{repreqId})")
    @Options(useGeneratedKeys = true, keyProperty="scheduleId")
    int insert(Schedule schedule);

    @Delete("DELETE FROM SCHEDULE where scheduleid=#{scheduleId} and mechanic=#{mechanic}")
    int delete(Integer scheduleId,String mechanic);

    @Update ("UPDATE SCHEDULE SET  mechanic=#{mechanic}, beginningtime=#{beginningTime}, endtime=#{endTime}, reparationdate=#{reparationDate}, repreqid=#{repReqId} WHERE scheduleid=#{scheduleId}")
    void updateSchedule(Integer scheduleId, String mechanic, Time beginningTime, Time endTime, LocalDate reparationDate, Integer repreqId);
}
