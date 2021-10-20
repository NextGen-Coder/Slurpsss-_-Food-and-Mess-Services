package co.in.nextgencoder.slurpss_foodandmessservices.model;

import java.util.HashMap;

public class Mess {

    private String id;
    private String name;
    private String email;
    private String address;
    private String addressURL;
    private String description;
    private String image;
    private String safetyMeasures;
    private HashMap<String, Package> packages;
    private HashMap<String, Dish> dishes;

    public Mess() { }

    public Mess(String id, String name, String email, String address, String addressURL, String description, String image, String safetyMeasures, HashMap<String, Package> packages, HashMap<String, Dish> dishes) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.addressURL = addressURL;
        this.description = description;
        this.image = image;
        this.safetyMeasures = safetyMeasures;
        this.packages = packages;
        this.dishes = dishes;
    }

    public Mess(String addressURL, String description, String safetyMeasures) {
        this.addressURL = addressURL;
        this.description = description;
        this.safetyMeasures = safetyMeasures;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressURL() {
        return addressURL;
    }

    public void setAddressURL(String addressURL) {
        this.addressURL = addressURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSafetyMeasures() {
        return safetyMeasures;
    }

    public void setSafetyMeasures(String safetyMeasures) {
        this.safetyMeasures = safetyMeasures;
    }

    public HashMap<String, Package> getPackages() {
        return packages;
    }

    public void setPackages(HashMap<String, Package> packages) {
        this.packages = packages;
    }

    public HashMap<String, Dish> getDishes() {
        return dishes;
    }

    public void setDishes(HashMap<String, Dish> dishes) {
        this.dishes = dishes;
    }
}
