package controller;

import java.io.IOException;
import java.sql.Connection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import utils.JdbcHelper;


public class TestDB extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/plain; charset=UTF-8");

        try {
            Connection conn = JdbcHelper.getConnection();
            resp.getWriter().println("CONNECTED OK!");
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("ERROR: " + e.toString());
            resp.getWriter().println("\nCAUSE: " + e.getCause());
        }
    }
}
