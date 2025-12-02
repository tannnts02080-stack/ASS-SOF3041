package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Trang con cần nhúng vào layout
        req.setAttribute("contentPage", "/views/admin/dashboard.jsp");

        // Nhúng vào layout admin
        req.getRequestDispatcher("/views/layout/adminLayout.jsp")
                .forward(req, resp);
    }
}
