package Interfaces;

import javax.swing.*;

public class EngenheiroI {
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JLabel nomeIndividuoLabel;
    private JLabel Logo;
    private JButton Config;
    private JLabel engenheiroLabel;
    private JPanel panel4;
    private JButton VoltarSair;
    private JButton Pesquisar;
    private JTextField pesquisaField;
    private JTable table;

    public static void main(String[] args) {
        JFrame frame = new JFrame("EngenheiroI");
        frame.setContentPane(new EngenheiroI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
