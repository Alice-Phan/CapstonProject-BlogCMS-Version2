package com.we.blogcms.dao;

import com.we.blogcms.model.Role1;

import java.util.List;

public interface Role1Dao {
    public Role1 create (Role1 role1);

    public List<Role1> readAll();

    public Role1 readById(int role1Id);

    public Role1 readByRole(String role1);

    public boolean update(Role1 role);

    public boolean delete(int role1Id);
}
