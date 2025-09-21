package com.pizzeria.spring_la_mia_pizzeria_crud.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="pizza")
public class Pizza {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank(message="Name is mandatory")
    @Column(name="name_pizza", nullable=false)
    private String name;

    @NotNull
    @NotBlank(message="Photo is mandatory")
    @Lob
    @Column(columnDefinition = "TEXT", nullable=true)
    private String description;

    @NotNull
    @NotBlank(message="Photo is mandatory")
    private String photo;

    @NotNull(message="Price cannot be null" )
    @Min(value=1)
    @Column(nullable=false)
    private Double price;

    public Pizza() {
    }

    public Pizza(String name, String description, String photo, Double price) {
        this.name = name;
        this.photo = photo;
        this.price = price;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    
}
