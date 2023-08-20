package Logic;

public interface DAO {
    //retorna se a inserção foi bem-sucedida
    public boolean inserir(Object obj);
    //retorna se a exclusão foi bem-sucedida (retorna falso caso não seja encontrado um item)
    public boolean excluir(Object obj);
    //retorna se a edição foi bem-sucedida (retorna falso caso não exista em primeiro lugar)
    public boolean editar(Object obj);
    //retorna o objeto, caso exista, e null, se não existe
    public Object pesquisar(Object obj);
}