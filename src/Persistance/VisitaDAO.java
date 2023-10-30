package Persistance;

import Logic.CPF;

import Logic.Visita;
import Logic.Visitante;

import java.sql.Connection;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class VisitaDAO implements DAO<Visita, CPF> {
    private static VisitaDAO visitaDAO;
    private static Connection conexao;

    public static VisitaDAO getInstance() {
        if(visitaDAO == null){
            visitaDAO = new VisitaDAO();
        }
        return visitaDAO;
    }

    private VisitaDAO() {
        try {
            ConnectBD bd = ConnectBD.getInstance();
            conexao = ConnectBD.getConexao();
        } catch (ClassNotFoundException | java.sql.SQLException ex) {
            System.out.println("Erro = " + ex);
        }
    }

    @Override
    public boolean inserir(Visita obj) {
        if(obj == null) return false;
        String sql = "INSERT INTO visita (cpf_visitante, cod_hangar, data_ingresso, tempo_estadia)"
                + "VALUES (?, ?, ?, ?)";
        try {
            java.sql.PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, obj.getVisitante().getCpf().toStringNaoFormatado());
            pstmt.setInt(2, obj.getHangar().getCodigo());
            pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(obj.getDataIngresso()));
            pstmt.setInt(4, (int) obj.getTempoEstadia().toSeconds());

            pstmt.executeUpdate();

            return true;
        } catch (java.sql.SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
        return false;
    }

    @Override
    public boolean excluir(CPF obj) {
        return false;
    }

    @Override
    public boolean editar(Visita obj) {
        return false;
    }

    @Override
    public Visita pesquisar(CPF obj) {
        if(obj == null) return null;
        String sql = "SELECT * FROM visita WHERE cpf_visitante = ?";
        try {
            java.sql.PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, obj.toStringNaoFormatado());
            java.sql.ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                Visita visita = new Visita(
                        rs.getTimestamp("data_ingresso").toLocalDateTime(),
                        VisitanteDAO.getInstance().pesquisar(new CPF(rs.getString("cpf_visitante"))),
                        HangarDAO.getInstance().pesquisar(rs.getInt("cod_hangar"))
                                );
                visita.setTempoEstadia(Duration.ofSeconds(rs.getInt("tempo_estadia")));
                return visita;
            }
        } catch (java.sql.SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
        return null;
    }

    @Override
    public ArrayList<Visita> pesquisarTudo() {
        ArrayList<Visita> visitas = new ArrayList<>();
        String sql = "SELECT * FROM visita";
        try {
            java.sql.PreparedStatement pstmt = conexao.prepareStatement(sql);
            java.sql.ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                visitas.add(new Visita(
                        rs.getTimestamp("data_ingresso").toLocalDateTime(),
                        VisitanteDAO.getInstance().pesquisar(new CPF(rs.getString("cpf_visitante"))),
                        HangarDAO.getInstance().pesquisar(rs.getInt("cod_hangar"))
                ));
            }
        } catch (java.sql.SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
        return visitas;
    }
}
