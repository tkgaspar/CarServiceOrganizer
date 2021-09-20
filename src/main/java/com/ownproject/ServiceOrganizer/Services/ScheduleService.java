package com.ownproject.ServiceOrganizer.Services;

import com.ownproject.ServiceOrganizer.Mapper.ScheduleMapper;
import com.ownproject.ServiceOrganizer.Model.Mechanic;
import com.ownproject.ServiceOrganizer.Model.Schedule;
import com.ownproject.ServiceOrganizer.Model.ScheduleForm;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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

    public List<Schedule> getSchedulesByDate(LocalDate reparationDate) {
        return this.scheduleMapper.getScheduleByDate(reparationDate);
    }

    public List<Mechanic> allMechanics() {
        return this.scheduleMapper.getAllMechanics();
    }

    public List<LocalDate>getAllScheduledDates(){
        return this.scheduleMapper.getAllScheduledDates().stream().sorted().collect(Collectors.toList());
    }

    public Integer addSchedule(ScheduleForm scheduleForm) {
        Schedule schedule = new Schedule();
        schedule.setMechanic(scheduleForm.getMechanic());
        schedule.setBeginningTime(Instant.parse(scheduleForm.getBeginningTime()));
        schedule.setEndTime(schedule.getBeginningTime().plus(scheduleForm.getDuration(), HOURS));
        schedule.setDuration(scheduleForm.getDuration());
        System.out.println("endtime:" + schedule.getEndTime());
        System.out.println("duration: " + scheduleForm.getDuration());
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
    public Map<LocalDate,Map<String,List<Boolean>>> allSchedules(){
       Map allSchedules=new TreeMap<LocalDate,Map<String,List<Boolean>>>();
        getAllScheduledDates().forEach(i->{
            allSchedules.put(i,scheduleOfAllMechanicsByDate(getSchedulesByDate(i)));
        });
        return allSchedules;
    }

    public Map<String, List<Boolean>> scheduleOfAllMechanicsByDate(List<Schedule> scheduleListOfDate) {
        Map<String, List<Boolean>> mechanicsWithSchedule = new HashMap<>();
        for (Schedule schedule : scheduleListOfDate) {
            allMechanics().forEach(i -> {
                if (i.getMechanicName().equals(schedule.getMechanic())) {
                    mechanicsWithSchedule.put(i.getMechanicName(), isScheduled(schedule));
                }
            });
        }
        for (Mechanic mech : allMechanics()) {
            if (!mechanicsWithSchedule.containsKey(mech.getMechanicName())) {
                List<Boolean> allFalse = new ArrayList<Boolean>(Arrays.asList(new Boolean[18]));
                Collections.fill(allFalse, Boolean.FALSE);
                mechanicsWithSchedule.put(mech.getMechanicName(), allFalse);
            }
        }
       /* for (Map.Entry<String, List<Boolean>> entry : mechanicsWithSchedule.entrySet()) {
            System.out.println("mechanic in map is: " + entry.getKey());
            entry.getValue().forEach(i -> System.out.println(i.booleanValue()));
        }*/
        return mechanicsWithSchedule;
    }

    public List<Boolean> isScheduled(Schedule sch) {
        List<Boolean> isScheduled = new ArrayList<>();
        String[] wH = {"08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30"};
        List<String> workingHours = Arrays.asList(wH.clone());
        for (int i = 0; i < workingHours.size(); i++) {
            // System.out.println("String workingHours= " + workingHours.get(i) + ", and substring of beginningTime= " + sch.getBeginningTime().toString().substring(11, 16));
            if (workingHours.get(i).equals(sch.getBeginningTime().toString().substring(11, 16))) {

                for (int j = 0; j <= sch.getDuration() * 2; j++) {
                    isScheduled.add(true);
                }
            } else {
                isScheduled.add(false);

            }
            //   System.out.println("Mechanic is :"+sch.getMechanic()+", index is: " + (i) + " value of isScheduled at index i = " + isScheduled.get(i));
        }
        isScheduled = isScheduled.subList(0, 18);
        return isScheduled;
    }


}