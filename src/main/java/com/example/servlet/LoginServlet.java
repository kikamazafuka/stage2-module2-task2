package com.example.servlet;

import com.example.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user")==null){
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else req.getRequestDispatcher("/user/hello.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Users users = Users.getInstance();

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if(users.getUsers().contains(login) && !password.isEmpty()){
            session.setAttribute("user", login);
            resp.sendRedirect("/user/hello.jsp");
        } else req.getRequestDispatcher("login.jsp").forward(req,resp);
    }
}
