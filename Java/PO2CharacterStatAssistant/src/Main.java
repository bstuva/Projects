import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    // Path to the database file
    private static final String DATABASE_PATH = "database/character.db";

    private static JPanel characterListPanel;
    private static JScrollPane characterListScrollPane;
    private static String selectedCharacterName = null; // Store the selected character's name

    public static void main(String[] args) {
        // Main frame setup
        JFrame main = new JFrame();
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setSize(1100, 800);
        main.setLocationRelativeTo(null);
        main.setLayout(new BorderLayout());

        // Left-hand panel with "New Character" and "Delete Character" buttons
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(300, 800));
        leftPanel.setLayout(null);
        leftPanel.setBackground(new Color(30, 30, 30));

        JButton newCharacterButton = new JButton("New Character");
        newCharacterButton.setBounds(40, 200, 220, 50);
        newCharacterButton.addActionListener(e -> {
            // Open the NewCharacterWindow
            JFrame newFrame = new NewCharacterWindow();
            newFrame.setSize(1100, 800);
            newFrame.setLocationRelativeTo(null);
            newFrame.setVisible(true);
            main.setVisible(false);
        });

        JButton deleteCharacterButton = new JButton("Delete Character");
        deleteCharacterButton.setBounds(40, 300, 220, 50);
        deleteCharacterButton.addActionListener(e -> {
            if (selectedCharacterName != null) {
                deleteCharacterFromDatabase(selectedCharacterName);
                refreshCharacterList(); // Refresh the list after deletion
            } else {
                JOptionPane.showMessageDialog(main, "No character selected!", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        leftPanel.add(newCharacterButton);
        leftPanel.add(deleteCharacterButton);
        main.add(leftPanel, BorderLayout.WEST);

        // Right-hand character list panel
        characterListScrollPane = createCharacterList();
        main.add(characterListScrollPane, BorderLayout.CENTER);

        main.setVisible(true);
    }

    private static JScrollPane createCharacterList() {
        characterListPanel = new JPanel();
        characterListPanel.setLayout(new BoxLayout(characterListPanel, BoxLayout.Y_AXIS));
        characterListPanel.setBackground(Color.BLACK);

        // Load character data from the database
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_PATH);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT name, class, image_path FROM characters")) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String characterClass = resultSet.getString("class");
                String imagePath = resultSet.getString("image_path");

                // Create a panel for this character
                JPanel characterPanel = createCharacterPanel(name, characterClass, imagePath);
                characterListPanel.add(characterPanel);
                characterListPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing between characters
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to load character data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        // Wrap the panel in a scroll pane
        JScrollPane scrollPane = new JScrollPane(characterListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null); // Remove the default border of JScrollPane

        return scrollPane;
    }

    private static JPanel createCharacterPanel(String name, String characterClass, String imagePath) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(40, 40, 40)); // Dark styling
        panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        panel.setPreferredSize(new Dimension(300, 80)); // Adjust panel width

        // Highlight panel when clicked
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                selectedCharacterName = name; // Set the selected character's name
                for (Component component : characterListPanel.getComponents()) {
                    if (component instanceof JPanel) {
                        component.setBackground(new Color(40, 40, 40)); // Reset background
                    }
                }
                panel.setBackground(new Color(60, 60, 60)); // Highlight selected panel
            }
        });

        // Character portrait
        JLabel portraitLabel = new JLabel();
        ImageIcon portraitIcon = new ImageIcon(imagePath); // Ensure image paths are correct

        // Scale the image while maintaining its aspect ratiob
        int imageWidth = 64; // Desired width
        int imageHeight = (portraitIcon.getIconHeight() * imageWidth) / portraitIcon.getIconWidth(); // Maintain aspect ratio
        Image scaledImage = portraitIcon.getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
        portraitLabel.setIcon(new ImageIcon(scaledImage));
        portraitLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        panel.add(portraitLabel, BorderLayout.WEST);

        // Character info
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false); // Transparent background

        JLabel nameLabel = new JLabel(name);
        nameLabel.setForeground(Color.ORANGE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel classLabel = new JLabel(characterClass);
        classLabel.setForeground(Color.LIGHT_GRAY);
        classLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        infoPanel.add(nameLabel);
        infoPanel.add(classLabel);

        panel.add(infoPanel, BorderLayout.CENTER);

        return panel;
    }

    private static void deleteCharacterFromDatabase(String name) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_PATH);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM characters WHERE name = ?")) {

            statement.setString(1, name);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Character deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                selectedCharacterName = null; // Reset the selected character
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to delete character: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private static void refreshCharacterList() {
        characterListPanel.removeAll();
        characterListScrollPane.setViewportView(createCharacterList().getViewport().getView());
        characterListPanel.revalidate();
        characterListPanel.repaint();
    }
}
