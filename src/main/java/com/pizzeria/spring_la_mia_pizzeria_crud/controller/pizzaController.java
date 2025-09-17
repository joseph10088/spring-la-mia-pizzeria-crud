package com.pizzeria.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pizzeria.spring_la_mia_pizzeria_crud.model.Pizza;
import com.pizzeria.spring_la_mia_pizzeria_crud.repository.PizzaRepository;


@Controller
@RequestMapping("/")
public class pizzaController {

    @Autowired
    private PizzaRepository repository; 

    @GetMapping("/")
    public String pizza(Model model) {
        List<Pizza> pizza = repository.findAll();
        model.addAttribute("pizza", pizza);
        return "index";
    }
}
