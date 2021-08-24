package com.ownproject.ServiceOrganizer.Model;

public class RepRequestForm {
    private Integer repReqId;
    private String clientName;
    private String defectDescription;
    private String vinNumber;
    private String licencePlate;
    private Integer userId;


    public RepRequestForm() {
    }

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

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
