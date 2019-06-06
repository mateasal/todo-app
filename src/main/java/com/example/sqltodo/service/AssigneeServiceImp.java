package com.example.sqltodo.service;

import com.example.sqltodo.model.Assignee;
import com.example.sqltodo.repository.AssigneeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssigneeServiceImp implements IAssigneeService {

  private AssigneeRepository assigneeRepository;

  public AssigneeServiceImp(AssigneeRepository assigneeRepository) {
    this.assigneeRepository = assigneeRepository;
  }

  @Override
  public List<Assignee> findAll() {
    List<Assignee> assignees = new ArrayList<>();
    assigneeRepository.findAll().forEach(assignees::add);
    return assignees;
  }

  @Override
  public Assignee findById(long assignee_id) {
    return assigneeRepository.findById(assignee_id).orElse(null);
  }

  @Override
  public void save(Assignee assignee) {
    assigneeRepository.save(assignee);
  }

  @Override
  public void delete(long assignee_id) {
    assigneeRepository.deleteById(assignee_id);
  }

  @Override
  public void update(long assignee_id, String name) {
    Assignee assignee = assigneeRepository.findById(assignee_id).orElse(null);
    assignee.setName(name);
    assigneeRepository.save(assignee);
  }
}
