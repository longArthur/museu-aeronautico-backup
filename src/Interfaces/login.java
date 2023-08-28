package Interfaces;

import javax.swing.*;

public class login {
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JLabel label1;
    private JPanel login;
    private JLabel loginLabel;
    private JTextField loginField;
    private JLabel senhaLabel;
    private JPasswordField senhaField;
    private JCheckBox checkBox1;
    private JButton Entrar;

    public static void main(String[] args) {
        JFrame frame = new JFrame("login");
        frame.setContentPane(new login().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
