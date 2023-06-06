package com.example.advquerying;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.repositories.ShampooDao;
import com.example.advquerying.service.IngredientService;
import com.example.advquerying.service.LabelService;
import com.example.advquerying.service.ShamPooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class ApplicationRunner implements CommandLineRunner {

    private final ShampooDao shampooDao;
    private final ShamPooService shamPooService;
    private final LabelService labelService;
    private final IngredientService ingredientService;

    @Autowired
    public ApplicationRunner(ShampooDao shampooDao, ShamPooService shamPooService, LabelService labelService, IngredientService ingredientService) {
        this.shampooDao = shampooDao;
        this.shamPooService = shamPooService;
        this.labelService = labelService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        //1.	Select Shampoos by Size
//        List<Shampoo> shampoos = shampooDao.findAllBySizeOrderByIdAsc(Size.MEDIUM);
//        for (Shampoo shampoo : shampoos) {
//            System.out.println(shampoo);
//        }
        //2.	Select Shampoos by Size or Label
//        List<Shampoo> shampoos = shampooDao.findBySizeOrLabelIdOrderByPriceAsc(Size.MEDIUM, 10L);
//        for (Shampoo shampoo : shampoos) {
//            System.out.println(shampoo);
//        }

        //3.	Select Shampoos by Price
//        List<Shampoo> byPrice = this.shamPooService.findByPrice(BigDecimal.valueOf(Long.parseLong(scanner.nextLine())));
//        for (Shampoo shampoo : byPrice) {
//            System.out.println(shampoo);
//        }

        //4.	Select Ingredients by Name
        //       List<Ingredient> ingredients = this.ingredientService.findByNameStartsWith(scanner.nextLine().charAt(0));
//        for (Ingredient ingredient : ingredients) {
//            System.out.println(ingredient);
//        }

        //5.	Select Ingredients by Names
//        String first = scanner.nextLine();
//        String second = scanner.nextLine();
//        String third = scanner.nextLine();
//        Set<String> ingredients = Set.of(first, second, third);
//
//        List<Ingredient> ingredientsByCollection = this.ingredientService.findIngredientsByCollection(ingredients);
//        for (Ingredient ingredient : ingredientsByCollection) {
//            System.out.println(ingredient);
//        }

        //6.	Count Shampoos by Price
//        double value = Double.parseDouble(scanner.nextLine());
//        System.out.println(this.shamPooService.findByPriceLower(BigDecimal.valueOf(value)));

        ///JPQL Querying
        //7.	Select Shampoos by Ingredients

//        String first = scanner.nextLine();
//        String second = scanner.nextLine();
//        Set<String> names = Set.of(first, second);
//
//        List<Shampoo> shampoos = this.shampooDao.findByIngredientsNames(names);
//        for (Shampoo shampoo : shampoos) {
//            System.out.println(shampoo);
//        }
        //

        //8.	Select Shampoos by Ingredients Count
//        int count = Integer.parseInt(scanner.nextLine());
//        List<Shampoo> shampoos = this.shamPooService.findShampoosByIngredientCount(count);
//        for (Shampoo shampoo : shampoos) {
//            System.out.println(shampoo);
//        }

        //9.	Delete Ingredients by Name
//        this.ingredientService.deleteByName("Apple");

        //10.	Update Ingredients by Price
        this.ingredientService.increasePriceByTenPercent(0.10);
    }
}
