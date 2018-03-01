package helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.Tarefa;

/**
 * Created by Diego on 27/02/2018.
 */

public class TarefaDAO implements ITarefaDAO{

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public TarefaDAO(Context context) {
        DbHelper db = new DbHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Tarefa tarefa) {

        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNomeTarefa());

    try{
        escreve.insert(DbHelper.TABELAS_TAREFAS, null, cv);
        Log.i("INFO", " Tarefa salva com sucesso!");
    }catch (Exception e)
    {
        Log.i("INFO", " Erro ao salvar tarefa" + e.getMessage());
       return false;
    }
        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {

        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNomeTarefa());

        try{
            String[]  args = {tarefa.getId().toString()};
            escreve.update(DbHelper.TABELAS_TAREFAS, cv , "id=?", args);
            Log.i("INFO", " Tarefa atualizada com sucesso!");
        }catch (Exception e)
        {
            Log.i("INFO", " Erro ao atualizar tarefa" + e.getMessage());
            return false;
        }
        return true;
    }


    @Override
    public boolean deletar(Tarefa tarefa) {

        String[]  args = {tarefa.getId().toString()};

        try{
            escreve.delete(DbHelper.TABELAS_TAREFAS, "id=?", args);
            Log.i("INFO", " Tarefa removida com sucesso!");
        }catch (Exception e)
        {
            Log.i("INFO", " Erro ao remover tarefa" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Tarefa> listar() {

        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM " + DbHelper.TABELAS_TAREFAS + " ;";
        Cursor c = le.rawQuery(sql, null);

        while(c.moveToNext()){

            Tarefa tarefa = new Tarefa();

            Long id = c.getLong(c.getColumnIndex("id"));
            String nomeTarefa = c.getString(c.getColumnIndex("nome"));

            tarefa.setId(id);
            tarefa.setNomeTarefa(nomeTarefa);

            tarefas.add(tarefa);
        }
        return tarefas;
    }
}


