package cn.itcast.store.service;

import cn.itcast.store.domain.User;

import java.sql.SQLException;
import java.text.ParseException;

public interface UserService {

    boolean userActive(String code) throws SQLException;

    void userRegist(User user) throws SQLException, ParseException;

    User userLogin(User user) throws SQLException;
}
