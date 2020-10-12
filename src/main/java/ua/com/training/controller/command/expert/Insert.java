package ua.com.training.controller.command.expert;

import ua.com.training.controller.command.Action;
import ua.com.training.dao.StockDao;
import ua.com.training.dao.entity.Product;
import ua.com.training.dao.entity.Size;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Insert implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // todo validate entry data on front in input fields without loop but for every one
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");
        String size = request.getParameter("size");

        boolean addProduct = new StockDao().save(
                new Product.ProductBuilder()
                .code(code)
                .name(name)
                .size(Size.valueOf(size.toUpperCase()))
                .price(Double.parseDouble(price))
                .quantity(Integer.parseInt(quantity))
                .build());

        if (addProduct) {
            return "mainUser.jsp";
        } else {
            request.getSession().setAttribute("error", "product can't be added");
            return "error.jsp";
        }
    }
}
