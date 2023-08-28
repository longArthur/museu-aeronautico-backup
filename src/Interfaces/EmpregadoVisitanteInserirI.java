package Interfaces;

import javax.swing.*;

public class EmpregadoVisitanteInserirI {
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JLabel nomeIndividuoLabel;
    private JLabel Logo;
    private JButton Config;
    private JLabel empregadoLabel;
    private JButton VoltarSair;
    private JPanel panel5;
    private JPanel panel6;
    private JComboBox GeneroComboBox;
    private JLabel generoLabel;
    private JLabel sobrenomeLabel;
    private JTextField sobrenomeField;
    private JLabel nomeLabel;
    private JLabel CPFLabel;
    private JTextField cpfField;

    public static void main(String[] args) {
        JFrame frame = new JFrame("EmpregadoVisitanteInserirI");
        frame.setContentPane(new EmpregadoVisitanteInserirI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
