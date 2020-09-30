package ua.com.training.controller.command;

import ua.com.training.dao.ProductDao;
import ua.com.training.dao.entity.Product;
import ua.com.training.dao.entity.Size;
import ua.com.training.dao.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Insert implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Long commodityExpertId = user.getId();
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");
        String size = request.getParameter("size");

        Product product = new Product();
        // todo validate entry data on front in input fields without loop but for every one
        product.setCode(code);
        product.setName(name);
        product.setPrice(Double.parseDouble(price));
        product.setQuantity(Integer.parseInt(quantity));
        product.setSize(Size.valueOf(size.toUpperCase()));
        product.setCommodityExpertId(commodityExpertId);

        boolean addProduct = new ProductDao().addProduct(product);

        if (addProduct) {
            return "commodityExpert.jsp";
        } else {
            session.setAttribute("error", "product can't be added");
            return "error.jsp";
        }
    }
}