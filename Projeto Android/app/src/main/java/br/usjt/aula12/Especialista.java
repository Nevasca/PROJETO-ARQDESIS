package br.usjt.aula12;

import java.util.ArrayList;

import br.usjt.aula12.model.Automovel;

/**
 * Created by Bruno on 12/09/2015.
 */


public class Especialista
{

    //Método de teste para o ListView. Os automóveis serão consultados no banco posteriormente
    public static ArrayList<Automovel> consultarAutomovel(String cidade)
    {
        ArrayList<Automovel> lista = null;

        switch(cidade)
        {
            case "São Paulo":
                lista = new ArrayList<Automovel>();
                lista.add(new Automovel("Camaro", "Chevrolet", "auto001", "A", 1, "12361235", "ABC1234", "São Paulo", "SP", 123.3, 15.50, 66.60));
                lista.add(new Automovel("Punto", "Fiat", "auto002", "A", 1, "12361235", "ABC1234", "São Paulo", "SP", 123.3, 15.50, 66.60));
                break;

            case "Rio de Janeiro":
                lista = new ArrayList<Automovel>();
                lista.add(new Automovel("Blindado", "Militar", "auto003", "A", 1, "12361235", "ABC1234", "Rio de Janeiro", "RJ", 123.3, 15.50, 66.60));
                lista.add(new Automovel("Bicicleta", "Barbie", "auto004", "A", 1, "12361235", "ABC1234", "Rio de Janeiro", "RJ", 123.3, 15.50, 66.60));
                break;

            case "Rio Branco":
                lista = new ArrayList<Automovel>();
                lista.add(new Automovel("T'Rex", "Dinossauro", "auto005", "A", 1, "12361235", "ABC1234", "Rio Branco", "AC", 123.3, 15.50, 66.60));
                lista.add(new Automovel("Pterodáctilo", "Dinossauro", "auto006", "A", 1, "12361235", "ABC1234", "Rio Branco", "AC", 123.3, 15.50, 66.60));
                break;
        }

        return lista;
    }
}
