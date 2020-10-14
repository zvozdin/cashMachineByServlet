package ua.com.training.controller.command.cashier;

import ua.com.training.controller.Page;
import ua.com.training.controller.command.Action;
import ua.com.training.dao.StockDao;
import ua.com.training.dao.entity.Order;
import ua.com.training.dao.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class Open implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        if (order == null) {
            session.setAttribute("order", new Order.OrderBuilder().build());
        }

        Integer total = new StockDao().getRowsCount();
        Integer size = 3;
        Integer maxPageNum = total % size == 0 ? (total / size) : (total / size + 1);

        String pageNumber = request.getParameter("page");
        if (pageNumber == null || pageNumber.isEmpty()) {
            pageNumber = "1";
        }
        Page page = new Page(Integer.valueOf(pageNumber), size);

        List<Product> products = new StockDao().findAllProducts(page.getOffset(), page.getSize());
        session.setAttribute("products", products);
        session.setAttribute("maxPageNum", maxPageNum);
        session.setAttribute("page", page);

        return "cashier.jsp?page=" + Integer.valueOf(pageNumber);
    }
}
