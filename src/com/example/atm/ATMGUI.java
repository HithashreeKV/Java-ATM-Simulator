package com.example.atm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ATMGUI extends JFrame {

    private Account account;
    private CardLayout cards;
    private JPanel mainPanel;

    private JTextField tfAccountId;
    private JTextField tfOwnerName;
    private JTextField tfInitialBalance;

    private JLabel lblWelcome;
    private JLabel lblBalance;
    private JTextField tfAmount;
    private JTextArea taHistory;

    public ATMGUI() {
        setTitle("ATM Simulator");
        setSize(450, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cards = new CardLayout();
        mainPanel = new JPanel(cards);

        mainPanel.add(createAccountScreen(), "create");
        mainPanel.add(createDashboardScreen(), "dashboard");

        add(mainPanel);
        cards.show(mainPanel, "create");
    }

    // ---------------------------- CREATE ACCOUNT SCREEN ----------------------------
    private JPanel createAccountScreen() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(245, 245, 245));

        JLabel title = new JLabel("Create Your Account", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(new EmptyBorder(20, 0, 20, 0));

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(6, 1, 10, 10));
        form.setBorder(new EmptyBorder(40, 40, 40, 40));
        form.setBackground(Color.WHITE);

        tfAccountId = new JTextField();
        tfOwnerName = new JTextField();
        tfInitialBalance = new JTextField();

        form.add(labeledPanel("Account ID:", tfAccountId));
        form.add(labeledPanel("Owner Name:", tfOwnerName));
        form.add(labeledPanel("Initial Balance:", tfInitialBalance));

        JButton createBtn = new JButton("Create Account");
        createBtn.setFont(new Font("Arial", Font.BOLD, 16));
        createBtn.addActionListener(this::createAccount);

        form.add(createBtn);

        panel.add(title, BorderLayout.NORTH);
        panel.add(form, BorderLayout.CENTER);
        return panel;
    }

    

    private JPanel createDashboardScreen() {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBackground(new Color(240, 240, 240));

    Font mainFont = new Font("Monospaced", Font.PLAIN, 14);

    // Header
    lblWelcome = new JLabel("Welcome!", SwingConstants.CENTER);
    lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 22));
    lblWelcome.setBorder(new EmptyBorder(20, 0, 10, 0));

    // Balance
    lblBalance = new JLabel("Balance: \u20B9 0.00", SwingConstants.CENTER);
    lblBalance.setFont(mainFont);
    lblBalance.setForeground(new Color(0, 120, 0));

    // Operations panel
    JPanel operations = new JPanel(new GridLayout(6, 1, 10, 10));
    operations.setBorder(new EmptyBorder(20, 40, 20, 40));
    operations.setBackground(Color.WHITE);

    tfAmount = new JTextField();
    tfAmount.setFont(mainFont);
    operations.add(labeledPanel("Enter Amount:", tfAmount));

    JButton btnDeposit = new JButton("Deposit");
    JButton btnWithdraw = new JButton("Withdraw");
    JButton btnHistory = new JButton("Show History");

    styleButton(btnDeposit);
    styleButton(btnWithdraw);
    styleButton(btnHistory);

    btnDeposit.setFont(mainFont);
    btnWithdraw.setFont(mainFont);
    btnHistory.setFont(mainFont);

    btnDeposit.addActionListener(e -> doDeposit());
    btnWithdraw.addActionListener(e -> doWithdraw());
    btnHistory.addActionListener(e -> showHistory());

    operations.add(btnDeposit);
    operations.add(btnWithdraw);
    operations.add(btnHistory);

    // History area
    taHistory = new JTextArea();
    taHistory.setEditable(false);
    taHistory.setFont(mainFont);
    JScrollPane scroll = new JScrollPane(taHistory);

    // ADDING COMPONENTS IN CORRECT LAYOUT
    JPanel centerPanel = new JPanel(new BorderLayout());
    centerPanel.add(lblBalance, BorderLayout.NORTH);
    centerPanel.add(scroll, BorderLayout.CENTER);

    panel.add(lblWelcome, BorderLayout.NORTH);
    panel.add(centerPanel, BorderLayout.CENTER);
    panel.add(operations, BorderLayout.SOUTH);

    return panel;
    }


    private JPanel labeledPanel(String text, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        field.setFont(new Font("Monospaced", Font.PLAIN, 14)); // FIX added
        panel.add(label, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    private void styleButton(JButton btn) {
        btn.setBackground(new Color(0, 123, 255));
        btn.setForeground(Color.WHITE);
    }

    // ---------------------------- EVENT HANDLERS ----------------------------
    private void createAccount(ActionEvent e) {
        try {
            String id = tfAccountId.getText().trim();
            String name = tfOwnerName.getText().trim();
            double balance = Double.parseDouble(tfInitialBalance.getText().trim());

            if (id.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.");
                return;
            }

            account = new Account(id, name, balance);

            lblWelcome.setText("Welcome, " + account.getOwnerName() + "!");
            updateBalanceLabel();

            cards.show(mainPanel, "dashboard");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Input error: " + ex.getMessage());
        }
    }

    private void doDeposit() {
        try {
            double amt = Double.parseDouble(tfAmount.getText().trim());
            account.deposit(amt);
            updateBalanceLabel();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid Amount!");
        }
    }

    private void doWithdraw() {
        try {
            double amt = Double.parseDouble(tfAmount.getText().trim());
            account.withdraw(amt);
            updateBalanceLabel();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void showHistory() {
        taHistory.setText("");
        List<Transaction> txs = account.getTransactions();
        for (Transaction t : txs) {
            taHistory.append(t.toString() + "\n");
        }
    }

    private void updateBalanceLabel() {
    lblBalance.setText("Balance: \u20B9" + String.format("%.2f", account.getBalance()));
    }

}

