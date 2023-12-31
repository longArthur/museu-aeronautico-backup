package Persistance;

import Logic.CPF;
import Logic.Visita;

import java.sql.Connection;
import java.time.Duration;
import java.util.ArrayList;

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
    public CPF inserir(Visita obj) {
        if(obj == null) return null;
        String sql = "INSERT INTO visita (cpf_visitante, cod_hangar, data_ingresso, tempo_estadia)"
                + "VALUES (?, ?, ?, ?)";
        try {
            java.sql.PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, obj.getVisitante().getCpf().toStringNaoFormatado());
            pstmt.setInt(2, obj.getHangar().getCodigo());
            pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(obj.getDataIngresso()));

            if (obj.getTempoEstadia() == null) {
                pstmt.setNull(4, java.sql.Types.INTEGER);
            } else {
                pstmt.setInt(4, (int) obj.getTempoEstadia().toSeconds());
            }

            pstmt.executeUpdate();

            return obj.getVisitante().getCpf();
        } catch (java.sql.SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
        return null;
    }

    @Override
    public boolean excluir(CPF obj) {
        if(obj == null) return false;
        if(pesquisar(obj) == null) return false;
        String sql = "DELETE FROM visita WHERE cpf_visitante = ?";
        try {
            java.sql.PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, obj.toStringNaoFormatado());
            pstmt.executeUpdate();
            return true;
        } catch (java.sql.SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
        return false;
    }

    @Override
    public boolean editar(Visita obj) {
        if(obj == null) return false;
        if(pesquisar(obj.getVisitante().getCpf()) == null) return false;
        String sql = "UPDATE visita SET cod_hangar = ?, tempo_estadia = ? WHERE cpf_visitante = ? AND data_ingresso = ?";
        try {
            java.sql.PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, obj.getHangar().getCodigo());
            if (obj.getTempoEstadia() == null) {
                pstmt.setNull(2, java.sql.Types.INTEGER);
            } else {
                pstmt.setInt(2, (int) obj.getTempoEstadia().toSeconds());
            }
            pstmt.setString(3, obj.getVisitante().getCpf().toStringNaoFormatado());
            pstmt.setTimestamp(4, java.sql.Timestamp.valueOf(obj.getDataIngresso()));


            pstmt.executeUpdate();
            return true;
        } catch (java.sql.SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
        return false;
    }

    @Override
    public Visita pesquisar(CPF obj) {
        if(obj == null) return null;
        String sql = "SELECT * FROM visita WHERE cpf_visitante = ? ORDER BY data_ingresso DESC LIMIT 1";
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
        String sql = "SELECT * FROM visita ORDER BY data_ingresso";
        try {
            java.sql.PreparedStatement pstmt = conexao.prepareStatement(sql);
            java.sql.ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Visita visita = new Visita(
                        rs.getTimestamp("data_ingresso").toLocalDateTime(),
                        VisitanteDAO.getInstance().pesquisar(new CPF(rs.getString("cpf_visitante"))),
                        HangarDAO.getInstance().pesquisar(rs.getInt("cod_hangar"))
                );
                visita.setTempoEstadia(Duration.ofSeconds(rs.getInt("tempo_estadia")));
                visitas.add(visita);
            }
        } catch (java.sql.SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
        return visitas;
    }
}
