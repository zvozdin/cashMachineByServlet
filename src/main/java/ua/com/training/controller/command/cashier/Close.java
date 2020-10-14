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

    private static final String ORDER_DOESNT_EXIST = "message01";
    private static final String CHECK_IS_EMPTY = "message02";
    private static final String CANT_CLOSE_ORDER = "message03";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");

        List<Product> cart = (List<Product>) session.getAttribute("cart");

        if (isOrderNotValid(session, order, cart)) {
            return "report.jsp";
        }

        int checkCode = new OrdersDao().save(cart, (User) session.getAttribute("user"));
        if (checkCode > 0) {
            session.setAttribute("checkCode", checkCode);
            return "check.jsp";
        }

        session.setAttribute("report", CANT_CLOSE_ORDER);
        return "report.jsp";
    }

    private boolean isOrderNotValid(HttpSession session, Order order, List<Product> products) {
        if (order == null) {
            session.setAttribute("report", ORDER_DOESNT_EXIST);
            return true;
        }

        if (products == null || products.size() == 0) {
            session.setAttribute("report", CHECK_IS_EMPTY);
            return true;
        }

        return false;
    }
}
