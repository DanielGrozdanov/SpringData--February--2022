package com.example.advquerying.service;

import com.example.advquerying.entities.Ingredient;

import java.util.List;
import java.util.Set;

public interface IngredientService {


    List<Ingredient> findByNameStartsWith(char m);

    List<Ingredient> findIngredientsByCollection(Set<String> ingredients);

    void deleteByName(String apple);

    void increasePriceByTenPercent(double v);
}
