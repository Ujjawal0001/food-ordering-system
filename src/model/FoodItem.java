package model;

public class FoodItem {
    private String id;
    private String restaurantId;
    private String name;
    private String description;
    private double price;
    private boolean availability;

    public FoodItem(String restaurantId,  String name,double price, String id, String description) {
        this.restaurantId = restaurantId;
        this.price = price;
        this.name = name;
        this.id = id;
        this.description = description;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }
}
