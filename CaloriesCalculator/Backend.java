package CaloriesCalculator;

// Import necessary libraries
import java.io.*;  // For file operations
import java.util.*;  // For collections like ArrayList, HashMap
import java.text.SimpleDateFormat;  // For date formatting

// Custom exception class for login errors
class MyException extends Exception {
    MyException(String message) {
        super(message);  // Pass error message to parent class
    }
}

// Main calculator class for MET values
class METCalculator {
    // HashMap stores exercise names and their MET values
    // MET = Metabolic Equivalent of Task (calories burned per kg per hour)
    private static final Map<String, Double> MET_VALUES = new HashMap<>();
    
    // Static block runs once when class is loaded
    static {
        MET_VALUES.put("sitting", 1.0);        // Very light activity
        MET_VALUES.put("walking", 3.5);         // Light activity
        MET_VALUES.put("running", 7.0);         // Vigorous activity
        MET_VALUES.put("cycling", 6.0);         // Moderate activity
        MET_VALUES.put("weight lifting", 6.0);  // Strength training
        MET_VALUES.put("push-ups", 8.0);        // Calisthenics
        MET_VALUES.put("jumping jacks", 8.0);   // Cardio
        MET_VALUES.put("treadmill", 7.5);       // Running machine
        MET_VALUES.put("yoga", 2.5);            // Flexibility
    }

    // Method to calculate calories burned
    public String calculateCalories(String weight, String minutes, String activity) {
        try {
            // Convert string inputs to numbers
            double mins = Double.parseDouble(minutes);
            double kgs = Double.parseDouble(weight);
            
            // Check if activity exists in our database
            if (!MET_VALUES.containsKey(activity)) {
                return "Unknown Activity";
            }
            
            // Get MET value for selected activity
            double met = MET_VALUES.get(activity);
            
            // Convert minutes to hours (formula needs hours)
            double hours = mins / 60.0;
            
            // Formula: Calories = MET × weight(kg) × hours
            double calories = met * kgs * hours;
            
            // Return formatted result with 2 decimal places
            return String.format("%.2f", calories);
            
        } catch (NumberFormatException e) {
            // If user enters non-numeric values
            return "Invalid Input";
        }
    }
}

// User class stores all information about a person
class User {
    // User properties
    String name;              // User's name
    int age;                  // User's age
    String password;          // User's password
    String gender;           // User's gender (Male/Female/Other)
    List<String> activityRecords;  // List of all activities done
    
    // Constructor - runs when new User is created
    public User(String name, int age, String password, String gender) {
        this.name = name;
        this.age = age;
        this.password = password;
        this.gender = gender;
        this.activityRecords = new ArrayList<>();  // Initialize empty list
    }
    
    // Add a new activity record for this user
    public void addActivity(String weight, String exercise, String minutes, String calories) {
        // Get current date and time
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        
        // Create formatted record string
        String record = String.format("  • %s: %skg, %s, %s min → %s cal", 
                                     timestamp, weight, exercise, minutes, calories);
        
        // Add to user's activity list
        activityRecords.add(record);
    }
    
    // Get complete user data as formatted string (for display)
    public String getUserData() {
        StringBuilder sb = new StringBuilder();
        
        // Add user info
        sb.append("NAME: ").append(name);
        sb.append(" | AGE: ").append(age);
        sb.append(" | GENDER: ").append(gender);
        sb.append(" | PASSWORD: ").append(password);
        
        // Add all activity records
        for (String record : activityRecords) {
            sb.append("\n").append(record);
        }
        
        return sb.toString();
    }
}

// Database class handles all file operations
class UserDatabase {
    private List<User> users = new ArrayList<>();  // List of all users
    private final String FILE_NAME = "Data.txt";    // File name for storage
    
    // Constructor - loads users when object is created
    public UserDatabase() {
        loadUsers();
    }
    
