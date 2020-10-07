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
        Order order = (Order) session.getAttribute("order");
        List<Product> cart = SessionProductsAttribute.getChosenProducts(request);

        if (isEmptyCartAndOrder(session, cart)) {
            return "error.jsp";
        }

        User user = (User) session.getAttribute("user");
        order.setUserId(user.getId());
        order.setProducts((List<Product>) session.getAttribute("cart"));
        session.setAttribute("order", order);

        return "cashierCart.jsp";
    }

    private boolean isEmptyCartAndOrder(HttpSession session, List<Product> cart) {
        if (session.getAttribute("order") == null) {
            session.setAttribute("error", "order doesn't exist");
            return true;
        }

        if (session.getAttribute("cart") == null && cart.size() == 0) {
            session.setAttribute("error", "check is empty.");
            return true;
        }

        if (cart.size() > 0) {
            session.setAttribute("cart", cart);
        }

        return false;
    }
}
