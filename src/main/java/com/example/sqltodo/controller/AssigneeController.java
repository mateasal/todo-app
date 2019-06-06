package com.example.sqltodo.controller;

import com.example.sqltodo.model.Assignee;
import com.example.sqltodo.service.IAssigneeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AssigneeController {

  private IAssigneeService assigneeService;

  public AssigneeController(IAssigneeService assigneeService) {
    this.assigneeService = assigneeService;
  }

  @GetMapping("/assignees")
  public String renderAssigneesPage(Model model) {
    model.addAttribute("listOfAssignees", assigneeService.findAll());
    return "users";
  }

  @GetMapping("/deleteassignee")
  public String deleteAssignee(@RequestParam long assignee_id) {
    assigneeService.delete(assignee_id);
    return "redirect:/assignees";
  }

  @GetMapping("/addassignee")
  public String renderAddAssignee() {
    return "addassignee";
  }

  @GetMapping("/editassignee")
  public String renderEditAssignee(@RequestParam long assignee_id, Model model) {
    model.addAttribute("assignee_id", assignee_id);
    Assignee assignee = assigneeService.findById(assignee_id);
    model.addAttribute("name", assignee.getName());
    return "editassignee";
  }

  @PostMapping("/editassignee")
  public String updateAssignee(@RequestParam long assignee_id, String name) {
    assigneeService.update(assignee_id, name);
    return "redirect:/assignees";
  }

  @PostMapping("/newassignee")
  public String collectAssignee(String name, String email) {
    Assignee assignee = new Assignee(name, email);
    assigneeService.save(assignee);
    return "redirect:/assignees";
  }

  @GetMapping("/assigneeinfo")
  public String renderAssigneeInfo(@RequestParam long assignee_id, Model model) {
    model.addAttribute("todos", assigneeService.findById(assignee_id).getTodos());
    return "assigneeinfo";
  }
}
