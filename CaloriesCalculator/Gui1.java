package CaloriesCalculator;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;

public class Gui1 extends JFrame {
    BackendHandler backend = new BackendHandler();
    
    // Panels
    JPanel mainPanel, signUpPanel, calcPanel, loginPanel, userPanel, updatePanel;
    
    // Buttons
    JButton btnSignIn, btnLogin, btnSignUp, btnCalculate, btnLoginSubmit, 
            btnDelete, btnUpdate, btnDone, btnBack;
    
    // Text Fields
    JTextField txtName, txtAge, txtWeight, txtMinutes;
    JTextField txtLoginName, txtUpdateWeight, txtUpdateMinutes;
    JTextField txtCalcWeight, txtCalcMinutes, txtCalcOutput, txtUpdateOutput;
    JPasswordField txtPassword, txtLoginPassword;
    
    // Combo Boxes
    JComboBox<String> cmbGender, cmbExercise, cmbUpdateExercise;
    
    // Text Area
    JTextArea txtUserData;
    
    // Image
    JLabel imageLabel;
    
    // User session
    String currentName, currentPass, currentGender;
    int currentAge;
    
    public Gui1() {
        setTitle("Fitness Tracker");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);  // Simple null layout jese tune kiya tha
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 248, 255)); // Light blue
        
        createMainPanel();
        createSignUpPanel();
        createCalcPanel();
        createLoginPanel();
        createUserPanel();
        createUpdatePanel();
        
        // Show main panel first
        mainPanel.setVisible(true);
        signUpPanel.setVisible(false);
        calcPanel.setVisible(false);
        loginPanel.setVisible(false);
        userPanel.setVisible(false);
        updatePanel.setVisible(false);
        
        setVisible(true);
    }
    
    void hideAllPanels() {
        mainPanel.setVisible(false);
        signUpPanel.setVisible(false);
        calcPanel.setVisible(false);
        loginPanel.setVisible(false);
        userPanel.setVisible(false);
        updatePanel.setVisible(false);
    }
    
    void createMainPanel() {
        mainPanel = new JPanel(null);
        mainPanel.setBounds(0, 0, 700, 600);
        mainPanel.setBackground(new Color(240, 248, 255));
         // Main panel ki image - top right par
        // try {
        // ImageIcon icon = new ImageIcon("slim-woman-holding-rope-gym.jpg");
        // Image img = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        // JLabel imgLabel = new JLabel(new ImageIcon(img));
        // imgLabel.setBounds(450, 20, 200, 150);
        // imgLabel.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 3));
        // mainPanel.add(imgLabel);
        // } catch (Exception e) {}
        
        JLabel title = new JLabel("FITNESS CALORIE TRACKER", SwingConstants.CENTER);
        title.setBounds(150, 150, 400, 50);
        title.setFont(new Font("Arial Black", Font.BOLD, 24));
        title.setForeground(new Color(0, 102, 204)); // Neon blue
        mainPanel.add(title);
        
        btnSignIn = new JButton("SIGN UP");
        btnSignIn.setBounds(250, 250, 200, 50);
        btnSignIn.setFont(new Font("Arial", Font.BOLD, 20));
        btnSignIn.setBackground(new Color(0, 153, 255)); // Bright blue
        btnSignIn.setForeground(Color.WHITE);
        btnSignIn.setBorder(new BevelBorder(BevelBorder.RAISED));
        btnSignIn.addActionListener(e -> {
            hideAllPanels();
            signUpPanel.setVisible(true);
        });
        mainPanel.add(btnSignIn);
        
        btnLogin = new JButton("LOGIN");
        btnLogin.setBounds(250, 320, 200, 50);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 20));
        btnLogin.setBackground(new Color(0, 153, 255));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBorder(new BevelBorder(BevelBorder.RAISED));
        btnLogin.addActionListener(e -> {
            hideAllPanels();
            loginPanel.setVisible(true);
        });
        mainPanel.add(btnLogin);
        
        add(mainPanel);
    }
    
    void createSignUpPanel() {
        signUpPanel = new JPanel(null);
        signUpPanel.setBounds(0, 0, 700, 600);
        signUpPanel.setBackground(new Color(240, 248, 255));
        
        // Image - poori height ki
        // try {
        //     ImageIcon icon = new ImageIcon("peakpx.jpg");
        //     Image img = icon.getImage().getScaledInstance(350, 550, Image.SCALE_SMOOTH);
        //     imageLabel = new JLabel(new ImageIcon(img));
        //     imageLabel.setBounds(400, 50, 350, 800);
        //     imageLabel.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 3));
        //     signUpPanel.add(imageLabel);
        // } catch (Exception e) 
        {
            // Agar image na ho to blue rectangle
            JPanel imagePlaceholder = new JPanel();
            imagePlaceholder.setBounds(400, 50, 250, 500);
            imagePlaceholder.setBackground(new Color(0, 153, 255, 100));
            imagePlaceholder.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 3));
            signUpPanel.add(imagePlaceholder);
        }
        
        JLabel title = new JLabel("CREATE ACCOUNT");
        title.setBounds(50, 60, 300, 40);
        title.setFont(new Font("Arial Black", Font.BOLD, 24));
        title.setForeground(new Color(0, 102, 204));
        signUpPanel.add(title);
        
        // Name
        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(50, 100, 100, 30);
        lblName.setFont(new Font("Arial", Font.BOLD, 18));
        signUpPanel.add(lblName);
        
        txtName = new JTextField();
        txtName.setBounds(150, 100, 200, 35);
        txtName.setFont(new Font("Arial", Font.PLAIN, 16));
        txtName.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 2));
        signUpPanel.add(txtName);
        
        // Age
        JLabel lblAge = new JLabel("Age:");
        lblAge.setBounds(50, 160, 100, 30);
        lblAge.setFont(new Font("Arial", Font.BOLD, 18));
        signUpPanel.add(lblAge);
        
        txtAge = new JTextField();
        txtAge.setBounds(150, 160, 100, 35);
        txtAge.setFont(new Font("Arial", Font.PLAIN, 16));
        txtAge.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 2));
        signUpPanel.add(txtAge);
        
        // Gender
        JLabel lblGender = new JLabel("Gender:");
        lblGender.setBounds(50, 220, 100, 30);
        lblGender.setFont(new Font("Arial", Font.BOLD, 18));
        signUpPanel.add(lblGender);
        
        String[] genders = {"Male", "Female", "Other"};
        cmbGender = new JComboBox<>(genders);
        cmbGender.setBounds(150, 220, 150, 35);
        cmbGender.setFont(new Font("Arial", Font.PLAIN, 16));
        cmbGender.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 2));
        signUpPanel.add(cmbGender);
        
        // Password
        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(50, 280, 100, 30);
        lblPass.setFont(new Font("Arial", Font.BOLD, 18));
        signUpPanel.add(lblPass);
        
        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 280, 200, 35);
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 16));
        txtPassword.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 2));
        signUpPanel.add(txtPassword);
        
        // Sign Up Button
        btnSignUp = new JButton("SIGN UP");
        btnSignUp.setBounds(100, 360, 200, 45);
        btnSignUp.setFont(new Font("Arial", Font.BOLD, 20));
        btnSignUp.setBackground(new Color(0, 153, 255));
        btnSignUp.setForeground(Color.WHITE);
        btnSignUp.setBorder(new BevelBorder(BevelBorder.RAISED));
        btnSignUp.addActionListener(e -> handleSignUp());
        signUpPanel.add(btnSignUp);
        
        // Back Button
        btnBack = new JButton("← BACK");
        btnBack.setBounds(20, 20, 100, 35);
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        btnBack.setBackground(new Color(0, 153, 255));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBorder(new BevelBorder(BevelBorder.RAISED));
        btnBack.addActionListener(e -> {
            hideAllPanels();
            mainPanel.setVisible(true);
        });
        signUpPanel.add(btnBack);
        
        add(signUpPanel);
    }
    
    void createCalcPanel() {
        calcPanel = new JPanel(null);
        calcPanel.setBounds(0, 0, 700, 600);
        calcPanel.setBackground(new Color(240, 248, 255));
        
        JLabel title = new JLabel("CALCULATE CALORIES", SwingConstants.CENTER);
        title.setBounds(150, 50, 400, 50);
        title.setFont(new Font("Arial Black", Font.BOLD, 28));
        title.setForeground(new Color(0, 102, 204));
        calcPanel.add(title);
        
        JLabel lblWelcome = new JLabel("Welcome " + (currentName != null ? currentName : "") + "! Enter your activity");
        lblWelcome.setBounds(200, 120, 300, 30);
        lblWelcome.setFont(new Font("Arial", Font.ITALIC, 18));
        calcPanel.add(lblWelcome);
        
        // Weight
        JLabel lblWeight = new JLabel("Weight (kg):");
        lblWeight.setBounds(150, 200, 120, 30);
        lblWeight.setFont(new Font("Arial", Font.BOLD, 18));
        calcPanel.add(lblWeight);
        
        txtCalcWeight = new JTextField();
        txtCalcWeight.setBounds(280, 200, 100, 35);
        txtCalcWeight.setFont(new Font("Arial", Font.PLAIN, 16));
        txtCalcWeight.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 2));
        calcPanel.add(txtCalcWeight);
        
        // Exercise
        JLabel lblExercise = new JLabel("Exercise:");
        lblExercise.setBounds(150, 260, 120, 30);
        lblExercise.setFont(new Font("Arial", Font.BOLD, 18));
        calcPanel.add(lblExercise);
        
        String[] exercises = {"sitting", "walking", "running", "cycling", 
                              "weight lifting", "push-ups", "jumping jacks", 
                              "treadmill", "yoga"};
        cmbExercise = new JComboBox<>(exercises);
        cmbExercise.setBounds(280, 260, 150, 35);
        cmbExercise.setFont(new Font("Arial", Font.PLAIN, 16));
        cmbExercise.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 2));
        calcPanel.add(cmbExercise);
        
        // Minutes
        JLabel lblMins = new JLabel("Minutes:");
        lblMins.setBounds(150, 320, 120, 30);
        lblMins.setFont(new Font("Arial", Font.BOLD, 18));
        calcPanel.add(lblMins);
        
        txtCalcMinutes = new JTextField();
        txtCalcMinutes.setBounds(280, 320, 100, 35);
        txtCalcMinutes.setFont(new Font("Arial", Font.PLAIN, 16));
        txtCalcMinutes.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 2));
        calcPanel.add(txtCalcMinutes);
        
        // Calculate Button
        btnCalculate = new JButton("CALCULATE");
        btnCalculate.setBounds(200, 390, 200, 45);
        btnCalculate.setFont(new Font("Arial", Font.BOLD, 20));
        btnCalculate.setBackground(new Color(0, 153, 255));
        btnCalculate.setForeground(Color.WHITE);
        btnCalculate.setBorder(new BevelBorder(BevelBorder.RAISED));
        calcPanel.add(btnCalculate);
        
        // Output
        JLabel lblOutput = new JLabel("Calories Burned:");
        lblOutput.setBounds(150, 460, 150, 30);
        lblOutput.setFont(new Font("Arial", Font.BOLD, 16));
        calcPanel.add(lblOutput);
        
        txtCalcOutput = new JTextField();
        txtCalcOutput.setBounds(300, 460, 150, 35);
        txtCalcOutput.setFont(new Font("Arial", Font.BOLD, 18));
        txtCalcOutput.setEditable(false);
        txtCalcOutput.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 2));
        txtCalcOutput.setHorizontalAlignment(JTextField.CENTER);
        calcPanel.add(txtCalcOutput);
        
        // Save Button
        JButton btnSave = new JButton("SAVE & CONTINUE");
        btnSave.setBounds(200, 520, 200, 40);
        btnSave.setFont(new Font("Arial", Font.BOLD, 16));
        btnSave.setBackground(new Color(50, 150, 50));
        btnSave.setForeground(Color.WHITE);
        btnSave.setBorder(new BevelBorder(BevelBorder.RAISED));
        calcPanel.add(btnSave);
        
        // Calculate action
        btnCalculate.addActionListener(e -> {
            String wt = txtCalcWeight.getText();
            String ex = (String) cmbExercise.getSelectedItem();
            String min = txtCalcMinutes.getText();
            
            if (wt.isEmpty() || min.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
                return;
            }
            
            try {
                Double.parseDouble(wt);
                Double.parseDouble(min);
            } catch (NumberFormatException exe) {
                JOptionPane.showMessageDialog(this, "Weight and minutes must be numbers!");
                return;
            }
            
            METCalculator calc = new METCalculator();
            String result = calc.calculateCalories(wt, min, ex);
            txtCalcOutput.setText(result + " cal");
        });
        
        // Save action
        btnSave.addActionListener(e -> {
            String wt = txtCalcWeight.getText();
            String ex = (String) cmbExercise.getSelectedItem();
            String min = txtCalcMinutes.getText();
            String cal = txtCalcOutput.getText().replace(" cal", "");
            
            if (cal.isEmpty() || cal.equals("")) {
                JOptionPane.showMessageDialog(this, "Please calculate first!");
                return;
            }
            
            boolean success = backend.doSignUp(currentName, currentAge, currentPass, 
                                             currentGender, wt, ex, min);
            
            if (success) {
                JOptionPane.showMessageDialog(this, "Account created and activity saved!");
                hideAllPanels();
                userPanel.setVisible(true);
                refreshUserData();
            } else {
                JOptionPane.showMessageDialog(this, "Username already exists!");
            }
        });
        
        // Back Button
        btnBack = new JButton("← BACK");
        btnBack.setBounds(20, 20, 100, 35);
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        btnBack.setBackground(new Color(0, 153, 255));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBorder(new BevelBorder(BevelBorder.RAISED));
        btnBack.addActionListener(e -> {
            hideAllPanels();
            signUpPanel.setVisible(true);
        });
        calcPanel.add(btnBack);
        
        add(calcPanel);
    }
    
    void createLoginPanel() {
        loginPanel = new JPanel(null);
        loginPanel.setBounds(0, 0, 700, 600);
        loginPanel.setBackground(new Color(240, 248, 255));
         // Login panel ki image - left side
        // try {
        // ImageIcon icon = new ImageIcon("smiling-young-charming-blonde-woman-sportswear-posing-graviton-machine-modern-fitness-center.jpg");
        // Image img = icon.getImage().getScaledInstance(250, 400, Image.SCALE_SMOOTH);
        // JLabel imgLabel = new JLabel(new ImageIcon(img));
        // imgLabel.setBounds(50, 100, 250, 400);
        // imgLabel.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 3));
        // loginPanel.add(imgLabel);
        // } catch (Exception e) {}
        JLabel title = new JLabel("LOGIN", SwingConstants.CENTER);
        title.setBounds(200, 100, 300, 60);
        title.setFont(new Font("Arial Black", Font.BOLD, 36));
        title.setForeground(new Color(0, 102, 204));
        loginPanel.add(title);
        
        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(150, 200, 100, 30);
        lblName.setFont(new Font("Arial", Font.BOLD, 20));
        loginPanel.add(lblName);
        
        txtLoginName = new JTextField();
        txtLoginName.setBounds(250, 200, 200, 35);
        txtLoginName.setFont(new Font("Arial", Font.PLAIN, 16));
        txtLoginName.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 2));
        loginPanel.add(txtLoginName);
        
        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(150, 260, 100, 30);
        lblPass.setFont(new Font("Arial", Font.BOLD, 20));
        loginPanel.add(lblPass);
        
        txtLoginPassword = new JPasswordField();
        txtLoginPassword.setBounds(250, 260, 200, 35);
        txtLoginPassword.setFont(new Font("Arial", Font.PLAIN, 16));
        txtLoginPassword.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 2));
        loginPanel.add(txtLoginPassword);
        
        btnLoginSubmit = new JButton("LOGIN");
        btnLoginSubmit.setBounds(220, 340, 200, 45);
        btnLoginSubmit.setFont(new Font("Arial", Font.BOLD, 22));
        btnLoginSubmit.setBackground(new Color(0, 153, 255));
        btnLoginSubmit.setForeground(Color.WHITE);
        btnLoginSubmit.setBorder(new BevelBorder(BevelBorder.RAISED));
        btnLoginSubmit.addActionListener(e -> handleLogin());
        loginPanel.add(btnLoginSubmit);
        
        btnBack = new JButton("← BACK");
        btnBack.setBounds(20, 20, 100, 35);
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        btnBack.setBackground(new Color(0, 153, 255));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBorder(new BevelBorder(BevelBorder.RAISED));
        btnBack.addActionListener(e -> {
            hideAllPanels();
            mainPanel.setVisible(true);
        });
        loginPanel.add(btnBack);
        
        add(loginPanel);
    }
    
    void createUserPanel() {
        userPanel = new JPanel(null);
        userPanel.setBounds(0, 0, 700, 600);
        userPanel.setBackground(new Color(240, 248, 255));
        // try {
        // ImageIcon icon = new ImageIcon("peakpx.jpg");
        // Image img = icon.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
        // JLabel imgLabel = new JLabel(new ImageIcon(img));
        // imgLabel.setBounds(500, 20, 150, 100);
        // imgLabel.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 3));
        // userPanel.add(imgLabel);
        // } catch (Exception e) {}
        JLabel title = new JLabel("YOUR ACTIVITIES", SwingConstants.CENTER);
        title.setBounds(200, 20, 300, 40);
        title.setFont(new Font("Arial Black", Font.BOLD, 24));
        title.setForeground(new Color(0, 102, 204));
        userPanel.add(title);
        
        txtUserData = new JTextArea();
        txtUserData.setBounds(50, 80, 600, 300);
        txtUserData.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txtUserData.setEditable(false);
        txtUserData.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 3));
        JScrollPane scroll = new JScrollPane(txtUserData);
        scroll.setBounds(50, 80, 600, 300);
        userPanel.add(scroll);
        
        btnUpdate = new JButton("ADD NEW ACTIVITY");
        btnUpdate.setBounds(150, 420, 200, 45);
        btnUpdate.setFont(new Font("Arial", Font.BOLD, 16));
        btnUpdate.setBackground(new Color(0, 153, 255));
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setBorder(new BevelBorder(BevelBorder.RAISED));
        btnUpdate.addActionListener(e -> {
            hideAllPanels();
            updatePanel.setVisible(true);
        });
        userPanel.add(btnUpdate);
        
        btnDelete = new JButton("DELETE ACCOUNT");
        btnDelete.setBounds(380, 420, 180, 45);
        btnDelete.setFont(new Font("Arial", Font.BOLD, 16));
        btnDelete.setBackground(new Color(200, 50, 50));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setBorder(new BevelBorder(BevelBorder.RAISED));
        btnDelete.addActionListener(e -> handleDelete());
        userPanel.add(btnDelete);
        
        btnBack = new JButton("← LOGOUT");
        btnBack.setBounds(20, 20, 120, 35);
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        btnBack.setBackground(new Color(0, 153, 255));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBorder(new BevelBorder(BevelBorder.RAISED));
        btnBack.addActionListener(e -> {
            hideAllPanels();
            mainPanel.setVisible(true);
        });
        userPanel.add(btnBack);
        
        add(userPanel);
    }
    
    void createUpdatePanel() {
        updatePanel = new JPanel(null);
        updatePanel.setBounds(0, 0, 700, 600);
        updatePanel.setBackground(new Color(240, 248, 255));
        
        JLabel title = new JLabel("ADD NEW ACTIVITY", SwingConstants.CENTER);
        title.setBounds(150, 50, 400, 50);
        title.setFont(new Font("Arial Black", Font.BOLD, 26));
        title.setForeground(new Color(0, 102, 204));
        updatePanel.add(title);
        
        JLabel lblWeight = new JLabel("Weight (kg):");
        lblWeight.setBounds(150, 150, 120, 30);
        lblWeight.setFont(new Font("Arial", Font.BOLD, 20));
        updatePanel.add(lblWeight);
        
        txtUpdateWeight = new JTextField();
        txtUpdateWeight.setBounds(280, 150, 100, 35);
        txtUpdateWeight.setFont(new Font("Arial", Font.PLAIN, 16));
        txtUpdateWeight.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 2));
        updatePanel.add(txtUpdateWeight);
        
        JLabel lblExercise = new JLabel("Exercise:");
        lblExercise.setBounds(150, 210, 120, 30);
        lblExercise.setFont(new Font("Arial", Font.BOLD, 20));
        updatePanel.add(lblExercise);
        
        String[] exercises = {"sitting", "walking", "running", "cycling", 
                              "weight lifting", "push-ups", "jumping jacks", 
                              "treadmill", "yoga"};
        cmbUpdateExercise = new JComboBox<>(exercises);
        cmbUpdateExercise.setBounds(280, 210, 150, 35);
        cmbUpdateExercise.setFont(new Font("Arial", Font.PLAIN, 16));
        cmbUpdateExercise.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 2));
        updatePanel.add(cmbUpdateExercise);
        
        JLabel lblMins = new JLabel("Minutes:");
        lblMins.setBounds(150, 270, 120, 30);
        lblMins.setFont(new Font("Arial", Font.BOLD, 20));
        updatePanel.add(lblMins);
        
        txtUpdateMinutes = new JTextField();
        txtUpdateMinutes.setBounds(280, 270, 100, 35);
        txtUpdateMinutes.setFont(new Font("Arial", Font.PLAIN, 16));
        txtUpdateMinutes.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 2));
        updatePanel.add(txtUpdateMinutes);
        
        JButton btnCalcUpdate = new JButton("CALCULATE");
        btnCalcUpdate.setBounds(200, 330, 150, 40);
        btnCalcUpdate.setFont(new Font("Arial", Font.BOLD, 16));
        btnCalcUpdate.setBackground(new Color(0, 153, 255));
        btnCalcUpdate.setForeground(Color.WHITE);
        btnCalcUpdate.setBorder(new BevelBorder(BevelBorder.RAISED));
        updatePanel.add(btnCalcUpdate);
        
        JLabel lblOutput = new JLabel("Calories:");
        lblOutput.setBounds(150, 390, 100, 30);
        lblOutput.setFont(new Font("Arial", Font.BOLD, 18));
        updatePanel.add(lblOutput);
        
        txtUpdateOutput = new JTextField();
        txtUpdateOutput.setBounds(250, 390, 150, 35);
        txtUpdateOutput.setFont(new Font("Arial", Font.BOLD, 18));
        txtUpdateOutput.setEditable(false);
        txtUpdateOutput.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 2));
        updatePanel.add(txtUpdateOutput);
        
        btnDone = new JButton("SAVE ACTIVITY");
        btnDone.setBounds(200, 450, 200, 45);
        btnDone.setFont(new Font("Arial", Font.BOLD, 18));
        btnDone.setBackground(new Color(50, 150, 50));
        btnDone.setForeground(Color.WHITE);
        btnDone.setBorder(new BevelBorder(BevelBorder.RAISED));
        updatePanel.add(btnDone);
        
        // Calculate button action
        btnCalcUpdate.addActionListener(e -> {
            String wt = txtUpdateWeight.getText();
            String ex = (String) cmbUpdateExercise.getSelectedItem();
            String min = txtUpdateMinutes.getText();
            
            if (wt.isEmpty() || min.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
                return;
            }
            
            try {
                Double.parseDouble(wt);
                Double.parseDouble(min);
            } catch (NumberFormatException exe) {
                JOptionPane.showMessageDialog(this, "Weight and minutes must be numbers!");
                return;
            }
            
            METCalculator calc = new METCalculator();
            String result = calc.calculateCalories(wt, min, ex);
            txtUpdateOutput.setText(result + " cal");
        });
        
        // Save button action
        btnDone.addActionListener(e -> {
            String wt = txtUpdateWeight.getText();
            String ex = (String) cmbUpdateExercise.getSelectedItem();
            String min = txtUpdateMinutes.getText();
            String cal = txtUpdateOutput.getText().replace(" cal", "");
            
            if (cal.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please calculate first!");
                return;
            }
            
            boolean success = backend.doUpdate(currentName, currentPass, wt, ex, min);
            
            if (success) {
                JOptionPane.showMessageDialog(this, "Activity saved successfully!");
                hideAllPanels();
                userPanel.setVisible(true);
                refreshUserData();
                txtUpdateWeight.setText("");
                txtUpdateMinutes.setText("");
                txtUpdateOutput.setText("");
            }
        });
        
        btnBack = new JButton("← BACK");
        btnBack.setBounds(20, 20, 100, 35);
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        btnBack.setBackground(new Color(0, 153, 255));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBorder(new BevelBorder(BevelBorder.RAISED));
        btnBack.addActionListener(e -> {
            hideAllPanels();
            userPanel.setVisible(true);
        });
        updatePanel.add(btnBack);
        
        add(updatePanel);
    }
    
    void handleSignUp() {
        String name = txtName.getText().trim();
        String ageStr = txtAge.getText().trim();
        String pass = new String(txtPassword.getPassword()).trim();
        String gender = (String) cmbGender.getSelectedItem();
        
        if (name.isEmpty() || ageStr.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
            return;
        }
        
        // Check for duplicate user
        backend.refreshData();
        for (User user : backend.userList) {
            if (user.name.equals(name)) {
                JOptionPane.showMessageDialog(this, "Username already exists!");
                return;
            }
        }
        
        try {
            currentAge = Integer.parseInt(ageStr);
            if (currentAge <= 0 || currentAge > 120) {
                JOptionPane.showMessageDialog(this, "Please enter valid age (1-120)!");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Age must be a number!");
            return;
        }
        
        currentName = name;
        currentPass = pass;
        currentGender = gender;
        
        // Go to calculation panel (don't save yet)
        hideAllPanels();
        calcPanel.setVisible(true);
    }
    
    void handleLogin() {
        String name = txtLoginName.getText().trim();
        String pass = new String(txtLoginPassword.getPassword()).trim();
        
        if (name.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
            return;
        }
        
        try {
            if (backend.doLogin(name, pass)) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                currentName = name;
                currentPass = pass;
                refreshUserData();
                hideAllPanels();
                userPanel.setVisible(true);
            }
        } catch (MyException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    void handleDelete() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete your account?", 
            "Confirm Delete", JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            if (backend.doDelete(currentName, currentPass)) {
                JOptionPane.showMessageDialog(this, "Account deleted successfully!");
                hideAllPanels();
                mainPanel.setVisible(true);
            }
        }
    }
    
    void refreshUserData() {
        backend.refreshData();
        txtUserData.setText(backend.getUserDisplayData());
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Gui1().setVisible(true));
    }
}

// Panel	Image Position	Size
// Main	Top Right	200x150
// Sign Up	Right Side (Full)	350x550
// Login	Left Side	250x400
// User	Top Right	150x100
// Update	Bottom Right	200x150