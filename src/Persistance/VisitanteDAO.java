package Persistance;

import Logic.CPF;
import Logic.Visitante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    public boolean inserir(Visitante obj) {
        if(obj == null) return false;
        String sql = "INSERT INTO visitante (cpf, nome, sobrenome, genero)"
                + "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, obj.getCpf().toStringNaoFormatado());
            pstmt.setString(2, obj.getNome());
            pstmt.setString(3, obj.getSobrenome());
            pstmt.setString(4, obj.getGenero().toString());

            pstmt.executeUpdate();

            return true;
        } catch (SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
        return false;
    }

    @Override
    public boolean excluir(CPF obj) {
        return false;
    }

    @Override
    public boolean editar(Visitante obj) {
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
                        rs.getString("genero").toUpperCase().equals("HOMEM") ? Visitante.Genero.HOMEM : rs.getString("genero").toUpperCase().equals("MULHER") ? Visitante.Genero.MULHER : Visitante.Genero.NAOBINARIO
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
                        rs.getString("genero").toUpperCase().equals("HOMEM") ? Visitante.Genero.HOMEM : rs.getString("genero").toUpperCase().equals("MULHER") ? Visitante.Genero.MULHER : Visitante.Genero.NAOBINARIO
                ));
            }
        } catch (SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
        return visitantes;
    }
}
