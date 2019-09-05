package com.tap2up.pojo;

import java.util.Objects;

public class Role {
    private Integer roleid;

    private String rolename;

    private String description;

    private Boolean isavailable;

    private Integer isdelete;

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Boolean getIsavailable() {
        return isavailable;
    }

    public void setIsavailable(Boolean isavailable) {
        this.isavailable = isavailable;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(roleid, role.roleid) &&
                Objects.equals(rolename, role.rolename) &&
                Objects.equals(description, role.description) &&
                Objects.equals(isavailable, role.isavailable) &&
                Objects.equals(isdelete, role.isdelete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleid, rolename, description, isavailable, isdelete);
    }
}