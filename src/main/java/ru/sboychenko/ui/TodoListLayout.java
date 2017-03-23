package ru.sboychenko.ui;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sboychenko.dao.Todo;
import ru.sboychenko.dao.TodoRepository;
import ru.sboychenko.ui.TodoItemLayout;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by SBoichenko on 22.03.2017.
 */
@SpringComponent
public class TodoListLayout extends VerticalLayout implements TodoChangeListener {

    @Autowired
    TodoRepository repository;

    @PostConstruct
    void init() {
        update();
    }

    private void update() {
        setTodos(repository.findAll());
    }

    private void setTodos(List<Todo> all) {
        removeAllComponents();

        all.forEach(todo -> addComponent(new TodoItemLayout(todo, this)));
    }

    public void add(Todo todo) {
        repository.save(todo);
        update();
    }

    public int countCompleted() {
        return repository.countByDone(true);
    }

    public void deleteCompleted() {
        repository.deleteByDone(true);
        update();
    }

    @Override
    public void todoChanged(Todo todo) {
        add(todo);
    }
}
