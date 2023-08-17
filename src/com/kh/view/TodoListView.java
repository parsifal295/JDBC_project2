package com.kh.view;

import com.kh.common.JDBCTemplate;
import com.kh.controller.TodoListController;
import com.kh.model.vo.TodoList;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TodoListView {

  private TodoListController tc = new TodoListController();
  private Scanner sc = new Scanner(System.in);
  private String userInput = "";

  public void mainMenu() {
    while (true) {
      System.out.println("");
      System.out.println("1. 할일 조회");
      System.out.println("2. 할일 추가");
      System.out.println("3. 할일 수정");
      System.out.println("4. 할일 삭제");
      System.out.println("5. 완료 처리");
      System.out.println("6. 완료 목록 조회");
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
        case "5":
          completeTask();
          break;
        case "6":
          selectComplete();
          break;
        case "0":
          return;
        default:
          System.out.println("없는 메뉴 입니다. 다시 선택해 주세요.");
      }
    }
  }

  private void selectAll() {
    ArrayList<TodoList> temp = tc.selectAll();
    String priority = "";
    for (TodoList todoList : temp) {
      if (todoList.getPriority() == 3) {
        priority = "매우 중요";
      } else if (todoList.getPriority() == 2) {
        priority = "중요";
      } else if (todoList.getPriority() == 1) {
        priority = "보통";
      }
      System.out.print("고유번호: " + todoList.getSequence());
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
    System.out.print("기한 입력 (ex.20230930) > ");
    String date = sc.nextLine();
    System.out.print("우선순위 입력(3: 매우 중요 / 2: 중요 / 1: 보통) > ");
    int priority = Integer.parseInt(sc.nextLine());
    tc.addTask(new TodoList('N', task, date, priority));
  }

  private void updateTask() {
    System.out.println("---------할일 수정------------");
    System.out.print("수정 고유번호 입력 > ");
    int seq = Integer.parseInt(sc.nextLine());
    System.out.print("할일 입력 > ");
    String task = sc.nextLine();
    System.out.print("기한 입력 > (ex 20230812)");
    String duedate = sc.nextLine();
    System.out.print("우선순위 입력(3: 매우 중요 / 2: 중요 / 1: 보통) > ");
    int priority = Integer.parseInt(sc.nextLine());
    tc.updateTask(new TodoList(seq, 'N', task, duedate, 0, priority));
  }

  private void completeTask() {
    System.out.println("---------할일 수정------------");
    System.out.print("완료한 목록 고유번호 입력 > ");
    int seq = Integer.parseInt(sc.nextLine());
    new TodoListController().completeTask(seq);
  }

  private void deleteTask() {
    System.out.println("---------할일 삭제------------");
    System.out.print("수정 고유번호 입력 > ");
    int seq = Integer.parseInt(sc.nextLine());
    TodoList temp = new TodoList();
    temp.setSequence(seq);
    tc.deleteTask(temp);
  }

  private void selectComplete() {
    ArrayList<TodoList> temp = tc.selectComplete();
    String priority = "";
    for (TodoList todoList : temp) {
      if (todoList.getPriority() == 3) {
        priority = "매우 중요";
      } else if (todoList.getPriority() == 2) {
        priority = "중요";
      } else if (todoList.getPriority() == 1) {
        priority = "보통";
      }
      System.out.print("고유번호: " + todoList.getSequence());
      System.out.print("\t실행여부: " + todoList.getExecute());
      System.out.print("\t할일: " + todoList.getTask());
      System.out.print("\t기한: " + todoList.getDueDate());
      System.out.print("\t남은일자: " + todoList.getRemaining());
      System.out.println("\t우선순위: " + priority);
      System.out.println();
    }
  }

  public void displaySuccess() {
    System.out.println("서비스 요청 성공!!");
  }

  public void displayFail() {
    System.out.println("서비스 요청 실패!!");
  }

  public void displayList(
    ArrayList<TodoList> list,
    ArrayList<TodoList> completeList
  ) {
    System.out.println("\n조회된 리스트는 총 " + list.size() + "개 입니다.");
    String str = null;
    FileOutputStream fs;

    try {
      fs = new FileOutputStream("todoList.html");
      fs.write(JDBCTemplate.htmlStart().getBytes());
      for (int i = 0; i < list.size(); i++) {
        str =
          "<tr> <td height=\"25\"> " +
          list.get(i).getExecute() +
          "</td>" +
          "<td>" +
          list.get(i).getTask() +
          "</td>" +
          "<td>" +
          list.get(i).getDueDate() +
          "</td>" +
          "<td>" +
          list.get(i).getRemaining() +
          "</td> </tr>";
        fs.write(str.getBytes());
      }
      fs.write(JDBCTemplate.htmlComplete().getBytes());
      for (int i = 0; i < completeList.size(); i++) {
        str =
          "<tr> <td height=\"25\"> " +
          completeList.get(i).getExecute() +
          "</td>" +
          "<td>" +
          completeList.get(i).getTask() +
          "</td>" +
          "<td>" +
          completeList.get(i).getDueDate() +
          "</td>" +
          "<td>" +
          completeList.get(i).getRemaining() +
          "</td> </tr>";
        fs.write(str.getBytes());
      }
      fs.write(JDBCTemplate.htmlEnd().getBytes());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
