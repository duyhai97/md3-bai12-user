package controller;

import model.User;
import service.Service;
import service.ServiceJDBC;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "Servlet", urlPatterns = "/users")
public class Servlet extends HttpServlet {
    private Service service = new ServiceJDBC();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";
//        try {
            switch (action){
                case "create":
                    showCreateForm(request,response);
                    break;
                case "edit":
                    showEditForm(request,response);
                    break;
                case "delete":
                    showDeleteForm(request,response);
                    break;
                case "view":
                    viewUser(request,response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
//        }
        }

    private void viewUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = this.service.findByID(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("users/view.jsp");
        request.setAttribute("user",user);
        dispatcher.forward(request,response);
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = this.service.findByID(id);
        RequestDispatcher dispatcher;
        if  (user == null) dispatcher = request.getRequestDispatcher("error-404.jsp");
        else {
            request.setAttribute("user",user);
            dispatcher = request.getRequestDispatcher("users/delete.jsp");
        }
        dispatcher.forward(request,response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = this.service.findByID(id);
        RequestDispatcher dispatcher;
        if (user == null) dispatcher = request.getRequestDispatcher("error-404.jsp");
        else {
            request.setAttribute("user",user);
            dispatcher = request.getRequestDispatcher("users/edit.jsp");
        }
        dispatcher.forward(request,response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("users/create.jsp");
        dispatcher.forward(request,response);
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = this.service.findAll();
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("users/list.jsp");
        dispatcher.forward(request,response);
    }




    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";
        try {
        switch (action){
            case "create":
                createUser(request,response);
                break;
            case "edit":
                editUser(request,response);
                break;
            case "delete":
                deleteUser(request,response);
                break;


        }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = this.service.findByID(id);

        RequestDispatcher dispatcher;
        if (user == null) dispatcher = request.getRequestDispatcher("error-404.jsp");
        else {
            this.service.remote(id);
            dispatcher = request.getRequestDispatcher("users/delete.jsp");
        }
        request.setAttribute("message","successful");
        dispatcher.forward(request,response);
    }

    private void editUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User user = new User(id,name,email,country);
        RequestDispatcher dispatcher;
        service.update(id,user);
        dispatcher = request.getRequestDispatcher("users/edit.jsp");
        request.setAttribute("message","Successful product repair");
        dispatcher.forward(request,response);
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = (int) (Math.random()*10000);
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User user = new User(id,name,email,country);
        this.service.save(user);
        RequestDispatcher dispatcher =request.getRequestDispatcher("users/create.jsp");
        request.setAttribute("message","New successful product added");
        dispatcher.forward(request,response);
    }
}
