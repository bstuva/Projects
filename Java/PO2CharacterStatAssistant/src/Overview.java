import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Overview extends JFrame {
    // Path to the database file
    private static final String DATABASE_PATH = "database/character.db"; // Will be localized later

    public Overview() {
        // Set up the frame
        setTitle("Character Overview");
        setSize(1400, 1000); // Further increased size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Use BorderLayout for the frame
        setLayout(new BorderLayout());

        // Create a panel for the top image with proper aspect ratio and padding
        JPanel imagePanel = new JPanel() {
            private final Image backgroundImage = new ImageIcon("images/inventory/inventory.png").getImage(); // Adjust path if needed

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Maintain aspect ratio
                int panelWidth = getWidth();
                int panelHeight = getHeight();
                int imgWidth = backgroundImage.getWidth(this);
                int imgHeight = backgroundImage.getHeight(this);

                double widthScale = (double) panelWidth / imgWidth;
                double heightScale = (double) panelHeight / imgHeight;
                double scale = Math.min(widthScale, heightScale);

                int scaledWidth = (int) (imgWidth * scale);
                int scaledHeight = (int) (imgHeight * scale);

                // Center the image
                int x = (panelWidth - scaledWidth) / 2;
                int y = (panelHeight - scaledHeight) / 2;

                g.drawImage(backgroundImage, x, y, scaledWidth, scaledHeight, this);
            }
        };
        imagePanel.setPreferredSize(new Dimension(getWidth(), 600)); // Increased height for the image panel
        imagePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 0, 30)); // Add padding

        // Create the table model
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Name", "Class", "Image"}, 0);

        // Load character data from the database
        loadCharacterData(tableModel);

        // Create a table to display the data
        JTable table = new JTable(tableModel);
        table.setRowHeight(40); // Further increase row height for better readability
        JScrollPane scrollPane = new JScrollPane(table);

        // Add a back button below the table
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            // Go back to the main menu
            JFrame main = new JFrame();
            main.setVisible(true);
            dispose();
        });
        bottomPanel.add(scrollPane, BorderLayout.CENTER);
        bottomPanel.add(backButton, BorderLayout.SOUTH);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30)); // Add padding

        // Add components to the frame
        add(imagePanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void loadCharacterData(DefaultTableModel tableModel) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_PATH);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT name, class, image_path FROM characters")) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String characterClass = resultSet.getString("class");
                String imagePath = resultSet.getString("image_path");

                // Add the data to the table model
                tableModel.addRow(new Object[]{name, characterClass, imagePath});
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to load character data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Overview::new);
    }
}
