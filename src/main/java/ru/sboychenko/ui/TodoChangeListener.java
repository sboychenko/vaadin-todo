package ru.sboychenko.ui;

import ru.sboychenko.dao.Todo;

/**
 * Created by SBoichenko on 23.03.2017.
 */
public interface TodoChangeListener {

    void todoChanged(Todo todo);
}
