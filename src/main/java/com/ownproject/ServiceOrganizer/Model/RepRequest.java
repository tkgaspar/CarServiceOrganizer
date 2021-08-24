package com.ownproject.ServiceOrganizer.Model;

/*CREATE TABLE IF NOT EXISTS REPREQUEST (
        noteid INT PRIMARY KEY auto_increment,
        created_at TIMESTAMP,
        clientname VARCHAR(20),
        licenceplate VARCHAR(20),
        vinnumber VARCHAR(20),
        defectdescription VARCHAR (1000),
        userid INT,
        foreign key (userid) references USERS(userid)
        );*/

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class RepRequest {

    private Integer repReqId;
    private static final Timestamp timeStamp=new Timestamp(System.currentTimeMillis());
    private String clientName;
    private String defectDescription;
    private String licencePlate;
    private String vinNumber;
    private Integer userId;

    public RepRequest() {
    }


    public RepRequest(Integer repReqId, String clientName, String defectDescription, String licencePlate, String vinNumber, Integer userId) {
        this.repReqId = repReqId;
        this.clientName = clientName;
        this.defectDescription = defectDescription;
        this.licencePlate = licencePlate;
        this.vinNumber = vinNumber;
        this.userId = userId;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    /*public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }*/

    public Integer getRepReqId() {
        return repReqId;
    }

    public void setRepReqId(Integer repReqId) {
        this.repReqId = repReqId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getDefectDescription() {
        return defectDescription;
    }

    public void setDefectDescription(String defectDescription) {
        this.defectDescription = defectDescription;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }


}
