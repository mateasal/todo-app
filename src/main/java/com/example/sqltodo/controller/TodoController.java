package com.example.sqltodo.controller;

import com.example.sqltodo.model.Todo;
import com.example.sqltodo.repository.ITodoRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoController {

  ITodoRepository todoRepository;

  public TodoController(ITodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  @GetMapping("/")
  public String list(Model model) {
    model.addAttribute("todos", todoRepository.findAll());
    return "todolist";
  }

  @GetMapping("/addtodo")
  public String addTodo() {
    return "addtodo";
  }

  @GetMapping("/delete")
  public String deleteTodo(@RequestParam long id) {
    todoRepository.deleteById(id);
    return "redirect:/";
  }

  @GetMapping("/edit")
  public String editTodo(@RequestParam long id, Model model) {
    model.addAttribute("id", id);
    Todo todo1 = todoRepository.findById(id).orElse(null);
    model.addAttribute("todo", todo1.getTitle());
    model.addAttribute("urgent", todo1.isUrgent());
    model.addAttribute("done", todo1.isDone());
    return "edit";
  }

  @PostMapping("/edit")
  public String updateTodo(@RequestParam long id, String todo, boolean urgency, boolean done) {
    Todo todo1 = todoRepository.findById(id).orElse(null);
    todo1.setTitle(todo);
    todo1.setUrgent(urgency);
    todo1.setDone(done);
    todoRepository.save(todo1);
    return "redirect:/";
  }

  @PostMapping("/newtodo")
  public String collectTodo(String todo, boolean urgency) {
    Todo todo1 = new Todo(todo);
    todo1.setUrgent(urgency);
    todoRepository.save(todo1);
    return "redirect:/";
  }
}