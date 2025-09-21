package com.pizzeria.spring_la_mia_pizzeria_crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pizzeria.spring_la_mia_pizzeria_crud.model.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    public List<Pizza> getByNameContainingIgnoreCase(String name);

}
