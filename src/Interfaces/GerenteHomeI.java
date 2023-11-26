package Interfaces;

import Logic.CPF;
import Logic.Empregado;
import Persistance.EmpregadoDAO;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Locale;
import java.util.NoSuchElementException;

public class GerenteHomeI {
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JLabel nomeIndividuoLabel;
    private JLabel label1;
    private JButton button5;
    private JLabel gerenteLabel;
    private JButton voltarSairButton;
    private JPanel panel5;
    private JPanel panel6;
    private JButton pilotosButton;
    private JButton engenheiroButton;
    private JButton historiadorButton1;
    private JButton empregadoButton;
    private JButton hangarButton;
    private JButton pesquisarButton;
    private JTextField textField1;
    private JButton inserirEmpregadoButton;
    private JTable table1;
    private Empregado empregado;

    private JFrame frame;


    public GerenteHomeI(Empregado empregado) {
        this.empregado = empregado;
        JFrame frame = new JFrame("Tabela de Empregados");
        $$$setupUI$$$();

        JPopupMenu menu = new JPopupMenu();
        JMenuItem deletar = new JMenuItem("Deletar");

        deletar.addActionListener(e -> {
            EmpregadoDAO empregadoDAO = EmpregadoDAO.getInstance();
            int rowAtPoint = table1.rowAtPoint(SwingUtilities.convertPoint(menu, new Point(0, 0), table1));

            if (empregadoDAO.excluir((CPF) table1.getValueAt(table1.getSelectedRow(), 0))) {
                JOptionPane.showMessageDialog(frame, "Exclusao bem-sucedida!");
                DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                tableModel.setRowCount(0);
                for (Object[] objects : populateData()) {
                    tableModel.addRow(objects);
                }
            } else
                JOptionPane.showMessageDialog(frame, "Exclusao mal-sucedida");

        });

        menu.addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        int rowAtPoint = table1.rowAtPoint(SwingUtilities.convertPoint(menu, new Point(0, 0), table1));
                        if (rowAtPoint > -1) {
                            table1.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                        }
                    }
                });
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }
        });

        menu.add(deletar);
        table1.setComponentPopupMenu(menu);

        this.frame = frame;
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        voltarSairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new login();
                frame.dispose();
            }
        });

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                Point point = e.getPoint();
                int row = table.rowAtPoint(point);
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    Empregado empregado1 = (Empregado) EmpregadoDAO.getInstance().pesquisar((CPF) table.getValueAt(table.getSelectedRow(), 0));
                    if (empregado1 == null)
                        JOptionPane.showMessageDialog(frame, "Tabela mal-funcionando, contate um administrador.");
                    else {
                        new GerenteInfoI(empregado, empregado1);
                        frame.dispose();
                    }
                }
            }
        });

        empregadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmpregadoHome(empregado);
                frame.dispose();
            }
        });
        engenheiroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EngenheiroI(empregado);
                frame.dispose();
            }
        });
        pilotosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PilotoI(empregado);
                frame.dispose();
            }
        });
        historiadorButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HistoriadorI(empregado);
                frame.dispose();
            }
        });
        hangarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GerenteHangarI(empregado);
                frame.dispose();
            }
        });
        inserirEmpregadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GerenteInserirI(empregado);
                frame.dispose();
            }
        });

                    pesquisarButton.addActionListener(new ActionListener() {
                @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    java.util.List<Empregado> empregados = EmpregadoDAO.getInstance().pesquisarTudo();
                    Empregado empregado1 = empregados.stream().filter(empregado2 -> empregado2.getCpf().equals(new CPF(textField1.getText()))).findFirst().get();

                    DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                    tableModel.setRowCount(0);

                    tableModel.addRow(new Object[]{empregado1.getCpf(), empregado1.getNomeSobrenome(), empregado.getClass().getName().substring(6)});
                } catch (NoSuchElementException exception) {
                    JOptionPane.showMessageDialog(frame, "CPF nao encontrado.");
                } catch (Exception exception) {
                    if (textField1.getText().isEmpty()) {
                        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                        tableModel.setRowCount(0);
                        for (Object[] objects : populateData()) {
                            tableModel.addRow(objects);
                        }
                    } else
                        JOptionPane.showMessageDialog(frame, exception.getMessage());
                }
            }
        });
    }


    private Object[][] populateData() {
        ArrayList<Empregado> empregadoList = EmpregadoDAO.getInstance().pesquisarTudo();
        System.out.println(empregadoList);
        Object[][] dados = new Object[empregadoList.size()][3];

        for (int i = 0; i < empregadoList.size(); i++) {
            dados[i] = new Object[]
                    {empregadoList.get(i).getCpf(),
                            empregadoList.get(i).getNomeSobrenome(),
                            empregadoList.get(i).getClass().getName().substring(6)};
        }

        return dados;
    }

    private void createUIComponents() {
        EmpregadoDAO empregadoDAO = EmpregadoDAO.getInstance();
        nomeIndividuoLabel = new JLabel(empregado.getNome() + " " + empregado.getSobrenome());



        String[] colunas = {"CPF", "Nome completo", "Cargo"};
        Object[][] dados = populateData();



        table1 = new JTable(new DefaultTableModel(dados, colunas));
        table1.setDefaultEditor(Object.class, null);
    }


    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-2960427));
        Font panel1Font = this.$$$getFont$$$(null, -1, 16, panel1.getFont());
        if (panel1Font != null) panel1.setFont(panel1Font);
        panel1.setForeground(new Color(-2960427));
        panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel3.setBackground(new Color(-11842739));
        panel3.setEnabled(false);
        panel2.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(1280, 720), null, null, 0, false));
        panel4 = new JPanel();
        panel4.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(8, 11, new Insets(0, 0, 0, 0), -1, -1));
        panel4.setBackground(new Color(-349868));
        panel3.add(panel4, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(1280, 160), new Dimension(1280, 160), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel4.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 4, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        Font nomeIndividuoLabelFont = this.$$$getFont$$$("Arial", Font.BOLD, 36, nomeIndividuoLabel.getFont());
        if (nomeIndividuoLabelFont != null) nomeIndividuoLabel.setFont(nomeIndividuoLabelFont);
        nomeIndividuoLabel.setForeground(new Color(-1));
        panel4.add(nomeIndividuoLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panel4.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, 1, new Dimension(30, -1), new Dimension(35, -1), new Dimension(45, -1), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        panel4.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(7, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        panel4.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        panel4.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(4, 10, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer6 = new com.intellij.uiDesigner.core.Spacer();
        panel4.add(spacer6, new com.intellij.uiDesigner.core.GridConstraints(4, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer7 = new com.intellij.uiDesigner.core.Spacer();
        panel4.add(spacer7, new com.intellij.uiDesigner.core.GridConstraints(4, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer8 = new com.intellij.uiDesigner.core.Spacer();
        panel4.add(spacer8, new com.intellij.uiDesigner.core.GridConstraints(4, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer9 = new com.intellij.uiDesigner.core.Spacer();
        panel4.add(spacer9, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer10 = new com.intellij.uiDesigner.core.Spacer();
        panel4.add(spacer10, new com.intellij.uiDesigner.core.GridConstraints(5, 9, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        label1 = new JLabel();
        label1.setIcon(new ImageIcon(getClass().getResource("/Interfaces/Img/logoP.png")));
        label1.setInheritsPopupMenu(false);
        label1.setText("");
        panel4.add(label1, new com.intellij.uiDesigner.core.GridConstraints(2, 9, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        button5 = new JButton();
        button5.setAutoscrolls(false);
        button5.setBackground(new Color(-349868));
        button5.setBorderPainted(false);
        button5.setContentAreaFilled(true);
        button5.setEnabled(false);
        button5.setHideActionText(true);
        button5.setIcon(new ImageIcon(getClass().getResource("/Interfaces/Img/CONFIG.png")));
        button5.setText("");
        button5.setVerifyInputWhenFocusTarget(false);
        button5.setVisible(true);
        panel4.add(button5, new com.intellij.uiDesigner.core.GridConstraints(2, 8, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gerenteLabel = new JLabel();
        gerenteLabel.setBackground(new Color(-1));
        gerenteLabel.setEnabled(true);
        Font gerenteLabelFont = this.$$$getFont$$$("Arial", Font.BOLD, 36, gerenteLabel.getFont());
        if (gerenteLabelFont != null) gerenteLabel.setFont(gerenteLabelFont);
        gerenteLabel.setForeground(new Color(-1));
        gerenteLabel.setText("Gerente");
        panel4.add(gerenteLabel, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer11 = new com.intellij.uiDesigner.core.Spacer();
        panel4.add(spacer11, new com.intellij.uiDesigner.core.GridConstraints(3, 6, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer12 = new com.intellij.uiDesigner.core.Spacer();
        panel4.add(spacer12, new com.intellij.uiDesigner.core.GridConstraints(2, 7, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel7.setBackground(new Color(-11842739));
        panel3.add(panel7, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, 1, new Dimension(1280, 33), new Dimension(1280, 33), new Dimension(32767, 32767), 0, true));
        final com.intellij.uiDesigner.core.Spacer spacer13 = new com.intellij.uiDesigner.core.Spacer();
        panel7.add(spacer13, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        voltarSairButton = new JButton();
        voltarSairButton.setBackground(new Color(-11842739));
        Font voltarSairButtonFont = this.$$$getFont$$$(null, -1, 20, voltarSairButton.getFont());
        if (voltarSairButtonFont != null) voltarSairButton.setFont(voltarSairButtonFont);
        voltarSairButton.setIcon(new ImageIcon(getClass().getResource("/Interfaces/Img/flecha.png")));
        voltarSairButton.setText("Voltar/Sair");
        panel7.add(voltarSairButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 36), new Dimension(200, 36), new Dimension(200, 36), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer14 = new com.intellij.uiDesigner.core.Spacer();
        panel7.add(spacer14, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        panel5 = new JPanel();
        panel5.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 5, new Insets(0, 0, 0, 0), -1, -1));
        panel5.setBackground(new Color(-11842739));
        panel5.setEnabled(true);
        panel3.add(panel5, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(12, 504), new Dimension(12, 504), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer15 = new com.intellij.uiDesigner.core.Spacer();
        panel5.add(spacer15, new com.intellij.uiDesigner.core.GridConstraints(1, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        panel6 = new JPanel();
        panel6.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel6.setBackground(new Color(-11842739));
        panel5.add(panel6, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(200, -1), new Dimension(200, -1), new Dimension(200, -1), 0, false));
        pilotosButton = new JButton();
        Font pilotosButtonFont = this.$$$getFont$$$(null, Font.BOLD, 20, pilotosButton.getFont());
        if (pilotosButtonFont != null) pilotosButton.setFont(pilotosButtonFont);
        pilotosButton.setText("Pilotos");
        panel6.add(pilotosButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 70), new Dimension(200, 70), new Dimension(200, 70), 0, false));
        engenheiroButton = new JButton();
        Font engenheiroButtonFont = this.$$$getFont$$$(null, Font.BOLD, 20, engenheiroButton.getFont());
        if (engenheiroButtonFont != null) engenheiroButton.setFont(engenheiroButtonFont);
        engenheiroButton.setText("Engenheiro");
        panel6.add(engenheiroButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 70), new Dimension(200, 70), new Dimension(200, 70), 0, false));
        historiadorButton1 = new JButton();
        Font historiadorButton1Font = this.$$$getFont$$$(null, Font.BOLD, 20, historiadorButton1.getFont());
        if (historiadorButton1Font != null) historiadorButton1.setFont(historiadorButton1Font);
        historiadorButton1.setText("Historiador");
        panel6.add(historiadorButton1, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 70), new Dimension(200, 70), new Dimension(200, 70), 0, false));
        empregadoButton = new JButton();
        Font empregadoButtonFont = this.$$$getFont$$$(null, Font.BOLD, 20, empregadoButton.getFont());
        if (empregadoButtonFont != null) empregadoButton.setFont(empregadoButtonFont);
        empregadoButton.setText("Empregado");
        panel6.add(empregadoButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 70), new Dimension(200, 70), new Dimension(200, 70), 0, false));
        hangarButton = new JButton();
        Font hangarButtonFont = this.$$$getFont$$$(null, Font.BOLD, 20, hangarButton.getFont());
        if (hangarButtonFont != null) hangarButton.setFont(hangarButtonFont);
        hangarButton.setText("Hangar");
        panel6.add(hangarButton, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 70), new Dimension(200, 70), new Dimension(200, 70), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer16 = new com.intellij.uiDesigner.core.Spacer();
        panel5.add(spacer16, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        pesquisarButton = new JButton();
        pesquisarButton.setText("Pesquisar");
        panel5.add(pesquisarButton, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField1 = new JTextField();
        panel5.add(textField1, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        inserirEmpregadoButton = new JButton();
        Font inserirEmpregadoButtonFont = this.$$$getFont$$$(null, -1, 20, inserirEmpregadoButton.getFont());
        if (inserirEmpregadoButtonFont != null) inserirEmpregadoButton.setFont(inserirEmpregadoButtonFont);
        inserirEmpregadoButton.setText("Inserir Empregado");
        panel5.add(inserirEmpregadoButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, -1), new Dimension(200, -1), new Dimension(200, -1), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer17 = new com.intellij.uiDesigner.core.Spacer();
        panel5.add(spacer17, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel5.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane1.setViewportView(table1);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
