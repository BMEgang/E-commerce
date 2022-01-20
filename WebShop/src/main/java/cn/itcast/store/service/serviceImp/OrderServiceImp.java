package cn.itcast.store.service.serviceImp;

import cn.itcast.store.dao.OrderDao;
import cn.itcast.store.dao.daoImp.OrderDaoImp;
import cn.itcast.store.domain.Order;
import cn.itcast.store.domain.OrderItem;
import cn.itcast.store.domain.User;
import cn.itcast.store.service.OrderService;
import cn.itcast.store.utils.JDBCUtils;
import cn.itcast.store.utils.PageModel;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderServiceImp implements OrderService {
    OrderDao orderDao = new OrderDaoImp();
    @Override
    public void saveOrder(Order order) throws SQLException {
        Connection conn = null;
        try{
            //获取连接
            conn = JDBCUtils.getConnection();
            //开启事务
            conn.setAutoCommit(false);
            //保存订单
            orderDao.saveOrder(conn,order);
            //保存订单项
            for (OrderItem item : order.getList())
            {
                orderDao.saveOrderItem(conn,item);
            }
            //提交
            conn.commit();
        } catch (Exception e) {
            //回滚
            conn.rollback();
        }
    }

    @Override
    public PageModel findMyOrdersWithPage(User user, int curNum) throws SQLException, InvocationTargetException, IllegalAccessException {
        int totalRecords = orderDao.getTotalRecords(user);
        PageModel pm = new PageModel(curNum,totalRecords,3);

        List list = orderDao.findMyOrdersWithPage(user, pm.getStartIndex(), pm.getPageSize());
        pm.setList(list);

        pm.setUrl("OrderServlet?method=findMyOrdersWithPage");

        return pm;
    }

    @Override
    public Order findOrdersByOid(String oid) throws SQLException, InvocationTargetException, IllegalAccessException {
        return orderDao.findOrdersByOid(oid);
    }

    @Override
    public void updateOrder(Order order) throws SQLException {
        orderDao.updateOrder(order);
    }
}
