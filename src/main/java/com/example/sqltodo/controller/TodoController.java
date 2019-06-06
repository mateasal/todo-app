package com.example.sqltodo.controller;

import com.example.sqltodo.model.Assignee;
import com.example.sqltodo.model.Todo;
import com.example.sqltodo.repository.ITodoRepository;
import com.example.sqltodo.service.IAssigneeService;
import com.example.sqltodo.service.ITodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TodoController {

  private ITodoService todoService;
  private IAssigneeService assigneeService;

  public TodoController(ITodoService todoService, IAssigneeService assigneeService) {
    this.todoService = todoService;
    this.assigneeService = assigneeService;
  }

  @GetMapping("/")
  public String list(Model model, @RequestParam (required = false) String search) {
    model.addAttribute("todos", todoService.findWithQuery(search));
    return "todolist";
  }

  @GetMapping("/addtodo")
  public String addTodo(Model model) {
    model.addAttribute("assignees", assigneeService.findAll());
    return "addtodo";
  }

  @GetMapping("/delete")
  public String deleteTodo(@RequestParam long id) {
    todoService.delete(id);
    return "redirect:/";
  }

  @GetMapping("/edit")
  public String editTodo(@RequestParam long id, Model model) {
    model.addAttribute("id", id);
    Todo todo1 = todoService.findById(id);
    model.addAttribute("todo", todo1.getTitle());
    model.addAttribute("urgent", todo1.isUrgent());
    model.addAttribute("done", todo1.isDone());
    model.addAttribute("assignees", assigneeService.findAll());
    return "edit";
  }

  @PostMapping("/edit")
  public String updateTodo(@RequestParam long id, String todo, boolean urgency, boolean done, Assignee assignee) {
    todoService.update(id, todo, done, urgency, assignee);
    return "redirect:/";
  }

  @PostMapping("/newtodo")
  public String collectTodo(String todo, boolean urgency, long assignee_id) {
    Todo todo1 = new Todo(todo);
    todo1.setUrgent(urgency);
    todo1.setAssignee(assigneeService.findById(assignee_id));
    todoService.save(todo1);
    return "redirect:/";
  }

  @PostMapping("/search")
  public String searchTodo(String search) {
    return "redirect:/?search=" + search;
  }
}