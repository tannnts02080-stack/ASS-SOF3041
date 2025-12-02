package controller;

import dao.NewsletterDao;
import entity.Newsletter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet({"/newsletter", "/newsletter/delete"})
public class NewsletterServlet extends HttpServlet {

    NewsletterDao dao = new NewsletterDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String uri = req.getRequestURI();

        // Xử lý xóa
        if (uri.contains("delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            dao.delete(id);
            resp.sendRedirect(req.getContextPath() + "/newsletter");
            return;
        }

        // Load danh sách đăng ký nhận tin
        req.setAttribute("list", dao.findAll());

        // 🟢 ĐÚNG: set contentPage = newsletter.jsp
        req.setAttribute("contentPage", "/views/admin/newsletter.jsp");

        // Forward vào layout
        req.getRequestDispatcher("/views/layout/adminLayout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String email = req.getParameter("email");
        dao.insert(email);

        resp.sendRedirect(req.getContextPath() + "/newsletter");
    }
}
