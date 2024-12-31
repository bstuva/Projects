import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class NewCharacterWindow extends JFrame {

    private JLabel imageLabel; // Label to display the image
    private JButton doneButton; // Done button
    private String imagePath; // Path of the currently selected image

    // Path to the database file
    private static final String DATABASE_PATH = "database/character.db";

    public NewCharacterWindow() {
        // Set up the frame
        setTitle("New Character");
        setSize(1100, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Use BorderLayout for the frame
        setLayout(new BorderLayout());

        // Top panel to display the image
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(imageLabel, BorderLayout.NORTH);

        // Center panel for other components
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Dropdown menu
        JLabel dropdownLabel = new JLabel("Choose Class:");
        String[] options = {"Warrior", "Mercenary", "Monk", "Ranger", "Sorceress", "Witch"};
        JComboBox<String> dropdown = new JComboBox<>(options);
        dropdown.addItemListener(e -> updateImage((String) e.getItem()));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        centerPanel.add(dropdownLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        centerPanel.add(dropdown, gbc);

        // Text box with a label
        JLabel nameLabel = new JLabel("Enter Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        centerPanel.add(nameLabel, gbc);

        JTextField characterName = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        centerPanel.add(characterName, gbc);

        // Add a "Done" button
        doneButton = new JButton("Done");
        doneButton.setEnabled(false); // Initially disable the button
        doneButton.addActionListener(e -> {
            String name = characterName.getText().trim();
            String characterClass = (String) dropdown.getSelectedItem();
            saveCharacterToDatabase(name, characterClass, imagePath);

            // Open the Overview window
            JFrame overview = new Overview();
            overview.setSize(1100, 800);
            overview.setLocationRelativeTo(null);
            overview.setVisible(true);

            // Close the current NewCharacterWindow
            dispose();
        });

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        centerPanel.add(doneButton, gbc);

        // Add a DocumentListener to enable/disable the button based on text field input
        characterName.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                toggleDoneButton(characterName);
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                toggleDoneButton(characterName);
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                toggleDoneButton(characterName);
            }
        });

        // Add the center panel to the frame
        add(centerPanel, BorderLayout.CENTER);

        // Initial image display
        updateImage((String) dropdown.getSelectedItem());

        // Initialize the database
        initializeDatabase();
    }

    // Method to enable or disable the Done button
    private void toggleDoneButton(JTextField characterName) {
        doneButton.setEnabled(!characterName.getText().trim().isEmpty());
    }

    // Method to update the image based on dropdown selection
    private void updateImage(String className) {
        imagePath = "images/base/" + className + "_portrait.png";
        ImageIcon icon = new ImageIcon(imagePath);
        imageLabel.setIcon(icon);
        imageLabel.setText(icon.getIconWidth() > 0 ? "" : "No image available");
    }

    // Initialize the database and create the table if not exists
    private void initializeDatabase() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_PATH);
             Statement statement = connection.createStatement()) {

            // Create the table if it doesn't exist
            String createTableSQL = "CREATE TABLE IF NOT EXISTS characters (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "class TEXT NOT NULL," +
                    "image_path TEXT NOT NULL" +
                    ")";
            statement.execute(createTableSQL);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database initialization failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Save character data to the SQLite database
    private void saveCharacterToDatabase(String name, String characterClass, String imagePath) {
        String insertSQL = "INSERT INTO characters (name, class, image_path) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_PATH);
             PreparedStatement statement = connection.prepareStatement(insertSQL)) {

            // Set parameters for the query
            statement.setString(1, name);
            statement.setString(2, characterClass);
            statement.setString(3, imagePath);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Character saved successfully!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to save character: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}