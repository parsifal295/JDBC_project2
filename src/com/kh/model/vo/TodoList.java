package com.kh.model.vo;

import java.sql.Date;

public class TodoList {

  private int sequence;
  private char execute;
  private String task;
  private Date dueDate;
  private int remaining;
  private int priority;

  public TodoList() {}

  public TodoList(
    int sequence,
    char execute,
    String task,
    Date dueDate,
    int remaining,
    int priority
  ) {
    this.sequence = sequence;
    this.execute = execute;
    this.task = task;
    this.dueDate = dueDate;
    this.remaining = remaining;
    this.priority = priority;
  }

  public TodoList(char execute, String task, Date duedate, int priority) {
    this.execute = execute;
    this.task = task;
    this.dueDate = duedate;
    this.priority = priority;
  }

  public int getSequence() {
    return this.sequence;
  }

  public void setSequence(int sequence) {
    this.sequence = sequence;
  }

  public char getExecute() {
    return this.execute;
  }

  public void setExecute(char execute) {
    this.execute = execute;
  }

  public String getTask() {
    return this.task;
  }

  public void setTask(String task) {
    this.task = task;
  }

  public Date getDueDate() {
    return this.dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public int getRemaining() {
    return this.remaining;
  }

  public void setRemaining(int remaining) {
    this.remaining = remaining;
  }

  public int getPriority() {
    return this.priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }

  @Override
  public String toString() {
    return (
      "{" +
      " sequence='" +
      getSequence() +
      "'" +
      ", execute='" +
      getExecute() +
      "'" +
      ", task='" +
      getTask() +
      "'" +
      ", dueDate='" +
      getDueDate() +
      "'" +
      ", remaining='" +
      getRemaining() +
      "'" +
      ", priority='" +
      getPriority() +
      "'" +
      "}"
    );
  }
}
