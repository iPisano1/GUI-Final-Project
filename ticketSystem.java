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
   
   JFrame loginFrame;
   JFrame mainFrame;
   JFrame bookingFrame;
   JPanel loginPanel;
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
      
      mainUI();
      
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
      errorLabel.setVisible(false); // Initially hidden
      
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
      mainFrame.setLayout(null);
      
      JLabel titleLabel = new JLabel("NetFlex");
      titleLabel.setFont(new Font("Fira Code", Font.BOLD, 30));
      titleLabel.setBounds(30, 10, 400, 50);
      titleLabel.setForeground(Color.decode("#DB202C"));
      
      JButton logOutButton = new JButton("Log Out");
      logOutButton.setBounds(650, 17, 100, 40);
      logOutButton.setFocusPainted(false);
      logOutButton.setBackground(Color.black);
      logOutButton.setForeground(Color.white);
      logOutButton.setBorder(BorderFactory.createLineBorder(Color.decode("#DB202C")));
      logOutButton.addActionListener(new movieButtonListener());
      logOutButton.setActionCommand("out");
      
      JPanel sectionPanel1 = new JPanel();
      sectionPanel1.setBackground(Color.black);
      sectionPanel1.setBounds(30, 310, 725, 150);
      sectionPanel1.setLayout(new GridLayout(1, 5, 20, 100));
      
      JPanel sectionPanel2 = new JPanel();
      sectionPanel2.setBackground(Color.black);
      sectionPanel2.setBounds(30, 480, 725, 150);
      sectionPanel2.setLayout(new GridLayout(1, 5, 20, 100));
      
      JButton[] buttons = new JButton[10];
      for (int i = 0; i < 10; i++) {
         buttons[i] = new JButton("Movie " + (i + 1));
         buttons[i].setFocusPainted(false);
         buttons[i].setActionCommand("" + (i + 1) + "");
         buttons[i].addActionListener(new movieButtonListener()); 
         buttons[i].setBackground(Color.decode("#5c5b5b"));
         buttons[i].setForeground(Color.white);
         buttons[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
         
         try{
            if(i == 0){
               ImageIcon movie1Icon = new ImageIcon("image/movie1.jpg");
               Image scaledImage = movie1Icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
               ImageIcon resizedIcon = new ImageIcon(scaledImage);
               buttons[i].setIcon(resizedIcon);
               buttons[i].setText("");
               buttons[i].setHorizontalAlignment(SwingConstants.CENTER);
               buttons[i].setVerticalAlignment(SwingConstants.CENTER);
            }else if(i == 1){
               ImageIcon movie1Icon = new ImageIcon("image/movie2.jpeg");
               Image scaledImage = movie1Icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
               ImageIcon resizedIcon = new ImageIcon(scaledImage);
               buttons[i].setIcon(resizedIcon);
               buttons[i].setText("");
               buttons[i].setHorizontalAlignment(SwingConstants.CENTER);
               buttons[i].setVerticalAlignment(SwingConstants.CENTER);
            }else if(i == 2){
               ImageIcon movie1Icon = new ImageIcon("image/movie3.jpg");
               Image scaledImage = movie1Icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
               ImageIcon resizedIcon = new ImageIcon(scaledImage);
               buttons[i].setIcon(resizedIcon);
               buttons[i].setText("");
               buttons[i].setHorizontalAlignment(SwingConstants.CENTER);
               buttons[i].setVerticalAlignment(SwingConstants.CENTER);
            }else if(i == 3){
               ImageIcon movie1Icon = new ImageIcon("image/movie4.jpg");
               Image scaledImage = movie1Icon.getImage().getScaledInstance(130, 150, Image.SCALE_SMOOTH);
               ImageIcon resizedIcon = new ImageIcon(scaledImage);
               buttons[i].setIcon(resizedIcon);
               buttons[i].setText("");
               buttons[i].setHorizontalAlignment(SwingConstants.CENTER);
               buttons[i].setVerticalAlignment(SwingConstants.CENTER);
            }else if(i == 4){
               ImageIcon movie1Icon = new ImageIcon("image/movie5.jpg");
               Image scaledImage = movie1Icon.getImage().getScaledInstance(130, 150, Image.SCALE_SMOOTH);
               ImageIcon resizedIcon = new ImageIcon(scaledImage);
               buttons[i].setIcon(resizedIcon);
               buttons[i].setText("");
               buttons[i].setHorizontalAlignment(SwingConstants.CENTER);
               buttons[i].setVerticalAlignment(SwingConstants.CENTER);
            }
         }catch(Exception e){
            buttons[i].setText("No Image");  
         }
         if(i < 5){
            sectionPanel1.add(buttons[i]);
         } 
         else{
            sectionPanel2.add(buttons[i]);
         }
      }
      
      mainFrame.add(titleLabel);
      mainFrame.add(logOutButton);
      mainFrame.add(sectionPanel1);
      mainFrame.add(sectionPanel2);
      mainFrame.setVisible(true);
      
   }
   
   public void bookingUI(){
      
      bookingWindow = true;
   
      Border etch = BorderFactory.createEtchedBorder();
      bookingFrame = new JFrame("Booking Screen");
      bookingFrame.setSize(500, 350);
      bookingFrame.setResizable(false);
      bookingFrame.setLocationRelativeTo(null);
      bookingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      bookingFrame.setLayout(new BorderLayout(10, 10));
      
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(5, 5, 5, 5);
        
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
      
      buttonPanel.add(nextButton);
           
      bookingFrame.add(buyerPanel, BorderLayout.NORTH);
      bookingFrame.add(ticketPanel, BorderLayout.CENTER);
      bookingFrame.add(buttonPanel, BorderLayout.SOUTH);
      bookingFrame.setVisible(true);
      
   }
   
   public class movieButtonListener implements ActionListener{
      
      public void actionPerformed(ActionEvent e){
         
         String command = e.getActionCommand();
         
         if(bookingWindow){
            bookingFrame.dispose();
         }
         if(command.equals("out")){
            mainFrame.dispose();
            loginUI();
         }else{
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
            bookingUI();
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
         String pass = passField.getText();
         
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