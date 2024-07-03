package model;

import java.util.ArrayList;

public class User {
    private String id;
    private String userName;
    private String password;
    private String email;
    private String role;
    private ArrayList<Restaurant> restaurants=new ArrayList<>();



    public User(String email, String id, String password, String role, String userName) {
        this.email = email;
        this.id = id;
        this.password = password;
        this.role = role;
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
