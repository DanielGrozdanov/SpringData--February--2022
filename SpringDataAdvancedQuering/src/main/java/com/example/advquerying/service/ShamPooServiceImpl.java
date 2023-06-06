package com.example.advquerying.service;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.repositories.ShampooDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShamPooServiceImpl implements ShamPooService {

    private ShampooDao shampooDao;

    @Autowired
    public ShamPooServiceImpl(ShampooDao shampooDao) {
        this.shampooDao = shampooDao;
    }

    @Override
    public List<Shampoo> findByPrice(BigDecimal price) {
        return this.shampooDao.findByPriceAfterOrderByPriceDesc(price);
    }

    @Override
    public int findByPriceLower(BigDecimal valueOf) {
        return this.shampooDao.findShampooByPriceLessThan(valueOf);
    }

    @Override
    public List<Shampoo> findShampoosByIngredientCount(int count) {
       return this.shampooDao.findShampooByIngredientsLessThan(count);
    }
}
