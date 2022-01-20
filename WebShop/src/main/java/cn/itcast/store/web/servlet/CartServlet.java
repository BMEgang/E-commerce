package cn.itcast.store.web.servlet;

import cn.itcast.store.domain.Cart;
import cn.itcast.store.domain.CartItem;
import cn.itcast.store.domain.Product;
import cn.itcast.store.service.ProductService;
import cn.itcast.store.service.serviceImp.ProductServiceImp;
import cn.itcast.store.web.base.BaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartServlet extends BaseServlet {

    public String addCartItemToCart(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (null == cart)
        {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }

        String pid = req.getParameter("pid");
        int num = Integer.parseInt(req.getParameter("quantity"));

        ProductService productService = new ProductServiceImp();
        Product product = productService.findProductByPid(pid);

        CartItem cartItem = new CartItem();
        cartItem.setNum(num);
        cartItem.setProduct(product);

        cart.addCartItemToCar(cartItem);
        resp.sendRedirect("/jsp/cart.jsp");
        return null;
    }

    public String removeCartItem(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String pid = req.getParameter("id");
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.removeCartItem(pid);
        resp.sendRedirect("/jsp/cart.jsp");
        return null;
    }

    //clearCart
    public String clearCart(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.clearCart();
        resp.sendRedirect("/jsp/cart.jsp");
        return null;
    }

}
