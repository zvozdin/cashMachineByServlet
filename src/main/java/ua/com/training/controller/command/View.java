package ua.com.training.controller.command;

import ua.com.training.dao.ProductDao;
import ua.com.training.dao.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class View implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Product> products = new ProductDao().findAllProducts();
        request.getSession().setAttribute("products", products);
        return "commodityExpertAllProducts.jsp";
    }
}
