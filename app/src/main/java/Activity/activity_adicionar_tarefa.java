package Activity;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import cursoandroid.cardoso.thiago.com.listatarefas.R;
import helper.TarefaDAO;
import model.Tarefa;

public class activity_adicionar_tarefa extends AppCompatActivity {

    private TextInputEditText editTarefa;
    private Tarefa tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        editTarefa = findViewById(R.id.textTarefa);

        //Recuperar tarefa, caso seja edição
        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");

        if(tarefaAtual != null)
        {
            editTarefa.setText( tarefaAtual.getNomeTarefa() );
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar_tarefa, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.itemSalvar:
                //Executa a ação para o item salvar
                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

              if( tarefaAtual != null )
              {
                  String nomeTarefa = editTarefa.getText().toString();

                  if(!nomeTarefa.isEmpty())
                  {
                      Tarefa tarefa = new Tarefa();
                      tarefa.setNomeTarefa(nomeTarefa);
                      tarefa.setId(tarefaAtual.getId());

                      if(tarefaDAO.atualizar(tarefa))
                      {
                          finish();
                          Toast.makeText(getApplicationContext(), "Sucesso ao atualizar tarefa!", Toast.LENGTH_LONG).show();

                      }else{

                          Toast.makeText(getApplicationContext(), "Erro ao atualizar tarefa!", Toast.LENGTH_LONG).show();
                      }
                  }

              }else{

                  String nomeTarefa = editTarefa.getText().toString();
                  Tarefa tarefa = new Tarefa();
                  tarefa.setNomeTarefa(nomeTarefa);

                  if(tarefaDAO.salvar(tarefa))
                  {
                      Toast.makeText(getApplicationContext(), "Sucesso ao salvar tarefa!", Toast.LENGTH_LONG).show();
                      finish();
                  }else{
                      Toast.makeText(getApplicationContext(), "Erro ao salvar tarefa!", Toast.LENGTH_LONG).show();
                      finish();
                  }

              }
                //Toast.makeText(activity_adicionar_tarefa.this,
                        //"Botao salvar presssionado", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
