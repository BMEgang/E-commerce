package cn.itcast.store.service.serviceImp;

import cn.itcast.store.dao.UserDao;
import cn.itcast.store.dao.daoImp.UserDaoImp;
import cn.itcast.store.domain.User;
import cn.itcast.store.service.UserService;

import java.sql.SQLException;
import java.text.ParseException;

public class UserServiceImp implements UserService {

    @Override
    public boolean userActive(String code) throws SQLException {
        //实现注册功能
        UserDao userDao = new UserDaoImp();
        //对db发送select * from user where code=?
        User user = userDao.userActive(code);

        if (null != user)
        {
            //可以查到
            //更改用户状态
            user.setState(1);
            user.setCode(null);
            //对数据库执行一次真实的更新操作
            userDao.updateUser(user);
            return true;
        }else {
            //查不到
            return false;
        }

    }

    @Override
    public void userRegist(User user) throws SQLException, ParseException {
        //实现注册功能
        UserDao userDao = new UserDaoImp();
        userDao.userRegist(user);

    }

    @Override
    public User userLogin(User user) throws SQLException {
        UserDao userDao = new UserDaoImp();
        //select * from user where username=? and password=?
        User uu = userDao.userLogin(user);
        if (null == uu){
            throw new RuntimeException("password is wrong");
        }else if (uu.getState()==0)
        {
            throw new RuntimeException("user is not activated!");
        }
        else {
            return uu;
        }
    }
}
