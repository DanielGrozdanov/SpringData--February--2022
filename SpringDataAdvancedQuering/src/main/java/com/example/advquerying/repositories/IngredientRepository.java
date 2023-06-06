package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findByNameStartsWith(char letter);

    @Query("SELECT i FROM Ingredient i WHERE i.name IN :ingredients")
    List<Ingredient> findByIngredientsAndOrderByPriceAsc(Set<String> ingredients);

    @Modifying
    @Transactional
    @Query("DELETE FROM Ingredient i WHERE i.name = :apple")
    void deleteByName(String apple);

    @Modifying
    @Transactional
    @Query("UPDATE Ingredient i SET i.price = i.price * :multiplier + i.price")
    void increasePriceByPercent(@Param("multiplier") BigDecimal percent);
}
