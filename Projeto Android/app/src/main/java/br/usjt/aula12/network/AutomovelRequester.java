package br.usjt.aula12.network;

import br.usjt.aula12.model.Automovel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;


/**
 * Created by Bruno on 20/10/2015.
 */
public class AutomovelRequester
{
    OkHttpClient client = new OkHttpClient();

    public ArrayList<Automovel> get(String url, String cidade) throws IOException
    {
        ArrayList<Automovel> lista = new ArrayList<>();

        RequestBody formBody = new FormEncodingBuilder()
                .add("cidade", cidade)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();

        String jsonStr = response.body().string();
        System.out.println(jsonStr);
        System.out.println(url);

        try
        {
            JSONArray root = new JSONArray(jsonStr);
            JSONObject item = null;
            for (int i = 0; i < root.length(); i++)
            {
                item = (JSONObject)root.get(i);

                Automovel auto = new Automovel();

                auto.setId(item.getInt("id"));
                auto.setGrupo(item.getString("grupo"));
                auto.setAcessorio(item.getInt("acessorio"));
                auto.setChassi(item.getString("chassi"));
                auto.setPlaca(item.getString("placa"));
                auto.setCidade(item.getString("cidade"));
                auto.setUf(item.getString("uf"));
                auto.setQuilometragem(item.getDouble("quilometragem"));
                auto.setModelo(item.getString("modelo"));
                auto.setFabricante(item.getString("fabricante"));
                auto.setTarifa(item.getDouble("tarifa"));
                auto.setTarifaControlada(item.getDouble("tarifaControlada"));

                lista.add(auto);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(lista.size() == 0)
            {
                Automovel auto = new Automovel();
                auto.setId(0);
                auto.setModelo("Não encontrado");
                auto.setFabricante("Não Encontrado");

                lista.add(auto);
            }
        }

        return lista;
    }

    public boolean isConnected(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
