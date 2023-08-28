package Interfaces;

import javax.swing.*;

public class EmpregadoTableI {
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JLabel nomeIndividuoLabel;
    private JLabel logo;
    private JButton buttonConfig;
    private JLabel empregadoLabel;
    private JPanel panel5;
    private JButton VoltarSair;
    private JPanel panel6;
    private JButton pesquisarButton;
    private JTextField buscaField;
    private JTable tableVisitantes;


    public static void main(String[] args) {
        JFrame frame = new JFrame("EmpregadoTableI");
        frame.setContentPane(new EmpregadoTableI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
