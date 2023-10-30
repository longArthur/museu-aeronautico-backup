package Persistance;

import Logic.Hangar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HangarDAO implements DAO<Hangar, Integer> {
    private static HangarDAO hangarDAO;
    private static Connection conexao;

    public static HangarDAO getInstance() {
        if(hangarDAO == null){
            hangarDAO = new HangarDAO();
        }
        return hangarDAO;
    }

    private HangarDAO() {
        try {
            ConnectBD bd = ConnectBD.getInstance();
            conexao = ConnectBD.getConexao();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro = " + ex);
        }
    }

    @Override
    public boolean inserir(Hangar obj) {
        if(obj == null) return false;
        String sql = "INSERT INTO hangar (bloco, qtd_vagas, largura_metros, comprimento_metros, capacidade_visitantes, cod_departamento, endereco)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, obj.getBloco());
            pstmt.setInt(2, obj.getQuantidadeVagas());
            pstmt.setDouble(3, obj.getLarguraMetros());
            pstmt.setDouble(4, obj.getComprimentoMetros());
            pstmt.setInt(5, obj.getCapacidadeVisitantes());
            pstmt.setInt(6, obj.getDepartamento().getCodigo());
            pstmt.setInt(7, obj.getEndereco().getCodigo());
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
    public boolean editar(Hangar obj) {
        return false;
    }

    @Override
    public Hangar pesquisar(Integer obj) {
        if(obj == null) return null;
        String sql = "SELECT * FROM hangar WHERE codigo = ?";
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, obj);
            var rs = pstmt.executeQuery();
            if(rs.next()){
                Hangar hangar = new Hangar(rs.getString("bloco"), rs.getInt("qtd_vagas"), rs.getDouble("largura_metros"),
                        rs.getDouble("comprimento_metros"), rs.getInt("capacidade_visitantes"),
                        EnderecoDAO.getInstance().pesquisar(rs.getInt("endereco")),
                        DepartamentoDAO.getInstance().pesquisar(rs.getInt("cod_departamento")));
                hangar.setCodigo(rs.getInt("codigo"));
                return hangar;
            }
        } catch (SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
        return null;
    }

    @Override
    public ArrayList<Hangar> pesquisarTudo() {
        ArrayList<Hangar> hangares = new ArrayList<>();
        String sql = "SELECT * FROM hangar";
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            var rs = pstmt.executeQuery();
            while(rs.next()){
                Hangar hangar = new Hangar(rs.getString("bloco"), rs.getInt("qtd_vagas"), rs.getDouble("largura_metros"),
                        rs.getDouble("comprimento_metros"), rs.getInt("capacidade_visitantes"),
                        EnderecoDAO.getInstance().pesquisar(rs.getInt("endereco")),
                        DepartamentoDAO.getInstance().pesquisar(rs.getInt("cod_departamento")));
                hangar.setCodigo(rs.getInt("codigo"));
                hangares.add(hangar);
            }
        } catch (SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
        return hangares;
    }
}
