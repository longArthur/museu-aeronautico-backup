package Interfaces;

import javax.swing.*;

public class EmpregadoHome {
    private JPanel panel10;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JLabel Nome;
    private JLabel Logo;
    private JButton Config;
    private JLabel empregadoLabel;
    private JButton voltarSair;
    private JPanel panel5;
    private JPanel panel6;
    private JScrollPane scrollPane1;
    private JTable tabela;
    private JLabel CPFLabel;
    private JButton cadastrarVisitante;
    private JButton IniciarVisita;
    private JButton visitantesCadastrados;
    private JTextField textFieldCPF;
    private JPanel panel7;

    public static void main(String[] args) {
        JFrame frame = new JFrame("EmpregadoHome");
        frame.setContentPane(new EmpregadoHome().panel10);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
