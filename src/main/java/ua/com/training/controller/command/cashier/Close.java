package ua.com.training.controller.command.cashier;

import ua.com.training.controller.command.Action;
import ua.com.training.dao.OrdersDao;
import ua.com.training.dao.entity.Order;
import ua.com.training.dao.entity.Product;
import ua.com.training.dao.entity.User;

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
        List<Product> products = order.getProducts();

        if (isOrderExists(session, order, products)) {
            return "error.jsp";
        }

        int checkCode = new OrdersDao().save(products, (User) session.getAttribute("user"));

        if (checkCode > 0) {
            session.setAttribute("checkCode", checkCode);
            return "check.jsp";
        }

        session.setAttribute("error", "can't close order. Check product quantity");
        return "error.jsp";
    }

    private boolean isOrderExists(HttpSession session, Order order, List<Product> products) {
        if (order == null) {
            session.setAttribute("error", "order doesn't exist");
            return true;
        }

        if (products.size() == 0) {
            session.setAttribute("error", "check is empty.");
            return true;
        }

        return false;
    }
}
