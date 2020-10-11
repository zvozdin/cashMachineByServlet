package ua.com.training.controller.command;

import ua.com.training.dao.entity.Order;
import ua.com.training.utility.SessionProductsAttribute;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Open implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        SessionProductsAttribute.setSessionAttributeProductsActualData(request);
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        if (order == null) {
            session.setAttribute("order", new Order.OrderBuilder().build());
        }

        return "cashier.jsp";
    }
}
