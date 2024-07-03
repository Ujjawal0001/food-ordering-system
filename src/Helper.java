import model.FoodItem;

import java.util.ArrayList;

public class Helper {
    private static Helper instance;
    private String currentUserId;

    private Helper() {}

    public static synchronized Helper getInstance() {
        if (instance == null) {
            instance = new Helper();
        }
        return instance;
    }



    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(String userId) {
        this.currentUserId = userId;
    }

    public void clearCurrentUserId() {
        this.currentUserId = null;
    }

}
