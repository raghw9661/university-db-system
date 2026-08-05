package ui;

import dao.StudentDao;
import models.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentPanel extends JPanel {

    private JTextField nameField, emailField, dobField, deptIdField;
    private JButton addBtn, updateBtn, deleteBtn, clearBtn;
    private JTable table;
    private DefaultTableModel tableModel;
    private StudentDao dao = new StudentDao();

    public StudentPanel() {
        setLayout(new BorderLayout(10, 10));

        // --- FORM PANEL ---
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Student Details"));

        formPanel.add(new JLabel("Full Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        formPanel.add(new JLabel("Date of Birth (YYYY-MM-DD):"));
        dobField = new JTextField();
        formPanel.add(dobField);

        formPanel.add(new JLabel("Department ID:"));
        deptIdField = new JTextField();
        formPanel.add(deptIdField);

        add(formPanel, BorderLayout.NORTH);

        // --- TABLE ---
        tableModel = new DefaultTableModel(
                new String[]{"ID", "Full Name", "Email", "Date of Birth", "Dept ID"}, 0);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // --- BUTTONS ---
        JPanel btnPanel = new JPanel(new FlowLayout());
        addBtn    = new JButton("Add");
        updateBtn = new JButton("Update");
        deleteBtn = new JButton("Delete");
        clearBtn  = new JButton("Clear");

        btnPanel.add(addBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(clearBtn);
        add(btnPanel, BorderLayout.SOUTH);

        // --- ACTIONS ---
        addBtn.addActionListener(e -> addStudent());
        updateBtn.addActionListener(e -> updateStudent());
        deleteBtn.addActionListener(e -> deleteStudent());
        clearBtn.addActionListener(e -> clearFields());

        // Click row to fill form
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = table.getSelectedRow();
                nameField.setText(tableModel.getValueAt(row, 1).toString());
                emailField.setText(tableModel.getValueAt(row, 2).toString());
                dobField.setText(tableModel.getValueAt(row, 3).toString());
                deptIdField.setText(tableModel.getValueAt(row, 4).toString());
            }
        });

        loadData();
    }

    private void loadData() {
        tableModel.setRowCount(0);
        try {
            List<Student> list = dao.getAllStudents();
            for (Student s : list) {
                tableModel.addRow(new Object[]{
                        s.getStudentId(), s.getFullName(), s.getEmail(),
                        s.getDateOfBirth(), s.getDeptId()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
        }
    }

    private void addStudent() {
        String name   = nameField.getText().trim();
        String email  = emailField.getText().trim();
        String dob    = dobField.getText().trim();
        String deptId = deptIdField.getText().trim();
        if (name.isEmpty() || email.isEmpty() || dob.isEmpty() || deptId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
            return;
        }
        try {
            dao.addStudent(new Student(name, email, dob, Integer.parseInt(deptId)));
            JOptionPane.showMessageDialog(this, "Student added successfully");
            clearFields();
            loadData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void updateStudent() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to update");
            return;
        }
        int id = (int) tableModel.getValueAt(row, 0);
        try {
            dao.updateStudent(new Student(id,
                    nameField.getText().trim(),
                    emailField.getText().trim(),
                    dobField.getText().trim(),
                    Integer.parseInt(deptIdField.getText().trim())
            ));
            JOptionPane.showMessageDialog(this, "Student updated successfully");
            clearFields();
            loadData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void deleteStudent() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete");
            return;
        }
        int id = (int) tableModel.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?");
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                dao.deleteStudent(id);
                JOptionPane.showMessageDialog(this, "Student deleted successfully");
                clearFields();
                loadData();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    private void clearFields() {
        nameField.setText("");
        emailField.setText("");
        dobField.setText("");
        deptIdField.setText("");
        table.clearSelection();
    }
}