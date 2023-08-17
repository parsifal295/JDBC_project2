package com.kh.model.dao;

import com.kh.model.vo.TodoList;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class TodoListDao {

  private Properties prop = new Properties();

  public TodoListDao() {
    try {
      prop.loadFromXML(new FileInputStream("resources/todoList-mapper.xml"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public ArrayList<TodoList> selectAll(Connection conn) {
    ArrayList<TodoList> temp = new ArrayList<TodoList>();
    try (
      PreparedStatement pStmt = conn.prepareStatement(
        prop.getProperty("selectAll")
      )
    ) {
      try (ResultSet rSet = pStmt.executeQuery()) {
        while (rSet.next()) {
          temp.add(
            new TodoList(
              rSet.getInt("TODO_SEQ"),
              rSet.getString("TODO_EXECUTE").charAt(0),
              rSet.getString("TODO_TASK"),
              rSet.getString("TODO_DUEDATE"),
              rSet.getInt("DDAY"),
              rSet.getInt("TODO_PRIORITY")
            )
          );
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return temp;
  }

  public ArrayList<TodoList> selectComplete(Connection conn) {
    ArrayList<TodoList> temp = new ArrayList<TodoList>();
    try (
      PreparedStatement pStmt = conn.prepareStatement(
        prop.getProperty("selectComplete")
      )
    ) {
      try (ResultSet rSet = pStmt.executeQuery()) {
        while (rSet.next()) {
          temp.add(
            new TodoList(
              rSet.getInt("TODO_SEQ"),
              rSet.getString("TODO_EXECUTE").charAt(0),
              rSet.getString("TODO_TASK"),
              rSet.getString("TODO_DUEDATE"),
              rSet.getInt("DDAY"),
              rSet.getInt("TODO_PRIORITY")
            )
          );
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return temp;
  }

  public int addTask(Connection conn, TodoList tList) {
    int result = 0;
    try (
      PreparedStatement pStmt = conn.prepareStatement(
        prop.getProperty("addTask")
      )
    ) {
      pStmt.setString(1, tList.getTask());
      pStmt.setString(2, tList.getDueDate());
      pStmt.setInt(3, tList.getPriority());
      result = pStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  public int updateTask(Connection conn, TodoList tList) {
    int result = 0;
    try (
      PreparedStatement pStmt = conn.prepareStatement(
        prop.getProperty("updateTask")
      )
    ) {
      pStmt.setString(1, tList.getTask());
      pStmt.setString(2, tList.getDueDate());
      pStmt.setInt(3, tList.getPriority());
      pStmt.setInt(4, tList.getSequence());
      result = pStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  public int completeTask(Connection conn, int seq) {
    int result = 0;
    try (
      PreparedStatement pstmt = conn.prepareStatement(
        prop.getProperty("completeTask")
      )
    ) {
      pstmt.setInt(1, seq);
      result = pstmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public int deleteTask(Connection conn, TodoList tList) {
    int result = 0;
    try (
      PreparedStatement pStmt = conn.prepareStatement(
        prop.getProperty("deleteTask")
      )
    ) {
      pStmt.setInt(1, tList.getSequence());
      result = pStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }
}
