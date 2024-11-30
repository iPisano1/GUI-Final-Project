// Please send suggestions sa gc
// Day 1 - Naka Set nakog Layout sa home window and booking window nya kuwang nalang og search function para ma completo.
// Day 2 - Encountered an issue inig logout nya log in balik kay dili na mo loading ang movies sa display.
// Day 3 - Added a Search Function and Scroll Bar para sa movie catalog. Still trying na mag encapsulation para less hassle mag add og remove sa codes. 

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
   JPanel homePanel, bookingPanel, seatSelectionPanel;
   JTextField userField;
   JPasswordField passField;
   JLabel errorLabel;
   JTextField ticketsField;
   JTextField searchField;

   public int totalAmountToPay;
   
   JLabel movieImageDisplay = new JLabel();
   JLabel movieTitleDisplay = new JLabel("Inside Out");
   JLabel moviePriceDisplay = new JLabel("450");
   JLabel movieGenreDisplay = new JLabel("Children's Film");

   JLabel ticketImageDisplay = new JLabel();
   JLabel movieTitleLabel = new JLabel("Invalid");
   JLabel moviePriceLabel = new JLabel("0");
   JLabel movieGenreLabel = new JLabel("None");
   JLabel totalPayment = new JLabel("0");
   
   JLabel selectionMovieDisplay = new JLabel();
   JLabel ticketQuantity = new JLabel();
   JLabel selectionErrorLabel = new JLabel();

   public static void main(String[] args){
      
      new ticketSystem();
   
   }
   
   // Initialize Movies 
   private void initializeMovies() {
      movies = new ArrayList<>();
      movies.add(new Movie("Inside Out", 450, "image/movie1.jpg", "Children's Film"));
      movies.add(new Movie("Frozen", 320, "image/movie2.jpg", "Children's Film"));
      movies.add(new Movie("How to Train Your Dragon", 250, "image/movie3.jpg", "Children's Film"));
      movies.add(new Movie("Minions", 350, "image/movie4.jpg", "Children's Film"));
      movies.add(new Movie("Kung Fu Panda", 280, "image/movie5.jpg", "Children's Film"));
      movies.add(new Movie("Spider Man", 250, "image/movie6.jpg", "Superhero"));
      movies.add(new Movie("Hulk", 180, "image/movie7.jpg", "Superhero"));
      movies.add(new Movie("Aquaman", 320, "image/movie8.jpg", "Superhero"));
      movies.add(new Movie("Wonder Woman", 290, "image/movie9.jpg", "Superhero"));
      movies.add(new Movie("Man Of Steel", 230, "image/movie10.jpg", "Superhero"));
      movies.add(new Movie("Top Gun Maverick" , 280,"image/movie11.jpg", "Action"));
      movies.add(new Movie("John Wick", 380, "image/movie12.jpg", "Action"));
      movies.add(new Movie("Fast and Furious", 230, "image/movie13.jpg", "Action"));
      movies.add(new Movie("Baby Driver", 250, "image/movie14.jpg", "Action"));
      movies.add(new Movie("Extraction", 320, "image/movie15.jpg", "Action"));
      movies.add(new Movie("The Conjuring", 280, "image/movie16.jpg", "Horror"));
      movies.add(new Movie("Ready Or Not", 320, "image/movie17.jpg", "Horror"));
      movies.add(new Movie("Annabelle", 320, "image/movie18.jpg", "Horror"));
      movies.add(new Movie("UnFriended", 220, "image/movie19.jpg", "Horror"));
      movies.add(new Movie("The Ring", 280, "image/movie20.jpg", "Horror"));
   }

   // Constructor
   public ticketSystem(){
      
      initializeMovies();
      loginUI();
      
   }
   
   // Login UI Frame
   public void loginUI(){
      
      loginFrame = new JFrame("Login");
      loginFrame.setIconImage(new ImageIcon("image/logo.png").getImage());
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
      
      // Set Default Movie to Display
      movieTitleDisplay.setText("Inside Out");
      moviePriceDisplay.setText("450");
      movieGenreDisplay.setText("Children's Film");

      // GridBagLayout insets for headerPanel
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(5, 5, 5, 5);

      mainFrame = new JFrame("NETFLEX");
      mainFrame.setIconImage(new ImageIcon("image/logo.png").getImage());
      mainFrame.setSize(1000, 900);
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
      headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 25));

      JLabel titleLabel = new JLabel("NETFLEX");
      titleLabel.setFont(new Font("Fira Code", Font.BOLD, 35));
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

      // Search UI
      searchField = new JTextField(25);
      searchField.setBorder(BorderFactory.createLineBorder(Color.white));
      searchField.setPreferredSize(new Dimension(80, 25));
      searchField.setFont(new Font("Fira Code", Font.PLAIN, 15));

      JButton searchButton = new JButton("Search");
      searchButton.setFocusPainted(false);
      searchButton.setPreferredSize(new Dimension(80, 28));
      searchButton.setBackground(Color.black);
      searchButton.setForeground(Color.white);
      searchButton.setActionCommand("search");
      searchButton.addActionListener(new movieButtonListener());
      mainFrame.getRootPane().setDefaultButton(searchButton);
      
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
      Image scaledDisplayImage = movieDisplayIcon.getImage().getScaledInstance(170, 200, Image.SCALE_SMOOTH);
      movieImageDisplay.setIcon(new ImageIcon(scaledDisplayImage));

      // MOVIE TEXT PANEL
      GridBagConstraints gbcTextDisplay = new GridBagConstraints();
      gbcTextDisplay.insets = new Insets(10, 10, 10, 10);
      // DIPLAY TEXT PANEL
      JPanel textDisplayPanel = new JPanel();
      textDisplayPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
      textDisplayPanel.setBackground(Color.black);
      textDisplayPanel.setForeground(Color.white);
      textDisplayPanel.setLayout(new GridBagLayout());
      
      // DISPLAY BOX TEXT
      Font textFont = new Font("Fira Code", Font.PLAIN, 15);
      Font textFont2 = new Font("Fira Code", Font.BOLD, 15);

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
      selectMovieButton.setPreferredSize(new Dimension(100, 40));
      selectMovieButton.setBackground(Color.black);
      selectMovieButton.setForeground(Color.white);
      selectMovieButton.setActionCommand("select");
      selectMovieButton.addActionListener(new movieButtonListener());

      // MOVIE SELECTION PANEL
      JPanel movieSelectionPanel = new JPanel();
      movieSelectionPanel.setBackground(Color.black);
      movieSelectionPanel.setLayout(new GridLayout(0, 5, 20, 20));
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
               Image scaledImage = movieIcon.getImage().getScaledInstance(170, 200, Image.SCALE_SMOOTH);
               movieButton.setIcon(new ImageIcon(scaledImage));
         }catch (Exception e){
               movieButton.setText(movie.getTitle());
         }

         movieSelectionPanel.add(movieButton);
      }
      
      JScrollPane scrollPane = new JScrollPane(movieSelectionPanel);
      scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
      scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
      scrollPane.getVerticalScrollBar().setUnitIncrement(15);
      scrollPane.getViewport().setPreferredSize(new Dimension(600, 400));

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
      homePanel.add(scrollPane, BorderLayout.SOUTH);

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
      
      // DETAILS PANEL
      JPanel buyerPanel = new JPanel();
      buyerPanel.setLayout(new GridBagLayout());
      buyerPanel.setBorder(BorderFactory.createTitledBorder(etch, "Enter Details"));
      
      Font detailFont = new Font("Arial", Font.BOLD, 15);
      // Dimension screenSize = new Dimension(20, 20);

      JLabel nameLabel = new JLabel("Full Name: ");
      nameLabel.setFont(detailFont);
      JTextField nameField = new JTextField(32);
      nameField.setBorder(BorderFactory.createLineBorder(Color.black));

      JLabel emailLabel = new JLabel("Email Address: ");
      emailLabel.setFont(detailFont);
      JTextField emailField = new JTextField(32);
      emailField.setBorder(BorderFactory.createLineBorder(Color.black));
      
      JLabel phoneLabel = new JLabel("Phone No: ");
      phoneLabel.setFont(detailFont);
      JTextField phoneField = new JTextField(32);
      phoneField.setBorder(BorderFactory.createLineBorder(Color.black));
      
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
      
      // TICKETS PANEL
      JPanel ticketPanel = new JPanel();
      ticketPanel.setLayout(new GridBagLayout());
      ticketPanel.setBorder(BorderFactory.createTitledBorder(etch, "Buy Ticket"));
      
      // Ticket Image Panel
      JPanel ticketImagePanel = new JPanel();
      ticketImagePanel.setLayout(new GridBagLayout());

      ticketImageDisplay = new JLabel();
      // ImageIcon movieDisplayIcon = new ImageIcon("image/movie1.jpg");
      // Image scaledDisplayImage = movieDisplayIcon.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
      // ticketImageDisplay.setIcon(new ImageIcon(scaledDisplayImage));
      ticketImageDisplay.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

      // Ticket Text Panel
      JPanel ticketTextPanel = new JPanel();
      ticketTextPanel.setLayout(new GridBagLayout());
      ticketTextPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 20));

      Font ticketLabelFont = new Font("Arial", Font.BOLD,13);
      Font ticketDisplayFont = new Font("Arial", Font.BOLD,13);

      JLabel titleLabel = new JLabel("Movie:");
      titleLabel.setFont(ticketLabelFont);
      movieTitleLabel.setFont(ticketDisplayFont);

      JLabel priceLabel = new JLabel("Price:");
      priceLabel.setFont(ticketLabelFont);
      moviePriceDisplay.setFont(ticketDisplayFont);
      
      JLabel ticketsLabel = new JLabel("Tickets:");
      ticketsLabel.setFont(ticketLabelFont);
      
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
      totalAmountLabel.setFont(ticketLabelFont);
      totalPayment.setFont(ticketDisplayFont);
      
      gbc.gridx = 0;
      gbc.gridy = 0;
      ticketTextPanel.add(titleLabel, gbc);
      
      gbc.gridx = 1;
      ticketTextPanel.add(movieTitleLabel, gbc);
      
      gbc.gridx = 0;
      gbc.gridy = 1;
      ticketTextPanel.add(priceLabel, gbc);
      
      gbc.gridx = 1;
      ticketTextPanel.add(moviePriceLabel, gbc);
      
      gbc.gridx = 0;
      gbc.gridy = 2;
      ticketTextPanel.add(ticketsLabel, gbc);
      
      gbc.gridx = 1;
      ticketTextPanel.add(ticketsField, gbc);
      
      gbc.gridx = 2;
      ticketTextPanel.add(decButton, gbc);
      
      gbc.gridx = 3;
      ticketTextPanel.add(addButton, gbc);
      
      gbc.gridx = 0;
      gbc.gridy = 3;
      ticketTextPanel.add(totalAmountLabel, gbc);
      
      gbc.gridx = 1;
      ticketTextPanel.add(totalPayment, gbc);
      
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.gridheight = 3;
      ticketImagePanel.add(ticketImageDisplay, gbc);

      ticketPanel.add(ticketImagePanel);
      ticketPanel.add(ticketTextPanel);

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

   public void seatSelectionUI(){

      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(5, 5, 5, 5);

      seatSelectionPanel = new JPanel();
      seatSelectionPanel.setBackground(Color.black);
      seatSelectionPanel.setLayout(new BorderLayout()); 

      Font headerFont = new Font("Fira Code", Font.PLAIN, 23);
      Font headerDisplayFont = new Font("Fira Code", Font.BOLD, 23);

      // HEADER CONTAINS - MOVIE NAME - TICKETS QUANTITY -
      JPanel selectionHeader = new JPanel();
      selectionHeader.setLayout(new GridBagLayout());
      selectionHeader.setBackground(Color.black);
      selectionHeader.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 25));
      
      // Movie Title Display
      JLabel selectionMovieLabel = new JLabel("Movie:");
      selectionMovieLabel.setFont(headerFont);
      selectionMovieLabel.setForeground(Color.white);

      selectionMovieDisplay = new JLabel("Movie 1");
      selectionMovieDisplay.setFont(headerDisplayFont);
      selectionMovieDisplay.setForeground(Color.white);

      // Error Display 
      selectionErrorLabel.setFont(headerDisplayFont);
      selectionErrorLabel.setForeground(Color.red);

      // Ticket Quantity Display
      JLabel ticketQuantityLabel = new JLabel("Tickets:");
      ticketQuantityLabel.setFont(headerFont);
      ticketQuantityLabel.setForeground(Color.white);

      ticketQuantity = new JLabel("0");
      ticketQuantity.setFont(headerDisplayFont);
      ticketQuantity.setForeground(Color.white);

      // CENTER VIEW CONTAINS - SCREEN - SEAT NUMBERS
      JPanel seatSelectionView = new JPanel();
      seatSelectionView.setBackground(Color.black);
      seatSelectionView.setLayout(new BorderLayout());
      
      // FOOTER CONTAINS BUTTONS - BACK - NEXT
      JPanel selectionFooter = new JPanel();
      selectionFooter.setBackground(Color.black);
      selectionFooter.setLayout(new BorderLayout());
      selectionFooter.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));

      Dimension footerButtonDimension = new Dimension(120, 40);

      JButton selectionBackButton = new JButton("Back");
      selectionBackButton.setFocusPainted(false);
      selectionBackButton.setPreferredSize(footerButtonDimension);
      selectionBackButton.setBackground(Color.black);
      selectionBackButton.setForeground(Color.white);
      selectionBackButton.setActionCommand("back");
      selectionBackButton.addActionListener(new seatSelectionListener());

      JButton selectionNextButton = new JButton("Next");
      selectionNextButton.setFocusPainted(false);
      selectionNextButton.setPreferredSize(footerButtonDimension);
      selectionNextButton.setBackground(Color.black);
      selectionNextButton.setForeground(Color.white);
      selectionNextButton.setActionCommand("next");
      selectionNextButton.addActionListener(new seatSelectionListener());

      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.weightx = 0;
      gbc.anchor = GridBagConstraints.WEST;
      selectionHeader.add(selectionMovieLabel, gbc);

      gbc.gridx = 1;
      gbc.gridy = 0;
      gbc.anchor = GridBagConstraints.WEST;
      selectionHeader.add(selectionMovieDisplay, gbc);

      gbc.gridx = 1;
      gbc.gridy = 0;
      gbc.weightx = 1;
      gbc.anchor = GridBagConstraints.CENTER;
      selectionHeader.add(selectionErrorLabel, gbc);

      gbc.gridx = 2;
      gbc.gridy = 0;
      gbc.weightx = 0;
      gbc.anchor = GridBagConstraints.EAST;
      selectionHeader.add(ticketQuantityLabel, gbc);

      gbc.gridx = 3;
      gbc.anchor = GridBagConstraints.EAST;
      selectionHeader.add(ticketQuantity, gbc);

      selectionFooter.add(selectionBackButton, BorderLayout.WEST);
      selectionFooter.add(selectionNextButton, BorderLayout.EAST);
      
      seatSelectionPanel.add(selectionHeader, BorderLayout.NORTH);
      seatSelectionPanel.add(seatSelectionView, BorderLayout.CENTER);
      seatSelectionView.add(selectionFooter, BorderLayout.SOUTH);

      mainViewPanel.add(seatSelectionPanel, "seatSelectionPanel");
   }
   
   // Listener for Home Panel Buttons
   public class movieButtonListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         String command = e.getActionCommand();
         
         try{
            switch(command){

               case "out":
                  mainFrame.dispose();
                  initializeMovies();
                  loginUI();
                  break;

               case "select":
                  bookingUI();

                  ticketsField.setText("0");
                  totalPayment.setText("0");                 

                  String title = movieTitleDisplay.getText();

                  for(int i = 0; i < movies.size(); i++){
                     
                     Movie movie = movies.get(i);
                     if(title.equalsIgnoreCase(movie.getTitle())){
                        ImageIcon movieDisplayIcon2 = new ImageIcon(movie.getImagePath());
                        Image scaledDisplayImage2 = movieDisplayIcon2.getImage().getScaledInstance(170, 200, Image.SCALE_SMOOTH);
                        ticketImageDisplay.setIcon(new ImageIcon(scaledDisplayImage2));

                        movieTitleLabel.setText(movieTitleDisplay.getText());
                        moviePriceLabel.setText(moviePriceDisplay.getText());
                     }
                  }
                  
                  cardLayout.show(mainViewPanel, "bookingPanel");
                  break;

               case "search":
                  String searchedMovie = searchField.getText();

                  for (int i = 0; i < movies.size(); i++) {

                     Movie movie = movies.get(i);
                     if (searchedMovie.equalsIgnoreCase(movie.getTitle())) {

                        searchField.setBorder(BorderFactory.createLineBorder(Color.white));
                        ImageIcon movieDisplayIcon = new ImageIcon(movie.getImagePath());
                        Image scaledDisplayImage = movieDisplayIcon.getImage().getScaledInstance(170, 200, Image.SCALE_SMOOTH);
                        movieImageDisplay.setIcon(new ImageIcon(scaledDisplayImage));

                        movieTitleDisplay.setText(movie.getTitle());
                        moviePriceDisplay.setText(String.valueOf(movie.getPrice()));
                        movieGenreDisplay.setText(movie.getGenre());

                     }
                  }
                  break;

               default:
                  Movie clickedMovie = movies.get(Integer.parseInt(command));

                  ImageIcon movieDisplayIcon = new ImageIcon(clickedMovie.getImagePath());
                  Image scaledDisplayImage = movieDisplayIcon.getImage().getScaledInstance(170, 200, Image.SCALE_SMOOTH);
                  movieImageDisplay.setIcon(new ImageIcon(scaledDisplayImage));
                  
                  movieTitleDisplay.setText(clickedMovie.getTitle());
                  moviePriceDisplay.setText(String.valueOf(clickedMovie.getPrice()));
                  movieGenreDisplay.setText(clickedMovie.getGenre());

            }
         }catch(NumberFormatException ex){
               System.err.println("Invalid command: " + command);
               return;
         }

      }

   }
  
   // Listener for Booking Panel Buttons
   public class bookingButtonListener implements ActionListener{
   
      public void actionPerformed(ActionEvent e){
         
         String command = e.getActionCommand();
         int numTickets = 0;
         int moviePrice = 0;

         try{

            numTickets = Integer.parseInt(ticketsField.getText());
            moviePrice = Integer.parseInt(moviePriceLabel.getText());

            switch(command){
               
               case "dec":
                  numTickets = Math.max(0, numTickets - 1);
                  numTickets = Math.min(20, numTickets);  
                  break;

               case "add":
                  ticketsField.setBorder(BorderFactory.createLineBorder(Color.gray));
                  numTickets = Math.min(20, numTickets + 1);
                  numTickets = Math.max(0, numTickets);
                  break;

               case "back":
                  cardLayout.show(mainViewPanel, "homePanel");
                  break;
                  
               case "next":
                  
                  if(Integer.parseInt(ticketsField.getText()) == 0){
                     ticketsField.setBorder(BorderFactory.createLineBorder(Color.red));
                  }else{
                     seatSelectionUI();
                     selectionMovieDisplay.setText(movieTitleDisplay.getText());
                     ticketQuantity.setText(String.valueOf(numTickets));
                     ticketsField.setBorder(BorderFactory.createLineBorder(Color.gray));
                     cardLayout.show(mainViewPanel, "seatSelectionPanel");
                  }
                  break;

            }
         }catch(NumberFormatException nfe) {
            numTickets = 0;
         }
         
         ticketsField.setText(String.valueOf(numTickets));
         totalAmountToPay = moviePrice * numTickets;
         totalPayment.setText(String.valueOf(totalAmountToPay));
      }
   
   }
   
   // Listenere for Seat Selection Panel 
   public class seatSelectionListener implements ActionListener{
   
      public void actionPerformed(ActionEvent e){

         String command = e.getActionCommand();

         switch(command){

            case "back":

               cardLayout.show(mainViewPanel, "bookingPanel");
               break;

            case "next":

               System.out.println("Next");
               break;

         }

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