    // Load users from file into memory
    public void loadUsers() {
        File file = new File(FILE_NAME);
        
        // If file doesn't exist, create it
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        
        // Read file line by line
        try (Scanner scanner = new Scanner(file)) {
            users.clear();  // Clear existing list
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) continue;  // Skip empty lines
                
                // Format: Name|Age|Password|Gender|Activity1|Activity2...
                String[] parts = line.split("\\|");
                
                if (parts.length >= 4) {
                    // Parse basic user info
                    String name = parts[0].trim();
                    int age = Integer.parseInt(parts[1].trim());
                    String password = parts[2].trim();
                    String gender = parts[3].trim();
                    
                    // Create user object
                    User user = new User(name, age, password, gender);
                    
                    // Add activity records if any exist (starting from index 4)
                    for (int i = 4; i < parts.length; i++) {
                        if (!parts[i].trim().isEmpty()) {
                            user.activityRecords.add("  • " + parts[i].trim());
                        }
                    }
                    
                    users.add(user);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    // Save all users from memory to file
    public void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            
            for (User user : users) {
                StringBuilder sb = new StringBuilder();
                
                // Write user info separated by |
                sb.append(user.name).append("|");
                sb.append(user.age).append("|");
                sb.append(user.password).append("|");
                sb.append(user.gender);
                
                // Write activity records without the "  • " prefix
                for (String record : user.activityRecords) {
                    String cleanRecord = record.replace("  • ", "");
                    sb.append("|").append(cleanRecord);
                }
                
                writer.write(sb.toString());
                writer.newLine();  // New line for next user
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Register new user
    public boolean signUp(String name, int age, String password, String gender, 
                          String weight, String exercise, String minutes, String calories) {
        
        // Check if username already exists
        for (User user : users) {
            if (user.name.equals(name)) {
                return false;  // User already exists
            }
        }
        
        // Create new user
        User newUser = new User(name, age, password, gender);
        
        // Add first activity
        newUser.addActivity(weight, exercise, minutes, calories);
        
        // Add to list and save
        users.add(newUser);
        saveUsers();
        return true;
    }
    
    // Login user
    public User login(String name, String password) throws MyException {
        for (User user : users) {
            if (user.name.equals(name) && user.password.equals(password)) {
                return user;  // Return found user
            }
        }
        throw new MyException("Invalid username or password");
    }
    
    // Delete user
    public boolean deleteUser(String name, String password) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.name.equals(name) && user.password.equals(password)) {
                users.remove(i);  // Remove from list
                saveUsers();      // Save updated list
                return true;
            }
        }
        return false;
    }
    
    // Add new activity for existing user
    public boolean updateUser(String name, String password, String weight, 
                              String exercise, String minutes, String calories) {
        for (User user : users) {
            if (user.name.equals(name) && user.password.equals(password)) {
                user.addActivity(weight, exercise, minutes, calories);
                saveUsers();
                return true;
            }
        }
        return false;
    }
    
    // Get all users
    public List<User> getAllUsers() {
        return users;
    }
}

// Bridge class between GUI and database
class BackendHandler extends METCalculator {
    public static UserDatabase db = new UserDatabase();
    public List<User> userList = db.getAllUsers();
    public int currentUserIndex = -1;  // -1 means no user selected
    
    // Refresh data from file
    public void refreshData() {
        db = new UserDatabase();
        userList = db.getAllUsers();
    }
    
    // Handle sign up from GUI
    public boolean doSignUp(String name, int age, String password, String gender,
                           String weight, String exercise, String minutes) {
        String calories = calculateCalories(weight, minutes, exercise);
        boolean success = db.signUp(name, age, password, gender, weight, exercise, minutes, calories);
        if (success) {
            refreshData();  // Update list
        }
        return success;
    }
    
    // Handle login from GUI
    public boolean doLogin(String name, String password) throws MyException {
        User user = db.login(name, password);
        refreshData();
        
        // Find user index
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).name.equals(name)) {
                currentUserIndex = i;
                return true;
            }
        }
        return false;
    }
    
    // Handle delete from GUI
    public boolean doDelete(String name, String password) {
        boolean success = db.deleteUser(name, password);
        if (success) {
            refreshData();
            currentUserIndex = -1;
        }
        return success;
    }
    
    // Handle update from GUI
    public boolean doUpdate(String name, String password, String weight, 
                           String exercise, String minutes) {
        String calories = calculateCalories(weight, minutes, exercise);
        boolean success = db.updateUser(name, password, weight, exercise, minutes, calories);
        if (success) {
            refreshData();
        }
        return success;
    }
    
    // Get user data for display
    public String getUserDisplayData() {
        if (currentUserIndex >= 0 && currentUserIndex < userList.size()) {
            return userList.get(currentUserIndex).getUserData();
        }
        return "No user selected";
    }
}