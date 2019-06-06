package com.example.sqltodo.service;

import com.example.sqltodo.model.Assignee;
import com.example.sqltodo.model.Todo;

import java.util.List;

public interface ITodoService {
  List<Todo> findAll();
  Todo findById(long id);
  List<Todo> findWithQuery(String search);
  List<Todo> findWithValidQuery(String search);
  void save(Todo todo);
  void delete(long id);
  void update(long id, String name, boolean done, boolean urgent, Assignee assignee);
}