package com.ownproject.ServiceOrganizer.Model;

public class Role {
    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role() {
    }

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
