package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.UserDao;
import entity.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    UserDao dao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getParameter("id");
        String pw = req.getParameter("password");

        User u = dao.findByIdAndPassword(id, pw);

        if (u == null) {
            req.setAttribute("message", "Sai tài khoản hoặc mật khẩu!");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("user", u);

            // phân quyền
            switch (u.getRole()) {
                case 1: // admin
                	resp.sendRedirect("admin");
                    break;

                case 0: // reporter
                    resp.sendRedirect("reporter/home");
                    break;

                default: // reader
                    resp.sendRedirect("home");
                    break;
            }
        }
    }
}
