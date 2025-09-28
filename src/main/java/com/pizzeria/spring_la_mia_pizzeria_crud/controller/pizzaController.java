package com.pizzeria.spring_la_mia_pizzeria_crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pizzeria.spring_la_mia_pizzeria_crud.model.Pizza;
import com.pizzeria.spring_la_mia_pizzeria_crud.repository.PizzaRepository;

import jakarta.validation.Valid;





@Controller
@RequestMapping("/pizzas")
public class pizzaController {

    @Autowired
    private PizzaRepository repository; 

    @GetMapping
    public String pizza(Model model, @RequestParam(name="keyword", required=false) String keyword) {
        List<Pizza> pizza = null;
        if(keyword == null || keyword.isBlank()){
            pizza = repository.findAll();
        } else {
            pizza = repository.getByNameContainingIgnoreCase(keyword);
        }
        model.addAttribute("pizzas", pizza);
        return "/pizzas/index";
    }

    @GetMapping("/show/{id}")
        public String pizzaDetails(@PathVariable("id") Integer id, Model model){
            Optional<Pizza> optPizza = repository.findById(id);
            if(optPizza.isPresent()){
                model.addAttribute("pizza", optPizza.get());
                model.addAttribute("empty", false);
            } else {
                model.addAttribute("empty", true);
            }
            return "/pizzas/show";
        }

        @GetMapping("/create")
        public String createPizza(Model model) {
            model.addAttribute("pizza", new Pizza());
            return "/pizzas/create";
        }

        @PostMapping("/create")
        public String savePizza(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

            if(bindingResult.hasErrors()){
                return "/pizzas/create"; // --> resto sulla pagina
            }
            repository.save(formPizza);
            redirectAttributes.addFlashAttribute("successMessage", "Pizza created success");
            return "redirect:/pizzas";
        }

        @GetMapping("/edit/{id}")
        public String editPizza(@PathVariable("id")Integer id, Model model) {
            Pizza pizza = repository.findById(id).get();
            model.addAttribute("pizza", pizza);
            return "/pizzas/edit";
        }
        

        @PostMapping("/edit/{id}")
        public String editSavePizza(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
            Pizza oldPizza = repository.findById(formPizza.getId()).get();
            if(!formPizza.getName().equals(oldPizza.getName())){
                bindingResult.addError(new ObjectError("name", "not possible to change the name !"));
            }
            if(bindingResult.hasErrors()){
                return "/pizzas/edit";
            }
            repository.save(formPizza);
            return "redirect:/pizzas";
        }
        
        @PostMapping("/delete/{id}")
        public String delete(@PathVariable("id") Integer id) {
            repository.deleteById(id);
            return "redirect:/pizzas";
        }
        
        
}
