package com.kh.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCTemplate {

  private JDBCTemplate() {}

  public static Connection getConnection() {
    Properties prop = new Properties();
    try {
      prop.load(new FileInputStream("resources/driver.properties"));
    } catch (IOException err) {
      err.printStackTrace();
    }
    Connection conn = null;
    try {
      Class.forName(prop.getProperty("driver"));
      conn =
        DriverManager.getConnection(
          prop.getProperty("url"),
          prop.getProperty("username"),
          prop.getProperty("password")
        );
      conn.setAutoCommit(false);
    } catch (ClassNotFoundException | SQLException ec) {
      throw new RuntimeException(ec);
    }
    return conn;
  }

  public static void commit(Connection conn) {
    try {
      if (conn != null && !conn.isClosed()) {
        conn.commit();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static void rollback(Connection conn) {
    try {
      if (conn != null && !conn.isClosed()) {
        conn.rollback();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static void close(Connection conn) {
    try {
      if (conn != null && !conn.isClosed()) {
        conn.close();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static void close(Statement stmt) {
    try {
      if (stmt != null && !stmt.isClosed()) {
        stmt.close();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static void close(ResultSet rSet) {
    try {
      if (rSet != null && !rSet.isClosed()) {
        rSet.close();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
