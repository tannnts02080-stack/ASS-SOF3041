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

@WebServlet("/category/page")
public class CategoryPageServlet extends HttpServlet {

    NewsDao newsDao = new NewsDao();
    CategoryDao cateDao = new CategoryDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        String id = req.getParameter("id"); // CT01, CT02...

        // Lấy thông tin danh mục
        Category cate = cateDao.findById(id);
        req.setAttribute("category", cate);

        // Lấy tin theo danh mục
        List<News> list = newsDao.findByCategory(id);
        req.setAttribute("list", list);

        req.getRequestDispatcher("/views/category.jsp").forward(req, resp);
    }
}
