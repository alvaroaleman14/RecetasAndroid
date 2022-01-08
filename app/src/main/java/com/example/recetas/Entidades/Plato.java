package com.example.recetas.Entidades;

import com.example.recetas.Enum.Alergenos;
import com.example.recetas.Enum.TipoComida;

import java.util.List;

public class Plato {

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String description;
    private Float protein;
    private Float calorie;
    private Float carbohydrate;
    private Float fat;
    private List<Alergenos> allergen;
    private Boolean is_restaurant;
    private List<TipoComida> type;
    private String recipe;
    private String URL;
    private Integer id_restaurant;

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

    public Float getProtein() {
        return protein;
    }

    public void setProtein(Float protein) {
        this.protein = protein;
    }

    public Float getCalorie() {
        return calorie;
    }

    public void setCalorie(Float calorie) {
        this.calorie = calorie;
    }

    public Float getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(Float carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public Float getFat() {
        return fat;
    }

    public void setFat(Float fat) {
        this.fat = fat;
    }

    public List<Alergenos> getAllergen() {
        return allergen;
    }

    public void setAllergen(List<Alergenos> allergen) {
        this.allergen = allergen;
    }

    public Boolean getIs_restaurant() {
        return is_restaurant;
    }

    public void setIs_restaurant(Boolean is_restaurant) {
        this.is_restaurant = is_restaurant;
    }

    public List<TipoComida> getType() {
        return type;
    }

    public void setType(List<TipoComida> type) {
        this.type = type;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Integer getId_restaurant() {
        return id_restaurant;
    }

    public void setId_restaurant(Integer id_restaurant) {
        this.id_restaurant = id_restaurant;
    }
}
