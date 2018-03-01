package helper;

import java.util.List;

import model.Tarefa;

/**
 * Created by Diego on 27/02/2018.
 */

public interface ITarefaDAO {

    public boolean salvar(Tarefa tarefa);
    public boolean atualizar(Tarefa tarefa);
    public boolean deletar(Tarefa tarefa);
    public List<Tarefa> listar();
}
