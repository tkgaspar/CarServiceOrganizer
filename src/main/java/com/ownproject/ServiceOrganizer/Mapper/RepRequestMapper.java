package com.ownproject.ServiceOrganizer.Mapper;

import com.ownproject.ServiceOrganizer.Model.RepRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RepRequestMapper {

    @Select("SELECT *  from REPREQUEST where userid=#{userId}")
    List<RepRequest> getAllRequests(Integer userId);

    @Select(("SELECT * FROM REPREQUEST WHERE clientname=#{clientName} and userid=#{userId}"))
    RepRequest getRepairRequest(String clientName, Integer userId);

    @Insert("INSERT INTO REPREQUEST (timestamp,clientname,licenceplate,vinnumber, defectdescription,userid) VALUES (#{timeStamp},#{clientName},#{licencePlate},#{vinNumber},#{defectDescription},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty="repReqId")
    int insert(RepRequest repRequest);

    @Delete("DELETE from REPREQUEST where clientname=#{clientName} and userid=#{userid}")
    int delete(String clientName, Integer userid);

    @Update("UPDATE REPREQUEST SET clientname=#{clientName}, defectdescription=#{defectDescription} WHERE repreqid = #{repReqId}")
    void updateRepairRequest(String clientName, String defectDescription, Integer repReqId);


}
