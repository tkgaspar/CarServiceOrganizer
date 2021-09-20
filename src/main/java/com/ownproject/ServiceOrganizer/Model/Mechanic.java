package com.ownproject.ServiceOrganizer.Model;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public class Mechanic {
    private Integer mechId;
    private String mechanicName;



    public Mechanic(Integer mechId, String mechanicName) {
        this.mechId = mechId;
        this.mechanicName = mechanicName;
    }

    public Integer getMechId() {
        return mechId;
    }

    public void setMechId(Integer mechId) {
        this.mechId = mechId;
    }

    public String getMechanicName() {
        return mechanicName;
    }

    public void setMechanicName(String mechanicName) {
        this.mechanicName = mechanicName;
    }
}
