package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface ShampooDao extends JpaRepository<Shampoo, Long> {

    List<Shampoo> findByBrand(String brand);

    List<Shampoo> findAllByBrandAndSize(String brand, Size size);

    List<Shampoo> findAllBySizeOrderByIdAsc(Size size);

    List<Shampoo> findBySizeOrLabelIdOrderByPriceAsc(Size size, Long label_id);

    @Query("SELECT s FROM Shampoo s" +
            " JOIN s.ingredients AS i" +
            " WHERE i.name IN :ingredients")
    List<Shampoo> findByIngredientsNames(Set<String> ingredients);

    List<Shampoo> findByPriceAfterOrderByPriceDesc(BigDecimal price);

    @Query("SELECT count(s.id) FROM Shampoo s WHERE s.price < :valueOf")
    int findShampooByPriceLessThan(BigDecimal valueOf);


    @Query("SELECT s FROM Shampoo s WHERE s.ingredients.size < :count")
    List<Shampoo> findShampooByIngredientsLessThan(int count);
}
