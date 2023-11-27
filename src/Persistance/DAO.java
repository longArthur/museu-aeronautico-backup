package Persistance;

import java.util.ArrayList;

public interface DAO<T, K> {
    //retorna se a inserção foi bem-sucedida
    K inserir(T obj);
    //retorna se a exclusão foi bem-sucedida (retorna falso caso não seja encontrado um item)
    boolean excluir(K obj);
    //retorna se a edição foi bem-sucedida (retorna falso caso não exista em primeiro lugar)
    boolean editar(T obj);
    //retorna o objeto, caso exista, e null, se não existe
    T pesquisar(K obj);

    ArrayList<T> pesquisarTudo();
}