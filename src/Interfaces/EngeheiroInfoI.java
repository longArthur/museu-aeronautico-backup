package Interfaces;

import javax.swing.*;

public class EngeheiroInfoI {
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JLabel nomeIndividuoLabel;
    private JLabel Logo;
    private JButton Config;
    private JLabel engenheiroLabel;
    private JPanel panel5;
    private JPanel panel6;
    private JLabel codigoField;
    private JLabel CodigoLabel;
    private JLabel comrpimentoLabel;
    private JLabel comprimentoField;
    private JLabel tipoLabel;
    private JLabel TipoField;
    private JLabel estadoLabel;
    private JLabel estadoField;
    private JLabel marcaLabel;
    private JLabel marcaField;
    private JLabel envergaduraLabel;
    private JLabel envergaduraField;
    private JLabel areaAtuacaoLabel;
    private JLabel AreaAtuacaoField;
    private JLabel dataProducaoLabel;
    private JLabel dataField;
    private JLabel codeHangarLabel;
    private JLabel codeHangarField;
    private JLabel materialUsadoLabel;
    private JLabel materialUsadoField;
    private JLabel HistoriaLabel;
    private JButton editarButton;
    private JTextPane historiaFieldText;

    public static void main(String[] args) {
        JFrame frame = new JFrame("EngeheiroInfoI");
        frame.setContentPane(new EngeheiroInfoI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
