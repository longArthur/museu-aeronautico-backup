package Interfaces;

import javax.swing.*;

public class EngenheiroEditI {
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JLabel nomeIndividuoLabel;
    private JLabel Logo;
    private JButton Config;
    private JLabel engenheiroLabel;
    private JPanel panel5;
    private JButton VoltarSair;
    private JPanel panel6;
    private JPanel panel7;
    private JLabel CODIGOField;
    private JLabel codigoLabel;
    private JLabel comprimentoMLabel;
    private JLabel ComprimentoField;
    private JLabel TipoField;
    private JLabel TipoLabel;
    private JLabel EstadoLabel;
    private JComboBox EstadoComboBox;
    private JLabel marcaLabel;
    private JLabel marcaField;
    private JLabel EnvergaduraLabel;
    private JLabel EnvergaduraField;
    private JLabel AreaAtuacaoLabel;
    private JLabel AreaField;
    private JLabel DataProduLabel;
    private JLabel DataProduField;
    private JLabel CodeHangarLabel;
    private JLabel CodeHangarField;
    private JLabel MaterialUsadoLabel;
    private JLabel MaterailUsadoField;
    private JLabel HistoriaLabel;
    private JButton inserirButton;
    private JTextPane historiaTextPane;

    public static void main(String[] args) {
        JFrame frame = new JFrame("EngenheiroEditI");
        frame.setContentPane(new EngenheiroEditI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }



}
