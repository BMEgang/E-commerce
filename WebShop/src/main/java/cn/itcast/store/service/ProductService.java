package cn.itcast.store.service;

import cn.itcast.store.domain.Product;
import cn.itcast.store.utils.PageModel;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {


    List<Product> findHots() throws SQLException;

    List<Product> findNews() throws SQLException;

    Product findProductByPid(String pid) throws SQLException;

    PageModel findProductsByCidWithPage(String cid, int curNum) throws SQLException;
}
