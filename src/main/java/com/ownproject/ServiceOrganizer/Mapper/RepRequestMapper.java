package com.ownproject.ServiceOrganizer.Mapper;

import com.ownproject.ServiceOrganizer.Model.RepRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RepRequestMapper {

    @Select("SELECT *  from REPREQUEST where userid=#{userId}")
    List<RepRequest> getAllRequestsByUserId(Integer userId);

    @Select("SELECT *  from REPREQUEST where isscheduled='0'")
    List<RepRequest> getAllUnScheduledRequests();

    @Select("SELECT * FROM REPREQUEST WHERE clientname=#{clientName} and userid=#{userId}")
    RepRequest getRepairRequest(String clientName, Integer userId);

    @Select("SELECT * FROM serviceorganizer.reprequest where repreqid=#{repReqId}")
    RepRequest getRepReqById(Integer repReqId);

    @Insert("INSERT INTO REPREQUEST (timestamp,clientname,licenceplate,vinnumber, defectdescription,userid,ispartsordered,isscheduled,isfinished) " +
            "VALUES (#{timeStamp},#{clientName},#{licencePlate},#{vinNumber},#{defectDescription},#{userId},#{isPartsOrdered},#{isScheduled},#{isFinished})")
    @Options(useGeneratedKeys = true, keyProperty="repReqId")
    int insert(RepRequest repRequest);

    @Delete("DELETE from REPREQUEST where clientname=#{clientName} and userid=#{userid}")
    int delete(String clientName, Integer userid);

    @Update("UPDATE REPREQUEST SET clientname=#{clientName}, defectdescription=#{defectDescription},  WHERE repreqid = #{repReqId}")
    void updateRepairRequest(String clientName, String defectDescription, Integer repReqId );

    @Update("UPDATE REPREQUEST SET ispartsordered=#{isPartsOrdered} WHERE repreqid=#{repReqId}")
    void setOrderedStatus(Integer repReqId, Boolean isPartsOrdered);

    @Update("UPDATE REPREQUEST SET  isscheduled=#{isScheduled} WHERE repreqid=#{repReqId}")
    void setScheduledStatus(Integer repReqId,  Boolean isScheduled);

    @Update("UPDATE REPREQUEST SET isfinished=#{isFinished} WHERE repreqid=#{repReqId}")
    void setFinishedStatus(Integer repReqId,  Boolean isFinished);


}
