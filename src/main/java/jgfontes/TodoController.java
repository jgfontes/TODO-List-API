package jgfontes;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class TodoController {

    private final TodoRepository repository;

    public TodoController (final TodoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    public Flux<Todo> findAll() {
        return repository.findAll();
    }

    @GetMapping("/all/{done}")
    public Flux<Todo> findByDone(@PathVariable boolean done) {
        return repository.findByDone(done);
    }

    @PostMapping("/all")
    public Mono<Todo> create(@RequestBody Todo todo) {
        return repository.save(todo);
    }

    @PostMapping("/all/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return repository.deleteById(id);
    }

    @PutMapping("all/{id}")
    public Mono<Todo> update(@PathVariable String id) {
        return repository
                .findById(id)
                .map(todoAtual ->
                    new Todo(id, todoAtual.title(), todoAtual.description(), !todoAtual.done())
                )
                .flatMap(repository::save)
                .onTerminateDetach();

    }




}
