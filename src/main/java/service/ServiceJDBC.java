package service;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceJDBC implements Service {

    Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kaka", "root", "123456");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }


    @Override
    public List<User> findAll() {

        List<User> userList = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from kaka");
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                userList.add(new User(id, name, email, country));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    @Override
    public void save(User user) {
        System.out.println("insert users SQL");

        Connection connection = getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("insert kaka (name ,email,country) values (?,?,?)");
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getCountry());
            System.out.println(statement);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public User findByID(int id) {
        User user = null;
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from kaka where id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user = new User(id, name, email, country);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public void update(int id, User user) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("update kaka set name = ?, email = ?, country = ? where id = ?");
        statement.setString(1, user.getName());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getCountry());
        statement.setInt(4, id);

        statement.executeUpdate();
    }

    @Override
    public void remote(int id) {

        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("delete from kaka where id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

//    @Override
//    public User findByName(String name) {
//        User user = null;
//        Connection connection = getConnection();
//        try {
//            PreparedStatement statement = connection.prepareStatement("select * from kaka where name = ?");
//            statement.setString(1,name);
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()){
//                int id = rs.getInt("id");
//                String email = rs.getString("email");
//                String country = rs.getString("country");
//                user = new User(id,name,email,country);
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return user;
//    }

    @Override
    public List<User> searchByName(String name) {
        List<User> userList = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from kaka where  name = ?");
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String country = rs.getString("country");
                userList.add(new User(id, name, email, country));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    @Override
    public List<User> searchByEmail(String email) {
        List<User> userList = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from kaka where  email = ?");
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String country = rs.getString("country");
                userList.add(new User(id, name, email, country));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    @Override
    public List<User> searchByCountry(String country) {
        List<User> userList = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from kaka where  country = ?");
            statement.setString(1, country);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                userList.add(new User(id, name, email, country));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }


    @Override
    public List<User> sortByName() {
        List<User> userList1 = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select  * from kaka" +
                    " order by name asc ");
            ResultSet rsx = statement.executeQuery();
            while (rsx.next()) {
                int id = rsx.getInt("id");
                String name = rsx.getString("name");
                String email = rsx.getString("email");
                String country = rsx.getString("country");
                userList1.add(new User(id, name, email, country));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList1;
    }

    @Override
    public List<User> timkiem(String value) {
        List<User> userList =new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select  * from kaka where name = ? , email = ? , country = ?");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");

                if (name.equals(value)) {
                    statement.setString(1,value);
                    userList.add(new User(id, value, email, country));
                }
                else if (email.equals(value)) {
                    statement.setString(1,value);
                    userList.add(new User(id, name, value, country));
                }
                else if (country.equals(value)){
                    statement.setString(1,country);
                    userList.add(new User(id, name, email, value));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }
}
