package ua.com.training.controller.command;

import ua.com.training.dao.ProductDao;
import ua.com.training.dao.entity.Product;
import ua.com.training.dao.entity.Size;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Insert implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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

        boolean addProduct = new ProductDao().addProduct(product);

        if (addProduct) {
            return "mainUser.jsp";
        } else {
            request.getSession().setAttribute("error", "product can't be added");
            return "error.jsp";
        }
    }
}
