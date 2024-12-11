// Please send suggestions sa gc
// Day 1 - Naka Set nakog Layout sa home window and booking window nya kuwang nalang og search function para ma completo.
// Day 2 - Encountered an issue inig logout nya log in balik kay dili na mo loading ang movies sa display.
// Day 3 - Added Search Function and Scroll Bar para sa movie catalog. Still trying na mag encapsulation para less hassle mag add og remove sa codes. 
// Day 4 - Okay na ang SelectionUI and still working pa sa PaymentUI. Murag lisod na i encapsulate kay taas nakayng code. Ako nalang tiwason akong nasugdan pero tryan gihapon nako.

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class ticketSystem{
   
   // ADMIN ACCOUNT ACCESS
   private static final String ADMIN_USERNAME = "admin";
   private static final String ADMIN_PASSWORD = "admin";
   
   // ENCAPSULATION
   private ArrayList<Movie> movies;
   private Set<String> selectedSeats = new HashSet<>();
   
   CardLayout cardLayout = new CardLayout();
   CardLayout paymentCardLayout = new CardLayout();
   JFrame loginFrame, mainFrame;
   JPanel loginPanel;
   JPanel mainViewPanel = new JPanel(cardLayout);;
   JPanel homePanel, bookingPanel, seatSelectionPanel;
   JTextField userField;
   JPasswordField passField;
   JLabel errorLabel;
   JTextField ticketsField, searchField, cashAmountField, nameField, emailField, phoneField;

   public int totalAmountToPay;
   
   // Home UI Display
   JLabel movieImageDisplay = new JLabel();
   JLabel movieTitleDisplay = new JLabel("Inside Out");
   JLabel moviePriceDisplay = new JLabel("450");
   JLabel movieGenreDisplay = new JLabel("Children's Film");

   // Booking UI Display
   JLabel ticketImageDisplay = new JLabel();
   JLabel movieTitleLabel = new JLabel("Invalid");
   JLabel moviePriceLabel = new JLabel("0");
   JLabel movieGenreLabel = new JLabel("None");
   JLabel totalPayment = new JLabel("0");
   JLabel bookingErrorLabel = new JLabel();
   
   // Selection UI Display
   JLabel selectionMovieDisplay = new JLabel();
   JLabel ticketQuantity = new JLabel();
   JLabel selectionErrorLabel = new JLabel();
   JButton seatButton;

   // Payment UI Display
   JPanel paymentMethodPanel = new JPanel();
   JPanel paymentCashMethodPanel = new JPanel();
   JPanel paymenConfirmationPanel = new JPanel();
   JPanel paymentMethodPanel_HEADER = new JPanel();
   JPanel paymentMethodPanel_CENTER = new JPanel();
   JPanel paymentMethodPanel_FOOTER = new JPanel();
   JLabel paymentTotalAmountDisplay = new JLabel();

   // Confirmation UI Display
   JLabel confirmPaymentDisplay;
   JLabel confirmNameDisplay;
   JLabel confirmTicketsDisplay;
   JLabel confirmCashDisplay;
   JLabel confirmTotalAmountDisplay;
   JLabel confirmChangeDisplay;
   JLabel confirmSeatsDisplay;

   JButton paymentBackButton = new JButton("Back");
   JButton paymentNextButton = new JButton("Next");
   JButton confirmButton = new JButton("Confirm");

   public static void main(String[] args){
      
      new ticketSystem();
   
   }
   
   // Initialize Movies 
   private void initializeMovies() {
      movies = new ArrayList<>();
      movies.add(new Movie(1 ,"Inside Out", 450, "image/movie1.jpg", "Children's Film"));
      movies.add(new Movie(2 ,"Frozen", 320, "image/movie2.jpg", "Children's Film"));
      movies.add(new Movie(3 ,"How to Train Your Dragon", 250, "image/movie3.jpg", "Children's Film"));
      movies.add(new Movie(4 ,"Minions", 350, "image/movie4.jpg", "Children's Film"));
      movies.add(new Movie(5 ,"Kung Fu Panda", 280, "image/movie5.jpg", "Children's Film"));
      movies.add(new Movie(6 ,"Spider Man", 250, "image/movie6.jpg", "Superhero"));
      movies.add(new Movie(7 ,"Hulk", 180, "image/movie7.jpg", "Superhero"));
      movies.add(new Movie(8 ,"Aquaman", 320, "image/movie8.jpg", "Superhero"));
      movies.add(new Movie(9 ,"Wonder Woman", 290, "image/movie9.jpg", "Superhero"));
      movies.add(new Movie(10 ,"Man Of Steel", 230, "image/movie10.jpg", "Superhero"));
      movies.add(new Movie(11 ,"Top Gun Maverick" , 280,"image/movie11.jpg", "Action"));
      movies.add(new Movie(12 ,"John Wick", 380, "image/movie12.jpg", "Action"));
      movies.add(new Movie(13 ,"Fast and Furious", 230, "image/movie13.jpg", "Action"));
      movies.add(new Movie(14 ,"Baby Driver", 250, "image/movie14.jpg", "Action"));
      movies.add(new Movie(15 ,"Extraction", 320, "image/movie15.jpg", "Action"));
      movies.add(new Movie(16 ,"The Conjuring", 280, "image/movie16.jpg", "Horror"));
      movies.add(new Movie(17 ,"Ready Or Not", 320, "image/movie17.jpg", "Horror"));
      movies.add(new Movie(18 ,"Annabelle", 320, "image/movie18.jpg", "Horror"));
      movies.add(new Movie(19 ,"UnFriended", 220, "image/movie19.jpg", "Horror"));
      movies.add(new Movie(20 ,"The Ring", 280, "image/movie20.jpg", "Horror"));
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
      headerPanel.setBorder(BorderFactory.createCompoundBorder(
         BorderFactory.createMatteBorder(0, 0, 1, 0 , Color.gray),
         BorderFactory.createEmptyBorder(15, 20, 20, 25)
      ));

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
      searchField.setPreferredSize(new Dimension(80, 30));
      searchField.setFont(new Font("Fira Code", Font.PLAIN, 15));
      searchField.setBorder(BorderFactory.createCompoundBorder(
         BorderFactory.createLineBorder(Color.gray),
         BorderFactory.createEmptyBorder(5, 5, 5, 5)
      ));

      JButton searchButton = new JButton("Search");
      searchButton.setFocusPainted(false);
      searchButton.setPreferredSize(new Dimension(80, 32));
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
      ImageDisplayPanel.setBorder(BorderFactory.createCompoundBorder(
         BorderFactory.createEmptyBorder(20, 20, 20, 0),
         BorderFactory.createLineBorder(Color.gray)
      ));
      ImageIcon movieDisplayIcon = new ImageIcon("image/movie1.jpg");
      Image scaledDisplayImage = movieDisplayIcon.getImage().getScaledInstance(170, 200, Image.SCALE_SMOOTH);
      movieImageDisplay.setIcon(new ImageIcon(scaledDisplayImage));

      // MOVIE TEXT PANEL
      GridBagConstraints gbcTextDisplay = new GridBagConstraints();
      gbcTextDisplay.insets = new Insets(10, 10, 10, 10);
      // DIPLAY TEXT PANEL
      JPanel textDisplayPanel = new JPanel();
      textDisplayPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 20));
      textDisplayPanel.setBackground(Color.black);
      textDisplayPanel.setForeground(Color.white);
      textDisplayPanel.setLayout(new GridBagLayout());
      
      // DISPLAY BOX TEXT
      Font textFont = new Font("Fira Code", Font.BOLD, 15);
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
      selectMovieButton.setPreferredSize(new Dimension(120, 40));
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
      scrollPane.getViewport().setPreferredSize(new Dimension(600, 450));
      
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
      
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(5, 5, 5, 5);

      bookingPanel = new JPanel();
      bookingPanel.setLayout(new GridBagLayout());
      bookingPanel.setBackground(Color.BLACK);

      JPanel bookingScreenPanel = new JPanel();
      bookingScreenPanel.setBackground(Color.black);
      bookingScreenPanel.setLayout(new BorderLayout(10, 10));
      bookingScreenPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
      
      // DETAILS PANEL
      JPanel buyerPanel = new JPanel();
      buyerPanel.setBackground(Color.black);
      buyerPanel.setLayout(new GridBagLayout());
      Border etch = BorderFactory.createEtchedBorder(Color.black, Color.gray);
      TitledBorder  titledBorder = BorderFactory.createTitledBorder(etch, "Enter Details");
      titledBorder.setTitleColor(Color.white);
      buyerPanel.setBorder(BorderFactory.createCompoundBorder(
         titledBorder,
         BorderFactory.createEmptyBorder(20, 20, 20, 20)
      ));
      
      Font detailFont = new Font("Arial", Font.BOLD, 17);
      // Dimension screenSize = new Dimension(20, 20);

      JLabel nameLabel = new JLabel("Full Name: ");
      nameLabel.setFont(detailFont);
      nameLabel.setForeground(Color.white);
      nameField = new JTextField(32);
      nameField.setBackground(Color.black);
      nameField.setForeground(Color.white);
      nameField.setBorder(BorderFactory.createCompoundBorder(
         BorderFactory.createLineBorder(Color.gray),
         BorderFactory.createEmptyBorder(5, 5, 5, 5)
      ));
      nameField.setFont(detailFont);

      JLabel emailLabel = new JLabel("Email Address: ");
      emailLabel.setFont(detailFont);
      emailLabel.setForeground(Color.white);
      emailField = new JTextField(32);
      emailField.setBackground(Color.black);
      emailField.setForeground(Color.white);
      emailField.setBorder(BorderFactory.createCompoundBorder(
         BorderFactory.createLineBorder(Color.gray),
         BorderFactory.createEmptyBorder(5, 5, 5, 5)
      ));
      emailField.setFont(detailFont);

      JLabel phoneLabel = new JLabel("Phone No: ");
      phoneLabel.setFont(detailFont);
      phoneLabel.setForeground(Color.white);
      phoneField = new JTextField(32);
      phoneField.setBackground(Color.black);
      phoneField.setForeground(Color.white);
      phoneField.setBorder(BorderFactory.createCompoundBorder(
         BorderFactory.createLineBorder(Color.gray),
         BorderFactory.createEmptyBorder(5, 5, 5, 5)
      ));
      phoneField.setFont(detailFont);

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
      ticketPanel.setBackground(Color.black);
      ticketPanel.setLayout(new GridBagLayout());
      Border ticketEtch = BorderFactory.createEtchedBorder(Color.black, Color.gray);
      TitledBorder  ticketTitledBorder = BorderFactory.createTitledBorder(ticketEtch, "Buy Ticket");
      ticketTitledBorder.setTitleColor(Color.white);
      ticketPanel.setBorder(BorderFactory.createCompoundBorder(
         ticketTitledBorder,
         BorderFactory.createEmptyBorder(20, 20, 20, 20)
      ));
      
      // Ticket Image Panel
      JPanel ticketImagePanel = new JPanel();
      ticketImagePanel.setBackground(Color.black);
      ticketImagePanel.setLayout(new GridBagLayout());

      ticketImageDisplay = new JLabel();

      // Ticket Text Panel
      JPanel ticketTextPanel = new JPanel();
      ticketTextPanel.setBackground(Color.black);
      ticketTextPanel.setLayout(new GridBagLayout());
      ticketTextPanel.setBorder(BorderFactory.createCompoundBorder(
         BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray),
         BorderFactory.createEmptyBorder(20, 20, 20, 20)
      ));

      Font ticketLabelFont = new Font("Fira Code", Font.BOLD,17);
      Font ticketDisplayFont = new Font("Fira Code", Font.BOLD,17);

      JLabel titleLabel = new JLabel("Movie:");
      titleLabel.setFont(ticketLabelFont);
      titleLabel.setForeground(Color.white);
      movieTitleLabel.setFont(ticketDisplayFont);
      movieTitleLabel.setForeground(Color.white);

      JLabel priceLabel = new JLabel("Price:");
      priceLabel.setFont(ticketLabelFont);
      priceLabel.setForeground(Color.white);
      moviePriceLabel.setFont(ticketDisplayFont);
      moviePriceLabel.setForeground(Color.white);
      
      JLabel ticketsLabel = new JLabel("Tickets:");
      ticketsLabel.setFont(ticketLabelFont);
      ticketsLabel.setForeground(Color.white);
      
      ticketsField = new JTextField(17);
      ticketsField.setBorder(BorderFactory.createLineBorder(Color.gray));
      ticketsField.setHorizontalAlignment(JTextField.CENTER);
      ticketsField.setBackground(Color.black);
      ticketsField.setForeground(Color.white);
      ticketsField.setFont(new Font("Fira Code", Font.BOLD, 15));
      ticketsField.setText("0");
      
      JButton decButton = new JButton("-");
      decButton.setBackground(Color.black);
      decButton.setForeground(Color.white);
      decButton.setFocusPainted(false);
      decButton.setActionCommand("dec");
      decButton.addActionListener(new bookingButtonListener());
      decButton.setFont(new Font("Arial", Font.PLAIN, 12));
      
      JButton addButton = new JButton("+");
      addButton.setBackground(Color.black);
      addButton.setForeground(Color.white);
      addButton.setFocusPainted(false);
      addButton.setActionCommand("add");
      addButton.addActionListener(new bookingButtonListener());
      addButton.setFont(new Font("Arial", Font.PLAIN, 12));
      
      JLabel totalAmountLabel = new JLabel("Total Payment:");
      totalAmountLabel.setFont(ticketLabelFont);
      totalAmountLabel.setForeground(Color.white);
      totalPayment.setFont(ticketDisplayFont);
      totalPayment.setForeground(Color.white);
      
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

      // BOOKING PANEL FOOTER
      JPanel buttonPanel = new JPanel();
      buttonPanel.setBackground(Color.black);
      buttonPanel.setLayout(new GridBagLayout());

      Dimension buttonPanelDimension = new Dimension(120, 50);
      
      JButton nextButton = new JButton("Next");
      nextButton.setBackground(Color.black);
      nextButton.setForeground(Color.white);
      nextButton.setPreferredSize(buttonPanelDimension);
      nextButton.setFocusPainted(false);
      nextButton.setActionCommand("next");
      nextButton.addActionListener(new bookingButtonListener());

      bookingErrorLabel = new JLabel();
      bookingErrorLabel.setFont(new Font("Fira Code", Font.BOLD, 15));
      bookingErrorLabel.setForeground(Color.red);

      JButton backButton = new JButton("Back");
      backButton.setBackground(Color.black);
      backButton.setForeground(Color.white);
      backButton.setPreferredSize(buttonPanelDimension);
      backButton.setFocusPainted(false);
      backButton.setActionCommand("back");
      backButton.addActionListener(new bookingButtonListener());
      
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.anchor = GridBagConstraints.WEST;
      buttonPanel.add(backButton, gbc);

      gbc.gridx = 1;
      gbc.weightx = 1;
      gbc.anchor = GridBagConstraints.CENTER;
      buttonPanel.add(bookingErrorLabel, gbc);

      gbc.gridx = 2;
      gbc.weightx = 0;
      gbc.anchor = GridBagConstraints.EAST;
      buttonPanel.add(nextButton, gbc);

      bookingScreenPanel.add(buyerPanel, BorderLayout.NORTH);
      bookingScreenPanel.add(ticketPanel, BorderLayout.CENTER);
      bookingScreenPanel.add(buttonPanel, BorderLayout.SOUTH);
      
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.anchor = GridBagConstraints.CENTER;
      bookingPanel.add(bookingScreenPanel, gbc);

      mainViewPanel.add(bookingPanel, "bookingPanel");

   }

   // Seat Selection UI
   public void seatSelectionUI(){

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

      selectionMovieDisplay.setFont(headerDisplayFont);
      selectionMovieDisplay.setForeground(Color.green);

      JLabel openLabel = new JLabel();
      openLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 85));
      openLabel.setFont(headerDisplayFont);
      openLabel.setForeground(Color.red);

      // Ticket Quantity Display
      JLabel ticketQuantityLabel = new JLabel("Tickets:");
      ticketQuantityLabel.setFont(headerFont);
      ticketQuantityLabel.setForeground(Color.white);

      ticketQuantity = new JLabel("0");
      ticketQuantity.setFont(headerDisplayFont);
      ticketQuantity.setForeground(Color.green);

      // CENTER VIEW CONTAINS - SCREEN - SEAT NUMBERS
      JPanel seatSelectionView = new JPanel();
      seatSelectionView.setBackground(Color.black);
      seatSelectionView.setLayout(new GridBagLayout());

      Font selectionFont = new Font("Fira Code", Font.BOLD, 30);

      // Screen Display Label
      JLabel screenDisplayLabel = new JLabel("SCREEN");
      screenDisplayLabel.setFont(selectionFont);
      screenDisplayLabel.setForeground(Color.gray);
      screenDisplayLabel.setBorder(BorderFactory.createCompoundBorder(
         BorderFactory.createLineBorder(Color.gray), 
         BorderFactory.createEmptyBorder(5, 170, 5, 170)
      ));

      GridBagConstraints gbcDisplay = new GridBagConstraints();
      gbcDisplay.insets = new Insets(5, 5, 5, 5);

      gbcDisplay.gridx = 0;
      gbcDisplay.gridy = 0;
      gbcDisplay.gridwidth = 5;
      gbcDisplay.anchor = GridBagConstraints.CENTER;
      seatSelectionView.add(screenDisplayLabel, gbcDisplay);

      // Seat Selection Buttons
      for(int i = 1; i <= 20; i++){

         seatButton = new JButton(String.valueOf(i));
         seatButton.setFocusPainted(false);
         seatButton.addActionListener(new seatSelectionListener());
         seatButton.setActionCommand(String.valueOf(i));
         seatButton.setBackground(Color.black);
         seatButton.setForeground(Color.white);
         seatButton.setFont(selectionFont);
         seatButton.setPreferredSize(new Dimension(120, 70));

         gbcDisplay.gridx = (i - 1) % 5;
         gbcDisplay.gridy = 1 + ((i - 1) / 5);
         gbcDisplay.gridwidth = 1;
         seatSelectionView.add(seatButton, gbcDisplay);
      }
      
      // FOOTER CONTAINS BUTTONS - BACK - NEXT
      JPanel selectionFooter = new JPanel();
      selectionFooter.setBackground(Color.black);
      selectionFooter.setLayout(new GridBagLayout());
      selectionFooter.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));

      Dimension footerButtonDimension = new Dimension(120, 40);

      JButton selectionBackButton = new JButton("Back");
      selectionBackButton.setFocusPainted(false);
      selectionBackButton.setPreferredSize(footerButtonDimension);
      selectionBackButton.setBackground(Color.black);
      selectionBackButton.setForeground(Color.white);
      selectionBackButton.setActionCommand("back");
      selectionBackButton.addActionListener(new seatSelectionListener());

      // Error Display 
      selectionErrorLabel.setFont(headerDisplayFont);
      selectionErrorLabel.setForeground(Color.red);

      JButton selectionNextButton = new JButton("Next");
      selectionNextButton.setFocusPainted(false);
      selectionNextButton.setPreferredSize(footerButtonDimension);
      selectionNextButton.setBackground(Color.black);
      selectionNextButton.setForeground(Color.white);
      selectionNextButton.setActionCommand("next");
      selectionNextButton.addActionListener(new seatSelectionListener());

      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(5, 5, 5, 5);

      // HEADER
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.weightx = 0;
      gbc.anchor = GridBagConstraints.WEST;
      selectionHeader.add(selectionMovieLabel, gbc);

      gbc.gridx = 1;
      gbc.gridy = 0;
      gbc.anchor = GridBagConstraints.WEST;
      selectionHeader.add(selectionMovieDisplay, gbc);

      gbc.gridx = 2;
      gbc.gridy = 0;
      gbc.weightx = 1;
      gbc.anchor = GridBagConstraints.CENTER;
      selectionHeader.add(openLabel, gbc);

      gbc.gridx = 3;
      gbc.gridy = 0;
      gbc.weightx = 0;
      gbc.anchor = GridBagConstraints.EAST;
      selectionHeader.add(ticketQuantityLabel, gbc);

      gbc.gridx = 4;
      gbc.anchor = GridBagConstraints.EAST;
      selectionHeader.add(ticketQuantity, gbc);

      // FOOTER
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.anchor = GridBagConstraints.WEST;
      selectionFooter.add(selectionBackButton, gbc);

      gbc.gridx = 1;
      gbc.gridy = 0;
      gbc.weightx = 1;
      gbc.anchor = GridBagConstraints.CENTER;
      selectionFooter.add(selectionErrorLabel, gbc);

      gbc.gridx = 2;
      gbc.gridy = 0;
      gbc.weightx = 0;
      gbc.anchor = GridBagConstraints.EAST;
      selectionFooter.add(selectionNextButton, gbc);
      
      seatSelectionPanel.add(selectionHeader, BorderLayout.NORTH);
      seatSelectionPanel.add(seatSelectionView, BorderLayout.CENTER);
      seatSelectionPanel.add(selectionFooter, BorderLayout.SOUTH);

      mainViewPanel.add(seatSelectionPanel, "seatSelectionPanel");
   }
   
   // PaymentUI Panel
   public void paymentUI(){

      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(5, 5, 5, 5);

      JPanel paymentPanel = new JPanel();
      paymentPanel.setBackground(Color.black);
      paymentPanel.setLayout(new GridBagLayout());

      JPanel paymentScreenPanel = new JPanel();
      paymentScreenPanel.setBackground(Color.gray);
      paymentScreenPanel.setLayout(new GridBagLayout());
      
      paymentMethodPanel = new JPanel();;
      paymentMethodPanel.setBorder(BorderFactory.createCompoundBorder(
         BorderFactory.createLineBorder(Color.gray),
         BorderFactory.createEmptyBorder(20, 20, 20, 20)   
      ));
      paymentMethodPanel.setLayout(new BorderLayout());

      // HEADER
      paymentMethodPanel_HEADER = new JPanel();
      paymentMethodPanel_HEADER.setLayout(new GridBagLayout());

      JLabel paymentMethodLabel = new JLabel("SELECT PAYMENT METHOD:");
      paymentMethodLabel.setFont(new Font("Fira Code", Font.BOLD, 30));

      Font paymentFont = new Font("Fira Code", Font.BOLD, 18);

      JLabel paymentTotalAmountLabel = new JLabel("Total Amount to Pay:");
      paymentTotalAmountLabel.setFont(paymentFont);

      paymentTotalAmountDisplay = new JLabel();
      paymentTotalAmountDisplay.setFont(paymentFont);

      // CENTER
      paymentMethodPanel_CENTER = new JPanel();
      paymentMethodPanel_CENTER.setLayout(new GridLayout(0, 2, 20, 20));
      paymentMethodPanel_CENTER.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

      Dimension paymentButtonDimension = new Dimension(100, 100); 
      Font methodFont = new Font("Fira Code", Font.BOLD, 20);

      JButton paymentCardButton = new JButton("CARD");
      paymentCardButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.blue));
      paymentCardButton.setFocusPainted(false);
      paymentCardButton.setFont(methodFont);
      paymentCardButton.setPreferredSize(paymentButtonDimension);
      paymentCardButton.setActionCommand("card");
      paymentCardButton.addActionListener(new paymentListener());
      
      JButton paymentCashButton = new JButton("CASH");
      paymentCashButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.green));
      paymentCashButton.setFocusPainted(false);
      paymentCashButton.setFont(methodFont);
      paymentCashButton.setPreferredSize(paymentButtonDimension);
      paymentCashButton.setActionCommand("cash");
      paymentCashButton.addActionListener(new paymentListener());

      // FOOTER
      paymentMethodPanel_FOOTER = new JPanel();
      paymentMethodPanel_FOOTER.setLayout(new GridBagLayout());
      paymentMethodPanel_FOOTER.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));

      paymentBackButton = new JButton("Back");
      paymentBackButton.setFocusPainted(false);
      paymentBackButton.setPreferredSize(new Dimension(80, 40));
      paymentBackButton.setActionCommand("back");
      paymentBackButton.addActionListener(new paymentListener());

      paymentNextButton = new JButton("Next");
      paymentNextButton.setFocusPainted(false);
      paymentNextButton.setPreferredSize(new Dimension(80, 40));
      paymentNextButton.setActionCommand("next");
      paymentNextButton.addActionListener(new paymentListener());

      confirmButton = new JButton("Confirm");
      confirmButton.setPreferredSize(new Dimension(80, 40));
      confirmButton.setActionCommand("confirm");
      confirmButton.addActionListener(new paymentListener());

      // HEADER LAYOUT
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.gridwidth = 2;
      gbc.anchor = GridBagConstraints.CENTER;
      paymentMethodPanel_HEADER.add(paymentMethodLabel, gbc);

      gbc.gridx = 0;
      gbc.gridy = 1;
      gbc.gridwidth = 1;
      gbc.anchor = GridBagConstraints.LINE_END;
      paymentMethodPanel_HEADER.add(paymentTotalAmountLabel, gbc);
      
      gbc.gridx = 1;
      gbc.anchor = GridBagConstraints.LINE_START;
      paymentMethodPanel_HEADER.add(paymentTotalAmountDisplay, gbc);
      
      // CENTER LAYOUT
      paymentMethodPanel_CENTER.add(paymentCardButton);
      paymentMethodPanel_CENTER.add(paymentCashButton);

      // FOOTER LAYOUT
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.anchor = GridBagConstraints.WEST;
      paymentMethodPanel_FOOTER.add(paymentBackButton, gbc);
      
      paymentMethodPanel.add(paymentMethodPanel_HEADER, BorderLayout.NORTH);
      paymentMethodPanel.add(paymentMethodPanel_CENTER, BorderLayout.CENTER);
      paymentMethodPanel.add(paymentMethodPanel_FOOTER, BorderLayout.SOUTH);

      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.anchor = GridBagConstraints.CENTER;
      paymentScreenPanel.add(paymentMethodPanel, gbc);

      // CASH METHOD

      paymentCashMethodPanel = new JPanel();
      paymentCashMethodPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
      paymentCashMethodPanel.setLayout(new GridBagLayout());

      Font cashFont = new Font("Fira Code", Font.BOLD, 18);
      Dimension cashDimension = new Dimension(0, 30);

      JLabel cashLabel = new JLabel("Cash:");
      cashLabel.setFont(cashFont);
      cashLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));

      cashAmountField = new JTextField(20);
      cashAmountField.setBorder(BorderFactory.createCompoundBorder(
         BorderFactory.createLineBorder(Color.black),
         BorderFactory.createEmptyBorder(5, 5, 5, 5)
      ));
      cashAmountField.setFont(cashFont);
      cashAmountField.setPreferredSize(cashDimension);

      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.anchor = GridBagConstraints.WEST;
      paymentCashMethodPanel.add(cashLabel);

      gbc.gridx = 1;
      gbc.anchor = GridBagConstraints.EAST;
      paymentCashMethodPanel.add(cashAmountField);

      // END OF CASH METHOD
      
      // PAYMENT CONFIRMATION UI
      
      Font confirmFont = new Font("Fira Code", Font.BOLD, 23);

      paymenConfirmationPanel = new JPanel();
      paymenConfirmationPanel.setLayout(new GridBagLayout());

      JLabel confirmationTitle = new JLabel("CONFIRM PAYMENT");
      confirmationTitle.setFont(confirmFont);
      confirmationTitle.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
      
      JLabel confirmPaymentLabel = new JLabel("Payment Method:");
      confirmPaymentLabel.setFont(confirmFont);
      confirmPaymentDisplay = new JLabel("Invalid");
      confirmPaymentDisplay.setFont(confirmFont);

      JLabel confirmNameLabel = new JLabel("Name:");
      confirmNameLabel.setFont(confirmFont);
      confirmNameDisplay = new JLabel("Invalid");
      confirmNameDisplay.setFont(confirmFont);

      JLabel confirmTicketsLabel = new JLabel("Ticket Quantity:");
      confirmTicketsLabel.setFont(confirmFont);
      confirmTicketsDisplay = new JLabel("Invalid");
      confirmTicketsDisplay.setFont(confirmFont);

      JLabel confirmCashLabel = new JLabel("Cash:");
      confirmCashLabel.setFont(confirmFont);
      confirmCashDisplay = new JLabel("Invalid");
      confirmCashDisplay.setFont(confirmFont);

      JLabel confirmTotalAmountLabel = new JLabel("Total Amount to Pay:");
      confirmTotalAmountLabel.setFont(confirmFont);
      confirmTotalAmountDisplay = new JLabel("Invalid");
      confirmTotalAmountDisplay.setFont(confirmFont);

      JLabel confirmChangeLabel = new JLabel("Change Amount:");
      confirmChangeLabel.setFont(confirmFont);
      confirmChangeDisplay = new JLabel("Invalid");
      confirmChangeDisplay.setFont(confirmFont);

      JLabel confirmSeatsLabel = new JLabel("Seats Allocated:");
      confirmSeatsLabel.setFont(confirmFont);
      confirmSeatsDisplay = new JLabel("Invalid");
      confirmSeatsDisplay.setFont(confirmFont);

      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.gridwidth = 2;
      gbc.anchor = GridBagConstraints.CENTER;
      paymenConfirmationPanel.add(confirmationTitle, gbc);

      gbc.gridx = 0;
      gbc.gridy = 1;
      gbc.gridwidth = 1;
      gbc.anchor = GridBagConstraints.WEST;
      paymenConfirmationPanel.add(confirmPaymentLabel, gbc);
      gbc.gridx = 1;
      gbc.anchor = GridBagConstraints.EAST;
      paymenConfirmationPanel.add(confirmPaymentDisplay, gbc);

      gbc.gridx = 0;
      gbc.gridy = 2;
      gbc.anchor = GridBagConstraints.WEST;
      paymenConfirmationPanel.add(confirmNameLabel, gbc);
      gbc.gridx = 1;
      gbc.anchor = GridBagConstraints.EAST;
      paymenConfirmationPanel.add(confirmNameDisplay, gbc);

      gbc.gridx = 0;
      gbc.gridy = 3;
      gbc.anchor = GridBagConstraints.WEST;
      paymenConfirmationPanel.add(confirmTicketsLabel, gbc);
      gbc.gridx = 1;
      gbc.anchor = GridBagConstraints.EAST;
      paymenConfirmationPanel.add(confirmTicketsDisplay, gbc);

      gbc.gridx = 0;
      gbc.gridy = 4;
      gbc.anchor = GridBagConstraints.WEST;
      paymenConfirmationPanel.add(confirmCashLabel, gbc);
      gbc.gridx = 1;
      gbc.anchor = GridBagConstraints.EAST;
      paymenConfirmationPanel.add(confirmCashDisplay, gbc);

      gbc.gridx = 0;
      gbc.gridy = 5;
      gbc.anchor = GridBagConstraints.WEST;
      paymenConfirmationPanel.add(confirmTotalAmountLabel, gbc);
      gbc.gridx = 1;
      gbc.anchor = GridBagConstraints.EAST;
      paymenConfirmationPanel.add(confirmTotalAmountDisplay, gbc);

      gbc.gridx = 0;
      gbc.gridy = 6;
      gbc.anchor = GridBagConstraints.WEST;
      paymenConfirmationPanel.add(confirmChangeLabel, gbc);
      gbc.gridx = 1;
      gbc.anchor = GridBagConstraints.EAST;
      paymenConfirmationPanel.add(confirmChangeDisplay, gbc);

      gbc.gridx = 0;
      gbc.gridy = 7;
      gbc.anchor = GridBagConstraints.WEST;
      paymenConfirmationPanel.add(confirmSeatsLabel, gbc);
      gbc.gridx = 1;
      gbc.anchor = GridBagConstraints.EAST;
      paymenConfirmationPanel.add(confirmSeatsDisplay, gbc);

      // END OF PAYMENT CONFIRMATION UI

      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.anchor = GridBagConstraints.CENTER;
      paymentPanel.add(paymentScreenPanel, gbc);

      mainViewPanel.add(paymentPanel, "paymentPanel");

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
                     if(title.equals(movie.getTitle())){
                        ImageIcon movieDisplayIcon2 = new ImageIcon(movie.getImagePath());
                        Image scaledDisplayImage2 = movieDisplayIcon2.getImage().getScaledInstance(170, 200, Image.SCALE_SMOOTH);
                        ticketImageDisplay.setBorder(BorderFactory.createCompoundBorder(
                           BorderFactory.createEmptyBorder(20, 20, 20, 20),
                           BorderFactory.createLineBorder(Color.gray)
                        ));
                        ticketImageDisplay.setIcon(new ImageIcon(scaledDisplayImage2));

                        movieTitleLabel.setText(movieTitleDisplay.getText());
                        moviePriceLabel.setText(moviePriceDisplay.getText());
                     }
                  }
                  
                  cardLayout.show(mainViewPanel, "bookingPanel");
                  break;

               case "search":
                  String searchedMovie = searchField.getText();
                  searchField.setBorder(BorderFactory.createCompoundBorder(
                     BorderFactory.createLineBorder(Color.gray),
                     BorderFactory.createEmptyBorder(5, 5, 5, 5)
                  ));

                  boolean movieFound = false;
                  for(int i = 0; i < movies.size(); i++){
                     Movie movie = movies.get(i);
                     if(searchedMovie.equalsIgnoreCase(movie.getTitle())){
                        movieFound = true;
                        ImageIcon movieDisplayIcon = new ImageIcon(movie.getImagePath());
                        Image scaledDisplayImage = movieDisplayIcon.getImage().getScaledInstance(170, 200, Image.SCALE_SMOOTH);
                        movieImageDisplay.setIcon(new ImageIcon(scaledDisplayImage));

                        movieTitleDisplay.setText(movie.getTitle());
                        moviePriceDisplay.setText(String.valueOf(movie.getPrice()));
                        movieGenreDisplay.setText(movie.getGenre());

                     }
                  }
                  if(!movieFound){
                     searchField.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.red),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)
                  ));
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
                  if(numTickets < 0){
                     bookingErrorLabel.setText("Tickets cannot go under 0");
                  }else{
                     bookingErrorLabel.setText("");
                  }  
                  break;

               case "add":
                  ticketsField.setBorder(BorderFactory.createLineBorder(Color.gray));
                  numTickets = Math.min(20, numTickets + 1);
                  numTickets = Math.max(0, numTickets);
                  if(numTickets == 20){
                     bookingErrorLabel.setText("Tickets cannot be more than 20");
                  }else{
                     bookingErrorLabel.setText("");
                  }
                  break;

               case "back":
                  cardLayout.show(mainViewPanel, "homePanel");
                  break;
                  
               case "next":
                  if(Integer.parseInt(ticketsField.getText()) == 0){
                     ticketsField.setBorder(BorderFactory.createLineBorder(Color.red));
                     bookingErrorLabel.setText("Tickets cannot be empty");  
                  }else if(numTickets > 20){
                     numTickets = 20;
                     bookingErrorLabel.setText("Tickets cannot be more than 20");
                  }else if(numTickets < 0){
                     numTickets = 0;
                     bookingErrorLabel.setText("Tickets cannot go under 0");
                  }else if(nameField.getText().equals("") || emailField.getText().equals("") || phoneField.getText().equals("")){
                     bookingErrorLabel.setText("Empty Fields");
                  }else{
                     seatSelectionUI();   
                     selectionMovieDisplay.setText(movieTitleDisplay.getText());
                     ticketQuantity.setText(String.valueOf(numTickets));
                     ticketsField.setBorder(BorderFactory.createLineBorder(Color.gray));
                     bookingErrorLabel.setText(""); 
                     selectionErrorLabel.setForeground(Color.white);
                     selectionErrorLabel.setText("Select Seats to Occupy");
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
   
   // Listener for Seat Selection Panel 
   public class seatSelectionListener implements ActionListener{
   
      public void actionPerformed(ActionEvent e){

         String command = e.getActionCommand();
         int ticketsLeft = Integer.parseInt(ticketQuantity.getText());
         seatButton = (JButton) e.getSource();

         switch(command){

            case "back":

               selectedSeats.clear();
               cardLayout.show(mainViewPanel, "bookingPanel");
               break;

            case "next":
               
               if(ticketsLeft != 0){
                  selectionErrorLabel.setForeground(Color.red);
                  selectionErrorLabel.setText(ticketsLeft + " Tickets left to Use");
               }else{
                  paymentUI();
                  selectionErrorLabel.setForeground(Color.red);
                  selectionErrorLabel.setText("");
                  paymentTotalAmountDisplay.setText(String.valueOf(totalAmountToPay));
                  cardLayout.show(mainViewPanel, "paymentPanel");  
               }
               break;

            default:
               Boolean isSelected = (Boolean) seatButton.getClientProperty("selected");
               String seatNumber = seatButton.getText();

               if(isSelected == null || !isSelected){
                  if(ticketsLeft > 0){
                     ticketQuantity.setText(String.valueOf(ticketsLeft - 1));
                     seatButton.setBorder(BorderFactory.createLineBorder(Color.red));
                     seatButton.putClientProperty("selected", true);
                     selectedSeats.add(seatNumber);
                     if(ticketsLeft - 1 == 0){
                        ticketQuantity.setForeground(Color.red);
                        selectionErrorLabel.setForeground(Color.red);
                        selectionErrorLabel.setText("No Tickets Available");
                     }
                  }
               }else{
                  ticketQuantity.setForeground(Color.green);   
                  ticketQuantity.setText(String.valueOf(ticketsLeft + 1));
                  selectionErrorLabel.setForeground(Color.white);
                  selectionErrorLabel.setText("Select Seat to Occupy");
                  seatButton.setBorder(UIManager.getBorder("Button.border"));
                  seatButton.putClientProperty("selected", false);
                  selectedSeats.remove(seatNumber);
               }
         }

      }

   }

   // Listener for Payment UI
   public class paymentListener implements ActionListener{

      public void actionPerformed(ActionEvent e){
         
         GridBagConstraints gbc = new GridBagConstraints();
         gbc.insets = new Insets(5, 5, 5, 5);

         String command = e.getActionCommand();
         int cashAmount = 0;

         switch(command){

            case "back":
               cardLayout.show(mainViewPanel, "seatSelectionPanel");
               break;
            case "backmethod":
               
               paymentMethodPanel_FOOTER.remove(paymentNextButton);
               paymentMethodPanel_FOOTER.remove(confirmButton);
               paymentMethodPanel.remove(paymentCashMethodPanel);
               paymentMethodPanel.add(paymentMethodPanel_CENTER, BorderLayout.CENTER);
               paymentBackButton.setActionCommand("back");

               paymentMethodPanel.revalidate();
               paymentMethodPanel.repaint();
               break;
            case "backcashmethod":

               paymentMethodPanel.add(paymentMethodPanel_HEADER, BorderLayout.NORTH);
               paymentMethodPanel.add(paymentCashMethodPanel, BorderLayout.CENTER);
               paymentMethodPanel.remove(paymenConfirmationPanel);

               paymentMethodPanel_FOOTER.remove(confirmButton);

               paymentBackButton.setActionCommand("backmethod");

               gbc.gridx = 1;
               gbc.gridy = 0;
               gbc.anchor = GridBagConstraints.EAST;
               paymentMethodPanel_FOOTER.add(paymentNextButton, gbc);

               paymentMethodPanel.revalidate();
               paymentMethodPanel.repaint();
               break;
            case "backcardmethod":

               paymentMethodPanel.add(paymentMethodPanel_HEADER, BorderLayout.NORTH);
               paymentMethodPanel.add(paymentMethodPanel_CENTER, BorderLayout.CENTER);
               paymentMethodPanel.remove(paymenConfirmationPanel);
               paymentMethodPanel.remove(paymenConfirmationPanel);

               paymentMethodPanel_FOOTER.remove(confirmButton);

               paymentMethodPanel.revalidate();
               paymentMethodPanel.repaint();
            break;
            case "card":
               
               paymentMethodPanel.remove(paymentMethodPanel_HEADER);
               paymentMethodPanel.remove(paymentMethodPanel_CENTER);
               
               paymentMethodPanel.add(paymenConfirmationPanel, BorderLayout.CENTER);

               paymentBackButton.setActionCommand("backcardmethod");

               gbc.gridx = 1;
               gbc.gridy = 0;
               gbc.anchor = GridBagConstraints.EAST;
               paymentMethodPanel_FOOTER.add(confirmButton, gbc);

               paymentMethodPanel.revalidate();
               paymentMethodPanel.repaint();

               confirmPaymentDisplay.setText("Card");
               confirmNameDisplay.setText(nameField.getText());
               confirmTicketsDisplay.setText(ticketsField.getText());
               confirmCashDisplay.setText("0");
               confirmTotalAmountDisplay.setText(String.valueOf(totalAmountToPay));
               confirmChangeDisplay.setText("0");
               confirmSeatsDisplay.setText(String.join(", ", selectedSeats));

               break;
            case "cash":

               paymentMethodPanel.remove(paymentMethodPanel_CENTER);
               paymentMethodPanel.add(paymentCashMethodPanel, BorderLayout.CENTER);

               gbc.gridx = 1;
               gbc.gridy = 0;
               gbc.anchor = GridBagConstraints.EAST;
               paymentMethodPanel_FOOTER.add(paymentNextButton, gbc);
               
               paymentBackButton.setActionCommand("backmethod");

               cashAmountField.setText("");

               paymentMethodPanel.revalidate();
               paymentMethodPanel.repaint();
               break;
            case "next":
               try{
                  cashAmount = Integer.parseInt(cashAmountField.getText());
                  
                  if(cashAmount >= totalAmountToPay){
                     cashAmountField.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.black),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)
                     ));
                     paymentMethodPanel.remove(paymentMethodPanel_HEADER);
                     paymentMethodPanel.remove(paymentCashMethodPanel);
                     
                     paymentMethodPanel.add(paymenConfirmationPanel, BorderLayout.CENTER);
                     paymentBackButton.setActionCommand("backcashmethod");   
                     paymentMethodPanel_FOOTER.remove(paymentNextButton);

                     gbc.gridx = 1;
                     gbc.gridy = 0;
                     gbc.anchor = GridBagConstraints.EAST;
                     paymentMethodPanel_FOOTER.add(confirmButton, gbc);

                     paymentMethodPanel.revalidate();
                     paymentMethodPanel.repaint();

                     confirmPaymentDisplay.setText("Cash");
                     confirmNameDisplay.setText(nameField.getText());
                     confirmTicketsDisplay.setText(ticketsField.getText());
                     confirmCashDisplay.setText(cashAmountField.getText());
                     confirmTotalAmountDisplay.setText(String.valueOf(totalAmountToPay));
                     confirmChangeDisplay.setText(String.valueOf(cashAmount - totalAmountToPay));
                     confirmSeatsDisplay.setText(String.join(", ", selectedSeats));
                  }else{
                     cashAmountField.setText("");
                     cashAmountField.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.red),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)
                     ));
                  }

               }catch(NumberFormatException ex){
                  cashAmount = 0;
                  cashAmountField.setText("");
                  cashAmountField.setBorder(BorderFactory.createCompoundBorder(
                     BorderFactory.createLineBorder(Color.red),
                     BorderFactory.createEmptyBorder(5, 5, 5, 5)
                  ));
               }
               break;
            case "confirm":
               cardLayout.show(mainViewPanel, "homePanel");
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
            loginFrame.dispose(); // Close Login UI/Frame
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