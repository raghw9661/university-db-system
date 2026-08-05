package ui;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("University Database Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create tabbed pane
        JTabbedPane tabs = new JTabbedPane();

        // Add each panel as a tab
        tabs.addTab("Departments", new DepartmentPanel());
        tabs.addTab("Students",    new StudentPanel());

        // Add tabs pane to frame
        add(tabs);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}