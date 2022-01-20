package cn.itcast.store.service.serviceImp;

import cn.itcast.store.dao.ProductDao;
import cn.itcast.store.dao.daoImp.ProductDaoImp;
import cn.itcast.store.domain.Product;
import cn.itcast.store.service.ProductService;
import cn.itcast.store.utils.PageModel;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImp implements ProductService {
    ProductDao productDao = new ProductDaoImp();
    @Override
    public List<Product> findHots() throws SQLException {
        return productDao.findHots();
    }

    @Override
    public List<Product> findNews() throws SQLException {
        return productDao.findNews();
    }

    @Override
    public Product findProductByPid(String pid) throws SQLException {
        return productDao.findProductByPid(pid);
    }

    @Override
    public PageModel findProductsByCidWithPage(String cid, int curNum) throws SQLException {
        //创建pagemodel对象，目的是为了计算分页参数
        //统计当前分类下商品的个数select count（*）from product where cid = ?
        int totalRecord = productDao.findTotalRecords(cid);
        PageModel pm = new PageModel(curNum,totalRecord,12);
        //关联集合
        List list = productDao.findProductsByCidWithPage(cid,pm.getStartIndex(),pm.getPageSize());
        pm.setList(list);
        //关联url
        pm.setUrl("ProductServlet?method=findProductsByCidWithPage&cid=" + cid);
        return pm;
    }
}
