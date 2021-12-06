package com.ownproject.ServiceOrganizer.Model;

public class Role {

    private Integer role_id;

    private String name;

    public Role(Integer id, String name) {
        this.role_id = id;
        this.name = name;
    }

    public Role() {
    }

    public Role(Object o) {
        this.name = o.toString();
    }


    public Integer getId() {
        return role_id;
    }

    public void setId(Integer id) {
        this.role_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {

        return this.name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((role_id == null) ? 0 : role_id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
       if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Role other = (Role) obj;
        if (role_id == null) {
            if (other.role_id != null)
                return false;
        } else if (!role_id.equals(other.role_id))
            return false;
        return true;
    }
}

