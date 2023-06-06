package com.example.advquerying.service;


import com.example.advquerying.entities.Shampoo;

import java.math.BigDecimal;
import java.util.List;

public interface ShamPooService {

    List<Shampoo> findByPrice(BigDecimal price);

    int findByPriceLower(BigDecimal valueOf);

    List<Shampoo> findShampoosByIngredientCount(int count);

}
