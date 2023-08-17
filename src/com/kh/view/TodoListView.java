package com.kh.view;

import com.kh.controller.TodoListController;
import com.kh.model.vo.TodoList;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class TodoListView {

  private TodoListController tc = new TodoListController();
  private Scanner sc = new Scanner(System.in);
  private String userInput = "";

  public void mainMenu() {
    while (true) {
      System.out.println("");
      System.out.println("1. 전체 조회");
      System.out.println("2. 할일 추가");
      System.out.println("3. 할일 수정");
      System.out.println("4. 할일 삭제");
      System.out.println("0. 프로그램 종료");
      System.out.print("메뉴 선택 > ");
      userInput = sc.nextLine();
      switch (userInput) {
        case "1":
          selectAll();
          break;
        case "2":
          addTask();
          break;
        case "3":
          updateTask();
          break;
        case "4":
          deleteTask();
          break;
        case "0":
          return;
        default:
          System.out.println("없는 메뉴 입니다. 다시 선택해 주세요.");
      }
    }
  }

  private void selectAll() {
    ArrayList<TodoList> temp = new TodoListController().selectAll();
    String priority = "";
    for (TodoList todoList : temp) {
      if (todoList.getPriority() == 3) {
        priority = "매우 중요";
      } else if (todoList.getPriority() == 2) {
        priority = "중요";
      } else if (todoList.getPriority() == 1) {
        priority = "보통";
      }
      System.out.println("고유번호: " + todoList.getSequence());
      System.out.print("\t실행여부: " + todoList.getExecute());
      System.out.print("\t할일: " + todoList.getTask());
      System.out.print("\t기한: " + todoList.getDueDate());
      System.out.print("\t남은일자: " + todoList.getRemaining());
      System.out.println("\t우선순위: " + priority);
      System.out.println();
    }
  }

  private void addTask() {
    System.out.println("---------할일 추가------------");
    System.out.print("할일 입력 > ");
    String task = sc.nextLine();
    System.out.print("기한 입력 > (ex 20230812)");
    String duedate = sc.nextLine();
    int year = Integer.parseInt(duedate.substring(0, 3));
    int month = Integer.parseInt(duedate.substring(4, 5));
    int day = Integer.parseInt(duedate.substring(6, 7));
    System.out.print("우선순위 입력(3: 매우 중요 / 2: 중요 / 1: 보통) > ");
    int priority = Integer.parseInt(sc.nextLine());
    tc.addTask(new TodoList('N', task, new Date(year, month, day), priority));
  }

  private void updateTask() {
    System.out.println("---------할일 수정------------");
    System.out.print("수정 고유번호 입력 > ");
    int seq = Integer.parseInt(sc.nextLine());
    System.out.print("할일 입력 > ");
    String task = sc.nextLine();
    System.out.print("기한 입력 > (ex 20230812)");
    String duedate = sc.nextLine();
    int year = Integer.parseInt(duedate.substring(0, 3));
    int month = Integer.parseInt(duedate.substring(4, 5));
    int day = Integer.parseInt(duedate.substring(6, 7));
    System.out.print("우선순위 입력(3: 매우 중요 / 2: 중요 / 1: 보통) > ");
    int priority = Integer.parseInt(sc.nextLine());
    tc.updateTask(
      new TodoList(seq, 'N', task, new Date(year, month, day), 0, priority)
    );
  }

  private void deleteTask() {
    System.out.println("---------할일 삭제------------");
    System.out.print("수정 고유번호 입력 > ");
    int seq = Integer.parseInt(sc.nextLine());
    TodoList temp = new TodoList();
    temp.setSequence(seq);
    tc.deleteTask(temp);
  }

  public void displaySuccess() {
    System.out.println("서비스 요청 성공!!");
  }

  public void displayFail() {
    System.out.println("서비스 요청 실패!!");
  }
}
