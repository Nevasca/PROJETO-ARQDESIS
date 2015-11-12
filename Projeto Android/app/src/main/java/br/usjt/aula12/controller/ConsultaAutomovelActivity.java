package br.usjt.aula12.controller;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import br.usjt.aula12.adapter.AutomovelAdapter;
import br.usjt.aula12.R;
import br.usjt.aula12.model.Automovel;

import java.util.ArrayList;

public class ConsultaAutomovelActivity extends ActionBarActivity {

    ListView listView;
    Activity atividade;
    public final static String AUTOMOVEL = "br.usjt.AUTOMOVEL";
    Automovel[] automoveis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_automovel);
        atividade = this;

        Intent intent = getIntent();

        TextView textView = (TextView)findViewById(R.id.LBNomeCidade);


        automoveis = ((ArrayList<Automovel>)intent.getSerializableExtra(MainActivity.AUTOMOVEIS)).toArray(new Automovel[0]);
        if(automoveis[0].getId() != 0)
        {
            textView.setText("Automóveis em " + automoveis[0].getCidade());
        }

        else
        {
            textView.setText("Não há automóveis na cidade informada");
        }

        listView = (ListView)findViewById(R.id.LVAutomoveis);
        BaseAdapter adapter = new AutomovelAdapter(this, automoveis);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                //Se for 0, a busca não retornou resultados, então não ir para a tela de detalhes do automovel
                if(automoveis[position].getId() != 0) {
                    Intent intent = new Intent(atividade, DetalheAutomovelActivity.class);
                    intent.putExtra(AUTOMOVEL, automoveis[position]);

                    startActivity(intent);
                }
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_consulta_automovel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
