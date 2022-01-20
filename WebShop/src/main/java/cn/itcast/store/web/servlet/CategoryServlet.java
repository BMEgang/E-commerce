package cn.itcast.store.web.servlet;

import cn.itcast.store.domain.Category;
import cn.itcast.store.service.CategoryService;
import cn.itcast.store.service.serviceImp.CategoryServiceImp;
import cn.itcast.store.web.base.BaseServlet;
import net.sf.json.JSONArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CategoryServlet extends BaseServlet {
    //
    public String findAllCats(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        //调用业务层获取全部分类
        CategoryService categoryService = new CategoryServiceImp();
        List<Category> list = categoryService.getAllCats();
        //将全部分类转换为json格式的数据
        String jsonStr = JSONArray.fromObject(list).toString();

        //将全部分类信息相应到客户端
        //告诉浏览器本次响应的数据是Jason格式的字符串
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().print(jsonStr);
        return null;
    }
}
