// Please send suggestions sa gc

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ticketSystem{
   
   // ADMIN ACCOUNT ACCESS
   private static final String ADMIN_USERNAME = "admin";
   private static final String ADMIN_PASSWORD = "admin";
   
   // ENCAPSULATION
   private ArrayList<Movie> movies;
   
   CardLayout cardLayout = new CardLayout();
   JFrame loginFrame, mainFrame;
   JPanel loginPanel;
   JPanel mainViewPanel = new JPanel(cardLayout);;
   JPanel homePanel, bookingPanel;
   JTextField userField;
   JPasswordField passField;
   JLabel errorLabel;
   JTextField ticketsField;
   
   public int totalAmountToPay;
   
   JLabel movieTitleLabel = new JLabel("Invalid");
   JLabel moviePriceLabel = new JLabel("0");
   JLabel totalPayment = new JLabel("0");
   
   public static void main(String[] args){
      
      new ticketSystem();
   
   }
   
   // Initialize Movies
   private void initializeMovies() {
      movies = new ArrayList<>();
      movies.add(new Movie("Inside Out", 450, "image/movie1.jpg"));
      movies.add(new Movie("Frozen", 320, "image/movie2.jpg"));
      movies.add(new Movie("How to Train Your Dragon", 250, "image/movie3.jpg"));
      movies.add(new Movie("Minions", 350, "image/movie4.jpg"));
      movies.add(new Movie("Kung Fu Panda", 280, "image/movie5.jpg"));
      movies.add(new Movie("Movie 6", 0, "image/movie6.jpg"));
      movies.add(new Movie("Movie 7", 0, "image/movie7.jpg"));
      movies.add(new Movie("Movie 8", 0, "image/movie8.jpg"));
      movies.add(new Movie("Movie 9", 0, "image/movie9.jpg"));
      movies.add(new Movie("Movie 10", 0, "image/movie10.jpg"));
   }

   // Constructor
   public ticketSystem(){
      
      initializeMovies();
      loginUI();
      
   }
   
   // Login UI Frame
   public void loginUI(){
      
      loginFrame = new JFrame("Login");
      loginFrame.setSize(320, 170);
      loginFrame.setResizable(false);
      loginFrame.setLocationRelativeTo(null);
      loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      loginFrame.setLayout(new GridBagLayout());

      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(5, 5, 5, 5);
      
      errorLabel = new JLabel();
      errorLabel.setForeground(Color.RED);
      errorLabel.setVisible(false);
      
      loginPanel = new JPanel();
      loginPanel.setLayout(new GridBagLayout());
         
      JLabel userLabel = new JLabel("Username: ");
      userField = new JTextField(18);
      JLabel passLabel = new JLabel("Password: ");
      passField = new JPasswordField(18);
      
      JButton loginButton = new JButton("Login");
      loginButton.setFocusPainted(false);
      loginButton.addActionListener(new loginButtonListener());
      
      loginFrame.getRootPane().setDefaultButton(loginButton);
      
      gbc.gridx = 0;
      gbc.gridy = 0;
      loginPanel.add(userLabel, gbc);
      
      gbc.gridx = 1;
      loginPanel.add(userField, gbc);
      
      gbc.gridx = 0;
      gbc.gridy = 1;
      loginPanel.add(passLabel, gbc);
      
      gbc.gridx = 1;
      loginPanel.add(passField, gbc);
      
      gbc.gridx = 0;
      gbc.gridy = 2;
      gbc.gridwidth = 2;
      gbc.anchor = GridBagConstraints.CENTER;
      loginPanel.add(loginButton, gbc);
      
      gbc.gridy = 3;
      loginPanel.add(errorLabel, gbc);
      
      loginFrame.add(loginPanel);
      
      loginFrame.setVisible(true);
      
   }
   
   // Main UI Frame
   public void mainUI(){
      
      mainFrame = new JFrame("NetFlex");
      mainFrame.setSize(800, 700);
      mainFrame.setResizable(false);
      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mainFrame.setLocationRelativeTo(null);
      mainFrame.getContentPane().setBackground(Color.black);
      mainFrame.setLayout(new BorderLayout(10, 10));   

      homePanel = new JPanel();
      homePanel.setLayout(new BorderLayout());
      homePanel.setBackground(Color.black);

      JPanel headerPanel = new JPanel();
      headerPanel.setLayout(new BorderLayout());
      headerPanel.setBackground(Color.black);
      headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 35, 10, 45));

      JLabel titleLabel = new JLabel("NetFlex");
      titleLabel.setFont(new Font("Fira Code", Font.BOLD, 30));
      titleLabel.setForeground(Color.decode("#DB202C"));
      
      JButton logOutButton = new JButton("Log Out");
      logOutButton.setFocusPainted(false);
      logOutButton.setFont(new Font("mono space", Font.BOLD, 15));
      logOutButton.setPreferredSize(new Dimension(90, 20));
      logOutButton.setBackground(Color.black);
      logOutButton.setForeground(Color.white);
      logOutButton.setBorder(BorderFactory.createEmptyBorder(13, 10, 10, 10));
      logOutButton.addActionListener(new movieButtonListener());
      logOutButton.setActionCommand("out");

      JPanel movieSelectionPanel = new JPanel();
      movieSelectionPanel.setBackground(Color.black);
      movieSelectionPanel.setLayout(new GridLayout(2, 5, 20, 20));
      movieSelectionPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));
      
      // Movie Selection Buttons
      // Thank you ani sir!
      JButton[] buttons = new JButton[10];
      for (int i = 0; i < movies.size(); i++) {
         Movie movie = movies.get(i);
         JButton movieButton = new JButton();
         movieButton.setFocusPainted(false);
         movieButton.setActionCommand(String.valueOf(i));
         movieButton.addActionListener(new movieButtonListener());
         movieButton.setBackground(Color.decode("#5c5b5b"));
         movieButton.setForeground(Color.white);
         movieButton.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));

         //Exception Handler if image file is missing or invalid
         try {
               ImageIcon movieIcon = new ImageIcon(movie.getImagePath());
               Image scaledImage = movieIcon.getImage().getScaledInstance(150, 230, Image.SCALE_SMOOTH);
               movieButton.setIcon(new ImageIcon(scaledImage));
         } catch (Exception e) {
               movieButton.setText(movie.getTitle());
         }

         movieSelectionPanel.add(movieButton);
      }
      
      headerPanel.add(titleLabel, BorderLayout.WEST);
      headerPanel.add(logOutButton, BorderLayout.EAST);

      homePanel.add(headerPanel, BorderLayout.NORTH);
      homePanel.add(movieSelectionPanel, BorderLayout.SOUTH);

      mainViewPanel.add(homePanel, BorderLayout.CENTER);
      mainViewPanel.add(homePanel, "homePanel");

      mainFrame.add(mainViewPanel);
      mainFrame.setVisible(true);
      
   }
   
   // Booking Panel UI
   // Subject to change //
   public void bookingUI(){
      
      Border etch = BorderFactory.createEtchedBorder();
      
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(5, 5, 5, 5);

      bookingPanel = new JPanel();
      bookingPanel.setLayout(new GridBagLayout());
      bookingPanel.setBackground(Color.BLACK);

      JPanel bookingScreenPanel = new JPanel();
      bookingScreenPanel.setLayout(new BorderLayout(10, 10));
      bookingScreenPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

      JPanel buyerPanel = new JPanel();
      buyerPanel.setLayout(new GridBagLayout());
      buyerPanel.setBorder(BorderFactory.createTitledBorder(etch, "Enter Details"));
      
      JLabel nameLabel = new JLabel("Full Name: ");
      JTextField nameField = new JTextField(32);
      
      JLabel emailLabel = new JLabel("Email Address: ");
      JTextField emailField = new JTextField(32);
      
      JLabel phoneLabel = new JLabel("Phone No: ");
      JTextField phoneField = new JTextField(32);
      
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.anchor = GridBagConstraints.EAST;
      buyerPanel.add(nameLabel, gbc);
      
      gbc.gridx = 1;
      buyerPanel.add(nameField, gbc);
      
      gbc.gridx = 0;
      gbc.gridy = 1;
      buyerPanel.add(emailLabel, gbc);
   
      gbc.gridx = 1;
      buyerPanel.add(emailField, gbc);
      
      gbc.gridx = 0;
      gbc.gridy = 2;
      buyerPanel.add(phoneLabel, gbc);
   
      gbc.gridx = 1;
      buyerPanel.add(phoneField, gbc);
      
      JPanel ticketPanel = new JPanel();
      ticketPanel.setLayout(new GridBagLayout());
      ticketPanel.setBorder(BorderFactory.createTitledBorder(etch, "Buy Ticket"));
      
      JLabel titleLabel = new JLabel("Movie: ");
      JLabel priceLabel = new JLabel("Price: ");
      
      JLabel ticketsLabel = new JLabel("Tickets:");
      
      ticketsField = new JTextField(15);
      ticketsField.setHorizontalAlignment(JTextField.CENTER);
      ticketsField.setFont(new Font("monospace", Font.BOLD, 13));
      ticketsField.setText("0");
      
      JButton decButton = new JButton("-");
      decButton.setFocusPainted(false);
      decButton.setActionCommand("dec");
      decButton.addActionListener(new bookingButtonListener());
      decButton.setFont(new Font("Arial", Font.PLAIN, 10));
      
      JButton addButton = new JButton("+");
      addButton.setFocusPainted(false);
      addButton.setActionCommand("add");
      addButton.addActionListener(new bookingButtonListener());
      addButton.setFont(new Font("Arial", Font.PLAIN, 10));
      
      JLabel totalAmountLabel = new JLabel("Total Payment:");
      
      gbc.gridx = 0;
      gbc.gridy = 0;
      ticketPanel.add(titleLabel, gbc);
      
      gbc.gridx = 1;
      ticketPanel.add(movieTitleLabel, gbc);
      
      gbc.gridx = 0;
      gbc.gridy = 1;
      ticketPanel.add(priceLabel, gbc);
      
      gbc.gridx = 1;
      ticketPanel.add(moviePriceLabel, gbc);
      
      gbc.gridx = 0;
      gbc.gridy = 2;
      ticketPanel.add(ticketsLabel, gbc);
      
      gbc.gridx = 1;
      ticketPanel.add(ticketsField, gbc);
      
      gbc.gridx = 2;
      ticketPanel.add(decButton, gbc);
      
      gbc.gridx = 3;
      ticketPanel.add(addButton, gbc);
      
      gbc.gridx = 0;
      gbc.gridy = 3;
      ticketPanel.add(totalAmountLabel, gbc);
      
      gbc.gridx = 1;
      ticketPanel.add(totalPayment, gbc);
      
      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new BorderLayout());
      JButton nextButton = new JButton("Next");
      nextButton.setFocusPainted(false);
      nextButton.setActionCommand("next");
      nextButton.addActionListener(new bookingButtonListener());

      JButton backButton = new JButton("Back");
      backButton.setFocusPainted(false);
      backButton.setActionCommand("back");
      backButton.addActionListener(new bookingButtonListener());
      
      buttonPanel.add(backButton, BorderLayout.WEST);
      buttonPanel.add(nextButton, BorderLayout.EAST);

      bookingScreenPanel.add(buyerPanel, BorderLayout.NORTH);
      bookingScreenPanel.add(ticketPanel, BorderLayout.CENTER);
      bookingScreenPanel.add(buttonPanel, BorderLayout.SOUTH);
      
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.anchor = GridBagConstraints.CENTER;
      bookingPanel.add(bookingScreenPanel, gbc);

      mainViewPanel.add(bookingPanel, BorderLayout.CENTER);
      mainViewPanel.add(bookingPanel, "bookingPanel");

      
   }
   
   // Listener for Movie Buttons
   public class movieButtonListener implements ActionListener {

      public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (bookingPanel == null) {
               bookingUI();
            }
            if (command.equals("out")) {
               mainFrame.dispose();
               loginUI();
            } else {
               ticketsField.setText("0");
               totalPayment.setText("0");
               int movieIndex = Integer.parseInt(command);
               Movie selectedMovie = movies.get(movieIndex);

               // Update labels with selected movie details
               movieTitleLabel.setText(selectedMovie.getTitle());
               moviePriceLabel.setText(String.valueOf(selectedMovie.getPrice()));

               // Initialize booking panel if necessary

               // Show booking panel
               cardLayout.show(mainViewPanel, "bookingPanel");
            }
      }
   }
  
   // Listener for Booking Panel
   public class bookingButtonListener implements ActionListener{
   
      public void actionPerformed(ActionEvent e){
         
         String command = e.getActionCommand();
         int num = Integer.parseInt(ticketsField.getText());
         
         switch(command){
            
            case "dec":
               if(num == 0){
                  num = 0;
               }else{
                  num -= 1;   
               }    
               break;
            case "add":
               num += 1;
               break;
         }

         if(command.equals("back")){
            cardLayout.previous(mainViewPanel);
         }else if(command.equals("next")){
            System.out.println("Next");
         }
         
         ticketsField.setText(String.valueOf(num));
         
         int moviePrice = Integer.parseInt(moviePriceLabel.getText());
         totalAmountToPay = Integer.parseInt(totalPayment.getText());
         
         totalAmountToPay = moviePrice * num;
         
         totalPayment.setText(String.valueOf(totalAmountToPay));
      }
   
   }
   
   // Listener for loginUI
   public class loginButtonListener implements ActionListener{
   
      public void actionPerformed(ActionEvent e){
         
         String user = userField.getText();
         String pass = new String(((JPasswordField) passField).getPassword());
         
         if(user.equals(ADMIN_USERNAME) && pass.equals(ADMIN_PASSWORD)){
            loginFrame.setVisible(false);
            loginFrame.dispose();
            mainUI();
         }else{
            errorLabel.setText("Invalid username or password");
            errorLabel.setVisible(true);
            userField.setText("");
            passField.setText("");
         }

         
      }
   
   }

}