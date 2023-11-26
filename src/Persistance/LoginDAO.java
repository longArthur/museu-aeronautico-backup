package Persistance;

import Logic.CPF;
import Logic.Empregado;
import Logic.Login;

import java.sql.*;
import java.util.ArrayList;

public class LoginDAO implements DAO<Login, CPF> {


    private Connection conexao;
    private static LoginDAO loginDAO;

    public static LoginDAO getInstance() {
        if(loginDAO == null){
            loginDAO = new LoginDAO();
        }
        return loginDAO;
    }

    private LoginDAO() {
        try {
            ConnectBD bd = ConnectBD.getInstance();
            conexao = ConnectBD.getConexao();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro = " + ex);
        }
    }

    @Override
    public CPF inserir(Login login) {
        if (login == null) return null;
        String sql = "INSERT INTO login (CPFEmpregado, senha)"
                + "VALUES (?, ?)";
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, login.getEmpregado().getCpf().toStringNaoFormatado());
            pstmt.setBytes(2, login.getSenhaHash());

            pstmt.executeUpdate();

            return login.getEmpregado().getCpf();
        } catch (SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
        return null;
    }

    @Override
    public boolean excluir(CPF obj) {
        return false;
    }

    @Override
    public boolean editar(Login obj) {
        return false;
    }

    @Override
    public Login pesquisar(CPF cpf) {
        if (cpf == null) return null;
        try {
            String sql = "SELECT * FROM login WHERE CPFEmpregado = '" + cpf.toStringNaoFormatado() + "'";
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.isBeforeFirst()){
                rs.next();
                CPF cpfget = new CPF(rs.getString(1));
                Empregado empregado = (Empregado) EmpregadoDAO.getInstance().pesquisar(cpfget);
                byte[] senha = rs.getBytes(2);

                return new Login(empregado, senha);
            }
        } catch (SQLException e) {
            System.out.println("Erro = " + e);
        }
        return null;
    }

    @Override
    public ArrayList<Login> pesquisarTudo() {
        return null;
    }
}