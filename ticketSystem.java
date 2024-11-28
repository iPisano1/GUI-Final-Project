// Please send suggestions sa gc
// Day 1 - Naka Set nakog Layout sa home window and booking window nya kuwang nalang og search function para ma completo.
// Day 2 - Encountered an issue inig logout nya log in balik kay dili na mo loading ang movies sa display.

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TicketSystem{
   
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
   
   JLabel movieImageDisplay = new JLabel();
   JLabel movieTitleDisplay = new JLabel("Inside Out");
   JLabel moviePriceDisplay = new JLabel("450");
   JLabel movieGenreDisplay = new JLabel("Children's Film");

   JLabel movieTitleLabel = new JLabel("Invalid");
   JLabel moviePriceLabel = new JLabel("0");
   JLabel movieGenreLabel = new JLabel("None");
   JLabel totalPayment = new JLabel("0");
   
   public static void main(String[] args){
      
      new TicketSystem();
   
   }
   
   // Initialize Movies 
   private void initializeMovies() {
      movies = new ArrayList<>();
      movies.add(new Movie("Inside Out", 450, "image/movie1.jpg", "Children's Film"));
      movies.add(new Movie("Frozen", 320, "image/movie2.jpg", "Children's Film"));
      movies.add(new Movie("How to Train Your Dragon", 250, "image/movie3.jpg", "Children's Film"));
      movies.add(new Movie("Minions", 350, "image/movie4.jpg", "Children's Film"));
      movies.add(new Movie("Kung Fu Panda", 280, "image/movie5.jpg", "Children's Film"));
      movies.add(new Movie("Spider-Man", 250, "image/movie6.jpg", "Superhero"));
      movies.add(new Movie("Hulk", 180, "image/movie7.jpg", "Superhero"));
      movies.add(new Movie("Aquaman", 320, "image/movie8.jpg", "Superhero"));
      movies.add(new Movie("Wonder Woman", 290, "image/movie9.jpg", "Superhero"));
      movies.add(new Movie("Man Of Steel", 230, "image/movie10.jpg", "Superhero"));
      // movies.add(new Movie("Movie 11", 0, "image/movie11.jpg", "none"));
      // movies.add(new Movie("Movie 12", 0, "image/movie12.jpg", "none"));
      // movies.add(new Movie("Movie 13", 0, "image/movie13.jpg", "none"));
      // movies.add(new Movie("Movie 14", 0, "image/movie14.jpg", "none"));
      // movies.add(new Movie("Movie 15", 0, "image/movie15.jpg", "none"));
   }

   // Constructor
   public TicketSystem(){
      
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
      
      mainViewPanel.removeAll();
      mainViewPanel.revalidate();
      mainViewPanel.repaint();

      // GridBagLayout insets for headerPanel
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(5, 5, 5, 5);

      mainFrame = new JFrame("NETFLEX");
      mainFrame.setSize(800, 700);
      mainFrame.setResizable(false);
      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mainFrame.setLocationRelativeTo(null);
      mainFrame.getContentPane().setBackground(Color.black);
      mainFrame.setLayout(new BorderLayout(10, 10));   

      homePanel = new JPanel();
      homePanel.setLayout(new BorderLayout());
      homePanel.setBackground(Color.black);

      // Changed to GridBagLayout
      JPanel headerPanel = new JPanel();
      headerPanel.setLayout(new GridBagLayout());
      headerPanel.setBackground(Color.black);
      headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 45));

      JLabel titleLabel = new JLabel("NETFLEX");
      titleLabel.setFont(new Font("Fira Code", Font.BOLD, 30));
      titleLabel.setForeground(Color.decode("#DB202C"));
      
      JButton logOutButton = new JButton("Log Out");
      logOutButton.setFocusPainted(false);
      logOutButton.setFont(new Font("Fira Code", Font.BOLD, 15));
      logOutButton.setPreferredSize(new Dimension(90, 25));
      logOutButton.setBackground(Color.black);
      logOutButton.setForeground(Color.white);
      logOutButton.setBorder(BorderFactory.createLineBorder(Color.decode("#DB202C")));
      logOutButton.addActionListener(new movieButtonListener());
      logOutButton.setActionCommand("out");

      JTextField searchField = new JTextField(15);
      searchField.setFont(new Font("Fira Code", Font.PLAIN, 15));

      JButton searchButton = new JButton("Search");
      searchButton.setFocusPainted(false);
      searchButton.setBackground(Color.black);
      searchButton.setForeground(Color.white);
      searchButton.setPreferredSize(new Dimension(80, 25));
      
      // Center Panel
      JPanel centerHomePanel = new JPanel();
      centerHomePanel.setLayout(new GridBagLayout());
      centerHomePanel.setBackground(Color.black);

      // DISPLAY PANEL 
      GridBagConstraints gbcDisplay = new GridBagConstraints();
      gbcDisplay.insets = new Insets(10, 10, 10, 10);
      JPanel movieDisplayPanel = new JPanel();
      movieDisplayPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#5c5b5b")));
      movieDisplayPanel.setBackground(Color.black);
      movieDisplayPanel.setLayout(new GridBagLayout());
      
      // MOVIE IMAGE PANEL
      JPanel ImageDisplayPanel = new JPanel();
      ImageDisplayPanel.setBackground(Color.black);
      ImageDisplayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
      ImageIcon movieDisplayIcon = new ImageIcon("image/movie1.jpg");
      Image scaledDisplayImage = movieDisplayIcon.getImage().getScaledInstance(120, 150, Image.SCALE_SMOOTH);
      movieImageDisplay.setIcon(new ImageIcon(scaledDisplayImage));

      // MOVIE TEXT PANEL
      GridBagConstraints gbcTextDisplay = new GridBagConstraints();
      gbcTextDisplay.insets = new Insets(10, 10, 10, 10);
      JPanel textDisplayPanel = new JPanel();
      textDisplayPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
      textDisplayPanel.setBackground(Color.black);
      textDisplayPanel.setForeground(Color.white);
      textDisplayPanel.setLayout(new GridBagLayout());
      
      Font textFont = new Font("Fira Code", Font.PLAIN, 12);
      Font textFont2 = new Font("Fira Code", Font.BOLD, 12);

      JLabel titleText = new JLabel("Title:");
      titleText.setForeground(Color.white);
      titleText.setFont(textFont);
      movieTitleDisplay.setForeground(Color.white);
      movieTitleDisplay.setFont(textFont2);

      JLabel priceText = new JLabel("Price:");
      priceText.setForeground(Color.white);
      priceText.setFont(textFont);
      moviePriceDisplay.setForeground(Color.white);
      moviePriceDisplay.setFont(textFont2);

      JLabel genreText = new JLabel("Genre:");
      genreText.setForeground(Color.white);
      genreText.setFont(textFont);
      movieGenreDisplay.setForeground(Color.white);
      movieGenreDisplay.setFont(textFont2);
      
      JButton selectMovieButton = new JButton("Select");
      selectMovieButton.setFocusPainted(false);
      selectMovieButton.setBackground(Color.black);
      selectMovieButton.setForeground(Color.white);
      selectMovieButton.setActionCommand("select");
      selectMovieButton.addActionListener(new movieButtonListener());

      // MOVIE SELECTION PANEL
      JPanel movieSelectionPanel = new JPanel();
      movieSelectionPanel.setBackground(Color.black);
      movieSelectionPanel.setLayout(new GridLayout(2, 5, 20, 20));
      movieSelectionPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));
      
      // Movie Selection Buttons (Footer)
      // Thank you ani sir!
      for(int i = 0; i < movies.size(); i++){
         Movie movie = movies.get(i);
         JButton movieButton = new JButton();
         movieButton.setFocusPainted(false);
         movieButton.setActionCommand(String.valueOf(i));
         movieButton.addActionListener(new movieButtonListener());
         movieButton.setBackground(Color.decode("#5c5b5b"));
         movieButton.setForeground(Color.white);
         movieButton.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));

         //Exception Handler if image file is missing or invalid
         try{
               ImageIcon movieIcon = new ImageIcon(movie.getImagePath());
               Image scaledImage = movieIcon.getImage().getScaledInstance(130, 160, Image.SCALE_SMOOTH);
               movieButton.setIcon(new ImageIcon(scaledImage));
         }catch (Exception e){
               movieButton.setText(movie.getTitle());
         }

         movieSelectionPanel.add(movieButton);
      }

      // Headers
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.weightx = 1;
      gbc.anchor = GridBagConstraints.WEST;
      headerPanel.add(titleLabel, gbc);
      
      gbc.gridx = 1;
      gbc.weightx = 0;
      gbc.anchor = GridBagConstraints.CENTER;
      headerPanel.add(searchField, gbc);
      
      gbc.gridx = 2;
      headerPanel.add(searchButton, gbc);

      gbc.gridx = 3;
      gbc.weightx = 1;
      gbc.anchor = GridBagConstraints.EAST;
      headerPanel.add(logOutButton, gbc);
      
      // Center Image Display 
      gbcDisplay.gridx = 0;
      gbcDisplay.gridy = 0;
      gbcDisplay.gridheight = 3;
      gbcDisplay.gridwidth = 0;
      gbcDisplay.weighty = 1;
      gbcDisplay.anchor = GridBagConstraints.CENTER;
      ImageDisplayPanel.add(movieImageDisplay, gbcDisplay);

      // Center Text Display
      gbcTextDisplay.gridx = 0;
      gbcTextDisplay.gridy = 0;
      gbcTextDisplay.gridheight = 1;
      gbcTextDisplay.anchor = GridBagConstraints.EAST;
      textDisplayPanel.add(titleText, gbcTextDisplay);

      gbcTextDisplay.gridx = 0;
      gbcTextDisplay.gridy = 1;
      textDisplayPanel.add(priceText, gbcTextDisplay);

      gbcTextDisplay.gridx = 0;
      gbcTextDisplay.gridy = 2;
      textDisplayPanel.add(genreText, gbcTextDisplay);

      gbcTextDisplay.gridx = 1;
      gbcTextDisplay.gridy = 0;
      gbcTextDisplay.anchor = GridBagConstraints.CENTER;
      textDisplayPanel.add(movieTitleDisplay, gbcTextDisplay);

      gbcTextDisplay.gridx = 1;
      gbcTextDisplay.gridy = 1;
      textDisplayPanel.add(moviePriceDisplay, gbcTextDisplay);

      gbcTextDisplay.gridx = 1;
      gbcTextDisplay.gridy = 2;
      textDisplayPanel.add(movieGenreDisplay, gbcTextDisplay);
      
      gbcTextDisplay.gridx = 0;
      gbcTextDisplay.gridy = 3;
      gbcTextDisplay.gridwidth = 2;
      textDisplayPanel.add(selectMovieButton, gbcTextDisplay);
      
      movieDisplayPanel.add(ImageDisplayPanel);
      movieDisplayPanel.add(textDisplayPanel);

      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.anchor = GridBagConstraints.CENTER;
      centerHomePanel.add(movieDisplayPanel, gbc);

      homePanel.add(headerPanel, BorderLayout.NORTH);
      homePanel.add(centerHomePanel, BorderLayout.CENTER);
      homePanel.add(movieSelectionPanel, BorderLayout.SOUTH);

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
      
      JLabel titleLabel = new JLabel("Movie:");
      JLabel priceLabel = new JLabel("Price:");
      
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

      mainViewPanel.add(bookingPanel, "bookingPanel");

   }
   
   // Listener for Movie Buttons
   public class movieButtonListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         String command = e.getActionCommand();
  
         try{
            if(command.equals("out")){
               mainFrame.dispose();
               initializeMovies();
               loginUI();
            }else if(command.equals("select")){

               bookingUI();

               ticketsField.setText("0");
               totalPayment.setText("0");

               movieTitleLabel.setText(movieTitleDisplay.getText());
               moviePriceLabel.setText(moviePriceDisplay.getText());

               cardLayout.show(mainViewPanel, "bookingPanel");
               
            }else{

               int movieIndex = Integer.parseInt(command);
               Movie selectedMovie = movies.get(movieIndex);

               ImageIcon movieDisplayIcon = new ImageIcon(selectedMovie.getImagePath());
               Image scaledDisplayImage = movieDisplayIcon.getImage().getScaledInstance(120, 150, Image.SCALE_SMOOTH);
               movieImageDisplay.setIcon(new ImageIcon(scaledDisplayImage));

               movieTitleDisplay.setText(selectedMovie.getTitle());
               moviePriceDisplay.setText(String.valueOf(selectedMovie.getPrice()));
               movieGenreDisplay.setText(selectedMovie.getGenre());
            }
         }catch(NumberFormatException ex){
            System.err.println("Invalid command: " + command);
         }
      }
   }
  
   // Listener for Booking Panel
   public class bookingButtonListener implements ActionListener{
   
      public void actionPerformed(ActionEvent e){
         
         String command = e.getActionCommand();
         int numTickets = Integer.parseInt(ticketsField.getText());
         int moviePrice = Integer.parseInt(moviePriceLabel.getText());
         
         switch(command){
            
            case "dec":
               if(numTickets == 0){
                  numTickets = 0;
               }else{
                  numTickets -= 1;   
               }    
               break;

            case "add":
            numTickets += 1;
               break;

            case "back":
               cardLayout.show(mainViewPanel, "homePanel");
               break;
               
            case "next":
               System.out.println("Next");
               break;

         }
         
         ticketsField.setText(String.valueOf(numTickets));
         totalAmountToPay = moviePrice * numTickets;
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
            initializeMovies();
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