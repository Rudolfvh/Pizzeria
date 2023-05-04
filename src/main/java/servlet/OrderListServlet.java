package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.OrderService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/orderlist")
public class OrderListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());


        Long customerId = Long.parseLong(req.getParameter("customerId"));
        //req.setAttribute("orderlist", OrderService.getInstance().findAllByCustomerId(customerId));
        req.getRequestDispatcher("/orderlist.jsp").forward(req, resp);
    }
}
