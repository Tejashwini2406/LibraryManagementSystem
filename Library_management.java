package javaapplication8;

/**
 *
 * @author TEJASHWINI
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Library_management extends JFrame implements ActionListener {
    private final Color DARK_BLUE = new Color(0, 0, 50);  
    private final Color LIGHT_BLUE = new Color(220, 240, 255); 
    private final Color DARK_GREEN = new Color(0, 100, 0); 
    private final Color DARK_RED = new Color(139, 0, 0); 

    private JLabel label1, label2, label3, label4, label5;
    private JTextField textField1, textField2, textField3, textField4, textField5;
    private JButton addButton, viewButton, deleteButton, clearButton;
    private JPanel panel;
    private ArrayList<String[]> books = new ArrayList<String[]>();

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE); 
        button.setFont(new Font("Arial", Font.BOLD, 16)); 
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

    public Library_management() {
        setTitle("Library Management System");
        setSize(1000, 800); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(LIGHT_BLUE); 

        setLocationRelativeTo(null);

        label1 = new JLabel("Book ID");
        label2 = new JLabel("Book Title");
        label3 = new JLabel("Author");
        label4 = new JLabel("Publisher");
        label5 = new JLabel("Year of Publication");
        label1.setFont(new Font("Arial", Font.BOLD, 14));
        label2.setFont(new Font("Arial", Font.BOLD, 14));
        label3.setFont(new Font("Arial", Font.BOLD, 14));
        label4.setFont(new Font("Arial", Font.BOLD, 14));
        label5.setFont(new Font("Arial", Font.BOLD, 14));
        
        textField1 = new JTextField(10);
        textField2 = new JTextField(20);
        textField3 = new JTextField(20);
        textField4 = new JTextField(20);
        textField5 = new JTextField(10);
        
        addButton = createStyledButton("Add", DARK_BLUE);
        viewButton = createStyledButton("View", DARK_GREEN); 
        deleteButton = createStyledButton("Delete", DARK_RED); 
        clearButton = createStyledButton("Clear", Color.GRAY); 

        addButton.addActionListener(this);
        viewButton.addActionListener(this);
        deleteButton.addActionListener(this);
        clearButton.addActionListener(this);

        panel = new JPanel(new GridLayout(10, 2, 10, 10)); 
        panel.setBackground(LIGHT_BLUE); 

        panel.add(label1);
        panel.add(textField1);
        panel.add(label2);
        panel.add(textField2);
        panel.add(label3);
        panel.add(textField3);
        panel.add(label4);
        panel.add(textField4);
        panel.add(label5);
        panel.add(textField5);
        
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));

        panel.add(addButton);
        panel.add(viewButton);
        panel.add(deleteButton);
        panel.add(clearButton);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String[] book = new String[6];
            book[0] = textField1.getText();
            book[1] = textField2.getText();
            book[2] = textField3.getText();
            book[3] = textField4.getText();
            book[4] = textField5.getText();
            books.add(book);
            JOptionPane.showMessageDialog(this, "Book added successfully");
            clearFields();
        } else if (e.getSource() == viewButton) {
            String[] columns = {"Book ID", "Book Title", "Author", "Publisher", "Year of Publication", "Number of Copies"};
            Object[][] data = new Object[books.size()][6];
            for (int i = 0; i < books.size(); i++) {
                data[i] = books.get(i);
            }
            JTable table = new JTable(data, columns);
            JScrollPane scrollPane = new JScrollPane(table);
            JFrame frame = new JFrame("View Books");
            frame.add(scrollPane);
            frame.setSize(800, 400);
            frame.setVisible(true);
        } else if (e.getSource() == deleteButton) {
            String bookID = JOptionPane.showInputDialog(this, "Enter book ID to delete:");
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i)[0].equals(bookID)) {
                    books.remove(i);
                    JOptionPane.showMessageDialog(this, "Book deleted successfully");
                    clearFields();
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Book not found");
        } else if (e.getSource() == clearButton) {
            clearFields();
        }
    }

    private void clearFields() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginScreen::new);
    }
}

class LoginScreen extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginScreen() {
        setTitle("Login");
        setSize(1000, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBackground(new Color(220, 240, 255));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel(""));
        panel.add(loginButton);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if ("1".equals(username) && "1".equals(password)) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                dispose(); 
                new Library_management(); 
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials. Try again.");
            }
        }
    }
}