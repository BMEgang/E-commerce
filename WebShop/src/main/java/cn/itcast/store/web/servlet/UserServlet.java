package cn.itcast.store.web.servlet;

import cn.itcast.store.domain.User;
import cn.itcast.store.service.UserService;
import cn.itcast.store.service.serviceImp.UserServiceImp;
import cn.itcast.store.utils.MailUtils;
import cn.itcast.store.utils.MyBeanUtils;
import cn.itcast.store.utils.UUIDUtils;
import cn.itcast.store.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class UserServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    public String registUI(HttpServletRequest req, HttpServletResponse resp)
    {
        return "/jsp/register.jsp";
    }
    public String loginUI(HttpServletRequest req, HttpServletResponse resp)
    {
        return "/jsp/login.jsp";
    }

    public String userRegist(HttpServletRequest req, HttpServletResponse resp) throws InvocationTargetException, IllegalAccessException {
        //accept the parameters from form
        User user = new User();

        MyBeanUtils.populate(user,req.getParameterMap());
        user.setUid(UUIDUtils.getId());
        user.setState(0);
        user.setCode(UUIDUtils.getCode());

//        System.out.println(user);

//        Set<String> keySet = map.keySet();
//        Iterator<String> iterator = keySet.iterator();
//
//        while (iterator.hasNext())
//        {
//            String str = iterator.next();
//            System.out.println(str);
//            String[] strings = map.get(str);
//
//            for (String string : strings) {
//                System.out.println(string);
//            }
//        }
        //调用业务层注册功能
        UserService userService = new UserServiceImp();

        try {
            userService.userRegist(user);
            //组册成功，向邮箱发送信息，跳转到提示页面

            MailUtils.sendMail(user.getEmail(),user.getCode());
            req.setAttribute("msg","user regist success, please activate.");
        }catch (Exception e){
            //注册失败，跳转到提示页面
            e.printStackTrace();
            req.setAttribute("msg","user regist fail, please regist again");
        }

        return "jsp/info.jsp";
    }

    public String active(HttpServletRequest req, HttpServletResponse resp) throws InvocationTargetException, IllegalAccessException, SQLException {
        //获取激活码
        String code = req.getParameter("code");
        //调用业务层激活功能
        UserService userService = new UserServiceImp();
        boolean flag = userService.userActive(code);

        //进行激活信息提示
        if (flag)
        {
            //用户激活成功，向request放入信息，转发到登录页面
            req.setAttribute("msg","user activate success, please login");
            return "/jsp/login.jsp";
        }else {
            //用户激活失败，转发到信息页面
            req.setAttribute("msg","user activate success, please reactivate");
            return "jsp/info.jsp";
        }
    }

    //userLogin
    public String userLogin(HttpServletRequest req, HttpServletResponse resp) throws InvocationTargetException, IllegalAccessException, SQLException {
        User user = new User();
        MyBeanUtils.populate(user,req.getParameterMap());

        UserService userService = new UserServiceImp();
        User user02 = null;

        try {
            //select * from user where username = ? and password = ?
            user02 = userService.userLogin(user);
            //用户登录成功后放入session中
            req.getSession().setAttribute("loginUser",user02);
            resp.sendRedirect("index.jsp");
            return null;
        } catch (Exception e) {
            String msg = e.getMessage();
            System.out.println(msg);
            req.setAttribute("msg",msg);
            return "/jsp/login.jsp";
        }

    }

    public String logOut(HttpServletRequest req, HttpServletResponse resp) throws InvocationTargetException, IllegalAccessException, SQLException, IOException {
        //清除session
        req.getSession().invalidate();
        //重定向到首页
        resp.sendRedirect("index.jsp");
        return null;
    }


    }
