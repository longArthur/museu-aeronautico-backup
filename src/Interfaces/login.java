package Interfaces;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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

    public login() {
        checkBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED)
                    senhaField.setEchoChar((char)0);
                else
                    senhaField.setEchoChar('•');
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("login");
        frame.setContentPane(new login().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
