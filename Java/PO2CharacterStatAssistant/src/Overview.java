import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Overview extends JFrame {
    public Overview() {
        // Set up the frame
        setTitle("Character Overview");
        setSize(1600, 900); // Larger size for detailed sections
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create the main panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding around the entire layout
        mainPanel.setBackground(new Color(30, 30, 30));
        add(mainPanel);

        // Top Panel: Weapon I and Weapon II Panels
        JPanel topPanel = createTopPanel();
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Bottom Panel: Buttons
        JPanel bottomPanel = createBottomPanel();
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(null); // Use null layout for precise positioning
        topPanel.setBackground(new Color(30, 30, 30));
        topPanel.setPreferredSize(new Dimension(1600, 500)); // Adjust height for balance

        // Weapon I Panel
        JPanel weaponIPanel = createWeaponPanel("Weapon I");
        weaponIPanel.setBounds(50, 50, 500, 400); // Positioned to the left

        // Weapon II Panel
        JPanel weaponIIPanel = createWeaponPanel("Weapon II");
        weaponIIPanel.setBounds(1050, 50, 500, 400); // Positioned to the right

        // Add the weapon panels to the top panel
        topPanel.add(weaponIPanel);
        topPanel.add(weaponIIPanel);

        return topPanel;
    }

    private JPanel createWeaponPanel(String title) {
        JPanel weaponPanel = new JPanel(new BorderLayout());
        weaponPanel.setBackground(new Color(20, 20, 20));

        // Center the title in the border
        TitledBorder weaponBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE), title
        );
        weaponBorder.setTitleJustification(TitledBorder.CENTER);
        weaponBorder.setTitlePosition(TitledBorder.TOP);
        weaponPanel.setBorder(weaponBorder);

        // Create panel to hold Average Base Damage row and the table
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(20, 20, 20));

        // Add the Average Base Damage row
        JTextField avgDmgField = new JTextField();
        JPanel avgDmgPanel = createAverageBaseDamagePanel(avgDmgField);
        contentPanel.add(avgDmgPanel, BorderLayout.NORTH);

        // Add the weapon table
        JTable weaponTable = createWeaponTable(avgDmgField);
        JScrollPane scrollPane = new JScrollPane(weaponTable);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        weaponPanel.add(contentPanel, BorderLayout.CENTER);

        return weaponPanel;
    }

    private JPanel createAverageBaseDamagePanel(JTextField avgDmgField) {
        JPanel avgDmgPanel = new JPanel(new BorderLayout());
        avgDmgPanel.setBackground(new Color(30, 30, 30));
        avgDmgPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Label for Average Base Damage
        JLabel label = new JLabel("Average Base Dmg:");
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(Color.WHITE);
        avgDmgPanel.add(label, BorderLayout.WEST);

        // Non-editable text field for the value
        avgDmgField.setPreferredSize(new Dimension(100, 25));
        avgDmgField.setEditable(false); // Make the field non-editable
        avgDmgPanel.add(avgDmgField, BorderLayout.CENTER);

        return avgDmgPanel;
    }

    private JTable createWeaponTable(JTextField avgDmgField) {
        String[] columnNames = {"Attribute", "Min", "Max"};
        String[][] data = {
                {"Weapon Base", "", ""},
                {"Physical", "", ""},
                {"Lightning", "", ""},
                {"Fire", "", ""},
                {"Cold", "", ""},
                {"Chaos", "", ""}
        };

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Only allow editing for Min and Max columns in rows 1-5
                return column != 0 && row >= 1 && row <= 5;
            }
        };

        JTable table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setBackground(new Color(40, 40, 40));
        table.setForeground(Color.WHITE);
        table.setGridColor(Color.GRAY);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(30, 30, 30));
        table.getTableHeader().setForeground(Color.WHITE);

        // Flag to prevent recursive updates
        final boolean[] isUpdating = {false};

        // Add TableModelListener to calculate Weapon Base and Average Base Damage
        tableModel.addTableModelListener(e -> {
            // Check if we are already updating to prevent recursion
            if (isUpdating[0]) return;

            // Only trigger when the "Min" or "Max" columns in rows 1-5 are edited
            if (e.getColumn() == 1 || e.getColumn() == 2) {
                int minColumnIndex = 1;
                int maxColumnIndex = 2;

                double minSum = 0, maxSum = 0;

                // Sum up the values for rows 1-5 (Physical to Chaos)
                for (int i = 1; i <= 5; i++) {
                    minSum += parseDouble((String) tableModel.getValueAt(i, minColumnIndex));
                    maxSum += parseDouble((String) tableModel.getValueAt(i, maxColumnIndex));
                }

                // Temporarily set the flag to true to prevent recursion
                isUpdating[0] = true;

                // Update Weapon Base row
                tableModel.setValueAt(String.valueOf(minSum), 0, minColumnIndex);
                tableModel.setValueAt(String.valueOf(maxSum), 0, maxColumnIndex);

                // Calculate and update Average Base Damage
                double averageBaseDamage = (minSum + maxSum) / 2;
                avgDmgField.setText(String.format("%.2f", averageBaseDamage));

                // Reset the flag
                isUpdating[0] = false;
            }
        });

        return table;
    }

    private double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0; // Treat invalid or empty cells as 0
        }
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(new Color(30, 30, 30));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            JFrame main = new JFrame(); // Replace with actual main window
            main.setVisible(true);
            dispose();
        });

        JButton addButton = new JButton("Add Character");
        addButton.addActionListener(e -> {
            JFrame newCharacterWindow = new NewCharacterWindow();
            newCharacterWindow.setVisible(true);
            dispose();
        });

        bottomPanel.add(addButton);
        bottomPanel.add(backButton);
        return bottomPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Overview::new);
    }
}
