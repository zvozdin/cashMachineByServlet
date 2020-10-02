package ua.com.training.controller.command;

import ua.com.training.dao.entity.Order;
import ua.com.training.dao.entity.Product;
import ua.com.training.utility.SessionProductsAttribute;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class Close implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        List<Product> products = SessionProductsAttribute.getChosenProducts(request);

        boolean b = order.getProducts().containsAll(products);

        // session remove order after insert into database
        return "mainUser.jsp";
    }
}
