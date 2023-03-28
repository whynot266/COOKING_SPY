package com.mycompany.spring_mvc_project_final.entities;



import javax.persistence.*;

@Entity
@Table(name = "food_label")
public class FoodsLabelsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "food_id")
    private FoodEntity food;
    @ManyToOne
    @JoinColumn(name = "label_id")
    private LabelEntity label;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FoodEntity getFood() {
        return food;
    }

    public void setFood(FoodEntity food) {
        this.food = food;
    }

    public LabelEntity getLabel() {
        return label;
    }

    public void setLabel(LabelEntity label) {
        this.label = label;
    }
}
