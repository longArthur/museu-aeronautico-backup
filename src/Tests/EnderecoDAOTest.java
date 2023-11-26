package Tests;

import Logic.*;
import Persistance.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class EnderecoDAOTest {
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
    public void testarEnderecoDAO(){
        EnderecoDAO enderecoDAO = EnderecoDAO.getInstance();
        Endereco endereco = new Endereco("Rua Teste", "Teste", "Teste", 123, "Teste", "Teste", "Teste");
        assert enderecoDAO != null : "EnderecoDAO é nula!";
        assert enderecoDAO.inserir(null)  == null : "EnderecoDAO inseriu um endereco nulo!";
        assert !enderecoDAO.excluir(null) : "EnderecoDAO excluiu um endereco nulo!";
        assert !enderecoDAO.editar(null) : "EnderecoDAO editou um endereco nulo!";

        endereco.setCodigo(enderecoDAO.inserir(endereco));

        assert enderecoDAO.inserir(endereco)  != null : "EnderecoDAO inseriu um endereco já existente!";

        assert enderecoDAO.editar(endereco) : "EnderecoDAO não editou um endereco válido!";

        assert enderecoDAO.pesquisar(endereco.getCodigo()) != null : "EnderecoDAO não pesquisou um endereco válido!";
        assert enderecoDAO.excluir(endereco.getCodigo()) : "EnderecoDAO não excluiu um endereco válido!";
        assert enderecoDAO.pesquisar(endereco.getCodigo()) == null : "EnderecoDAO não excluiu um endereco válido!";
        assert !enderecoDAO.excluir(endereco.getCodigo()) : "EnderecoDAO excluiu um endereco inexistente!";
        assert !enderecoDAO.editar(endereco) : "EnderecoDAO editou um endereco inexistente!";
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
