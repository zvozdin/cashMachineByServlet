package ua.com.training.controller.command.cashier;

import ua.com.training.controller.command.Action;
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

    private static final String ORDER_DOESNT_EXIST = "message01";
    private static final String CHECK_IS_EMPTY = "message02";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        List<Product> cart = SessionProductsAttribute.getChosenProducts(request);

        if (isEmptyCartAndOrder(session, cart)) {
            return "report.jsp";
        }

        session.setAttribute("order",
                new Order.OrderBuilder()
                .userId(((User) session.getAttribute("user")).getId())
                .products((List<Product>) session.getAttribute("cart"))
                .build());

        return "cashierCart.jsp";
    }

    private boolean isEmptyCartAndOrder(HttpSession session, List<Product> cart) {
        if (session.getAttribute("order") == null) {
            session.setAttribute("report", ORDER_DOESNT_EXIST);
            return true;
        }

        if (session.getAttribute("cart") == null && cart.size() == 0) {
            session.setAttribute("report", CHECK_IS_EMPTY);
            return true;
        }

        if (cart.size() > 0) {
            session.setAttribute("cart", cart);
        }

        return false;
    }
}
