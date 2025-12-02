package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.NewsDao;
import dao.CategoryDao;
import entity.News;
import entity.User;
import entity.Category;

@WebServlet("/reporter/news/*")
@MultipartConfig
public class ReporterNewsServlet extends HttpServlet {

    NewsDao newsDao = new NewsDao();
    CategoryDao cateDao = new CategoryDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || user.getRole() != 0) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String path = req.getPathInfo();

        // Load danh sách category
        req.setAttribute("categories", cateDao.findAll());

        // Form đăng mới
        if (path == null || path.equals("/new")) {
            req.setAttribute("contentPage", "/views/reporter/news.jsp");
            req.getRequestDispatcher("/views/layout/reporterLayout.jsp").forward(req, resp);
            return;
        }

        // Sửa bài
        if (path.equals("/edit")) {
            String id = req.getParameter("id");
            News n = newsDao.findById(id);

            req.setAttribute("news", n);
            req.setAttribute("contentPage", "/views/reporter/news.jsp");
            req.getRequestDispatcher("/views/layout/reporterLayout.jsp").forward(req, resp);
            return;
        }

        // Xoá
        if (path.equals("/delete")) {
            String id = req.getParameter("id");
            newsDao.delete(id);
            resp.sendRedirect(req.getContextPath() + "/reporter/home");
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || user.getRole() != 0) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String path = req.getPathInfo();

        if (path.equals("/save")) {

            String id = req.getParameter("id");

            News exists = newsDao.findById(id);

            boolean update = exists != null && req.getParameter("editing") != null;

            // ====== TRƯỜNG HỢP THÊM MỚI → KIỂM TRA TRÙNG ID ======
            if (!update && exists != null) {

                req.setAttribute("error", "❌ Mã tin '" + id + "' đã tồn tại. Vui lòng chọn mã khác!");

                // giữ lại toàn bộ dữ liệu đã nhập
                req.setAttribute("inputId", id);
                req.setAttribute("inputTitle", req.getParameter("title"));
                req.setAttribute("inputContent", req.getParameter("content"));
                req.setAttribute("inputCate", req.getParameter("categoryId"));
                req.setAttribute("inputHome", req.getParameter("home") != null);

                // load categories
                req.setAttribute("categories", cateDao.findAll());

                // load form
                req.setAttribute("contentPage", "/views/reporter/news.jsp");
                req.getRequestDispatcher("/views/layout/reporterLayout.jsp").forward(req, resp);
                return;
            }

            // ====== CONTINUE: insert / update ======
            News n = update ? exists : new News();

            n.setId(id);
            n.setTitle(req.getParameter("title"));
            n.setContent(req.getParameter("content"));
            n.setCategoryId(req.getParameter("categoryId"));
            n.setAuthor(user.getId());
            n.setHome(req.getParameter("home") != null);
            n.setPostedDate(new java.sql.Date(System.currentTimeMillis()));

            Part part = req.getPart("imageFile");
            String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();

            if (!fileName.isEmpty()) {
                String upload = req.getServletContext().getRealPath("/assets/img/");
                new File(upload).mkdirs();
                part.write(upload + "/" + fileName);
                n.setImage(fileName);
            }

            if (update) newsDao.update(n);
            else newsDao.insert(n);

            resp.sendRedirect(req.getContextPath() + "/reporter/home");
        }

    }
}
