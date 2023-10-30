package Persistance;


import Logic.Departamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DepartamentoDAO implements DAO<Departamento, Integer> {
    //todo change to mysql
    private Connection conexao;
    private static DepartamentoDAO departamentoDAO;

    public static DepartamentoDAO getInstance() {
        if(departamentoDAO == null){
            departamentoDAO = new DepartamentoDAO();
        }
        return departamentoDAO;
    }

    private DepartamentoDAO() {
        try {
            ConnectBD bd = ConnectBD.getInstance();
            conexao = ConnectBD.getConexao();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro = " + ex);
        }
    }

    @Override
    public boolean inserir(Departamento obj) {
        if (obj == null) return false;
        String sql = "INSERT INTO departamento (nome, data_criacao, orcamento)"
                + "VALUES (?, ?, ?)";
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, obj.getNome());
            pstmt.setDate(2, Date.valueOf(obj.getData_criacao().toLocalDate()));
            pstmt.setBigDecimal(3, obj.getOrcamento());

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
    public boolean editar(Departamento obj) {
        return false;
    }

    @Override
    public Departamento pesquisar(Integer obj) {
        if(obj == null) return null;
        try {
            String sql = "SELECT * FROM departamento WHERE codigo = ?";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, obj);

            ResultSet rs = pstmt.executeQuery();


            //public Departamento(LocalDateTime data_criacao, BigDecimal orcamento, String nome)
            if (rs.next()) {
                Departamento departamento = new Departamento(rs.getDate("data_criacao").toLocalDate().atStartOfDay(), rs.getBigDecimal("orcamento"), rs.getString("nome"));
                departamento.setCodigo(rs.getInt("codigo"));


                return departamento;
            }
        } catch (SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
        return null;
    }

    @Override
    public ArrayList<Departamento> pesquisarTudo() {
        ArrayList<Departamento> departamentos = new ArrayList<>();
        String sql = "SELECT * FROM departamento";
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Departamento departamento = new Departamento(rs.getDate("data_criacao").toLocalDate().atStartOfDay(), rs.getBigDecimal("orcamento"), rs.getString("nome"));
                departamento.setCodigo(rs.getInt("codigo"));
                departamentos.add(departamento);
            }
        } catch (SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
        return departamentos;
    }
}
