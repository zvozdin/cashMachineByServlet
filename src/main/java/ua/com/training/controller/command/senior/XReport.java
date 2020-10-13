package ua.com.training.controller.command.senior;

import ua.com.training.controller.command.Action;
import ua.com.training.dao.OrdersDao;
import ua.com.training.dao.entity.Report;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class XReport implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Optional<Report> xReport = new OrdersDao().findOrdersBillAndProductQuantitySum();

        if (xReport.isPresent()) {
            session.setAttribute("xReport", xReport.get());
            return "xReport.jsp";
        } else {
            session.setAttribute("report", "there is no checks today");
            return "report.jsp";
        }
    }
}
