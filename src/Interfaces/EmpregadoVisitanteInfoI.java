package Interfaces;

import javax.swing.*;

public class EmpregadoVisitanteInfoI {
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JLabel nomeIndividuoLabel;
    private JLabel logo;
    private JButton Config;
    private JLabel engenheiroLabel;
    private JPanel panel5;
    private JButton voltarSair;
    private JPanel panel6;
    private JPanel panel7;
    private JButton Editar;
    private JLabel GeneroLabelField;
    private JLabel sibrenomeLabelField;
    private JLabel GeneroLabel;
    private JLabel sobrenomeLabel;
    private JLabel cpfLabelField;
    private JLabel nomeLabelField;
    private JLabel CPFLabel;
    private JLabel nomeLabel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("EmpregadoVisitanteInfoI");
        frame.setContentPane(new EmpregadoVisitanteInfoI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
