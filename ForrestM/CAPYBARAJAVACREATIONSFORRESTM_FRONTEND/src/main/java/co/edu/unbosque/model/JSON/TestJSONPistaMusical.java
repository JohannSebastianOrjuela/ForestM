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

import co.edu.unbosque.model.dto.PistasMusicales;

public class TestJSONPistaMusical {
	
	private static URL url;
	private static String sitio = "http://localhost:8054/";
	
	public static ArrayList<PistasMusicales> getJSON() throws IOException, ParseException{
		url = new URL(sitio+"pistasMusicales/listar");
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");
		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		for (int i = 0; i<inp.length ; i++) {
			json += (char)inp[i];
		}
		ArrayList<PistasMusicales> lista = new ArrayList<PistasMusicales>();
		lista = parsingPistasMusicales(json);
		http.disconnect();
		return lista;
	}
	
	public static ArrayList<PistasMusicales> parsingPistasMusicales(String json) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		ArrayList<PistasMusicales> lista = new ArrayList<PistasMusicales>();
		JSONArray pistasMusicales = (JSONArray) jsonParser.parse(json);
		Iterator i = pistasMusicales.iterator();
		while (i.hasNext()) {
			JSONObject innerObj = (JSONObject) i.next();
			PistasMusicales pistaMusical = new PistasMusicales();
			pistaMusical.setNombreCancion(innerObj.get("nombreCancion").toString());
			pistaMusical.setGeneroMusical(innerObj.get("generoMusical").toString());
			pistaMusical.setNombreArtista(innerObj.get("nombreArtista").toString());
			pistaMusical.setNombreArchivo(innerObj.get("nombreArchivo").toString());
			lista.add(pistaMusical);
		}
		return lista;
	}
	
	public static int postJSON(PistasMusicales pistaMusical) throws IOException {
		url = new URL(sitio+"pistasMusicales/guardar");
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();
		http.setRequestMethod("POST");
		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");
		String data = "{"
				+ "\"nombreCancion\": \""+pistaMusical.getNombreCancion()+"\","
				+ "\"generoMusical\": \""+pistaMusical.getGeneroMusical()+"\","
				+ "\"nombreArtista\": \""+pistaMusical.getNombreArtista()+"\","
				+ "\"nombreArchivo\": \""+pistaMusical.getNombreArchivo()+"\""
				+ "}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		return http.getResponseCode();
	}
	public static int deleteJSON(String nombreCancion) throws IOException {
		url = new URL(sitio+"pistasMusicales/eliminar/"+nombreCancion);
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();
		http.setRequestMethod("DELETE");
		http.setRequestProperty("Accept", "application/json");
		return http.getResponseCode();
	}
}
