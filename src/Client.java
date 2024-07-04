import controller.FoodItemController;
import controller.OrderController;
import controller.RestaurantController;
import controller.UserController;
import model.FoodItem;
import model.Order;
import model.Restaurant;
import model.User;
import message.Message;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static javax.swing.UIManager.get;

public class Client {
    private static int option = 0;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();
    private static String id ;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserController userController = UserController.getInstance();
        RestaurantController restaurantController = RestaurantController.getInstance();
        FoodItemController foodItemController = FoodItemController.getInstance();
        OrderController orderController = OrderController.getInstance();

        while (true) {
            switch (option) {
                case 0:
                    mainMenu(sc);
                    break;
                case 1:
                    registerUser(sc);
                    option = 0; // Return to main menu
                    break;
                case 2:
                    loginMenu(sc);
                    break;
                case 3:
                    ownerMenu(sc);
                    break;
                case 4:
                    createRestaurantMenu(sc);
                    option = 3; // Return to owner menu
                    break;
                case 5:
                    updateRestaurantMenu(sc);
                    option = 3; // Return to owner menu
                    break;
                case 6:
                    deleteRestaurantMenu(sc);
                    option = 3; // Return to owner menu
                    break;
                case 7:
                    addFoodItemMenu(sc);
                    option = 3; // Return to owner menu
                    break;
                case 8:
                    updateFoodItemMenu(sc);
                    option = 3; // Return to owner menu
                    break;
                case 9:
                    customerMenu(sc);
                    break;
                case 10:
                    viewFoodItemList(sc);
                    option = 3;
                    break;
                case 11:
                    deleteFoodItem(sc);
                    option = 3;
                    break;
                case 12:
                    viewRestaurantList(sc);
                    option = 3;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    option = 0; // Return to main menu
                    break;
            }
        }
    }
    public static String generateUUID() {
        StringBuilder uuid = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
            uuid.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return uuid.toString();
    }
    private static String generateOrderId() {
        return "ORDER" + System.currentTimeMillis();
    }
    public static String generateRestrauntiD() {
        StringBuilder rid = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
            rid.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return rid.toString();
    }
    private static String getFoodItemsString(ArrayList<FoodItem> foodItems) {
        StringBuilder items = new StringBuilder();
        for (FoodItem item : foodItems) {
            items.append(item.getName()).append(" (").append(item.getPrice()).append("), ");
        }
        // Remove the last comma and space
        if (items.length() > 0) {
            items.setLength(items.length() - 2);
        }
        return items.toString();
    }
    private static long calculateTotalPrice(ArrayList<FoodItem> foodItems) {
        long totalPrice = 0;
        for (FoodItem foodItem : foodItems) {
            totalPrice += foodItem.getPrice();
        }
        return totalPrice;
    }
    private static void mainMenu(Scanner sc) {
        System.out.println("----- FOOD ORDERING SYSTEM -----");
        System.out.println("Choose your option : ");
        System.out.print("1. Register user \n2. Login \n3. Exit\n");
        System.out.println();

        try {
            option = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Please select a valid option");
            sc.next();
            return;
        }

        switch (option) {
            case 1:
            case 2:
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                option = 0; // Return to main menu
                break;
        }
    }
    private static void registerUser(Scanner sc) {
        UserController userController = UserController.getInstance();
        Message msg = new Message();
        System.out.println(">>>>>  SIGN UP  <<<<<");
        // Username validation
        String userName = "";
        while (true) {
            System.out.print("Enter username: ");
            userName = sc.nextLine().trim().toLowerCase();
            if (userName.isEmpty() || !userName.matches("[a-zA-Z0-9 ]{4,}")) {
                System.out.println("Invalid username. Please try again.");
            } else {
                break;
            }
        }

        // Password validation
        String password = "";
        while (true) {
            System.out.print("Set password: ");
            password = sc.nextLine().trim();
            if (password.isEmpty()) {
                System.out.println("Password cannot be empty. Please try again.");
            } else {
                break;
            }
        }
        id=generateUUID();
        // Email validation
        String email = "";
        while (true) {
            System.out.print("Enter email: ");
            email = sc.nextLine().trim();
            if (email.isEmpty() || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                System.out.println("Invalid email format. Please try again.");
            } else {
                break;
            }
        }
        // Role validation
        String role = "";
        while (true) {
            System.out.print("Press 'y' to sign up as a restaurant owner or 'n' to sign up as a customer: ");
            role = sc.nextLine().trim().toLowerCase();
            if (role.isEmpty()) {
                System.out.println("Role cannot be empty. Please try again.");
            } else if (!role.equals("y") && !role.equals("n")) {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            } else if (role.equals("y")) {
                role = "1";
                break;
            } else if (role.equals("n")) {
                role = "2";
                break;
            }
        }

        // Register user
        User success = userController.register(userName, password, id, email, role, msg);
        if (success != null) {
            System.out.println("You are registered as a user!");
        } else {
            System.out.println("Registration failed. Please try again.");
        }
        System.out.println("Press enter to continue");
        sc.nextLine();
    }
    private static void loginMenu(Scanner sc) {
        UserController userController = UserController.getInstance();
        Message msg = new Message();
        System.out.println(">>>>>  LOGIN  <<<<<");
        System.out.println("Login options");
        System.out.println("1. Login using username and password \n2. Login using email \n3. Return to main page");
        int opt = 0;
        System.out.println("Choose option :");

        try {
            opt = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Please select a valid option");
            sc.next();
            return;
        }

        switch (opt) {
            case 1:
                // Username validation
                String userName = "";
                while (true) {
                    System.out.print("Enter username: ");
                    userName = sc.nextLine().trim().toLowerCase();
                    if (userName.isEmpty() || !userName.matches("[a-zA-Z0-9 ]{4,}")) {
                        System.out.println("Invalid username. Please try again.");
                    }else {
                        break;
                    }
                }

                // Password validation
                String password = "";
                while (true) {
                    System.out.print("Enter password: ");
                    password = sc.nextLine().trim();
                    if (password.isEmpty()) {
                        System.out.println("Password cannot be empty. Please try again.");
                    } else {
                        break;
                    }
                }

                // Perform login
                User user = userController.loginByUserName(userName, password, msg);

                if (user != null) {
                    System.out.println("Login successful");
                    System.out.println("Your user id is :"+user.getId());
                    Helper.getInstance().setCurrentUserId(user.getId());
                    System.out.println("press enter to continue");
                    sc.nextLine();
                    // Determine role
                    String role = user.getRole();
                    switch (role) {
                        case "1":
                            option = 3; // Owner menu
                            break;
                        case "2":
                            option = 9; // Customer menu
                            break;
                        default:
                            System.out.println("Invalid role. Returning to main menu.");
                            option = 0; // Return to main menu
                            break;
                    }
                } else {
                    System.out.println("Login failed. Please try again.");
                    option = 0; // Return to main menu
                }
                break;

            case 2:
                // Similar login flow for email can be added here.
                // Username validation
                String email = "";
                while (true) {
                    System.out.print("Enter email: ");
                    email = sc.nextLine().trim().toLowerCase();
                    if (email.isEmpty() || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                        System.out.println("Invalid email. Please try again.");
                    } else {
                        break;
                    }
                }

                // Password validation
                String pass = "";
                while (true) {
                    System.out.print("Enter password: ");
                    pass = sc.nextLine().trim();
                    if (pass.isEmpty()) {
                        System.out.println("Password cannot be empty. Please try again.");
                    } else {
                        break;
                    }
                }

                // Perform login
                User user1 = userController.loginByEmail(email, pass, msg);

                if (user1!=null) {
                    System.out.println("Login successful");
                    System.out.println("Your user id is :"+user1.getId());
                    Helper.getInstance().setCurrentUserId(user1.getId());
                    System.out.println("press enter to continue");
                    sc.nextLine();
                    // Determine role
                    String role = user1.getRole();
                    switch (role) {
                        case "1":
                            option = 3; // Owner menu
                            break;
                        case "2":
                            option = 9; // Customer menu
                            break;
                        default:
                            System.out.println("Invalid role. Returning to main menu.");
                            option = 0; // Return to main menu
                            break;
                    }
                } else {
                    System.out.println("Login failed. Please try again.");
                    option = 0; // Return to main menu
                }
                break;

            case 3:
                option = 0; // Return to main menu
                break;

            default:
                System.out.println("Invalid option. Please try again.");
                option = 0; // Return to main menu
                break;
        }
    }
    private static void ownerMenu(Scanner sc) {
        System.out.println("Choose options :");
        System.out.print("1. View profile \n2. Create Restaurant\n3. View Restaurant List\n4. Update Restaurant\n5. Delete Restaurant\n6. Add food item to a restaurant\n7. Update food item of a restaurant\n8. View Food Item List\n9. Delete food item of a restaurant\n10. View Order List\n11. View order status\n12. Update order status\n13. LogOut");
        System.out.println();
        int options = 0;

        try {
            options = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Please select a valid option");
            sc.next();
            return;
        }

        switch (options) {
            case 1:
                viewProfile(sc);
                break;
            case 2:
                option = 4; // Create restaurant menu
                break;
            case 3:
                option=12; //View Restaurant List
                break;
            case 4:
                option = 5; // Update restaurant menu
                break;
            case 5:
                option = 6; // Delete restaurant menu
                break;
            case 6:
                option = 7; // Add food item menu
                break;
            case 7:
                option = 8; // Update food item menu
                break;
            case 8:
                // View food item logic
                option = 10;
                break;
            case 9:
                //Delete food item logic
                option = 11;
                break;
            case 10:
                viewOrders(sc);
                option=3;
                break;
            case 11:
                viewOrderStatus(sc);// View order status
                option=3;
                break;// Update order status logic

            case 12:
                updateOrderStatus(sc);
                option = 3; // return to owner menu
                break;
            case 13:
                option = 0; // Log out
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                option = 3; // Return to owner menu
                break;
        }
    }
    private static void viewProfile(Scanner sc){
        UserController userController = UserController.getInstance();
        System.out.println(">>>>>  VIEW PROFILE  <<<<<");
        String ownerId = "";
        User user = null;
        System.out.println("Enter 0 to return to main menu or enter anything to continue: ");
        String exitInput = sc.nextLine();
        if (exitInput.equals("0")) {
            return;
        }
        while (true) {
            System.out.print("Enter owner ID: ");
            ownerId = sc.nextLine().trim();
            user = userController.userProfile(ownerId);

            if (user != null && ownerId.equals(user.getId())) {
                System.out.println("User name: " + user.getUserName() + "\nPassword: " + user.getPassword() + "\nEmail: " + user.getEmail());

                ArrayList<Restaurant> restaurants = user.getRestaurants();
                if (!restaurants.isEmpty()) {
                    System.out.print("Your restaurants: ");
                    for (Restaurant restaurant : restaurants) {
                        System.out.print(" '" + restaurant.getName() + "' ");
                    }
                    System.out.println();
                } else {
                    System.out.println("You don't have any restaurants.");
                }

                System.out.println("Press enter to continue");
                sc.nextLine();
                break;
            } else {
                System.out.println("Invalid owner ID. Please try again.");
            }
        }
    }
    private static void createRestaurantMenu(Scanner sc) {
    RestaurantController restaurantController = RestaurantController.getInstance();
    UserController userController=UserController.getInstance();
    Message msg= new Message();
        System.out.println("Enter 0 to return to main menu or enter anything to continue: ");
        String exitInput = sc.nextLine();
        if (exitInput.equals("0")) {
            return;
        }
        String restaurantName = "";


        String id1 = generateRestrauntiD();

        String ownerIdInput = "";
        User ownerUser = null;
        while (true) {

            System.out.print("Enter owner ID: ");
            ownerIdInput = sc.nextLine().trim();
            if (ownerIdInput.isEmpty()) {
                System.out.println("Owner ID cannot be empty. Please try again.");
            } else {
                ownerUser = userController.userProfile(ownerIdInput);
                if (ownerUser != null && ownerIdInput.equals(ownerUser.getId())) {
                    while (true) {
                        System.out.print("Enter restaurant name: ");
                        restaurantName = sc.nextLine().trim();

                        if (restaurantName.isEmpty() || !restaurantName.matches("[a-zA-Z0-{4,} ]+")) {
                            System.out.println("Invalid restaurant name. Please try again.");
                        }
                        else {
                            break;
                        }
                    }
                    break;
                } else {
                    System.out.println("Invalid owner ID. Please try again.");
                }
            }
        }

        String address = "";
        while (true) {
            System.out.print("Enter address: ");
            address = sc.nextLine().trim();
            if (address.isEmpty() || !address.matches("[a-zA-Z ]+")) {
                System.out.println("Invalid address. Please try again.");

            } else {
                break;
            }
        }

        String phoneNumber = "";
        while (true) {

            System.out.print("Enter phone number: ");
            phoneNumber = sc.nextLine().trim();
            if (phoneNumber.isEmpty() || !phoneNumber.matches("\\d{10}")) {
                System.out.println("Invalid phone number. Please enter a 10-digit number.");
            }else {
                break;
            }
        }

        restaurantController.createRestaurant(restaurantName, id1, ownerIdInput, address, phoneNumber, msg);
        System.out.println("Restaurant created successfully");
        System.out.println("Press enter to continue ");
        sc.nextLine();
    }
    private static void updateRestaurantMenu(Scanner sc) {
        RestaurantController restaurantController = RestaurantController.getInstance();
        Message msg = new Message();
        System.out.println("Enter 0 to return to main menu or enter anything to continue: ");
        String exitInput = sc.nextLine();
        if (exitInput.equals("0")) {
            return;
        }
        while (true) {
            System.out.println("Choose options:");
            System.out.println("1. Update name of restaurant\n2. Update phone number of restaurant\n3. Update address of restaurant\n4. Update everything in the restaurant\n5. Exit");

            int newOption = 0;
            try {
                System.out.print("Enter option: ");
                newOption = sc.nextInt();
                sc.nextLine(); // Clear the buffer
            } catch (InputMismatchException e) {
                System.out.println("Please select a valid option.");
                sc.next(); // Clear the invalid input
                continue;
            }

            if (newOption == 1) {
                System.out.print("Enter restaurant id: ");
                String id1 = sc.nextLine().trim();
                Restaurant restaurant = restaurantController.isRestaurant(id1);

                if (restaurant != null) {
                    String newName = "";
                    while (true) {
                        System.out.print("Enter new name of restaurant: ");
                        newName = sc.nextLine().trim();
                        if (newName.isEmpty() || !newName.matches("[a-zA-Z ]+")) {
                            System.out.println("Invalid name. Please try again.");
                        } else {
                            break;
                        }
                    }
                    restaurantController.updateRestaurantName(id1, newName, msg);
                    System.out.println("Restaurant name updated successfully.");
                } else {
                    System.out.println("Invalid id. Enter 1 to view restaurant list by owner's Id");
                    int restro = 0;
                    try {
                        restro = sc.nextInt();
                        sc.nextLine(); // Clear the buffer
                    } catch (InputMismatchException e) {
                        System.out.println("Enter 1 only");
                        sc.next(); // Clear the invalid input
                        continue;
                    }

                    if (restro == 1) {
                        // Fetch restaurants by owner ID
                        ArrayList<Restaurant> restaurants = restaurantController.getRestaurantsByOwnerId(id, msg);
                        if (restaurants == null) {
                            restaurants = new ArrayList<>();
                        }
                        // Display fetched restaurants
                        if (!restaurants.isEmpty()) {
                            System.out.println("Restaurants owned by " + id + ":");
                            for (Restaurant r : restaurants) {
                                System.out.println("ID: " + r.getId() + ", Name: " + r.getName() + ", Address: " + r.getAddress());
                            }
                        } else {
                            System.out.println("No restaurants found for owner ID: " + id);
                        }
                    } else {
                        System.out.println("Invalid input");
                    }
                }
            } else if (newOption == 5) {
                break;
            }
        }
    }
    private static void deleteRestaurantMenu(Scanner sc) {
        RestaurantController restaurantController = RestaurantController.getInstance();
        Message msg= new Message();
        System.out.println("Enter 0 to return to main menu or enter anything to continue: ");
        String exitInput = sc.nextLine();
        if (exitInput.equals("0")) {
            return;
        }
        while (true) {
            System.out.print("Enter restaurant id: ");
            String id1 = sc.nextLine().trim();
            Restaurant restaurant = restaurantController.isRestaurant(id1);

            if (restaurant != null) {
                boolean a = restaurantController.deleteRestaurant(id1, msg);
                System.out.println(a);
                System.out.println("Restaurant deleted successfully");
                break;
            }
            else {
                System.out.println("Invalid id. Enter 1 to view restaurant list by owner's Id");
                int restro = 0;
                try {
                    restro = sc.nextInt();
                    sc.nextLine(); // Clear the buffer
                } catch (InputMismatchException e) {
                    System.out.println("Enter 1 only");
                    sc.next(); // Clear the invalid input
                    continue;
                }

                if (restro == 1) {
                    // Prompt for owner ID

                    // Fetch restaurants by owner ID
                    ArrayList<Restaurant> restaurants = restaurantController.getRestaurantsByOwnerId(id, msg);
                    if (restaurants == null) {
                        restaurants = new ArrayList<>();
                    }
                    // Display fetched restaurants
                    if (!restaurants.isEmpty()) {
                        System.out.println("Restaurants owned by " + id + ":");
                        for (Restaurant r : restaurants) {
                            System.out.println("ID: " + r.getId() + ", Name: " + r.getName() + ", Address: " + r.getAddress());
                        }
                    } else {
                        System.out.println("No restaurants found for owner ID: " + id);
                        break;
                    }
                } else {
                    System.out.println("Invalid input");

                }
            }
        }
    }
    private static void addFoodItemMenu(Scanner sc) {
        System.out.println("Enter 0 to return to main menu or enter anything to continue: ");
        String exitInput = sc.nextLine();
        if (exitInput.equals("0")) {
            return;
        }
        RestaurantController restaurantController = RestaurantController.getInstance();
        FoodItemController foodItemController = FoodItemController.getInstance();
        Message msg = new Message();
        String foodItemId = generateUUID();
        while (true) {
            String foodItemName = "";
            String restaurantId = "";
            while (true) {
                System.out.print("Enter restaurant id: ");
                restaurantId = sc.nextLine().trim();
                if (restaurantId.isEmpty()) {
                    System.out.println("Restaurant id cannot be empty. Please try again.");
                } else {
                    // Verify restaurant existence
                    Restaurant restaurant = restaurantController.isRestaurant(restaurantId);
                    if (restaurant != null) {
                        while (true) {
                            System.out.print("Enter food item name: ");
                            foodItemName = sc.nextLine().trim();
                            if (foodItemName.isEmpty() || !foodItemName.matches("[a-zA-Z ]+")) {
                                System.out.println("Invalid food item name. Please try again.");
                            } else {
                                break;
                            }
                        }
                        break;
                    } else {
                        System.out.println("Invalid restaurant id. Would you like to:");
                        System.out.println("1. View restaurant list");
                        System.out.println("2. Try entering restaurant id again");
                        int choice = 0;
                        try {
                            choice = sc.nextInt();
                            sc.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a number.");
                            sc.nextLine();
                            continue;
                        }
                        if (choice == 1) {

                            ArrayList<Restaurant> restaurants = restaurantController.getRestaurantsByOwnerId(id,msg);
                            for (Restaurant rest : restaurants) {
                                System.out.println("Id : " +rest.getId() + " Name : " + rest.getName());
                            }
                        } else if (choice == 2) {
                            continue; // Retry entering restaurant id
                        } else {
                            System.out.println("Invalid choice. Please select a valid option.");
                        }
                    }
                }
            }

            String description = "";
            while (true) {
                System.out.print("Enter food item description: ");
                description = sc.nextLine().trim();
                if (description.isEmpty()) {
                    System.out.println("Description cannot be empty. Please try again.");
                } else {
                    break;
                }
            }

            double price = 0.0;
            while (true) {
                System.out.print("Enter food item price: ");
                try {
                    price = sc.nextDouble();
                    sc.nextLine(); // Consume newline after double input
                    if (price <= 0) {
                        System.out.println("Price must be greater than zero. Please try again.");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid price.");
                    sc.nextLine(); // Clear the invalid input
                }
            }

            // Add the food item to the restaurant's menu
            boolean success = foodItemController.addFoodItem(restaurantId,foodItemName,price,foodItemId,description,msg);
            if (success) {
                System.out.println("Food item added successfully.");
                System.out.println("press enter to continue");
                sc.nextLine();
                break;
            } else {
                System.out.println("Failed to add food item. " + msg.getMessage());
                break;
            }

        }
    }
    private static void updateFoodItemMenu(Scanner sc) {
        RestaurantController restaurantController = RestaurantController.getInstance();
        FoodItemController foodItemController = FoodItemController.getInstance();
        Message msg = new Message();
        System.out.println("Enter 0 to return to main menu or enter anything to continue: ");
        String exitInput = sc.nextLine();
        if (exitInput.equals("0")) {
            return;
        }
        // Step 1: Show user's restaurants
        ArrayList<Restaurant> userRestaurants = restaurantController.getRestaurantsByOwnerId(id,msg);
        if (userRestaurants.isEmpty()) {
            System.out.println("You don't have any restaurants.");
            return;
        }

        System.out.println("Your restaurants:");
        System.out.printf("%-20s %-40s%n", "Restaurant ID", "Restaurant Name");
        System.out.println("-------------------------------------------------");
        for (Restaurant restaurant : userRestaurants) {
            System.out.printf("%-20s %-40s%n", restaurant.getId(), restaurant.getName());
        }
        while (true) {
            System.out.print("Enter restaurant id: ");
            String restaurantId = sc.nextLine().trim();
            Restaurant restaurant = restaurantController.isRestaurant(restaurantId);

            if (restaurant != null) {
                ArrayList<FoodItem> foodItems = restaurant.getFoodItems();
                if (foodItems.isEmpty()) {
                    System.out.println("No food items available in this restaurant.");
                    return;
                }


                System.out.println("Food items available in the restaurant:");
                System.out.printf("%-20s %-30s %-20s %-30s%n", "Food Item ID", "Food Item Name","Food Item Price","Food Item Description");
                System.out.println("-------------------------------------------------");
                for (FoodItem item : foodItems) {
                    System.out.printf("%-20s %-30s %-20s %-30s%n", item.getId(), item.getName(),item.getPrice(),item.getDescription());
                }

                System.out.print("Enter food item id: ");
                String foodItemId = sc.nextLine().trim();
                FoodItem foodItem = foodItemController.getFoodItemById(restaurantId,foodItemId,msg);

                if (foodItem != null) {
                    System.out.println("Choose options:");
                    System.out.println("1. Update name of food item");
                    System.out.println("2. Update price of food item");
                    System.out.println("3. Update description of food item");
                    System.out.println("4. Update everything in the food item");
                    System.out.println("5. Exit");

                    int newOption = 0;
                    try {
                        System.out.print("Enter option: ");
                        newOption = sc.nextInt();
                        sc.nextLine(); // Clear the buffer
                    } catch (InputMismatchException e) {
                        System.out.println("Please select a valid option.");
                        sc.next(); // Clear the invalid input
                        continue;
                    }

                    if (newOption == 1) {
                        System.out.print("Enter new name of food item: ");
                        String newName = sc.nextLine().trim();
                        if (newName.isEmpty() || !newName.matches("[a-zA-Z ]+")) {
                            System.out.println("Invalid name. Please try again.");
                        } else {
                            foodItemController.updateFoodItemByName(foodItemId, newName, msg);
                            System.out.println("Food item name updated successfully.");
                            System.out.println("press enter to continue");
                            sc.nextLine();
                            break;
                        }
                    } else if (newOption == 2) {
                        double newPrice = 0.0;
                        while (true) {
                            System.out.print("Enter new price of food item: ");
                            try {
                                newPrice = sc.nextDouble();
                                sc.nextLine(); // Clear the buffer
                                if (newPrice <= 0) {
                                    System.out.println("Price must be greater than zero. Please try again.");
                                } else {
                                    break;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a valid price.");
                                sc.next(); // Clear the invalid input
                            }
                        }
                        foodItemController.updateFoodItemByPrice(foodItemId, newPrice, msg);
                        System.out.println("Food item price updated successfully.");
                        System.out.println("press enter to continue");
                        sc.nextLine();
                        break;
                    } else if (newOption == 3) {
                        System.out.print("Enter new description of food item: ");
                        String newDescription = sc.nextLine().trim();
                        if (newDescription.isEmpty()) {
                            System.out.println("Description cannot be empty. Please try again.");
                        } else {
                            foodItemController.updateFoodItemDescription(foodItemId, newDescription, msg);
                            System.out.println("Food item description updated successfully.");
                            System.out.println("press enter to continue");
                            sc.nextLine();
                            break;
                        }
                    } else if (newOption == 4) {
                        System.out.print("Enter new name of food item: ");
                        String newName = sc.nextLine().trim();
                        double newPrice = 0.0;
                        while (true) {
                            System.out.print("Enter new price of food item: ");
                            try {
                                newPrice = sc.nextDouble();
                                sc.nextLine(); // Clear the buffer
                                if (newPrice <= 0) {
                                    System.out.println("Price must be greater than zero. Please try again.");
                                } else {
                                    break;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a valid price.");
                                sc.next(); // Clear the invalid input
                            }
                        }
                        System.out.print("Enter new description of food item: ");
                        String newDescription = sc.nextLine().trim();
                        foodItemController.updateFoodItem(foodItemId, newName, newPrice, newDescription, msg);
                        System.out.println("Food item updated successfully.");
                        System.out.println("press enter to continue");
                        sc.nextLine();
                        break;
                    } else if (newOption == 5) {
                        break;
                    }
                } else {
                    System.out.println("Invalid food item id. Please try again.");
                    System.out.println("Food items available in the restaurant:");
                    System.out.printf("%-20s %-30s %-20s %-30s%n", "Food Item ID", "Food Item Name","Food Item Price","Food Item Description");
                    System.out.println("-------------------------------------------------");
                    for (FoodItem item : foodItems) {
                        System.out.printf("%-20s %-30s %-20s %-30s%n", item.getId(), item.getName(),item.getPrice(),item.getDescription());
                    }
                }
            } else {
                System.out.println("Invalid restaurant id. Please try again.");
            }
        }
    }
    private static void viewFoodItemList(Scanner sc){
        RestaurantController restaurantController=RestaurantController.getInstance();
        Message msg = new Message();
        System.out.println("Enter 0 to return to main menu or enter anything to continue: ");
        String exitInput = sc.nextLine();
        if (exitInput.equals("0")) {
            return;
        }
        // Step 1: Show user's restaurants
        ArrayList<Restaurant> userRestaurants = restaurantController.getRestaurantsByOwnerId(id,msg);
        if (userRestaurants==null||userRestaurants.isEmpty()) {
            System.out.println("You don't have any restaurants.");
            return;
        }

        System.out.println("Your restaurants:");
        System.out.printf("%-20s %-40s%n", "Restaurant ID", "Restaurant Name");
        System.out.println("-------------------------------------------------");
        for (Restaurant restaurant : userRestaurants) {
            System.out.printf("%-20s %-40s%n", restaurant.getId(), restaurant.getName());
        }
        System.out.print("Enter restaurant id: ");
        String restaurantId = sc.nextLine().trim();
        Restaurant restaurant = restaurantController.isRestaurant(restaurantId);
        while (true) {
            if (restaurant != null) {
                ArrayList<FoodItem> foodItems = restaurant.getFoodItems();
                if (foodItems.isEmpty()) {
                    System.out.println("No food items available in this restaurant.");
                    System.out.println("press enter to continue");
                    sc.nextLine();
                    return;
                }


                System.out.println("Food items available in the restaurant:");
                System.out.printf("%-20s %-30s %-20s %-30s%n", "Food Item ID", "Food Item Name", "Food Item Price", "Food Item Description");
                System.out.println("-------------------------------------------------");
                for (FoodItem item : foodItems) {
                    System.out.printf("%-20s %-30s %-20s %-30s%n", item.getId(), item.getName(), item.getPrice(), item.getDescription());
                }
                System.out.println("press enter to continue");
                sc.nextLine();
                return;
            } else {
                System.out.println("Invalid restaurant id. Please try again.");
            }
        }
    }
    private static void deleteFoodItem(Scanner sc){
        FoodItemController foodItemController=FoodItemController.getInstance();
        RestaurantController restaurantController=RestaurantController.getInstance();
        Message msg = new Message();
        System.out.println("Enter 0 to return to main menu or enter anything to continue: ");
        String exitInput = sc.nextLine();
        if (exitInput.equals("0")) {
            return;
        }
        // Step 1: Show user's restaurants
        ArrayList<Restaurant> userRestaurants = restaurantController.getRestaurantsByOwnerId(id,msg);
        if (userRestaurants.isEmpty()) {
            System.out.println("You don't have any restaurants.");
            return;
        }

        System.out.println("Your restaurants:");
        System.out.printf("%-20s %-40s%n", "Restaurant ID", "Restaurant Name");
        System.out.println("-------------------------------------------------");
        for (Restaurant restaurant : userRestaurants) {
            System.out.printf("%-20s %-40s%n", restaurant.getId(), restaurant.getName());
        }
        System.out.print("Enter restaurant id: ");
        String restaurantId = sc.nextLine().trim();
        Restaurant restaurant = restaurantController.isRestaurant(restaurantId);
        while (true) {
            if (restaurant != null) {
                ArrayList<FoodItem> foodItems = restaurant.getFoodItems();
                if (foodItems.isEmpty()) {
                    System.out.println("No food items available in this restaurant.");
                    System.out.println("press enter to continue");
                    sc.nextLine();
                    return;
                }


                System.out.println("Food items available in the restaurant:");
                System.out.printf("%-20s %-30s %-20s %-30s%n", "Food Item ID", "Food Item Name", "Food Item Price", "Food Item Description");
                System.out.println("-------------------------------------------------");
                for (FoodItem item : foodItems) {
                    System.out.printf("%-20s %-30s %-20s %-30s%n", item.getId(), item.getName(), item.getPrice(), item.getDescription());
                }
                System.out.println("Enter Food Item id to delete");
                String fId="";
                fId=sc.nextLine();
                boolean deleted=foodItemController.deleteFoodItem(fId,msg);
                while(true) {
                    if (deleted) {
                        System.out.println("Food Item deleted successfully");
                        System.out.println("press enter to continue");
                        sc.nextLine();
                        return;
                    } else {
                        System.out.println("Wrong food Item id . Try again!");
                    }
                }
            } else {
                System.out.println("Invalid restaurant id. Please try again.");
            }
        }
    }
    private static void viewRestaurantList(Scanner sc){
            RestaurantController restaurantController=RestaurantController.getInstance();
            Message msg=new Message();
            ArrayList<Restaurant> restaurants=restaurantController.getRestaurantsByOwnerId(id,msg);
        System.out.println("Enter 0 to return to main menu or enter anything to continue: ");
        String exitInput = sc.nextLine();
        if (exitInput.equals("0")) {
            return;
        }
        if ((restaurants == null || restaurants.isEmpty())) {
            restaurants=new ArrayList<>();
            System.out.println("You don't have any restaurants.");
            System.out.println("press enter to continue");
            sc.nextLine();
            return;
        }

        System.out.println("Your restaurants:");
        System.out.printf("%-20s %-40s %-40s%n", "Restaurant ID", "Restaurant Name" ,"Restaurant Address");
        System.out.println("------------------------------------------------------------------------------");
        for (Restaurant restaurant : restaurants) {
            System.out.printf("%-20s %-40s %-40s%n", restaurant.getId(), restaurant.getName(), restaurant.getAddress());
        }
        System.out.println("press enter to continue");
        sc.nextLine();
    }
    private static void viewOrders(Scanner sc){
        RestaurantController restaurantController = RestaurantController.getInstance();
        OrderController orderController=OrderController.getInstance();
        Message msg= new Message();
        String ownerId= Helper.getInstance().getCurrentUserId();
        ArrayList<Restaurant>restaurants=restaurantController.getRestaurantsByOwnerId(ownerId,msg);
        System.out.println("Enter 0 to return to main menu or enter anything to continue: ");
        String exitInput = sc.nextLine();
        if (exitInput.equals("0")) {
            return;
        }
        if ((restaurants == null || restaurants.isEmpty())) {
            restaurants=new ArrayList<>();
            System.out.println("You don't have any restaurants.");
            System.out.println("press enter to continue");
            sc.nextLine();
            return;
        }

        System.out.println("Your restaurants:");
        System.out.printf("%-20s %-40s%n", "Restaurant ID", "Restaurant Name");
        System.out.println("-------------------------------------------------");
        for (Restaurant restaurant : restaurants) {
            System.out.printf("%-20s %-40s%n", restaurant.getId(), restaurant.getName());
        }
        while (true) {
            System.out.println("Enter restaurant id to view orders : ");
            String restaurantId = sc.nextLine();
            Restaurant restaurant = restaurantController.isRestaurant(restaurantId);
            if(!restaurantId.isEmpty()){
                if(restaurantId.equals(restaurant.getId())){
                    ArrayList<Order> orders=orderController.getOrdersByRestaurantId(restaurantId);
                    if (orders.isEmpty()) {
                        orders=new ArrayList<>();
                        System.out.println("You don't have any orders.");
                        System.out.println("Press enter to continue");
                        sc.nextLine();
                        return;
                    }
                    System.out.println("Your Orders:");
                    System.out.printf("%-20s %-40s %-30s %30s%n", "Order ID", "Customer Id" , "Total Price","Order Status");
                    System.out.println("-------------------------------------------------");
                    for (Order order : orders) {
                        System.out.printf("%-20s %-40s %-48s %-30s%n ", order.getId(), order.getCustomerId(),order.getTotalPrice(),order.getStatus());
                    }
                    System.out.println("press enter to continue");
                    sc.nextLine();
                    return;
                }
                else {
                    System.out.println("Restaurant id is wrong.Please try again");
                }
            }
            else{
                System.out.println("Id can't be empty");
            }
        }
    }
    private static void viewOrderStatus(Scanner sc){
        RestaurantController restaurantController = RestaurantController.getInstance();
        OrderController orderController=OrderController.getInstance();
        Message msg= new Message();
        String ownerId= Helper.getInstance().getCurrentUserId();
        ArrayList<Restaurant>restaurants=restaurantController.getRestaurantsByOwnerId(ownerId,msg);
        System.out.println("Enter 0 to return to main menu or enter anything to continue: ");
        String exitInput = sc.nextLine();
        if (exitInput.equals("0")) {
            return;
        }
        if ((restaurants == null || restaurants.isEmpty())) {
            restaurants=new ArrayList<>();
            System.out.println("You don't have any restaurants.");
            System.out.println("press enter to continue");
            sc.nextLine();
            return;
        }

        System.out.println("Your restaurants:");
        System.out.printf("%-20s %-40s%n", "Restaurant ID", "Restaurant Name");
        System.out.println("-------------------------------------------------");
        for (Restaurant restaurant : restaurants) {
            System.out.printf("%-20s %-40s%n", restaurant.getId(), restaurant.getName());
        }
        while (true) {
            System.out.println("Enter restaurant id to view orders : ");
            String restaurantId = sc.nextLine();
            Restaurant restaurant = restaurantController.isRestaurant(restaurantId);
            if(!restaurantId.isEmpty()){
                if(restaurantId.equals(restaurant.getId())){
                    ArrayList<Order> orders=orderController.getOrdersByRestaurantId(restaurantId);
                    if (orders.isEmpty()) {
                        orders=new ArrayList<>();
                        System.out.println("You don't have any orders.");
                        return;
                    }
                    System.out.println("Your Orders:");
                    System.out.printf("%-20s %-40s %-30s %30s%n", "Order ID", "Customer Id" , "Total Price","Order Status");
                    System.out.println("-------------------------------------------------");
                    for (Order order : orders) {
                        System.out.printf("%-20s %-40s %-30s %-30s%n ", order.getId(), order.getCustomerId(),order.getTotalPrice(),order.getStatus());
                        return;
                    }
                }
                else {
                    System.out.println("Restaurant id is wrong.Please try again");
                }
            }
            else{
                System.out.println("Id can't be empty");
            }
        }
    }
    private static void updateOrderStatus(Scanner sc){
        RestaurantController restaurantController = RestaurantController.getInstance();
        OrderController orderController=OrderController.getInstance();
        Message msg= new Message();
        String ownerId= Helper.getInstance().getCurrentUserId();
        ArrayList<Restaurant>restaurants=restaurantController.getRestaurantsByOwnerId(ownerId,msg);
        System.out.println("Enter 0 to return to main menu or enter anything to continue: ");
        String exitInput = sc.nextLine();
        if (exitInput.equals("0")) {
            return;
        }
        if ((restaurants == null || restaurants.isEmpty())) {
            restaurants=new ArrayList<>();
            System.out.println("You don't have any restaurants.");
            System.out.println("press enter to continue");
            sc.nextLine();
            return;
        }

        System.out.println("Your restaurants:");
        System.out.printf("%-20s %-40s%n", "Restaurant ID", "Restaurant Name");
        System.out.println("-------------------------------------------------");
        for (Restaurant restaurant : restaurants) {
            System.out.printf("%-20s %-40s%n", restaurant.getId(), restaurant.getName());
        }
        while (true) {
            System.out.println("Enter restaurant id to view orders : ");
            String restaurantId = sc.nextLine();
            Restaurant restaurant = restaurantController.isRestaurant(restaurantId);
            if (restaurant != null) {
                if (!restaurantId.isEmpty()) {
                    if (restaurantId.equals(restaurant.getId())) {
                        ArrayList<Order> orders = orderController.getOrdersByRestaurantId(restaurantId);
                        if (orders == null || orders.isEmpty()) {
                            orders = new ArrayList<>();
                            System.out.println("You don't have any orders.");
                            System.out.println("press enter to continue");
                            sc.nextLine();
                            return;
                        }
                        System.out.println("Your Orders:");
                        System.out.printf("%-20s %-40s %-30s %30s%n", "Order ID", "Customer Id", "Total Price", "Order Status");
                        System.out.println("-------------------------------------------------");
                        for (Order order : orders) {
                            System.out.printf("%-20s %-40s %-30s %-30s%n ", order.getId(), order.getCustomerId(), order.getTotalPrice(), order.getStatus());
                        }
                        System.out.println("Enter Order id to update status");
                        String orderId = sc.nextLine();
                        if (!orderId.isEmpty()) {
                            for (Order order : orders) {
                                if (orderId.equals(order.getId())) {
                                    System.out.println("enter 'i' - " + "In progress\n 'c' - " + "Completed");
                                    while (true) {
                                        String input = sc.nextLine();
                                        if (input.equalsIgnoreCase("i")) {
                                            order.setStatus("In Progress");
                                            System.out.println("Order status updated successfully!");
                                            System.out.println("press enter to continue");
                                            sc.nextLine();
                                            break;
                                        } else if (input.equalsIgnoreCase("c")) {
                                            order.setStatus("Completed");
                                            System.out.println("Order status updated successfully!");
                                            System.out.println("press enter to continue");
                                            sc.nextLine();
                                            return;
                                        } else {
                                            System.out.println("Invalid input ! Please try again");
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        System.out.println("Restaurant id is wrong.Please try again");
                    }
                } else {
                    System.out.println("Id can't be empty");
                }
                break;
            }
            else{
                System.out.println("Invalid restaurant id . Please try again !");
            }
        }
    }
    private static void customerMenu(Scanner sc) {
        System.out.println("Choose options :");
        System.out.print("1. View restaurants \n2. View menu of a restaurant\n3. Place order\n4. View order status\n5. View order history\n6. LogOut");
        System.out.println();
        int options = 0;

        try {
            options = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Please select a valid option");
            sc.next();
            return;
        }

        switch (options) {
            case 1:
                viewAllRestaurantList(sc);
                break;
            case 2:
                viewMenuOfARestaurant(sc);
                break;
            case 3:
                placeOrder(sc);
                break;
            case 4:
                viewCustomerOrderStatus(sc);
                break;
            case 5:
                viewOrderHistory(sc);
                option=9;
                break;
            case 6:
                option = 0; // Log out
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                option = 9; // Return to customer menu
                break;
        }

    }
    private static void viewAllRestaurantList(Scanner sc){
        RestaurantController restaurantController =RestaurantController.getInstance();
        System.out.println("Enter 0 to return to main menu or enter anything to continue: ");
        String exitInput = sc.nextLine();
        if (exitInput.equals("0")) {
            return;
        }
        ArrayList<Restaurant> restaurants=restaurantController.getAllRestaurants();
        if ((restaurants == null || restaurants.isEmpty())) {
            restaurants=new ArrayList<>();
            System.out.println("Sorry! No restaurants available.");
            System.out.println("press enter to continue");
            sc.nextLine();
            return;
        }
        System.out.println("Available restaurants:");
        System.out.printf("%-20s %-40s %-50s %-40s%n", "Restaurant ID", "Restaurant Name", "Restaurant Address", "Restaurant PhonuNumber");
        System.out.println("------------------------------------------------------------------------------------------------------------");
        for (Restaurant restaurant : restaurants) {
            System.out.printf("%-20s %-40s %-50s %-40s%n", restaurant.getId(), restaurant.getName(), restaurant.getAddress(), restaurant.getPhoneNumber());
        }
        System.out.println("Press enter to continue");
        sc.nextLine();
    }
    private static void viewMenuOfARestaurant(Scanner sc) {
        RestaurantController restaurantController = RestaurantController.getInstance();
        FoodItemController foodItemController = FoodItemController.getInstance();
        ArrayList<Restaurant> restaurants = restaurantController.getAllRestaurants();
        System.out.println("Enter 0 to return to main menu or enter anything to continue: ");
        String exitInput = sc.nextLine();
        if (exitInput.equals("0")) {
            return;
        }
        if ((restaurants == null || restaurants.isEmpty())) {
            restaurants = new ArrayList<>();
            System.out.println("Sorry! No restaurants available.");
            System.out.println("press enter to continue");
            sc.nextLine();
            return;
        }

        System.out.println("Available restaurants:");
        System.out.printf("%-20s %-40s %-50s %-40s%n", "Restaurant ID", "Restaurant Name", "Restaurant Address", "Restaurant PhonuNumber");
        System.out.println("------------------------------------------------------------------------------------------------------------");
        for (Restaurant restaurant : restaurants) {
            System.out.printf("%-20s %-40s %-50s %-40s%n", restaurant.getId(), restaurant.getName(), restaurant.getAddress(), restaurant.getPhoneNumber());
        }
        while (true) {
            System.out.println("Enter restaurant id to view menu : ");
            String restaurantId = sc.nextLine();
            Restaurant restaurant = restaurantController.isRestaurant(restaurantId);
            if (restaurant != null) {
                if (!restaurantId.isEmpty() ) {
                    if (restaurantId.equals(restaurant.getId())) {
                        ArrayList<FoodItem> foodItems = foodItemController.getFoodItemsByRestaurantId(restaurantId);
                        if (foodItems == null || foodItems.isEmpty()) {
                            foodItems = new ArrayList<>();
                            System.out.println("Sorry for Inconvenience ! there is no food Item available in the chosen restaurant");
                            return;
                        }
                        System.out.println("Menu");
                        System.out.printf("%-20s %-40s %-30s %-30s %-30s%n", "FoodItem ID", "FoodItem name", "Price", "FoodItem Description", "Availability");
                        System.out.println("----------------------------------------------------------------------------------------------------------------");
                        for (FoodItem foodItem : foodItems) {
                            System.out.printf("%-20s %-40s %-30s %-30s %-30s%n ", foodItem.getId(), foodItem.getName(), foodItem.getPrice(), foodItem.getDescription(), foodItem.isAvailability());
                        }
                        System.out.println("Press enter to continue");
                        sc.nextLine();
                        return;
                    } else {
                        System.out.println("Restaurant id is wrong.Please try again");
                    }
                } else {
                    System.out.println("Id can't be empty");
                }
                break;
            } else {
                System.out.println("Invalid restaurant id ! Please try again.");
            }
        }


    }
    private static void placeOrder(Scanner sc) {
        RestaurantController restaurantController = RestaurantController.getInstance();
        FoodItemController foodItemController = FoodItemController.getInstance();
        OrderController orderController = OrderController.getInstance();
        Message msg = new Message();
        System.out.println("Enter 0 to return to main menu or enter anything to continue: ");
        String exitInput = sc.nextLine();
        if (exitInput.equals("0")) {
            return;
        }
        ArrayList<Restaurant> restaurants = restaurantController.getAllRestaurants();
        if ((restaurants == null || restaurants.isEmpty())) {
            System.out.println("Sorry! No restaurants available.");
            System.out.println("Press enter to continue");
            sc.nextLine();
            return;
        }

        System.out.println("Available restaurants:");
        System.out.printf("%-20s %-40s %-50s %-40s%n", "Restaurant ID", "Restaurant Name", "Restaurant Address", "Restaurant Phone Number");
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        for (Restaurant restaurant : restaurants) {
            System.out.printf("%-20s %-40s %-50s %-40s%n", restaurant.getId(), restaurant.getName(), restaurant.getAddress(), restaurant.getPhoneNumber());
        }

        while (true) {
            System.out.println("Enter restaurant id to view menu:");
            String restaurantId = sc.nextLine();
            Restaurant restaurant = restaurantController.isRestaurant(restaurantId);
            if (restaurant != null) {
                ArrayList<FoodItem> foodItems = foodItemController.getFoodItemsByRestaurantId(restaurantId);
                if (foodItems == null || foodItems.isEmpty()) {
                    System.out.println("Sorry for the inconvenience! There are no food items available in the chosen restaurant.");
                    return;
                }

                System.out.println("Menu:");
                System.out.printf("%-20s %-40s %-30s %-30s %-30s%n", "FoodItem ID", "FoodItem Name", "Price", "FoodItem Description", "Availability");
                System.out.println("--------------------------------------------------------------------------------------------------------------");
                for (FoodItem foodItem : foodItems) {
                    System.out.printf("%-20s %-40s %-30s %-30s %-30s%n", foodItem.getId(), foodItem.getName(), foodItem.getPrice(), foodItem.getDescription(), foodItem.isAvailability());
                }

                ArrayList<FoodItem> selectedFoodItems = new ArrayList<>();
                while (true) {
                    System.out.print("Enter food item id to add to your order (or type 'done' to finish): ");
                    String fId = sc.nextLine();
                    if (fId.equalsIgnoreCase("done")) {
                        break;
                    }

                    FoodItem selectedFoodItem = null;
                    for (FoodItem foodItem : foodItems) {
                        if (fId.equals(foodItem.getId())) {
                            selectedFoodItem = foodItem;
                            break;
                        }
                    }

                    if (selectedFoodItem != null) {
                        selectedFoodItems.add(selectedFoodItem);
                        System.out.println("Added " + selectedFoodItem.getName() + " to your order.");
                    } else {
                        System.out.println("Invalid food item id. Please try again.");
                    }
                }

                if (!selectedFoodItems.isEmpty()) {
                    // Get current user ID from session
                    String customerId = Helper.getInstance().getCurrentUserId();
                    if (customerId != null) {
                        String orderId = generateOrderId(); // Method to generate a unique order ID
                        long totalPrice = calculateTotalPrice(selectedFoodItems);

                        boolean orderPlaced = orderController.placeOrder(customerId, orderId, restaurantId, "PENDING", totalPrice, selectedFoodItems, msg);
                        if (orderPlaced) {
                            System.out.println("Your order id is :"+orderId);
                            System.out.println("Order placed successfully.");
                        } else {
                            System.out.println("Failed to place order: " + msg.getMessage());
                        }
                    } else {
                        System.out.println("No customer ID found for the current session.");
                    }
                } else {
                    System.out.println("No food items selected.");
                }

                System.out.println("Press enter to continue");
                sc.nextLine();
                return;
            } else {
                System.out.println("Invalid restaurant id! Please try again.");
            }
        }
    }
    private static void viewCustomerOrderStatus(Scanner sc) {
        OrderController orderController = OrderController.getInstance();
        System.out.println("Enter 0 to return to main menu or enter anything to continue: ");
        String exitInput = sc.nextLine();
        if (exitInput.equals("0")) {
            return;
        }
        while (true) {
            System.out.print("Enter order id: ");
            String orderInput = sc.nextLine().trim();

            if (!orderInput.isEmpty()) {
                Order order = orderController.getOrdersByCustomerId(orderInput); // Assuming you have a method to get order by ID
                if (order == null) {
                    System.out.println("Order not found. Please enter a valid order ID.");
                } else {
                    System.out.println("Order ID: " + order.getId());
                    System.out.println("Customer ID: " + order.getCustomerId());
                    System.out.println("Restaurant ID: " + order.getRestaurantId());
                    System.out.println("Total Price: " + order.getTotalPrice());
                    System.out.println("Order Status: " + order.getStatus());
                    System.out.println("Food Items:");
                    for (FoodItem item : order.getFoodItems()) {
                        System.out.println(" - " + item.getName() + ": " + item.getPrice());
                    }
                    break;
                }
            } else {
                System.out.println("Order ID cannot be empty. Please try again.");
                return;
            }
        }

        System.out.println("Press enter to continue");
        sc.nextLine();
    }
    private static void viewOrderHistory(Scanner sc) {

        OrderController orderController = OrderController.getInstance();
        String customerId = Helper.getInstance().getCurrentUserId();
        System.out.println("Enter 0 to return to main menu or enter anything to continue: ");
        String exitInput = sc.nextLine();
        if (exitInput.equals("0")) {
            return;
        }
            if (customerId == null) {
                System.out.println("No customer ID found for the current session.");
                System.out.println("Press enter to continue");
                sc.nextLine();
                return;
            }
            ArrayList<Order> orders = orderController.getOrderHistory(customerId);
        System.out.println(orders);
            if (orders == null || orders.isEmpty()) {
                System.out.println("No orders found for the current user.");
                System.out.println("Press enter to continue");
                sc.nextLine();
                return;
            }

            System.out.println("Order History:");
            System.out.printf("%-20s %-20s %-20s %-20s %-20s%n", "Order ID", "Restaurant ID", "Total Price", "Status", "Food Items");
            System.out.println("-------------------------------------------------------------------------------------------------");

            for (Order order : orders) {
                System.out.printf("%-20s %-20s %-20s %-20s %-20s%n", order.getId(), order.getRestaurantId(), order.getTotalPrice(), order.getStatus(), getFoodItemsString(order.getFoodItems()));
            }

            System.out.println("Press enter to continue");
            sc.nextLine();

    }//not working
}
