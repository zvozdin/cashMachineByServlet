package ua.com.training.controller.command;

import ua.com.training.utility.SessionProductsAttribute;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Open implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        SessionProductsAttribute.setSessionAttributeProductsActualData(request);
        // todo implement cancelled checking for not changed check
//        request.getSession().getAttribute("order");
//        Order order = new Order();
//        request.getSession().setAttribute("order", order);
        return "cashier.jsp";
    }
}
