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

        if (isOrderNotValid(session, order)) {
            return "report.jsp";
        }

        List<Product> products = order.getProducts();
        int checkCode = new OrdersDao().save(products, (User) session.getAttribute("user"));
        if (checkCode > 0) {
            session.setAttribute("checkCode", checkCode);
            return "check.jsp";
        }

        session.setAttribute("report", "can't close order. Check product quantity");
        return "report.jsp";
    }

    private boolean isOrderNotValid(HttpSession session, Order order) {
        if (order == null) {
            session.setAttribute("report", "order doesn't exist");
            return true;
        }

        List<Product> products = order.getProducts();
        if (products == null || products.size() == 0) {
            session.setAttribute("report", "check is empty.");
            return true;
        }

        return false;
    }
}
