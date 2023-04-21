package servlet;

import dto.CustomerDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import service.CustomerService;
import utils.JspHelper;

import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final CustomerService customerService = CustomerService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("login")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        customerService.login(req.getParameter("phone"),req.getParameter("password"))
                .ifPresentOrElse(
                        user -> onLoginSuccess(user, req, resp),
                        () -> onLoginFail(req, resp)
                );
    }

    @SneakyThrows
    private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect("/login?error&phone=" + req.getParameter("phone"));
    }

    @SneakyThrows
    private void onLoginSuccess(CustomerDto customer, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("phone", customer.getPhone());
        req.getSession().setAttribute("password", customer.getPassword());
        resp.sendRedirect("/order");
    }
}
