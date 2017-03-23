package ru.sboychenko.ui;

import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sboychenko.dao.Todo;

/**
 * Created by SBoichenko on 22.03.2017.
 */
@SpringUI
public class TodoUI extends UI {

    private VerticalLayout root;

    @Autowired
    private TodoListLayout todoLayout;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();
        addHeader();
        addForm();
        addTodoList();
        addDeleteButton();
    }

    private void setupLayout() {
        root = new VerticalLayout();
        root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(root);
    }

    private void addHeader() {
        Label header = new Label("TODO");
        header.addStyleName(ValoTheme.LABEL_H1);
        root.addComponent(header);
    }

    private void addForm() {
        HorizontalLayout formLayout = new HorizontalLayout();
        formLayout.setWidth("80%");

        TextField task = new TextField();
        Button add = new Button("");
        add.addStyleName(ValoTheme.BUTTON_PRIMARY);
        add.setIcon(VaadinIcons.PLUS);

        formLayout.addComponentsAndExpand(task);
        formLayout.addComponents(add);

        add.addClickListener(click -> {
           todoLayout.add(new Todo(task.getValue()));
           task.clear();
           task.focus();
        });

        task.focus();
        add.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        root.addComponent(formLayout);
    }

    private void addTodoList() {
        todoLayout.setWidth("80%");
        root.addComponent(todoLayout);
    }

    private void addDeleteButton() {
        root.addComponent(new Button("Delete completed", click -> {
            if (todoLayout.countCompleted() == 0) {
                Notification.show("No completed task", Notification.Type.HUMANIZED_MESSAGE);
            }
            todoLayout.deleteCompleted();
        }));
    }
}
