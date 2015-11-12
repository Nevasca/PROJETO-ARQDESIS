package br.usjt.aula12.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.aula12.R;
import br.usjt.aula12.model.Automovel;
import br.usjt.aula12.network.AutomovelRequester;

public class MainActivity extends AppCompatActivity {

    //final String servidor = "192.168.0.4:8080/LocacaoAutomoveis/automovel";
    final String servidor = "10.0.2.2:8080/LocacaoAutomoveis/automovel";
    AutomovelRequester requester;
    ArrayList<Automovel> automoveis;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public final static String AUTOMOVEIS = "br.usjt.AUTOMOVEIS";

    public void consultarAutomoveis(View view)
    {

        Spinner spinner = (Spinner)findViewById(R.id.SPNCidades);
        TextView textView = (TextView)findViewById(R.id.LBErro);

        //Se ele selecionou algo no Spinner (0 é a opção de selecionar a cidade)
        if(spinner.getSelectedItemPosition() != 0)
        {
            final String cidade = spinner.getSelectedItem().toString();
            textView.setText(""); //Apaga o textview de erro para não ficar apresentando mensagem
            requester = new AutomovelRequester();

            if(requester.isConnected(this))
            {

                final Intent intent = new Intent(this, ConsultaAutomovelActivity.class);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try
                        {
                            automoveis = requester.get("http://" + servidor + "/consulta.json", cidade);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    intent.putExtra(AUTOMOVEIS, automoveis);
                                    startActivity(intent);
                                }
                            });
                        }
                        catch(IOException e)
                        {
                            System.out.println("passei pelo erro");
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
            else
            {
                Toast toast = Toast.makeText(this, "Rede indisponível!", Toast.LENGTH_LONG);
                toast.show();
            }
        }
        else
        {
            textView.setText("Selecione uma cidade");
        }
    }
}
