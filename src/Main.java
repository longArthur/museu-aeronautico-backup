import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        JFrame frame = new JFrame("Teste");
        JTabbedPane tabbed = new JTabbedPane();
        tabbed.add("Visitantes", new JPanel());
        tabbed.add("Visitas", new JPanel());
        frame.add(tabbed);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}