package ua.com.training.controller.command;

import ua.com.training.dao.entity.Order;
import ua.com.training.dao.entity.Product;
import ua.com.training.dao.entity.User;
import ua.com.training.utility.SessionProductsAttribute;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class Cart implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        List<Product> cart = SessionProductsAttribute.getChosenProducts(request);

        if (session.getAttribute("order") == null && cart.size() == 0) {
            session.setAttribute("error", "check can't be empty");
            return "error.jsp";
        }

        if (cart.size() > 0) {
            Order order = new Order();
            User user = (User) session.getAttribute("user");
            order.setUserId(user.getId());
            order.setProducts(cart);
            session.setAttribute("order", order);
        } else {
            // leave current order
        }

        return "cashierCart.jsp";
    }
}
