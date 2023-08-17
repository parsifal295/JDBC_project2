package com.kh.model.service;

import static com.kh.common.JDBCTemplate.*;

import com.kh.model.dao.TodoListDao;
import com.kh.model.vo.TodoList;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class TodoListService {

  public ArrayList<TodoList> selectAll() {
    ArrayList<TodoList> temp = null;
    try (Connection conn = getConnection()) {
      temp = new TodoListDao().selectAll(conn);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return temp;
  }

  public ArrayList<TodoList> selectComplete() {
    ArrayList<TodoList> temp = null;
    try (Connection conn = getConnection()) {
      temp = new TodoListDao().selectComplete(conn);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return temp;
  }

  public int addTask(TodoList tList) {
    int result = 0;
    try (Connection conn = getConnection()) {
      result = new TodoListDao().addTask(conn, tList);
      if (result > 0) {
        commit(conn);
      } else {
        rollback(conn);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  public int updateTask(TodoList tList) {
    int result = 0;
    try (Connection conn = getConnection()) {
      result = new TodoListDao().updateTask(conn, tList);
      if (result > 0) {
        commit(conn);
      } else {
        rollback(conn);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  public int completeTask(int seq) {
    int result = 0;
    try (Connection conn = getConnection()) {
      result = new TodoListDao().completeTask(conn, seq);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public int deleteTask(TodoList tList) {
    int result = 0;
    try (Connection conn = getConnection()) {
      result = new TodoListDao().deleteTask(conn, tList);
      if (result > 0) {
        commit(conn);
      } else {
        rollback(conn);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }
}
