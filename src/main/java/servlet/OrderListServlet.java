package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import spring.service.CustomerService;
import spring.service.OrderService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/orderlist")
public class OrderListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        var customerService = CustomerService.getINSTANCE();
        String phone = req.getSession().getAttribute("phone").toString();
        String password = req.getSession().getAttribute("password").toString();

        Long customerId = customerService.find(phone,password).get().getId();
        req.setAttribute("orderlist", OrderService.getInstance().findByCustomerId(customerId));
        req.getRequestDispatcher(JspHelper.getPath("orderlist")).forward(req, resp);
    }
}

