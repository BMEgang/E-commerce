package cn.itcast.store.service;

import cn.itcast.store.domain.Order;
import cn.itcast.store.domain.User;
import cn.itcast.store.utils.PageModel;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface OrderService {
    void saveOrder(Order order) throws SQLException;
    PageModel findMyOrdersWithPage(User user, int curNum) throws SQLException, InvocationTargetException, IllegalAccessException;
    Order findOrdersByOid(String oid) throws SQLException, InvocationTargetException, IllegalAccessException;
    void updateOrder(Order order) throws SQLException;
}
