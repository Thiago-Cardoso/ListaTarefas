package helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Diego on 27/02/2018.
 */

public class DbHelper extends SQLiteOpenHelper{

    public static int VERSION = 4;
    public static String NOME_DB = "DB_TAREFAS";
    public static String TABELAS_TAREFAS = "tarefas";

    public DbHelper(Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


       String sql = "CREATE TABLE IF NOT EXISTS " + TABELAS_TAREFAS
               + " (id INTEGER PRIMARY KEY AUTOINCREMENT ," +
               " nome TEXT NOT NULL  ); ";

       try {

           db.execSQL(sql);
           Log.i("INFO DB", "Sucesso ao criar a tabela");
       }catch (Exception e)
       {
           Log.i("INFO DB", "Erro ao criar a tabela" + e.getMessage());
       }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS " + TABELAS_TAREFAS + " ; ";

        try {

            db.execSQL(sql);
            onCreate(db);
            Log.i("INFO DB", "Sucesso ao atualizar App ");
        }catch (Exception e)
        {
            Log.i("INFO DB", "Erro ao atualizar App " + e.getMessage());
        }
    }
}
