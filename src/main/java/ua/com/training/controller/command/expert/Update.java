package ua.com.training.controller.command.expert;

import ua.com.training.controller.command.Action;
import ua.com.training.dao.StockDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Update implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        if (new StockDao()
                .updateQuantityProductByCode(
                        request.getParameter("code"),
                        Integer.parseInt(request.getParameter("value")))) {
            session.setAttribute("report", "Success");
        } else {
            session.setAttribute("report", "can't update. Check data");
        }
        return "report.jsp";
    }
}
