package Interfaces;

import javax.swing.*;

public class PilotoI {
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JLabel nomeIndividuoLabel;
    private JLabel logo;
    private JButton Config;
    private JLabel pilotoLabel;
    private JButton voltarSairButton;
    private JButton pesquisarButton;
    private JTextField pesquisarField;
    private JTable table;

    public static void main(String[] args) {
        JFrame frame = new JFrame("PilotoI");
        frame.setContentPane(new PilotoI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }



}
