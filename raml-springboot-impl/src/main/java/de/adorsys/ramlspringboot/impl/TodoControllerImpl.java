package de.adorsys.ramlspringboot.impl;

import de.adorsys.ramlspringboot.api.TodoController;
import de.adorsys.ramlspringboot.api.model.Todo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *  @author Florian Hirsch
 */
@Component
public class TodoControllerImpl implements TodoController {

    private List<Todo> todos = new ArrayList<>();

    @Override
    public ResponseEntity getTodos() {
        return ResponseEntity.ok(todos);
    }

    @Override
    public ResponseEntity createTodo(@RequestBody Todo todo) {
        // overwrite if a todo with the same id is present
        Optional<Todo> existingTodo = todos.stream().filter(t -> t.getId().equals(todo.getId())).findFirst();
        if (existingTodo.isPresent()) {
            todos.remove(existingTodo.get());
            todos.add(todo);
            return ResponseEntity.ok().build();
        }
        int id = todos.stream().map(Todo::getId).sorted().findFirst().orElse(0) + 1;
        todos.add(todo.withId(id));
        URI uri = UriComponentsBuilder.fromPath("/api/todos/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        Optional<Todo> todo = todos.stream().filter(t -> t.getId().equals(id.intValue())).findFirst();
        if (!todo.isPresent()) {
            throw new TodoNotFoundException();
        }
        return ResponseEntity.<Todo>ok().body(todo.get());
    }

}
