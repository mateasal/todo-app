package com.example.sqltodo.service;

import com.example.sqltodo.model.Assignee;

import java.util.List;

public interface IAssigneeService {
  List<Assignee> findAll();
  Assignee findById(long assignee_id);
  void save(Assignee assignee);
  void delete(long assignee_id);
  void update(long assignee_id, String name);
}
