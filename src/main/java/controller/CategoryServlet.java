package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.CategoryDao;
import entity.Category;

@WebServlet({"/category", "/category/edit", "/category/delete"})
public class CategoryServlet extends HttpServlet {

    CategoryDao dao = new CategoryDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        String id = req.getParameter("id");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "edit":
                edit(req, resp, id);
                break;
            case "delete":
                delete(req, resp, id);
                break;
            default:
                list(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("insert".equals(action)) {
            insert(req, resp);
        } else if ("update".equals(action)) {
            update(req, resp);
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Category> list = dao.findAll();
        req.setAttribute("categories", list);

        // ⭐ Gửi mã mới sang JSP
        req.setAttribute("nextId", dao.getNextId());

        req.setAttribute("contentPage", "/views/admin/category.jsp");
        req.getRequestDispatcher("/views/layout/adminLayout.jsp").forward(req, resp);
    }


    private void edit(HttpServletRequest req, HttpServletResponse resp, String id)
            throws ServletException, IOException {

        Category c = dao.findById(id);
        req.setAttribute("category", c);

        list(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp, String id)
            throws IOException {

        dao.delete(id);
        resp.sendRedirect(req.getContextPath() + "/category");
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        String id = req.getParameter("id");
        String name = req.getParameter("name");

        // --- KIỂM TRA TRÙNG ID ---
        if (dao.existsById(id)) {

            req.setAttribute("error", "❌ Mã loại '" + id + "' đã tồn tại. Vui lòng chọn mã khác!");
            req.setAttribute("inputId", id);
            req.setAttribute("inputName", name);

            List<Category> list = dao.findAll();
            req.setAttribute("categories", list);

            req.setAttribute("contentPage", "/views/admin/category.jsp");
            req.getRequestDispatcher("/views/layout/adminLayout.jsp").forward(req, resp);
            return;
        }

        // --- INSERT ---
        Category c = new Category(id, name);
        dao.insert(c);

        resp.sendRedirect(req.getContextPath() + "/category");
    }




    private void update(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String id = req.getParameter("id");
        String name = req.getParameter("name");

        Category c = new Category(id, name);
        dao.update(c);

        resp.sendRedirect(req.getContextPath() + "/category");
    }
}
