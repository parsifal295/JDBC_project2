package com.kh.controller;

import com.kh.model.service.TodoListService;
import com.kh.model.vo.TodoList;
import com.kh.view.TodoListView;
import java.util.ArrayList;

public class TodoListController {

  public ArrayList<TodoList> selectAll() {
    ArrayList<TodoList> temp = new TodoListService().selectAll();
    ArrayList<TodoList> temp2 = new TodoListService().selectComplete();
    if (temp.isEmpty()) {
      new TodoListView().displayFail();
    } else {
      new TodoListView().displayList(temp, temp2);
    }
    return temp;
  }

  public ArrayList<TodoList> selectComplete() {
    ArrayList<TodoList> temp = new TodoListService().selectComplete();
    if (temp.isEmpty()) {
      new TodoListView().displayFail();
    }
    return temp;
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
    if (result > 0) {
      new TodoListView().displaySuccess();
    } else {
      new TodoListView().displayFail();
    }
    return result;
  }

  public void completeTask(int seq) {
    int result = new TodoListService().completeTask(seq);
    if (result > 0) {
      new TodoListView().displaySuccess();
    } else {
      new TodoListView().displayFail();
    }
  }

  public int deleteTask(TodoList tList) {
    int result = new TodoListService().deleteTask(tList);
    if (result > 0) {
      new TodoListView().displaySuccess();
    } else {
      new TodoListView().displayFail();
    }
    return result;
  }
}
