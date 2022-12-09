package com.we.blogcms.model;

import java.util.Objects;

public class Role1 {

    private int roleID;
    private String role1;

    public Role1(int roleID, String role1) {
        this.roleID = roleID;
        this.role1 = role1;
    }

    public Role1() {

    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRole1() {
        return role1;
    }

    public void setRole1(String role1) {
        this.role1 = role1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role1 role11 = (Role1) o;
        return roleID == role11.roleID && Objects.equals(role1, role11.role1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleID, role1);
    }

}
