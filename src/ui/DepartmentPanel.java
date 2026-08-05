package ui;

import dao.DepartmentDao;
import models.Department;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DepartmentPanel extends JPanel {

    private JTextField deptNameField, hodNameField;
    private JButton addBtn, updateBtn, deleteBtn, clearBtn;
    private JTable table;
    private DefaultTableModel tableModel;
    private DepartmentDao dao = new DepartmentDao();

    public DepartmentPanel() {
        setLayout(new BorderLayout(10, 10));

        // --- FORM PANEL (TOP) ---
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Department Details"));

        formPanel.add(new JLabel("Department Name:"));
        deptNameField = new JTextField();
        formPanel.add(deptNameField);

        formPanel.add(new JLabel("HOD Name:"));
        hodNameField = new JTextField();
        formPanel.add(hodNameField);

        add(formPanel, BorderLayout.NORTH);

        // --- TABLE (CENTER) ---
        tableModel = new DefaultTableModel(new String[]{"ID", "Department Name", "HOD Name"}, 0);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // --- BUTTONS (SOUTH) ---
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

        // --- BUTTON ACTIONS ---
        addBtn.addActionListener(e -> addDepartment());
        updateBtn.addActionListener(e -> updateDepartment());
        deleteBtn.addActionListener(e -> deleteDepartment());
        clearBtn.addActionListener(e -> clearFields());

        // --- CLICK ON TABLE ROW TO FILL FORM ---
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = table.getSelectedRow();
                deptNameField.setText(tableModel.getValueAt(row, 1).toString());
                hodNameField.setText(tableModel.getValueAt(row, 2).toString());
            }
        });

        // Load data on startup
        loadData();
    }

    private void loadData() {
        tableModel.setRowCount(0);
        try {
            List<Department> list = dao.getAllDepartments();
            for (Department d : list) {
                tableModel.addRow(new Object[]{
                        d.getDeptId(), d.getDeptName(), d.getHodName()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
        }
    }

    private void addDepartment() {
        String name = deptNameField.getText().trim();
        String hod  = hodNameField.getText().trim();
        if (name.isEmpty() || hod.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
            return;
        }
        try {
            dao.addDepartment(new Department(name, hod));
            JOptionPane.showMessageDialog(this, "Department added successfully");
            clearFields();
            loadData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void updateDepartment() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to update");
            return;
        }
        int id     = (int) tableModel.getValueAt(row, 0);
        String name = deptNameField.getText().trim();
        String hod  = hodNameField.getText().trim();
        try {
            dao.updateDepartment(new Department(id, name, hod));
            JOptionPane.showMessageDialog(this, "Department updated successfully");
            clearFields();
            loadData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void deleteDepartment() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete");
            return;
        }
        int id = (int) tableModel.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete?");
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                dao.deleteDepartment(id);
                JOptionPane.showMessageDialog(this, "Department deleted successfully");
                clearFields();
                loadData();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    private void clearFields() {
        deptNameField.setText("");
        hodNameField.setText("");
        table.clearSelection();
    }
}