package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.NewsDao;
import entity.News;

@WebServlet("/news/detail")
public class NewsDetailServlet extends HttpServlet {

    NewsDao dao = new NewsDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getParameter("id");

        if (id == null) {
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }

        News n = dao.findById(id);

        if (n == null) {
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }

        // tăng lượt xem
        dao.increaseView(id);

        req.setAttribute("news", n);
        req.getRequestDispatcher("/views/news/detail.jsp").forward(req, resp);
    }
}
