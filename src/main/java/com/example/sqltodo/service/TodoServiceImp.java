package com.example.sqltodo.service;

import com.example.sqltodo.model.Assignee;
import com.example.sqltodo.model.Todo;
import com.example.sqltodo.repository.ITodoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoServiceImp implements ITodoService {

  private ITodoRepository todoRepository;

  TodoServiceImp(ITodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  @Override
  public List<Todo> findAll() {
    List<Todo> todos = new ArrayList<>();
    todoRepository.findAll().forEach(todos::add);
    return todos;
  }

  @Override
  public Todo findById(long id) {
    return todoRepository.findById(id).orElse(null);
  }

  @Override
  public List<Todo> findWithQuery(String search) {
    if (search == null || search.isEmpty()) {
      return findAll();
    }
    return findWithValidQuery(search);
  }

  @Override
  public List<Todo> findWithValidQuery(String search) {
    return todoRepository.findAllByTitleContains(search);
  }

  @Override
  public void save(Todo todo) {
    todoRepository.save(todo);
  }

  @Override
  public void delete(long id) {
    todoRepository.deleteById(id);
  }

  @Override
  public void update(long id, String title, boolean done, boolean urgent, Assignee assignee) {
    Todo todo = todoRepository.findById(id).orElse(null);
    todo.setTitle(title);
    todo.setDone(done);
    todo.setUrgent(urgent);
    todo.setAssignee(assignee);
    todoRepository.save(todo);
  }
}