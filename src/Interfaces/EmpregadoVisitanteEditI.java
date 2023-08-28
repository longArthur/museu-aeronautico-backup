package Interfaces;

import javax.swing.*;

public class EmpregadoVisitanteEditI {
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JLabel nomeIndividuoLabel;
    private JLabel Logo;
    private JButton Config;
    private JLabel empregadoLabel;
    private JPanel panel5;
    private JButton VoltarSair;
    private JPanel panel6;
    private JPanel panel7;
    private JButton Inserir;
    private JLabel genêroLabel;
    private JLabel sobrenomeLabel;
    private JLabel CPFLabel;
    private JLabel nomeLabel;
    private JTextField nomeField1;
    private JTextField cpfField1;
    private JTextField sobrenomeField1;
    private JComboBox GeneroComboBox1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("EmpregadoVisitanteEditI");
        frame.setContentPane(new EmpregadoVisitanteEditI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
