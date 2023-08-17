package com.kh.controller;

import com.kh.model.service.TodoListService;
import com.kh.model.vo.TodoList;
import com.kh.view.TodoListView;
import java.util.ArrayList;

public class TodoListController {

  public ArrayList<TodoList> selectAll() {
    ArrayList<TodoList> temp = new ArrayList<TodoList>();
    if (temp.isEmpty()) {
      new TodoListView().displayFail();
    }
    new TodoListService().selectAll();
    return null;
  }

  public int addTask(TodoList tList) {
    int result = new TodoListService().addTask(tList);
    if (result > 0) {
      new TodoListView().displaySuccess();
    } else {
      new TodoListView().displayFail();
    }
    return result;
  }

  public int updateTask(TodoList tList) {
    int result = new TodoListService().updateTask(tList);
    new TodoListService().updateTask(tList);
    if (result > 0) {
      new TodoListView().displaySuccess();
    } else {
      new TodoListView().displayFail();
    }
    return result;
  }

  public int deleteTask(TodoList tList) {
    int result = new TodoListService().deleteTask(tList);
    new TodoListService().updateTask(tList);
    if (result > 0) {
      new TodoListView().displaySuccess();
    } else {
      new TodoListView().displayFail();
    }
    return result;
  }
}
