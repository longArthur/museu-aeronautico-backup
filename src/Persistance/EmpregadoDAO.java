package Persistance;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import Logic.*;

public class EmpregadoDAO implements DAO<Empregado, CPF> {

    private Connection conexao;
    private static EmpregadoDAO empregadoDAO;

    public static EmpregadoDAO getInstance() {
        if(empregadoDAO == null){
            empregadoDAO = new EmpregadoDAO();
        }
        return empregadoDAO;
    }

    private EmpregadoDAO() {
        try {
            ConnectBD bd = ConnectBD.getInstance();
            conexao = ConnectBD.getConexao();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro = " + ex);
        }
    }

    @Override
    public boolean inserir(Empregado emp) {
        if (emp == null) return false;

        String cpf = emp.getCpf().toStringNaoFormatado();
        LocalDate data_ingresso = emp.getDataIngresso();
        String nome = emp.getNome();
        String sobrenome = emp.getSobrenome();
        BigDecimal salario = emp.getSalario();
        int endereco = emp.getEndereco().getCodigo();
        int departamento = emp.getDepartamento().getCodigo();

        if(emp instanceof Gerente gerente){
            try {
                PreparedStatement pstmt = conexao.prepareStatement("INSERT INTO empregado (CPF, data_ingressao, nome, sobrenome, salario, tipo, inicio_gerencia)"
                        + "VALUES (?, ?, ?, ?, ?, ?, ?)");
                pstmt.setString(1, cpf);
                pstmt.setDate(2, java.sql.Date.valueOf(data_ingresso));
                pstmt.setString(3, nome);
                pstmt.setString(4, sobrenome);
                pstmt.setBigDecimal(5, salario);
                pstmt.setString(6, "gerente");
                pstmt.setDate(7, java.sql.Date.valueOf(gerente.getInicio_gerencia()));

                EnderecoDAO.getInstance().inserir(gerente.getEndereco());

                pstmt.executeUpdate();

                return true;
            } catch (SQLException sqe) {
                System.out.println("Erro = " + sqe);
            }
        }
        if(emp instanceof Engenheiro engenheiro){
            try {
                PreparedStatement pstmt = conexao.prepareStatement("INSERT INTO empregado (CPF, data_ingressao, nome, sobrenome, salario, tipo, CREA, area_atuacao)"
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                pstmt.setString(1, cpf);
                pstmt.setDate(2, java.sql.Date.valueOf(data_ingresso));
                pstmt.setString(3, nome);
                pstmt.setString(4, sobrenome);
                pstmt.setBigDecimal(5, salario);
                pstmt.setString(6, "engenheiro");
                pstmt.setString(7, engenheiro.getCrea());
                pstmt.setString(8, engenheiro.getAreaAtuacao());

                EnderecoDAO.getInstance().inserir(engenheiro.getEndereco());

                pstmt.executeUpdate();

                return true;
            } catch (SQLException sqe) {
                System.out.println("Erro = " + sqe);
            }
        }
        if(emp instanceof Piloto piloto){
            try {
                PreparedStatement pstmt = conexao.prepareStatement("INSERT INTO empregado (CPF, data_ingressao, nome, sobrenome, salario, tipo, CHT)"
                        + "VALUES (?, ?, ?, ?, ?, ?, ?)");
                pstmt.setString(1, cpf);
                pstmt.setDate(2, java.sql.Date.valueOf(data_ingresso));
                pstmt.setString(3, nome);
                pstmt.setString(4, sobrenome);
                pstmt.setBigDecimal(5, salario);
                pstmt.setString(6, "piloto");
                pstmt.setString(7, piloto.getCHT());

                EnderecoDAO.getInstance().inserir(piloto.getEndereco());

                pstmt.executeUpdate();

                return true;
            } catch (SQLException sqe) {
                System.out.println("Erro = " + sqe);
            }
        }
        if(emp instanceof Historiador historiador){
            try {
                PreparedStatement pstmt = conexao.prepareStatement("INSERT INTO empregado (CPF, data_ingressao, nome, sobrenome, salario, tipo, registro)"
                        + "VALUES (?, ?, ?, ?, ?, ?, ?)");
                pstmt.setString(1, cpf);
                pstmt.setDate(2, java.sql.Date.valueOf(data_ingresso));
                pstmt.setString(3, nome);
                pstmt.setString(4, sobrenome);
                pstmt.setBigDecimal(5, salario);
                pstmt.setString(6, "historiador");
                pstmt.setString(7, historiador.getRegistro());

                EnderecoDAO.getInstance().inserir(historiador.getEndereco());


                pstmt.executeUpdate();

                return true;
            } catch (SQLException sqe) {
                System.out.println("Erro = " + sqe);
            }
        }
        try {
            PreparedStatement pstmt = conexao.prepareStatement("INSERT INTO empregado (CPF, data_ingressao, nome, sobrenome, salario, tipo)"
                    + "VALUES (?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, cpf);
            pstmt.setDate(2, java.sql.Date.valueOf(data_ingresso));
            pstmt.setString(3, nome);
            pstmt.setString(4, sobrenome);
            pstmt.setBigDecimal(5, salario);
            pstmt.setString(6, "empregado");

            EnderecoDAO.getInstance().inserir(emp.getEndereco());

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
    public boolean editar(Empregado obj) {
        return false;
    }

    @Override
    public Empregado pesquisar(CPF cpf) {
        if (cpf == null) return null;
        try {
            PreparedStatement pstmt = conexao.prepareStatement("SELECT * FROM empregado WHERE CPF = ?");
            pstmt.setString(1, cpf.toStringNaoFormatado());

            ResultSet resultado = pstmt.executeQuery();

            if (resultado.next()) {
                String nome = resultado.getString("nome");
                String sobrenome = resultado.getString("sobrenome");
                LocalDate data_ingresso = resultado.getDate("data_ingressao").toLocalDate();
                BigDecimal salario = resultado.getBigDecimal("salario");
                String tipo = resultado.getString("tipo");
                Endereco endereco = EnderecoDAO.getInstance().pesquisar(resultado.getInt("endereco"));
                Departamento departamento = (Departamento) DepartamentoDAO.getInstance().pesquisar(resultado.getInt("departamento"));

                for(Departamento departamento1 : DepartamentoDAO.getInstance().pesquisar()){
                    System.out.println(departamento1);
                }
                System.out.println(departamento);

                if(tipo.equals("gerente")){
                    LocalDate inicio_gerencia = resultado.getDate("inicio_gerencia").toLocalDate();
                    return new Gerente(cpf, data_ingresso, nome, sobrenome, salario, endereco, departamento, inicio_gerencia);
                }
                if(tipo.equals("engenheiro")){
                    String crea = resultado.getString("CREA");
                    String area_atuacao = resultado.getString("area_atuacao");
                    return new Engenheiro(cpf, data_ingresso, nome, sobrenome, salario, endereco, departamento, crea, area_atuacao);
                }
                if(tipo.equals("piloto")){
                    String CHT = resultado.getString("CHT");
                    return new Piloto(cpf, data_ingresso, nome, sobrenome, salario, endereco, departamento, CHT);
                }
                if(tipo.equals("historiador")){
                    String registro = resultado.getString("registro");
                    return new Historiador(cpf, data_ingresso, nome, sobrenome, salario, endereco, departamento, registro);
                }
                return new Empregado(cpf, data_ingresso, nome, sobrenome, salario, endereco, departamento);
            }
        } catch (SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
        return null;
    }

    @Override
    public ArrayList<Empregado> pesquisarTudo() {
        ArrayList<Empregado> empregados = new ArrayList<>();
        try {
            PreparedStatement pstmt = conexao.prepareStatement("SELECT * FROM empregado");
            ResultSet resultado = pstmt.executeQuery();

            while (resultado.next()) {
                String cpf = resultado.getString("CPF");
                String nome = resultado.getString("nome");
                String sobrenome = resultado.getString("sobrenome");
                LocalDate data_ingresso = resultado.getDate("data_ingressao").toLocalDate();
                BigDecimal salario = resultado.getBigDecimal("salario");
                String tipo = resultado.getString("tipo");
                Endereco endereco = EnderecoDAO.getInstance().pesquisar(resultado.getInt("endereco"));
                Departamento departamento = (Departamento) DepartamentoDAO.getInstance().pesquisar(resultado.getInt("departamento"));

                if(tipo.equals("gerente")){
                    LocalDate inicio_gerencia = resultado.getDate("inicio_gerencia").toLocalDate();
                    empregados.add(new Gerente(new CPF(cpf), data_ingresso, nome, sobrenome, salario, endereco, departamento, inicio_gerencia));
                }
                if(tipo.equals("engenheiro")){
                    String crea = resultado.getString("CREA");
                    String area_atuacao = resultado.getString("area_atuacao");
                    empregados.add(new Engenheiro(new CPF(cpf), data_ingresso, nome, sobrenome, salario, endereco, departamento, crea, area_atuacao));
                }
                if(tipo.equals("piloto")){
                    String CHT = resultado.getString("CHT");
                    empregados.add(new Piloto(new CPF(cpf), data_ingresso, nome, sobrenome, salario, endereco, departamento, CHT));
                }
                if(tipo.equals("historiador")){
                    String registro = resultado.getString("registro");
                    empregados.add(new Historiador(new CPF(cpf), data_ingresso, nome, sobrenome, salario, endereco, departamento, registro));
                }
                empregados.add(new Empregado(new CPF(cpf), data_ingresso, nome, sobrenome, salario, endereco, departamento));
            }
        } catch (SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
        return empregados;
    }
}
