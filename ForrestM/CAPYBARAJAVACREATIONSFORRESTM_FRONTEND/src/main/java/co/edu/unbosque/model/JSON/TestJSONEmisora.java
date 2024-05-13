package co.edu.unbosque.model.JSON;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import co.edu.unbosque.model.dto.Emisora;

public class TestJSONEmisora {
	
	private static URL url;
	private static String sitio = "http://localhost:8054/";
	
	public static ArrayList<Emisora> getJSON() throws IOException, ParseException{
		url = new URL(sitio+"emisora/listar");
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");
		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		for (int i = 0; i<inp.length ; i++) {
			json += (char)inp[i];
		}
		ArrayList<Emisora> lista = new ArrayList<Emisora>();
		lista = parsingEmisora(json);
		http.disconnect();
		return lista;
	}
	
	public static ArrayList<Emisora> parsingEmisora(String json) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		ArrayList<Emisora> lista = new ArrayList<Emisora>();
		JSONArray emisora = (JSONArray) jsonParser.parse(json);
		Iterator i = emisora.iterator();
		while (i.hasNext()) {
			JSONObject innerObj = (JSONObject) i.next();
			Emisora emisoraO = new Emisora();
			emisoraO.setModo_transmision(innerObj.get("modo_transmision").toString());
			emisoraO.setNombre_emisora(innerObj.get("nombre_emisora").toString());
			emisoraO.setTipo_musica(innerObj.get("tipo_musica").toString());
			lista.add(emisoraO);
		}
		return lista;
	}
	
	public static int postJSON(Emisora em) throws IOException {
		url = new URL(sitio+"emisora/guardar");
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();
		http.setRequestMethod("POST");
		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");
		String data = "{"
				+ "\"nombre_emisora\": \""+em.getNombre_emisora()+"\","
				+ "\"modo_transmision\": \""+em.getModo_transmision()+"\","
				+ "\"tipo_musica\": \""+em.getTipo_musica()+"\""
				+ "}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		return http.getResponseCode();
	}

}


