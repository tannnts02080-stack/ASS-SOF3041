package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.NewsDao;
import entity.News;
import entity.User;

@WebServlet("/reporter/home")
public class ReporterServlet extends HttpServlet {

    NewsDao newsDao = new NewsDao();

    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        if (user.getRole() != 0) {
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }

        // 🔥 LẤY TẤT CẢ TIN, KHÔNG CHỈ CỦA MÌNH
        List<News> list = newsDao.findAllOrderById();

        req.setAttribute("list", list);

        // 🔥 GỬI USERID QUA JSP ĐỂ KIỂM TRA QUYỀN
        req.setAttribute("myId", user.getId());

        req.setAttribute("contentPage", "/views/reporter/home.jsp");
        req.getRequestDispatcher("/views/layout/reporterLayout.jsp")
                .forward(req, resp);
    }



}
