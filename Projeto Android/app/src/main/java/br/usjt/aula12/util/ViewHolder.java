package br.usjt.aula12.util;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Bruno on 12/09/2015.
 */
public class ViewHolder
{
    private ImageView miniaturaAutomovel;
    private TextView modeloAutomovel, detalhesAutomovel;

    public ViewHolder(/*ImageView miniaturaAutomovel, */TextView modeloAutomovel, TextView detalhesAutomovel)
    {
        //setMiniaturaAutomovel(miniaturaAutomovel);
        setModeloAutomovel(modeloAutomovel);
        setDetalhesAutomovel(detalhesAutomovel);
    }

    public ImageView getMiniaturaAutomovel()
    {
        return miniaturaAutomovel;
    }

    public void setMiniaturaAutomovel(ImageView miniaturaAutomovel)
    {
        this.miniaturaAutomovel = miniaturaAutomovel;
    }

    public TextView getModeloAutomovel()
    {
        return modeloAutomovel;
    }

    public void setModeloAutomovel(TextView modeloAutomovel)
    {
        this.modeloAutomovel = modeloAutomovel;
    }

    public TextView getDetalhesAutomovel()
    {
        return detalhesAutomovel;
    }

    public void setDetalhesAutomovel(TextView detalhesAutomovel)
    {
        this.detalhesAutomovel = detalhesAutomovel;
    }

}
