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
            case "search":
                search(request,response);
                break;
            case "sortByName":
                sortByName(request,response);
                break;
            case "timkiem":
                timkiemU(request,response);
                break;


        }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void timkiemU(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String value = request.getParameter("value");
        List<User> userList = this.service.timkiem(value);
        RequestDispatcher dispatcher = request.getRequestDispatcher("users/timkiem.jsp");
        request.setAttribute("userList", userList);
        dispatcher.forward(request,response);

    }

    private void sortByName(HttpServletRequest request, HttpServletResponse response) {
        List<User> users = this.service.sortByName();
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("users/sortByName.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String select = request.getParameter("select");
        switch (select){
            case "name":
                String name = request.getParameter("aaa");
                List<User> userList = this.service.searchByName(name);
                RequestDispatcher dispatcher;
                if (userList == null) dispatcher = request.getRequestDispatcher("error-404.jsp");
                else dispatcher = request.getRequestDispatcher("users/searchByName.jsp");
                request.setAttribute("userList",userList);
                dispatcher.forward(request,response);
                break;
            case "email":
                String email = request.getParameter("aaa");
                List<User> userList1 = this.service.searchByEmail(email);
                RequestDispatcher dispatcher1 = null;
                if (userList1 == null) dispatcher1 = request.getRequestDispatcher("error-404.jsp");
                else dispatcher1 = request.getRequestDispatcher("users/searchByEmail.jsp");
                request.setAttribute("userList1",userList1);
                dispatcher1.forward(request,response);
                break;
            case "country":
                String country = request.getParameter("aaa");
                List<User> userList2 = this.service.searchByCountry(country);
                RequestDispatcher dispatcher2 = null;
                if (userList2 == null) dispatcher2 = request.getRequestDispatcher("error-404.jsp");
                else dispatcher2 = request.getRequestDispatcher("users/searchByCountry.jsp");
                request.setAttribute("userList2",userList2);
                dispatcher2.forward(request,response);
                break;

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
