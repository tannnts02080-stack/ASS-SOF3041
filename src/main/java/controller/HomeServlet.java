package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.NewsDao;
import dao.CategoryDao;
import entity.News;
import entity.Category;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    NewsDao newsDao = new NewsDao();
    CategoryDao cateDao = new CategoryDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String type = req.getParameter("type");

        // 🟥 Nếu bấm vào "Tin nổi bật"
        if ("hot".equals(type)) {
            List<News> hot = newsDao.findHomeNews();
            req.setAttribute("hotNews", hot);
            req.setAttribute("pageTitle", "🔥 Tin nổi bật");

            req.setAttribute("contentPage", "/views/hotNews.jsp");
            req.getRequestDispatcher("/views/layout/readerLayout.jsp").forward(req, resp);
            return;
        }

        // 🟦 Nếu bấm vào "Tin mới nhất"
        if ("new".equals(type)) {
            List<News> newest = newsDao.findAll();
            req.setAttribute("newest", newest);
            req.setAttribute("pageTitle", "🆕 Tin mới nhất");

            req.setAttribute("contentPage", "/views/newestNews.jsp");
            req.getRequestDispatcher("/views/layout/readerLayout.jsp").forward(req, resp);
            return;
        }

        // 🟩 MẶC ĐỊNH TRANG CHỦ (KHÔNG có tham số type)
        List<News> hotNews = newsDao.findHomeNews();
        List<Category> categories = cateDao.findAll();
        List<News> newest = newsDao.findAll();

        req.setAttribute("hotNews", hotNews);
        req.setAttribute("categories", categories);
        req.setAttribute("newest", newest);

        req.setAttribute("contentPage", "/views/homeContent.jsp");
        req.getRequestDispatcher("/views/layout/readerLayout.jsp").forward(req, resp);

    }
}
