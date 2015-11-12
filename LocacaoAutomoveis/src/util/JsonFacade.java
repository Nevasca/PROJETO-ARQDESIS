package util;

import model.Automovel;
import java.util.ArrayList;
import org.json.JSONArray; 
import org.json.JSONException; 
import org.json.JSONObject;

public class JsonFacade 
{
	public static String listToJson(ArrayList<Automovel> lista)
	{
		JSONArray vetor = new JSONArray();
		
		for(Automovel auto : lista)
		{
			JSONObject obj = new JSONObject();
			try
			{
				obj.put("id", auto.getIDAutomovel());
				obj.put("grupo", auto.getGruposAutomovel());
				obj.put("acessorio", auto.getAcessoriosAutomovel());
				obj.put("chassi", auto.getChassiAutomovel());
				obj.put("placa", auto.getPlacaAutomovel());
				obj.put("cidade", auto.getCidadeAutomovel());
				obj.put("uf", auto.getUFAutomovel());
				obj.put("quilometragem", auto.getKmRodadoAutomovel());
				obj.put("fabricante", auto.getFabricanteAutomovel());
				obj.put("tarifa", auto.getTarifaAutomovel());
				obj.put("tarifaControlada", auto.getTarifaControladaAutomovel());
				obj.put("modelo", auto.getModeloAutomovel());
				//obj.put("tpStatus", auto.getTPStatus());

				vetor.put(obj);
			}
			catch(JSONException e)
			{
				e.printStackTrace();
			}
		}
		
		return vetor.toString();
	}
}
