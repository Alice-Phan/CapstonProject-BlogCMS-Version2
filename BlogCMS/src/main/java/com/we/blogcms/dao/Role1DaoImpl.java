package com.we.blogcms.dao;

import com.we.blogcms.model.Role;
import com.we.blogcms.model.Role1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository

public class Role1DaoImpl implements Role1Dao {
    @Autowired
    private JdbcTemplate jdbc;

    private final String INSERT_NEW_ROLE = "INSERT INTO roles(userRole VALUES (?)";
    private final String SELECT_ALL_ROLES = "SELECT role1Id, userRole FROM roles";
    private final String SELECT_ROLE_BY_ID = "SELECT role1Id, userRole FROM roles WHERE role1Id = ?";
    private final String SELECT_ROLE_BY_ROLE = "SELECT role1Id, userRole FROM roles WHERE userRole = ?";
    private final String UPDATE_ROLE = "UPDATE roles SET userRole = ? WHERE role1Id = ?";
    private final String DELETE_ROLE = "DELETE FROM roles WHERE role1Id = ?";

    private final String DELETE_FROM_USERS_ROLES = "DELETE FROM author_roles WHERE role1Id = ?";

    @Override
    @Transactional
    public Role1 create(Role1 role1) {
        jdbc.update(INSERT_NEW_ROLE, role1.getRole1());
        int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        role1.setRoleID(newID);
        return role1;
    }



    @Override
    @Transactional
    public List<Role1> readAll() {
        return jdbc.query(SELECT_ALL_ROLES, new RoleMapper());
    }

    @Override
    public Role1 readById(int role1Id) {
        try {
            return jdbc.queryForObject(SELECT_ROLE_BY_ID, new RoleMapper(), role1Id);

        } catch (DataAccessException ex) {
            return null;
        }

    }

    @Override
    public Role1 readByRole(String role1) {
        try {
            return jdbc.queryForObject(SELECT_ROLE_BY_ROLE, new RoleMapper(), role1);

        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public boolean update(Role1 role1) {
        return jdbc.update(UPDATE_ROLE, role1.getRole1(), role1.getRoleID()) > 0;
    }

    @Override
    public boolean delete(int role1Id) {
        jdbc.update(DELETE_FROM_USERS_ROLES, role1Id);
        return jdbc.update(DELETE_ROLE, role1Id) > 0;
    }
    public final static class RoleMapper implements RowMapper<Role1> {
        @Override
        public Role1 mapRow(ResultSet rs, int i) throws SQLException {
            Role1 role1 = new Role1();
            role1.setRoleID((rs.getInt("role1Id")));
            role1.setRole1(rs.getString("userRole"));
            return role1;
        }
    }


}
