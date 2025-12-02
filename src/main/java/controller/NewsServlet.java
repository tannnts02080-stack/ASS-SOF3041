package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import dao.NewsDao;
import dao.CategoryDao;
import entity.News;
import entity.Category;

@WebServlet({"/news", "/news/edit", "/news/delete"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50
)
public class NewsServlet extends HttpServlet {

    private final NewsDao newsDao = new NewsDao();
    private final CategoryDao cateDao = new CategoryDao();

    // ======================= GET ============================
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String uri = req.getRequestURI();
        String id  = req.getParameter("id");

        // SỬA
        if (uri.contains("edit")) {
            News n = newsDao.findById(id);
            req.setAttribute("news", n);   // news.jsp sẽ hiểu là mode EDIT
        }

        // XÓA
        if (uri.contains("delete")) {
            newsDao.delete(id);
            resp.sendRedirect(req.getContextPath() + "/news");
            return;
        }

        load(req);
        req.setAttribute("contentPage", "/views/admin/news.jsp");
        req.getRequestDispatcher("/views/layout/adminLayout.jsp").forward(req, resp);
    }

    // ======================= POST ============================
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("insert".equals(action)) {
            insert(req, resp);
            return;
        }

        if ("update".equals(action)) {
            update(req, resp);
            return;
        }
    }

    // ======================= INSERT ============================
    private void insert(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getParameter("id");

        // 1. KIỂM TRA TRÙNG MÃ
        if (newsDao.existsById(id)) {

            req.setAttribute("error", "❌ Mã tin '" + id + "' đã tồn tại. Vui lòng chọn mã khác!");

            // Lưu lại dữ liệu form để hiển thị lại
            News form = new News();
            form.setId(id);
            form.setTitle(req.getParameter("title"));
            form.setContent(req.getParameter("content"));
            form.setAuthor(req.getParameter("author"));
            form.setCategoryId(req.getParameter("categoryId"));
            form.setHome(req.getParameter("home") != null);

            // viewCount: nếu rỗng thì về 0
            String vcStr = req.getParameter("viewCount");
            int viewCount = 0;
            if (vcStr != null && !vcStr.isBlank()) {
                try {
                    viewCount = Integer.parseInt(vcStr);
                } catch (NumberFormatException ignored) {}
            }
            form.setViewCount(viewCount);

            // 👉 GỬI QUA JSP DƯỚI TÊN formData, KHÔNG PHẢI news
            req.setAttribute("formData", form);

            // mode insert (isEdit = false) trong JSP
            load(req);
            req.setAttribute("contentPage", "/views/admin/news.jsp");
            req.getRequestDispatcher("/views/layout/adminLayout.jsp").forward(req, resp);
            return;
        }

        // 2. KHÔNG TRÙNG → THÊM MỚI
        try {
            News n = new News();
            n.setId(id);
            n.setTitle(req.getParameter("title"));
            n.setContent(req.getParameter("content"));
            n.setAuthor(req.getParameter("author"));

            // viewCount
            String vcStr = req.getParameter("viewCount");
            int viewCount = 0;
            if (vcStr != null && !vcStr.isBlank()) {
                viewCount = Integer.parseInt(vcStr);
            }
            n.setViewCount(viewCount);

            n.setCategoryId(req.getParameter("categoryId"));
            n.setHome(req.getParameter("home") != null);
            n.setPostedDate(new java.sql.Date(System.currentTimeMillis()));

            // Upload ảnh
            Part part = req.getPart("imageFile");
            String fileName = part != null
                    ? Path.of(part.getSubmittedFileName()).getFileName().toString()
                    : "";

            if (fileName != null && !fileName.isEmpty()) {
                String upload = req.getServletContext().getRealPath("/assets/img/");
                File folder = new File(upload);
                if (!folder.exists()) folder.mkdirs();

                part.write(upload + File.separator + fileName);
                n.setImage(fileName);
            } else {
                n.setImage(null);
            }

            newsDao.insert(n);
            resp.sendRedirect(req.getContextPath() + "/news");

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Có lỗi khi thêm tin. Vui lòng thử lại.");
            load(req);
            req.setAttribute("contentPage", "/views/admin/news.jsp");
            req.getRequestDispatcher("/views/layout/adminLayout.jsp").forward(req, resp);
        }
    }

    // ======================= UPDATE ============================
    private void update(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            News n = new News();
            n.setId(req.getParameter("id"));
            n.setTitle(req.getParameter("title"));
            n.setContent(req.getParameter("content"));
            n.setAuthor(req.getParameter("author"));

            String vcStr = req.getParameter("viewCount");
            int viewCount = 0;
            if (vcStr != null && !vcStr.isBlank()) {
                viewCount = Integer.parseInt(vcStr);
            }
            n.setViewCount(viewCount);

            n.setCategoryId(req.getParameter("categoryId"));
            n.setHome(req.getParameter("home") != null);
            n.setPostedDate(new java.sql.Date(System.currentTimeMillis()));

            String oldImage = req.getParameter("oldImage");

            Part part = req.getPart("imageFile");
            String fileName = part != null
                    ? Path.of(part.getSubmittedFileName()).getFileName().toString()
                    : "";

            if (fileName != null && !fileName.isEmpty()) {
                String uploadPath = req.getServletContext().getRealPath("/assets/img/");
                File folder = new File(uploadPath);
                if (!folder.exists()) folder.mkdirs();

                part.write(uploadPath + File.separator + fileName);
                n.setImage(fileName);
            } else {
                // giữ ảnh cũ
                n.setImage(oldImage);
            }

            newsDao.update(n);
            resp.sendRedirect(req.getContextPath() + "/news");

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Có lỗi khi cập nhật tin. Vui lòng thử lại.");
            load(req);
            req.setAttribute("contentPage", "/views/admin/news.jsp");
            req.getRequestDispatcher("/views/layout/adminLayout.jsp").forward(req, resp);
        }
    }

    // ======================= LOAD DỮ LIỆU LIST + CATEGORY ============================
    private void load(HttpServletRequest req) {
    	List<News> list = newsDao.findAllOrderById();

        List<Category> cates = cateDao.findAll();
        req.setAttribute("list", list);
        req.setAttribute("categories", cates);
    }
}
