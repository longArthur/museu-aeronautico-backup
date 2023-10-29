package Persistance;
import Logic.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EnderecoDAO implements DAO<Endereco, Integer> {
    private static EnderecoDAO enderecoDAO;
    private static Connection conexao;

    public static EnderecoDAO getInstance() {
        if(enderecoDAO == null){
            enderecoDAO = new EnderecoDAO();
        }
        return enderecoDAO;
    }

    private EnderecoDAO() {
        try {
            ConnectBD bd = ConnectBD.getInstance();
            conexao = ConnectBD.getConexao();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro = " + ex);
        }
    }

    @Override
    public boolean inserir(Endereco endereco) {
        if (endereco == null) return false;
        String sql = "INSERT INTO endereco (rua, numero_endereco, bairro, cidade, estado, cep, complemento)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, endereco.getRua());
            pstmt.setInt(2, endereco.getNumero_endereco());
            pstmt.setString(3, endereco.getBairro());
            pstmt.setString(4, endereco.getCidade());
            pstmt.setString(5, endereco.getEstado());
            pstmt.setString(6, endereco.getCep());
            pstmt.setString(7, endereco.getComplemento());

            pstmt.executeUpdate();

            return true;
        } catch (SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
        return false;
    }

    @Override
    public boolean excluir(Integer obj) {
        return false;
    }

    @Override
    public boolean editar(Endereco obj) {
        return false;
    }

    @Override
    public Endereco pesquisar(Integer obj) {
        if (obj == null) return null;
        try {
            PreparedStatement pstmt = conexao.prepareStatement("SELECT * FROM endereco WHERE codigo = ?");
            pstmt.setInt(1, obj);

            ResultSet resultado = pstmt.executeQuery();

            if (resultado.next()) {
                String rua = resultado.getString("rua");
                int numero_endereco = resultado.getInt("numero_endereco");
                String bairro = resultado.getString("bairro");
                String cidade = resultado.getString("cidade");
                String estado = resultado.getString("estado");
                String cep = resultado.getString("cep");
                String complemento = resultado.getString("complemento");
                int codigo = resultado.getInt("codigo");

                Endereco endereco = new Endereco(cidade, rua, bairro, numero_endereco, cep, estado, complemento);
                endereco.setCodigo(codigo);

                return endereco;
            }
        } catch (SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
        return null;
    }

    @Override
    public ArrayList<Endereco> pesquisarTudo() {
        ArrayList<Endereco> enderecos = new ArrayList<>();
        try {
            PreparedStatement pstmt = conexao.prepareStatement("SELECT * FROM endereco");
            ResultSet resultado = pstmt.executeQuery();

            while (resultado.next()) {
                String rua = resultado.getString("rua");
                int numero_endereco = resultado.getInt("numero_endereco");
                String bairro = resultado.getString("bairro");
                String cidade = resultado.getString("cidade");
                String estado = resultado.getString("estado");
                String cep = resultado.getString("cep");
                String complemento = resultado.getString("complemento");
                int codigo = resultado.getInt("codigo");

                Endereco endereco = new Endereco(cidade, rua, bairro, numero_endereco, cep, estado, complemento);
                endereco.setCodigo(codigo);

                enderecos.add(endereco);
            }
        } catch (SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
        return enderecos;
    }
}
