package Persistance;

import java.util.ArrayList;

public interface DAO<T, K> {
    //retorna se a inserção foi bem-sucedida
    public K inserir(T obj);
    //retorna se a exclusão foi bem-sucedida (retorna falso caso não seja encontrado um item)
    public boolean excluir(K obj);
    //retorna se a edição foi bem-sucedida (retorna falso caso não exista em primeiro lugar)
    public boolean editar(T obj);
    //retorna o objeto, caso exista, e null, se não existe
    public T pesquisar(K obj);

    public ArrayList<T> pesquisarTudo();
}