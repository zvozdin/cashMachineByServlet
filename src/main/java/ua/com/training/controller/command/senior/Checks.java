package ua.com.training.controller.command.senior;

import ua.com.training.controller.Page;
import ua.com.training.controller.command.Action;
import ua.com.training.dao.ChecksDao;
import ua.com.training.dao.entity.Check;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class Checks implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pageNumber = request.getParameter("page");
        if (pageNumber == null || pageNumber.isEmpty()) {
            pageNumber = "1";
        }

        String action = request.getRequestURI()
                .substring(request.getContextPath().length());
        switch (action) {
            case "/cancelOrder":
                setPaginationForPage(request, pageNumber, action);
                return "seniorDeleteCheck.jsp?page=" + Integer.valueOf(pageNumber);
            case "/cancelProduct":
                setPaginationForPage(request, pageNumber, action);
                return "seniorChooseCheckForDeleteProduct.jsp?page=" + Integer.valueOf(pageNumber);
            default:
                return "report.jsp";
        }
    }

    private void setPaginationForPage(HttpServletRequest request, String pageNumber, String operation) {
        HttpSession session = request.getSession();
        Integer total = new ChecksDao().getRowsCount();
        Integer size = 3;
        Integer maxPageNum = total % size == 0 ? (total / size) : (total / size + 1);
        Page page = new Page(Integer.valueOf(pageNumber), size);

        List<Check> checks =
                operation.equals("/cancelOrder")
                        ? new ChecksDao().findAllChecks(page.getOffset(), page.getSize())
                        : new ChecksDao().findAllChecksWithProducts(page.getOffset(), page.getSize());
        session.setAttribute("checks", checks);
        session.setAttribute("maxPageNum", maxPageNum);
        session.setAttribute("page", page);
    }
}
