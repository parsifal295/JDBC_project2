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
              rSet.getDate("TODO_DUEDATE"),
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
    return 0;
  }

  public int updateTask(Connection conn, TodoList tList) {
    return 0;
  }

  public int deleteTask(Connection conn, TodoList tList) {
    return 0;
  }
}
