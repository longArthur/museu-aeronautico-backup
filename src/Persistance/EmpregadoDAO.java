package Persistance;

import Logic.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

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
    public CPF inserir(Empregado emp) {
        if (emp == null) return null;

        int endereco = emp.getEndereco().getCodigo() == 0 ? EnderecoDAO.getInstance().inserir(emp.getEndereco()) : emp.getEndereco().getCodigo();

        String cpf = emp.getCpf().toStringNaoFormatado();
        LocalDate data_ingresso = emp.getDataIngresso();
        String nome = emp.getNome();
        String sobrenome = emp.getSobrenome();
        BigDecimal salario = emp.getSalario();
        int departamento = emp.getDepartamento().getCodigo();

        if(emp instanceof Gerente gerente){
            try {
                PreparedStatement pstmt = conexao.prepareStatement("INSERT INTO empregado (CPF, data_ingressao, nome, sobrenome, salario, tipo, inicio_gerencia, endereco, departamento)"
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                pstmt.setString(1, cpf);
                pstmt.setDate(2, java.sql.Date.valueOf(data_ingresso));
                pstmt.setString(3, nome);
                pstmt.setString(4, sobrenome);
                pstmt.setBigDecimal(5, salario);
                pstmt.setString(6, "gerente");
                pstmt.setDate(7, java.sql.Date.valueOf(gerente.getInicio_gerencia()));
                pstmt.setInt(8, endereco);
                pstmt.setInt(9, departamento);


                EnderecoDAO.getInstance().inserir(gerente.getEndereco());

                pstmt.executeUpdate();

                return emp.getCpf();
            } catch (SQLException sqe) {
                System.out.println("Erro = " + sqe);
            }
        }
        if(emp instanceof Engenheiro engenheiro){
            try {
                PreparedStatement pstmt = conexao.prepareStatement("INSERT INTO empregado (CPF, data_ingressao, nome, sobrenome, salario, tipo, CREA, area_atuacao, endereco, departamento)"
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                pstmt.setString(1, cpf);
                pstmt.setDate(2, java.sql.Date.valueOf(data_ingresso));
                pstmt.setString(3, nome);
                pstmt.setString(4, sobrenome);
                pstmt.setBigDecimal(5, salario);
                pstmt.setString(6, "engenheiro");
                pstmt.setString(7, engenheiro.getCrea());
                pstmt.setString(8, engenheiro.getAreaAtuacao());
                pstmt.setInt(9, endereco);
                pstmt.setInt(10, departamento);

                EnderecoDAO.getInstance().inserir(engenheiro.getEndereco());

                pstmt.executeUpdate();

                return emp.getCpf();
            } catch (SQLException sqe) {
                System.out.println("Erro = " + sqe);
            }
        }
        if(emp instanceof Piloto piloto){
            try {
                PreparedStatement pstmt = conexao.prepareStatement("INSERT INTO empregado (CPF, data_ingressao, nome, sobrenome, salario, tipo, CHT, endereco, departamento)"
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                pstmt.setString(1, cpf);
                pstmt.setDate(2, java.sql.Date.valueOf(data_ingresso));
                pstmt.setString(3, nome);
                pstmt.setString(4, sobrenome);
                pstmt.setBigDecimal(5, salario);
                pstmt.setString(6, "piloto");
                pstmt.setString(7, piloto.getCHT());
                pstmt.setInt(8, endereco);
                pstmt.setInt(9, departamento);

                EnderecoDAO.getInstance().inserir(piloto.getEndereco());

                pstmt.executeUpdate();

                return emp.getCpf();
            } catch (SQLException sqe) {
                System.out.println("Erro = " + sqe);
            }
        }
        if(emp instanceof Historiador historiador){
            try {
                PreparedStatement pstmt = conexao.prepareStatement("INSERT INTO empregado (CPF, data_ingressao, nome, sobrenome, salario, tipo, registro, endereco, departamento)"
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                pstmt.setString(1, cpf);
                pstmt.setDate(2, java.sql.Date.valueOf(data_ingresso));
                pstmt.setString(3, nome);
                pstmt.setString(4, sobrenome);
                pstmt.setBigDecimal(5, salario);
                pstmt.setString(6, "historiador");
                pstmt.setString(7, historiador.getRegistro());
                pstmt.setInt(8, endereco);
                pstmt.setInt(9, departamento);

                EnderecoDAO.getInstance().inserir(historiador.getEndereco());


                pstmt.executeUpdate();

                return emp.getCpf();
            } catch (SQLException sqe) {
                System.out.println("Erro = " + sqe);
            }
        }
        try {
            PreparedStatement pstmt = conexao.prepareStatement("INSERT INTO empregado (CPF, data_ingressao, nome, sobrenome, salario, tipo, endereco, departamento)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, cpf);
            pstmt.setDate(2, java.sql.Date.valueOf(data_ingresso));
            pstmt.setString(3, nome);
            pstmt.setString(4, sobrenome);
            pstmt.setBigDecimal(5, salario);
            pstmt.setString(6, "empregado");
            pstmt.setInt(7, endereco);
            pstmt.setInt(8, departamento);



            pstmt.executeUpdate();

            return emp.getCpf();
        } catch (SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
        return null;
    }

    @Override
    public boolean excluir(CPF obj) {
        if(obj == null) return false;
        String sql = "DELETE FROM empregado WHERE CPF = ?";
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, obj.toStringNaoFormatado());

            pstmt.executeUpdate();

            if (pstmt.getUpdateCount() > 0) {
                return true;
            }
        } catch (SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
        return false;
    }

    @Override
    public boolean editar(Empregado obj) {
        if(obj == null) return false;
        if(EmpregadoDAO.getInstance().pesquisar(obj.getCpf()) == null) return false;
        if(obj.getEndereco().getCodigo() == 0) EnderecoDAO.getInstance().inserir(obj.getEndereco());

        try {
            String sql = "";

            if (obj instanceof Gerente gerente) {
                sql = "UPDATE empregado SET data_ingressao = ?, nome = ?, sobrenome = ?, salario = ?, tipo = ?, inicio_gerencia = ?, endereco = ?, departamento = ? WHERE CPF = ?";
                PreparedStatement pstmt = conexao.prepareStatement(sql);
                pstmt.setDate(1, java.sql.Date.valueOf(obj.getDataIngresso()));
                pstmt.setString(2, obj.getNome());
                pstmt.setString(3, obj.getSobrenome());
                pstmt.setBigDecimal(4, obj.getSalario());
                pstmt.setString(5, "gerente");
                pstmt.setDate(6, java.sql.Date.valueOf(gerente.getInicio_gerencia()));
                pstmt.setInt(7, obj.getEndereco().getCodigo());
                pstmt.setInt(8, obj.getDepartamento().getCodigo());
                pstmt.setString(9, obj.getCpf().toStringNaoFormatado());
                pstmt.executeUpdate();
            }
            else if (obj instanceof Engenheiro engenheiro) {
                sql = "UPDATE empregado SET data_ingressao = ?, nome = ?, sobrenome = ?, salario = ?, tipo = ?, CREA = ?, area_atuacao = ?, endereco = ?, departamento = ? WHERE CPF = ?";
                PreparedStatement pstmt = conexao.prepareStatement(sql);
                pstmt.setDate(1, java.sql.Date.valueOf(obj.getDataIngresso()));
                pstmt.setString(2, obj.getNome());
                pstmt.setString(3, obj.getSobrenome());
                pstmt.setBigDecimal(4, obj.getSalario());
                pstmt.setString(5, "engenheiro");
                pstmt.setString(6, engenheiro.getCrea());
                pstmt.setString(7, engenheiro.getAreaAtuacao());
                pstmt.setInt(8, obj.getEndereco().getCodigo());
                pstmt.setInt(9, obj.getDepartamento().getCodigo());
                pstmt.setString(10, obj.getCpf().toStringNaoFormatado());
                pstmt.executeUpdate();
            }
            else if (obj instanceof Piloto piloto) {
                sql = "UPDATE empregado SET data_ingressao = ?, nome = ?, sobrenome = ?, salario = ?, tipo = ?, CHT = ?, endereco = ?, departamento = ? WHERE CPF = ?";
                PreparedStatement pstmt = conexao.prepareStatement(sql);
                pstmt.setDate(1, java.sql.Date.valueOf(obj.getDataIngresso()));
                pstmt.setString(2, obj.getNome());
                pstmt.setString(3, obj.getSobrenome());
                pstmt.setBigDecimal(4, obj.getSalario());
                pstmt.setString(5, "piloto");
                pstmt.setString(6, piloto.getCHT());
                pstmt.setInt(7, obj.getEndereco().getCodigo());
                pstmt.setInt(8, obj.getDepartamento().getCodigo());
                pstmt.setString(9, obj.getCpf().toStringNaoFormatado());
                pstmt.executeUpdate();
            }
            else if (obj instanceof Historiador historiador) {
                sql = "UPDATE empregado SET data_ingressao = ?, nome = ?, sobrenome = ?, salario = ?, tipo = ?, registro = ?, endereco = ?, departamento = ? WHERE CPF = ?";
                PreparedStatement pstmt = conexao.prepareStatement(sql);
                pstmt.setDate(1, java.sql.Date.valueOf(obj.getDataIngresso()));
                pstmt.setString(2, obj.getNome());
                pstmt.setString(3, obj.getSobrenome());
                pstmt.setBigDecimal(4, obj.getSalario());
                pstmt.setString(5, "historiador");
                pstmt.setString(6, historiador.getRegistro());
                pstmt.setInt(7, obj.getEndereco().getCodigo());
                pstmt.setInt(8, obj.getDepartamento().getCodigo());
                pstmt.setString(9, obj.getCpf().toStringNaoFormatado());
                pstmt.executeUpdate();
            }
            else {
                sql = "UPDATE empregado SET data_ingressao = ?, nome = ?, sobrenome = ?, salario = ?, tipo = ?, endereco = ?, departamento = ? WHERE CPF = ?";
                PreparedStatement pstmt = conexao.prepareStatement(sql);
                pstmt.setDate(1, java.sql.Date.valueOf(obj.getDataIngresso()));
                pstmt.setString(2, obj.getNome());
                pstmt.setString(3, obj.getSobrenome());
                pstmt.setBigDecimal(4, obj.getSalario());
                pstmt.setString(5, "empregado");
                pstmt.setInt(6, obj.getEndereco().getCodigo());
                pstmt.setInt(7, obj.getDepartamento().getCodigo());
                pstmt.setString(8, obj.getCpf().toStringNaoFormatado());
                pstmt.executeUpdate();
            }
            return true;
        } catch (SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
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
                Departamento departamento = DepartamentoDAO.getInstance().pesquisar(resultado.getInt("departamento"));

                

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
                Departamento departamento = DepartamentoDAO.getInstance().pesquisar(resultado.getInt("departamento"));

                if(tipo.equals("gerente")){
                    LocalDate inicio_gerencia = resultado.getDate("inicio_gerencia").toLocalDate();
                    empregados.add(new Gerente(new CPF(cpf), data_ingresso, nome, sobrenome, salario, endereco, departamento, inicio_gerencia));
                }
                else if(tipo.equals("engenheiro")){
                    String crea = resultado.getString("CREA");
                    String area_atuacao = resultado.getString("area_atuacao");
                    empregados.add(new Engenheiro(new CPF(cpf), data_ingresso, nome, sobrenome, salario, endereco, departamento, crea, area_atuacao));
                }
                else if(tipo.equals("piloto")){
                    String CHT = resultado.getString("CHT");
                    empregados.add(new Piloto(new CPF(cpf), data_ingresso, nome, sobrenome, salario, endereco, departamento, CHT));
                }
                else if(tipo.equals("historiador")){
                    String registro = resultado.getString("registro");
                    empregados.add(new Historiador(new CPF(cpf), data_ingresso, nome, sobrenome, salario, endereco, departamento, registro));
                }
                else
                    empregados.add(new Empregado(new CPF(cpf), data_ingresso, nome, sobrenome, salario, endereco, departamento));
            }
        } catch (SQLException sqe) {
            System.out.println("Erro = " + sqe);
        }
        return empregados;
    }
}
