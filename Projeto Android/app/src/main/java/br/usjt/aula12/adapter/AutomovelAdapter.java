package br.usjt.aula12.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import br.usjt.aula12.R;
import br.usjt.aula12.model.Automovel;
import br.usjt.aula12.util.Util;
import br.usjt.aula12.util.ViewHolder;

/**
 * Created by Bruno on 12/09/2015.
 */

public class AutomovelAdapter extends BaseAdapter
{

    Activity context;
    Automovel[] automoveis;

    public AutomovelAdapter(Activity context, Automovel[] automoveis)
    {
        this.context = context;
        this.automoveis = automoveis;
    }

    @Override
    public int getCount() {
        return automoveis.length;
    }

    @Override
    public Object getItem(int position) {
        if(position >= 0 && position < automoveis.length)
        {
            return automoveis[position];
        }
        else
        {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.linha_automovel, parent, false);

            //ImageView miniaturaAutomovel = (ImageView)view.findViewById(R.id.IVMiniaturaAutomovel);
            TextView modeloAutomovel = (TextView)view.findViewById(R.id.TVModeloAutomovel);
            TextView detalhesAutomovel = (TextView)view.findViewById(R.id.TVDetalhesAutomovel);

            //view.setTag(new ViewHolder(miniaturaAutomovel, modeloAutomovel, detalhesAutomovel));
            view.setTag(new ViewHolder(modeloAutomovel, detalhesAutomovel));
        }

        ViewHolder holder = (ViewHolder)view.getTag();

        //Drawable drawable = Util.getDrawable(context, automoveis[position].getImagem());
        //holder.getMiniaturaAutomovel().setImageDrawable(drawable);
        holder.getModeloAutomovel().setText(automoveis[position].getModelo());
        holder.getDetalhesAutomovel().setText(automoveis[position].getFabricante());

        return view;
    }
}
