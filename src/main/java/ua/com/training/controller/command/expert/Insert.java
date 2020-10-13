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
        boolean addProduct = new StockDao().save(
                new Product.ProductBuilder()
                        .code(request.getParameter("code"))
                        .name(request.getParameter("name"))
                        .size(Size.valueOf(request.getParameter("size")))
                        .price(Double.parseDouble(request.getParameter("price")))
                        .quantity(Integer.parseInt(request.getParameter("quantity")))
                        .build());

        if (addProduct) {
            request.getSession().setAttribute("report", "Success");
        } else {
            request.getSession().setAttribute("report", "product can't be added. Probably already exists");
        }

        return "report.jsp";
    }
}
