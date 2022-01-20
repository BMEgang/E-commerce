package cn.itcast.store.dao;

import cn.itcast.store.domain.Order;
import cn.itcast.store.domain.OrderItem;
import cn.itcast.store.domain.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    void saveOrder(Connection conn, Order order) throws SQLException;
    void saveOrderItem(Connection conn, OrderItem orderItem) throws SQLException;
    int getTotalRecords(User user) throws SQLException;
    List findMyOrdersWithPage(User user, int startIndex, int pageSize) throws SQLException, InvocationTargetException, IllegalAccessException;
    Order findOrdersByOid(String oid) throws SQLException, InvocationTargetException, IllegalAccessException;
    void updateOrder(Order order) throws SQLException;
}
