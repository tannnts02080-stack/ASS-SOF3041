package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

import dao.UserDao;
import entity.User;

@WebServlet({"/user", "/user/edit", "/user/delete"})
public class UserServlet extends HttpServlet {

    UserDao dao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String uri = req.getRequestURI();
        String id = req.getParameter("id");

        // Mặc định: KHÔNG có user → form "Thêm mới"
        req.setAttribute("user", null);

        // Nếu là edit thì set user
        if (uri.contains("edit")) {
            User u = dao.findById(id);
            req.setAttribute("user", u);
        }

        // Delete
        if (uri.contains("delete")) {
            dao.delete(id);
            resp.sendRedirect(req.getContextPath() + "/user");
            return;
        }

        load(req);

        req.setAttribute("contentPage", "/views/admin/user.jsp");
        req.getRequestDispatcher("/views/layout/adminLayout.jsp").forward(req, resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("insert".equals(action)) insert(req);
        else if ("update".equals(action)) update(req);

        resp.sendRedirect(req.getContextPath() + "/user");
    }

    private void insert(HttpServletRequest req) {
        User u = new User();
        u.setId(req.getParameter("id"));
        u.setPassword(req.getParameter("password"));
        u.setFullname(req.getParameter("fullname"));
        u.setBirthday(java.sql.Date.valueOf(req.getParameter("birthday")));
        u.setPhone(req.getParameter("phone"));
        u.setEmail(req.getParameter("email"));
        u.setRole(Integer.parseInt(req.getParameter("role")));
        dao.insert(u);
    }

    private void update(HttpServletRequest req) {
        User u = new User();
        u.setId(req.getParameter("id"));
        u.setPassword(req.getParameter("password"));
        u.setFullname(req.getParameter("fullname"));
        u.setBirthday(java.sql.Date.valueOf(req.getParameter("birthday")));
        u.setPhone(req.getParameter("phone"));
        u.setEmail(req.getParameter("email"));
        u.setRole(Integer.parseInt(req.getParameter("role")));
        dao.update(u);
    }

    private void load(HttpServletRequest req) {
        List<User> list = dao.findAll();
        req.setAttribute("list", list);
    }
}

