package cn.itcast.store.web.servlet;

import cn.itcast.store.domain.Product;
import cn.itcast.store.service.ProductService;
import cn.itcast.store.service.serviceImp.ProductServiceImp;
import cn.itcast.store.utils.PageModel;
import cn.itcast.store.web.base.BaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ProductServlet extends BaseServlet {
    //findProductByPid
    public String findProductByPid(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取商品pid
        String pid = req.getParameter("pid");
        //根据商品pid进行查询
        ProductService productService = new ProductServiceImp();
        Product product = productService.findProductByPid(pid);
        //将获得的商品放入request
        req.setAttribute("product",product);
        //转发到/jsp/product_info.jsp

        return "/jsp/product_info.jsp";
    }

    public String findProductsWithCidAndPage(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        //获取cid，num
        String cid = req.getParameter("cid");
        int curNum = Integer.parseInt(req.getParameter("num"));
        //调用业务层功能：以分页形式查询当前类别下的商品信息
        //返回pagemodel对象（1——当前页商品信息
        // 2——分页
        // 3——url）
        ProductService productService = new ProductServiceImp();
        PageModel pm = productService.findProductsByCidWithPage(cid,curNum);
        //将pagemodel对象放入request
        req.setAttribute("page",pm);
        //转发到/jsp/product_info.jsp
        return "/jsp/product_list.jsp";
    }
}

