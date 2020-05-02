package ru.sboychenko.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * Created by SBoichenko on 22.03.2017.
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Transactional
    void deleteByDone(boolean done);

    int countByDone(boolean done);
}
