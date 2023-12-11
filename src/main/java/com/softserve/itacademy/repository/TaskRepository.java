package com.softserve.itacademy.repository;

import org.postgresql.Driver;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TaskRepository {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    private static Connection connection;

    static {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private final List<Task> todo = new LinkedList<>();

    public boolean create(Task task) {
        String sql = "INSERT INTO tasks (title, priority) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, task.getTitle());
            statement.setString(2, task.getPriority().toString());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Task read(int id) {
        String sql = "SELECT title, priority FROM tasks WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String title = resultSet.getString("title");
                Priority priority = Priority.valueOf(resultSet.getString("priority"));
                return new Task(title, priority);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(Task newTask) {
        String sql = "UPDATE tasks SET title = ?, priority = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newTask.getTitle());
            statement.setString(2, newTask.getPriority().toString());
            statement.setInt(3, newTask.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Task> all() {
        String sql = "SELECT id, title, priority FROM tasks";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            List<Task> tasks = new LinkedList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                Priority priority = Priority.valueOf(resultSet.getString("priority"));
                tasks.add(new Task(id, title, priority));
            }

            return tasks;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteAll() {
        String sql = "DELETE FROM tasks";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static TaskRepository taskRepository = null;

    public synchronized static TaskRepository getTaskRepository() {
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        return taskRepository;
    }
}
