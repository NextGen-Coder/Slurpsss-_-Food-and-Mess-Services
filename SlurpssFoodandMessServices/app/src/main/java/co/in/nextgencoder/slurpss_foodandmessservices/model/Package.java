package co.in.nextgencoder.slurpss_foodandmessservices.model;

import java.util.HashMap;

public class Package {

    private String id;
    private String image;
    private String name;
    private String category;
    private Double price;
    private String description;
    private HashMap<String, Dish> dishes;

    public Package() { }

    public Package(String id, String image, String name, String category, Double price, String description, HashMap<String, Dish> dishes) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.dishes = dishes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashMap<String, Dish> getDishes() {
        return dishes;
    }

    public void setDishes(HashMap<String, Dish> dishes) {
        this.dishes = dishes;
    }
}
