package com.example.sqltodo.repository;

import com.example.sqltodo.model.Todo;
import org.springframework.data.repository.CrudRepository;

public interface ITodoRepository extends CrudRepository<Todo, Long> {
}
