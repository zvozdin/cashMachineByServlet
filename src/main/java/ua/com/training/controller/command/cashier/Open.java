package ua.com.training.controller.command.cashier;

import ua.com.training.controller.Page;
import ua.com.training.controller.command.Action;
import ua.com.training.dao.StockDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Open implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pageNumber = request.getParameter("page");
        if (pageNumber == null || pageNumber.isEmpty()) {
            pageNumber = "1";
        }

        setPaginationForPage(request, pageNumber);

        return "cashier.jsp?page=" + Integer.valueOf(pageNumber);
    }

    private void setPaginationForPage(HttpServletRequest request, String pageNumber) {
        HttpSession session = request.getSession();
        Integer total = new StockDao().getRowsCount();
        Integer size = 3;
        Integer maxPageNum = total % size == 0 ? (total / size) : (total / size + 1);
        Page page = new Page(Integer.valueOf(pageNumber), size);

        session.setAttribute("products", new StockDao().findAllProducts(page.getOffset(), page.getSize()));
        session.setAttribute("maxPageNum", maxPageNum);
        session.setAttribute("page", page);
    }
}
