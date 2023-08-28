package Interfaces;

import javax.swing.*;

public class PilotoEditI {
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JLabel nomeIndividuoLabel;
    private JLabel Logo;
    private JButton Config;
    private JLabel pilotoLabel;
    private JPanel panel5;
    private JButton voltarSair;
    private JLabel codigoLabel;
    private JLabel CODIGOField;
    private JLabel comprimentoMLabel;
    private JLabel comrpimentoField;
    private JLabel tipoLabel;
    private JLabel tipoField;
    private JLabel estadoLabel;
    private JComboBox EstadocomboBox3;
    private JLabel marcaLabel;
    private JLabel marcaField;
    private JLabel envergaduraMLabel;
    private JLabel envergaduraField;
    private JLabel areaDeAtuaçãoLabel;
    private JLabel areaField;
    private JLabel dataDeProducaoLabel;
    private JLabel dataField;
    private JLabel codigoHangarLabel;
    private JLabel codeHangarField;
    private JLabel materialUsadoLabel;
    private JLabel materialUsadoField;
    private JLabel historiaLabel;
    private JTextPane HistoriatextPane;
    private JButton InserirButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("PilotoEditI");
        frame.setContentPane(new PilotoEditI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
