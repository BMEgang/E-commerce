package cn.itcast.store.web.servlet;

import cn.itcast.store.domain.Category;
import cn.itcast.store.domain.Product;
import cn.itcast.store.service.CategoryService;
import cn.itcast.store.service.ProductService;
import cn.itcast.store.service.serviceImp.CategoryServiceImp;
import cn.itcast.store.service.serviceImp.ProductServiceImp;
import cn.itcast.store.web.base.BaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class IndexServlet extends BaseServlet {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //调用业务层功能：获取业务层全部信息
        CategoryService categoryService = new CategoryServiceImp();
        List<Category> list = categoryService.getAllCats();
        //将返回的集合放入request
        req.setAttribute("allCats",list);


        //调用业务层功能：查询最热商品，查询最新商品，返回两个集合
        ProductService productService = new ProductServiceImp();
        List<Product> list1 = productService.findHots();
        List<Product> list2 = productService.findNews();
        //将两个集合放入request
        req.setAttribute("hots",list1);
        req.setAttribute("news",list2);

        //转发到真实的首页
        return "/jsp/index.jsp";
    }
}
