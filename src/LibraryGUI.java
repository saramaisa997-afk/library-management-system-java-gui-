import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Book {
    int id;
    String title;
    String author;
    boolean isIssued;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }
}

public class LibraryGUI extends JFrame {
    ArrayList<Book> books = new ArrayList<>();

    JTextField idField, titleField, authorField;
    DefaultTableModel model;
    JTable table;

    public LibraryGUI() {
        setTitle("Library Management System");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel (Form)
        JPanel panel = new JPanel(new GridLayout(2, 4));

        panel.add(new JLabel("ID"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("Title"));
        titleField = new JTextField();
        panel.add(titleField);

        panel.add(new JLabel("Author"));
        authorField = new JTextField();
        panel.add(authorField);

        JButton addBtn = new JButton("Add Book");
        panel.add(addBtn);

        add(panel, BorderLayout.NORTH);

        // Table
        model = new DefaultTableModel(new String[]{"ID", "Title", "Author", "Issued"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Bottom Panel (Buttons)
        JPanel bottom = new JPanel();

        JButton issueBtn = new JButton("Issue");
        JButton returnBtn = new JButton("Return");
        JButton deleteBtn = new JButton("Delete");

        bottom.add(issueBtn);
        bottom.add(returnBtn);
        bottom.add(deleteBtn);

        add(bottom, BorderLayout.SOUTH);

        // Add Book Action
        addBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String title = titleField.getText();
                String author = authorField.getText();

                Book book = new Book(id, title, author);
                books.add(book);

                model.addRow(new Object[]{id, title, author, "No"});

                idField.setText("");
                titleField.setText("");
                authorField.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Input");
            }
        });

        // Issue Book
        issueBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                books.get(row).isIssued = true;
                model.setValueAt("Yes", row, 3);
            }
        });

        // Return Book
        returnBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                books.get(row).isIssued = false;
                model.setValueAt("No", row, 3);
            }
        });

        // Delete Book
        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                books.remove(row);
                model.removeRow(row);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LibraryGUI().setVisible(true);
        });
    }
}
