package br.usjt.aula12.controller;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

import br.usjt.aula12.R;
import br.usjt.aula12.model.Automovel;
import br.usjt.aula12.util.Util;

public class DetalheAutomovelActivity extends AppCompatActivity {

    TextView modeloAutomovel;
    //ImageView imagemAutomovel;
    TextView fabricanteAutomovel;
    TextView grupoAutomovel;
    TextView acessorioAutomovel;
    TextView chassiAutomovel;
    TextView placaAutomovel;
    TextView cidadeAutomovel;
    TextView ufAutomovel;
    TextView quilometragemAutomovel;
    TextView tarifaSAutomovel;
    TextView tarifaCAutomovel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_automovel);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(ConsultaAutomovelActivity.AUTOMOVEL);
        Automovel automovel = (Automovel)obj;

        setupViews(automovel);
    }

    private void setupViews(Automovel automovel)
    {
        modeloAutomovel = (TextView)findViewById(R.id.TVModeloAutomovelDetalhes);
        modeloAutomovel.setText(automovel.getModelo());
        //imagemAutomovel = (ImageView)findViewById(R.id.IVAutomovel);
        //Drawable drawable = Util.getDrawable(this, automovel.getImagem());
        //imagemAutomovel.setImageDrawable(drawable);
        fabricanteAutomovel = (TextView)findViewById(R.id.TVFabricanteAutomovelDetalhes);
        fabricanteAutomovel.setText(automovel.getFabricante());
        grupoAutomovel = (TextView)findViewById(R.id.TVGrupoAutomovelDetalhes);
        grupoAutomovel.setText(automovel.getNomeGrupo());
        acessorioAutomovel = (TextView)findViewById(R.id.TVAcessorioAutomovelDetalhes);
        acessorioAutomovel.setText(automovel.getNomeAcessorio());
        chassiAutomovel = (TextView)findViewById(R.id.TVChassiAutomovelDetalhes);
        chassiAutomovel.setText(automovel.getChassi());
        placaAutomovel = (TextView)findViewById(R.id.TVPlacaAutomovelDetalhes);
        placaAutomovel.setText(automovel.getPlaca());
        cidadeAutomovel = (TextView)findViewById(R.id.TVCidadeAutomovelDetalhes);
        cidadeAutomovel.setText(automovel.getCidade());
        ufAutomovel = (TextView)findViewById(R.id.TVUfAutomovelDetalhes);
        ufAutomovel.setText(automovel.getUf());
        quilometragemAutomovel = (TextView)findViewById(R.id.TVQuilometragemAutomovelDetalhes);
        quilometragemAutomovel.setText("" + automovel.getQuilometragem());
        tarifaSAutomovel = (TextView)findViewById(R.id.TVTarifaSAutomovelDetalhes);
        tarifaSAutomovel.setText("R$ " + automovel.getTarifa());
        tarifaCAutomovel = (TextView)findViewById(R.id.TVTarifaCAutomovelDetalhes);
        tarifaCAutomovel.setText("R$ " + automovel.getTarifaControlada());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalhe_automovel, menu);
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
