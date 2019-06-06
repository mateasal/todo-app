package com.example.sqltodo.repository;

import com.example.sqltodo.model.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITodoRepository extends CrudRepository<Todo, Long> {

  List<Todo> findAllByTitleContains(String title);

}
