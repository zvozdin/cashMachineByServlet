package ua.com.training.controller.command.senior;

import ua.com.training.controller.command.Action;
import ua.com.training.dao.OrdersDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteProduct implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String[] parameters = request.getParameter("productIdAndCheckCode").split("\\|");
        int productId = Integer.parseInt(parameters[0]);
        int checkCode = Integer.parseInt(parameters[1]);

        if (new OrdersDao().deleteProductByCheckCodeAndProductId(checkCode, productId)) {
            session.setAttribute("report", "Success");
        } else {
            session.setAttribute("report", "there is no checks today");
        }

        return "report.jsp";
    }
}
