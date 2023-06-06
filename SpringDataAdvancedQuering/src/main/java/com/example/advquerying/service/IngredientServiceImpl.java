package com.example.advquerying.service;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> findByNameStartsWith(char letter) {
        return this.ingredientRepository.findByNameStartsWith(letter);
    }

    @Override
    public List<Ingredient> findIngredientsByCollection(Set<String> ingredients) {
        return this.ingredientRepository.findByIngredientsAndOrderByPriceAsc(ingredients);
    }

    @Override
    public void deleteByName(String apple) {
        this.ingredientRepository.deleteByName(apple);
    }

    @Override
    public void increasePriceByTenPercent(double v) {
        BigDecimal actual = BigDecimal.valueOf(v);
        this.ingredientRepository.increasePriceByPercent(actual);
    }


}
