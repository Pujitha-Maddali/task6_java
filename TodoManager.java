import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TodoManager extends JFrame {
    private DefaultListModel<String> tasksModel;
    private JList<String> tasksDisplay;
    private JTextField inputField;
    private JButton addTaskBtn, removeTaskBtn;

    public TodoManager() {
        setTitle("Task Tracker");
        setSize(450, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tasksModel = new DefaultListModel<>();
        tasksDisplay = new JList<>(tasksModel);
        JScrollPane taskScroll = new JScrollPane(tasksDisplay);

        inputField = new JTextField();
        addTaskBtn = new JButton("Add");
        removeTaskBtn = new JButton("Remove");

        // Top panel for input
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.add(inputField, BorderLayout.CENTER);
        topPanel.add(addTaskBtn, BorderLayout.EAST);

        // Bottom panel for remove button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(removeTaskBtn);

        // Add components to frame
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(taskScroll, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Add button action
        addTaskBtn.addActionListener(e -> {
            String task = inputField.getText().trim();
            if (!task.isEmpty()) {
                tasksModel.addElement(task);
                inputField.setText("");
            }
        });

        // Remove button action
        removeTaskBtn.addActionListener(e -> {
            int index = tasksDisplay.getSelectedIndex();
            if (index != -1) {
                tasksModel.remove(index);
            } else {
                JOptionPane.showMessageDialog(this, "Select a task to remove.");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TodoManager().setVisible(true));
    }
}
