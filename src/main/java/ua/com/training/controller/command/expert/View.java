package ua.com.training.controller.command.expert;

import ua.com.training.controller.command.Action;
import ua.com.training.dao.StockDao;
import ua.com.training.dao.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class View implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Product> products = new StockDao().findAllProducts(0, new StockDao().getRowsCount());
        request.getSession().setAttribute("allProducts", products);
        return "commodityExpertAllProducts.jsp";
    }
}
