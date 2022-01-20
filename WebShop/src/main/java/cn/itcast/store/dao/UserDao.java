package cn.itcast.store.dao;

import cn.itcast.store.domain.User;

import java.sql.SQLException;

public interface UserDao {
    User userLogin(User user) throws SQLException;

    void userRegist(User user) throws SQLException;

    User userActive(String code) throws SQLException;

    void updateUser(User user) throws SQLException;
}
