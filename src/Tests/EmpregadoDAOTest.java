package Tests;
import Persistance.*;
import Logic.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EmpregadoDAOTest {

    @BeforeAll
    public static void transactionRollbackStart(){
        try {
            ConnectBD bd = ConnectBD.getInstance();
            ConnectBD.getConexao().setAutoCommit(false);
            ConnectBD.getConexao().setSavepoint();
            bd = null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testarEmpregadoDAO() {
        EmpregadoDAO empregadoDAO = EmpregadoDAO.getInstance();
        Departamento departamento = DepartamentoDAO.getInstance().pesquisar(1);
        Endereco endereco = new Endereco("Rua Teste", "Teste", "Teste", 123, "Teste", "Teste", "Teste");
        Empregado empregado = new Empregado(new CPF("30030030030"), LocalDate.now(), "Teste", "Teste", new BigDecimal(1000), endereco, departamento);
        assert empregadoDAO != null : "EmpregadoDAO é nula!";
        assert empregadoDAO.inserir(null) == null : "EmpregadoDAO inseriu um empregado nulo!";
        assert !empregadoDAO.excluir(null) : "EmpregadoDAO excluiu um empregado nulo!";
        assert !empregadoDAO.editar(null) : "EmpregadoDAO editou um empregado nulo!";

        assert empregadoDAO.inserir(empregado) != null : "EmpregadoDAO não inseriu um empregado válido!";
        assert empregadoDAO.inserir(empregado)  == null : "EmpregadoDAO inseriu um empregado já existente!";
        assert empregadoDAO.editar(empregado) : "EmpregadoDAO não editou um empregado válido!";
        assert empregadoDAO.pesquisar(empregado.getCpf()) != null : "EmpregadoDAO não pesquisou um empregado válido!";
        assert empregadoDAO.excluir(empregado.getCpf()) : "EmpregadoDAO não excluiu um empregado válido!";
        assert empregadoDAO.pesquisar(empregado.getCpf()) == null : "EmpregadoDAO não excluiu um empregado válido!";
        assert !empregadoDAO.excluir(empregado.getCpf()) : "EmpregadoDAO excluiu um empregado inexistente!";
        assert !empregadoDAO.editar(empregado) : "EmpregadoDAO editou um empregado inexistente!";

        System.out.println(empregadoDAO.pesquisarTudo());

    }

    @AfterAll
    public static void transactionRollbackEnd(){
        try {
            ConnectBD.getConexao().rollback();
            ConnectBD.getConexao().setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
