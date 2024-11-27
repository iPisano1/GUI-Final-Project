import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ticketSystem{
   
   private static final String ADMIN_USERNAME = "admin";
   private static final String ADMIN_PASSWORD = "admin";
   
   // WORK IN PROGRESS
   // private ArrayList<Movie> movies;
   
   CardLayout cardLayout = new CardLayout();

   JFrame loginFrame;
   JFrame mainFrame;
   // JFrame bookingFrame;
   JPanel loginPanel;
   JPanel mainViewPanel = new JPanel(cardLayout);;
   JPanel homePanel;
   JPanel bookingPanel;
   JTextField userField;
   JPasswordField passField;
   JLabel errorLabel;
   
   JTextField ticketsField;
   
   public boolean bookingWindow = false;
   
   public int totalAmountToPay;
   
   JLabel movieTitleLabel = new JLabel("Invalid");
   JLabel moviePriceLabel = new JLabel("0");
   JLabel totalPayment = new JLabel("0");
   
   public static void main(String[] args){
      
      new ticketSystem();
   
   }
   
   public ticketSystem(){
      
      loginUI();
      
   }
   
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
      movieSelectionPanel.setLayout(new GridLayout(2, 5, 20, 50));
      movieSelectionPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));
      
      // Movie Selection Buttons
      JButton[] buttons = new JButton[10];
      for(int i = 0; i < 10; i++){
         buttons[i] = new JButton("Movie " + (i + 1));
         buttons[i].setFocusPainted(false);
         buttons[i].setActionCommand("" + (i + 1));
         buttons[i].addActionListener(new movieButtonListener());
         buttons[i].setBackground(Color.decode("#5c5b5b"));
         buttons[i].setForeground(Color.white);
         buttons[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
      try{
         ImageIcon movieIcon = new ImageIcon("image/movie" + (i + 1) + ".jpg");
         Image scaledImage = movieIcon.getImage().getScaledInstance(150, 230, Image.SCALE_SMOOTH);
         buttons[i].setIcon(new ImageIcon(scaledImage));
         buttons[i].setText("");
      }catch (Exception e){
         buttons[i].setText("No Image");
      }
         movieSelectionPanel.add(buttons[i]);
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
   
   public void bookingUI(){
      
      bookingWindow = true;
   
      Border etch = BorderFactory.createEtchedBorder();
      
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(5, 5, 5, 5);
      
      bookingPanel = new JPanel();
      bookingPanel.setLayout(new BorderLayout(10, 10));

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
      JButton nextButton = new JButton("Next");
      nextButton.setFocusPainted(false);
      nextButton.setActionCommand("next");
      nextButton.addActionListener(new bookingButtonListener());

      JButton backButton = new JButton("Back");
      backButton.setFocusPainted(false);
      backButton.setActionCommand("back");
      backButton.addActionListener(new bookingButtonListener());
      
      buttonPanel.add(nextButton);
      buttonPanel.add(backButton);
           
      bookingPanel.add(buyerPanel, BorderLayout.NORTH);
      bookingPanel.add(ticketPanel, BorderLayout.CENTER);
      bookingPanel.add(buttonPanel, BorderLayout.SOUTH);
      
      mainViewPanel.add(bookingPanel, BorderLayout.CENTER);
      mainViewPanel.add(bookingPanel, "bookingPanel");

      
   }
   
   public class movieButtonListener implements ActionListener{
      
      public void actionPerformed(ActionEvent e){
         
         String command = e.getActionCommand();
         
         // if(bookingWindow){
         //    bookingFrame.dispose();
         // }
         if(command.equals("out")){
            mainFrame.dispose();
            loginUI();
         }
         else{

            if(bookingPanel == null){
               bookingUI();
            }
            
            ticketsField.setText("0");
            totalPayment.setText("0");

            switch(command){
               
               case "1":
                  movieTitleLabel.setText("Inside Out");
                  moviePriceLabel.setText("450");
                  break;
               case "2":
                  movieTitleLabel.setText("Frozen");
                  moviePriceLabel.setText("320");
                  break;
               case "3":
                  movieTitleLabel.setText("How to Train your Dragon");
                  moviePriceLabel.setText("250");
                  break;
               case "4":
                  movieTitleLabel.setText("Minions");
                  moviePriceLabel.setText("350");
                  break;
               case "5":
                  movieTitleLabel.setText("Kung Fu Panda");
                  moviePriceLabel.setText("280");
                  break;
               case "6":
                  System.out.println("Clicked Button 6");
                  break;
               case "7":
                  System.out.println("Clicked Button 7");
                  break;
               case "8":
                  System.out.println("Clicked Button 8");
                  break;
               case "9":
                  System.out.println("Clicked Button 9");
                  break;
               case "10":
                  System.out.println("Clicked Button 10");
                  break;  
            }
            cardLayout.show(mainViewPanel, "bookingPanel");
         } 
      }
      
   }
   
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