package entities.shampoo;


import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shampoos")
public class BasicShampoo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;


    @OneToOne(optional = false)
    @JoinColumn(name = "label_id", referencedColumnName = "id")
    private BasicLabel label;

    @ManyToOne(optional = false)
    @JoinColumn(name = "batch_id", referencedColumnName = "id")
    private ProductionBatch productionBatch;

    @ManyToMany
    @JoinTable(name = "shampoos_ingredients", joinColumns = @JoinColumn(name = "shampoo_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
    private Set<BasicIngredient> ingredients;

    public BasicShampoo() {
    }

    public BasicShampoo(String name, BasicLabel label, ProductionBatch productionBatch) {
        this.name = name;
        this.label = label;
        this.productionBatch = productionBatch;
        this.ingredients = new HashSet<>();
    }

    public void addIngredients(BasicIngredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public ProductionBatch getProductionBatch() {
        return productionBatch;
    }

    public void setProductionBatch(ProductionBatch productionBatch) {
        this.productionBatch = productionBatch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BasicLabel getLabel() {
        return label;
    }

    public void setLabel(BasicLabel label) {
        this.label = label;
    }

    public Set<BasicIngredient> getIngredients() {
        return Collections.unmodifiableSet(ingredients);
    }

    public void setIngredients(Set<BasicIngredient> ingredients) {
        this.ingredients = ingredients;
    }
}
