package com.example.sqltodo.model;

import javax.persistence.*;

@Entity
public class Todo {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  @Column(nullable = true, name = "Task")
  private String title;
  @Column(nullable = true, name = "Urgency")
  private boolean urgent = false;
  @Column(nullable = true, name = "Status")
  private boolean done = false;

  public Todo() {
  }

  public Todo(String title) {
    this.title = title;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public boolean isUrgent() {
    return urgent;
  }

  public void setUrgent(boolean urgent) {
    this.urgent = urgent;
  }

  public boolean isDone() {
    return done;
  }

  public void setDone(boolean done) {
    this.done = done;
  }
}
