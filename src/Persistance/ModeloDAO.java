package Persistance;


import Logic.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModeloDAO implements DAO<Modelo, Integer> {
    private static ModeloDAO modeloDAO;
    private static Connection conexao;

    public static ModeloDAO getInstance() {
        if(modeloDAO == null){
            modeloDAO = new ModeloDAO();
        }
        return modeloDAO;
    }

    private ModeloDAO() {
        try {
            ConnectBD bd = ConnectBD.getInstance();
            conexao = ConnectBD.getConexao();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro = " + ex);
        }
    }

    @Override
    public Integer inserir(Modelo obj) {
        if (obj == null) return null;
        // public Modelo(String marca, LocalDate dataProducao, double comprimentoMetros, double envergaduraMetros, String historia, Tipo tipo, String areaAtuacao,
        //                  String materialUsado, Hangar hangar, String estado)
        String sql = "INSERT INTO modelo (marca, data_producao, comprimento_metros, largura_metros, historia_aviao, tipo, cod_hangar, area_atuacao, material_usado, estado) VALUES"
                + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, obj.getMarca());
            pstmt.setDate(2, java.sql.Date.valueOf(obj.getDataProducao()));
            pstmt.setDouble(3, obj.getComprimentoMetros());
            pstmt.setDouble(4, obj.getEnvergaduraMetros());
            pstmt.setString(5, obj.getHistoria());
            pstmt.setString(6, obj.getTipo().toString());
            pstmt.setInt(7, obj.getHangar().getCodigo());
            pstmt.setString(8, obj.getAreaAtuacao());
            pstmt.setString(9, obj.getMaterialUsado());
            pstmt.setString(10, obj.getEstado());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
        return null;
    }

    @Override
    public boolean excluir(Integer obj) {
        if (obj == null) return false;
        if (this.pesquisar(obj) == null) return false;
        String sql = "DELETE FROM modelo WHERE codigo = ?";
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, obj);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro = " + e);
        }
        return false;
    }

    @Override
    public boolean editar(Modelo obj) {
        if (obj == null) return false;
        if (this.pesquisar(obj.getCodigo()) == null) return false;
        String sql = "UPDATE modelo SET marca = ?, data_producao = ?, comprimento_metros = ?, largura_metros = ?, historia_aviao = ?, tipo = ?, cod_hangar = ?, area_atuacao = ?, material_usado = ?, estado = ? WHERE codigo = ?";
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, obj.getMarca());
            pstmt.setDate(2, java.sql.Date.valueOf(obj.getDataProducao()));
            pstmt.setDouble(3, obj.getComprimentoMetros());
            pstmt.setDouble(4, obj.getEnvergaduraMetros());
            pstmt.setString(5, obj.getHistoria());
            pstmt.setString(6, obj.getTipo().toString());
            pstmt.setInt(7, obj.getHangar().getCodigo());
            pstmt.setString(8, obj.getAreaAtuacao());
            pstmt.setString(9, obj.getMaterialUsado());
            pstmt.setString(10, obj.getEstado());
            pstmt.setInt(11, obj.getCodigo());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro = " + e);
        }
        return false;
    }

    @Override
    public Modelo pesquisar(Integer obj) {
        if (obj == null) return null;
        String sql = "SELECT * FROM modelo WHERE codigo = ?";
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, obj);

            ResultSet resultado = pstmt.executeQuery();
            if (resultado.next()) {
                //public Modelo(String marca, LocalDate dataProducao, double comprimentoMetros, double envergaduraMetros, String historia, Tipo tipo, String areaAtuacao,
                //                  String materialUsado, Hangar hangar, String estado)
                Modelo modelo = new Modelo(resultado.getString("marca"),
                        resultado.getDate("data_producao").toLocalDate(),
                        resultado.getDouble("comprimento_metros"),
                        resultado.getDouble("largura_metros"),
                        resultado.getString("historia_aviao"),
                        resultado.getString("tipo").equalsIgnoreCase("AVIAO") ? Modelo.Tipo.AVIAO : Modelo.Tipo.REPLICA,
                        resultado.getString("area_atuacao"),
                        resultado.getString("material_usado"),
                        HangarDAO.getInstance().pesquisar(resultado.getInt("cod_hangar")),
                        resultado.getString("estado"));
                modelo.setCodigo(resultado.getInt("codigo"));
                return modelo;
            }
        } catch (SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
        return null;
    }

    @Override
    public ArrayList<Modelo> pesquisarTudo() {
        ArrayList<Modelo> modelos = new ArrayList<>();
        String sql = "SELECT * FROM modelo";
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            ResultSet resultado = pstmt.executeQuery();
            while (resultado.next()) {
                //public Modelo(String marca, LocalDate dataProducao, double comprimentoMetros, double envergaduraMetros, String historia, Tipo tipo, String areaAtuacao,
                //                  String materialUsado, Hangar hangar, String estado)
                Modelo modelo = new Modelo(resultado.getString("marca"),
                        resultado.getDate("data_producao").toLocalDate(),
                        resultado.getDouble("comprimento_metros"),
                        resultado.getDouble("largura_metros"),
                        resultado.getString("historia_aviao"),
                        resultado.getString("tipo").equalsIgnoreCase("AVIAO") ? Modelo.Tipo.AVIAO : Modelo.Tipo.REPLICA,
                        resultado.getString("area_atuacao"),
                        resultado.getString("material_usado"),
                        HangarDAO.getInstance().pesquisar(resultado.getInt("cod_hangar")),
                        resultado.getString("estado"));
                modelo.setCodigo(resultado.getInt("codigo"));
                modelos.add(modelo);
            }
        } catch (SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
        return modelos;
    }
}
