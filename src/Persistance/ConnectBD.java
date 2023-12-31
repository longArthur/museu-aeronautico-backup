package Persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 *
 * @author mk
 */
public class ConnectBD {

    /**
     * Classe respons�vel pelo gerenciamento da conex�o com o banco de dados
     * @author Karen Borges
     */
    //Atributos para manipulação do BD
    private static final String user="root";
    private static final String password=null;
    private static final String driver="com.mysql.cj.jdbc.Driver";
    private static final String url= "jdbc:mysql://localhost:3306/sigipev";
    private static Connection conexao;
    private static ConnectBD conexaoBD;

    public static ConnectBD getInstance() throws ClassNotFoundException, SQLException  {
        if( conexaoBD == null ) {
            conexaoBD = new ConnectBD();
        }
        return conexaoBD;
    }

    private ConnectBD() throws ClassNotFoundException, SQLException  {
        conexao = abrirConexao();
    }

    private Connection abrirConexao() throws ClassNotFoundException, SQLException {
        Class.forName(driver); //carrega o drive. Pode gerar ClassNotFoundException
        conexao = DriverManager.getConnection(url, user, password); // abre a conex�o para esta URL usando o driver carregado na linha anterior
        return conexao; //Conseguiu abrir a conexao
    }

    public static void fecharConexao() throws SQLException{
        conexao.close();
        conexao = null;
    }



    public static Connection getConexao() {
        return conexao;
    }


    public static void main(String[] args) {
        ConnectBD con;
        try {
            con = ConnectBD.getInstance();
            System.out.println("Abriu conexao" + con);
            ConnectBD.fecharConexao();
            System.out.println("Fechou conexao");
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Erro = " + ex);
        }

    }
}