package cn.itcast.store.web.filter;

import cn.itcast.store.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class PriviledgeFilter implements Filter {
    public PriviledgeFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest myReq = (HttpServletRequest) servletRequest;
        User user = (User) myReq.getSession().getAttribute("loginUser");
        if (null != user)
        {
            filterChain.doFilter(servletRequest,servletResponse);
        }
        else {
            myReq.setAttribute("msg","you can find the information after login");
            myReq.getRequestDispatcher("/jsp/info.jsp").forward(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
