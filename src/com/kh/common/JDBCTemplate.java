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

  public static String htmlStart() {
    String str =
      "<!DOCTYPE html>\r\n" +
      "<html lang=\"en\">\r\n" +
      "<head>\r\n" +
      "    <title>TodoList</title>\r\n" +
      "</head>\r\n" +
      "<body>\r\n" +
      "    \r\n" +
      "    <table border=\"1\">\r\n" +
      "        <caption><b>TodoList</b></caption>\r\n" +
      "        <thead>\r\n" +
      "            <th width=\"100\" height=\"25\">실행여부</th>\r\n" +
      "            <th width=\"400\">할일</th>\r\n" +
      "            <th width=\"150\">기한</th>\r\n" +
      "            <th width=\"100\">남은일자</th>\r\n" +
      "        </thead>\r\n";

    return str;
  }

  public static String htmlComplete() {
    String str =
      " </table>" +
      "<br />" +
      "<br />" +
      "<br />" +
      "<br />" +
      " <table border=\"1\">\r\n" +
      "        <caption><b>CompleteList</b></caption>\r\n" +
      "        <thead>\r\n" +
      "            <th width=\"100\" height=\"25\">실행여부</th>\r\n" +
      "            <th width=\"400\">할일</th>\r\n" +
      "            <th width=\"150\">기한</th>\r\n" +
      "            <th width=\"100\">남은일자</th>\r\n" +
      "        </thead>\r\n";

    return str;
  }

  public static String htmlEnd() {
    String str = "</table></html>\r\n";

    return str;
  }
}
