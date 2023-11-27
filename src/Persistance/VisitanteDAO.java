package Persistance;

import Logic.CPF;
import Logic.Visitante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VisitanteDAO implements DAO<Visitante, CPF> {
    private static VisitanteDAO visitanteDAO;
    private static Connection conexao;

    public static VisitanteDAO getInstance() {
        if(visitanteDAO == null){
            visitanteDAO = new VisitanteDAO();
        }
        return visitanteDAO;
    }

    private VisitanteDAO() {
        try {
            ConnectBD bd = ConnectBD.getInstance();
            conexao = ConnectBD.getConexao();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro = " + ex);
        }
    }

    @Override
    public CPF inserir(Visitante obj) {
        if(obj == null) return null;
        String sql = "INSERT INTO visitante (cpf, nome, sobrenome, genero)"
                + "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, obj.getCpf().toStringNaoFormatado());
            pstmt.setString(2, obj.getNome());
            pstmt.setString(3, obj.getSobrenome());
            pstmt.setString(4, obj.getGenero().toString());

            pstmt.executeUpdate();

            return obj.getCpf();
        } catch (SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
        return null;
    }

    @Override
    public boolean excluir(CPF obj) {
        if(obj == null) return false;
        if(pesquisar(obj) == null) return false;
        String sql = "DELETE FROM visitante WHERE cpf = ?";
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, obj.toStringNaoFormatado());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
        return false;
    }

    @Override
    public boolean editar(Visitante obj) {
        if(obj == null) return false;
        if(pesquisar(obj.getCpf()) == null) return false;
        String sql = "UPDATE visitante SET nome = ?, sobrenome = ?, genero = ? WHERE cpf = ?";
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, obj.getNome());
            pstmt.setString(2, obj.getSobrenome());
            pstmt.setString(3, obj.getGenero().toString());
            pstmt.setString(4, obj.getCpf().toStringNaoFormatado());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
        return false;
    }

    @Override
    public Visitante pesquisar(CPF obj) {
        if(obj == null) return null;
        String sql = "SELECT * FROM visitante WHERE cpf = ?";
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, obj.toStringNaoFormatado());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                return new Visitante(
                        new CPF(rs.getString("cpf")),
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        rs.getString("genero").equalsIgnoreCase("HOMEM") ? Visitante.Genero.HOMEM : rs.getString("genero").equalsIgnoreCase("MULHER") ? Visitante.Genero.MULHER : Visitante.Genero.NAOBINARIO
                );
            }
        } catch (SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
        return null;
    }

    @Override
    public ArrayList<Visitante> pesquisarTudo() {
        ArrayList<Visitante> visitantes = new ArrayList<>();
        String sql = "SELECT * FROM visitante";
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                visitantes.add(new Visitante(
                        new CPF(rs.getString("cpf")),
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        rs.getString("genero").equalsIgnoreCase("HOMEM") ? Visitante.Genero.HOMEM : rs.getString("genero").equalsIgnoreCase("MULHER") ? Visitante.Genero.MULHER : Visitante.Genero.NAOBINARIO
                ));
            }
        } catch (SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
        return visitantes;
    }
}
