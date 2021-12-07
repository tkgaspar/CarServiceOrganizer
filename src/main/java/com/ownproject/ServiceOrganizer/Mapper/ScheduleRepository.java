package com.ownproject.ServiceOrganizer.Mapper;
import com.ownproject.ServiceOrganizer.Model.Schedule;
import org.apache.ibatis.annotations.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Transactional
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    Schedule findByRepReqId(Integer repreqId);

    List<Schedule> findAllByMechanicAndByDate(String mechanic, LocalDate date);

    void deleteById(Integer id);

    List<Schedule> findByDate(LocalDate date);

    @Query("select distinct cast(serviceorganizer.schedule.beginningtime AS DATE) from schedule;")
    List<LocalDate> getAllScheduledDates();


//    @Update("UPDATE SCHEDULE SET  mechanic=#{mechanic}, beginningtime=#{beginningTime}, duration=#{duration}, endtime=#{endTime},  repreqid=#{repReqId} WHERE scheduleid=#{scheduleId}")
  //  void updateSchedule(Integer scheduleId, String mechanic, Instant beginningTime, Instant endTime, Integer repreqId);


}
