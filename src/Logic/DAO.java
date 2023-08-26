package Logic;

public interface DAO<T> {
    //retorna se a inserção foi bem-sucedida
    public boolean inserir(T obj);
    //retorna se a exclusão foi bem-sucedida (retorna falso caso não seja encontrado um item)
    public boolean excluir(T obj);
    //retorna se a edição foi bem-sucedida (retorna falso caso não exista em primeiro lugar)
    public boolean editar(T obj);
    //retorna o objeto, caso exista, e null, se não existe
    public T pesquisar(T obj);
}