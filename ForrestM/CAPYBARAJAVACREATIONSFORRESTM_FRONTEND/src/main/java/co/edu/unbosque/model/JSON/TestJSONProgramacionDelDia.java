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

import co.edu.unbosque.model.dto.ProgramacionDelDia;



public class TestJSONProgramacionDelDia {
	
	private static URL url;
	private static String sitio = "http://localhost:8054/";
	
	public static ArrayList<ProgramacionDelDia> getJSON() throws IOException, ParseException {
	    url = new URL(sitio + "programacionDelDia/listar");
	    HttpURLConnection http = (HttpURLConnection) url.openConnection();
	    http.setRequestMethod("GET");
	    http.setRequestProperty("Accept", "application/json");
	    InputStream respuesta = http.getInputStream();
	    byte[] inp = respuesta.readAllBytes();
	    String json = "";
	    for (int i = 0; i < inp.length; i++) {
	        json += (char) inp[i];
	    }
	    ArrayList<ProgramacionDelDia> lista = new ArrayList<ProgramacionDelDia>();
	    lista = parsingProgramacionDelDia(json);
	    http.disconnect();
	    return lista;
	}

	public static ArrayList<ProgramacionDelDia> parsingProgramacionDelDia(String json) throws ParseException {
	    JSONParser jsonParser = new JSONParser();
	    ArrayList<ProgramacionDelDia> lista = new ArrayList<ProgramacionDelDia>();
	    JSONArray programaciones = (JSONArray) jsonParser.parse(json);
	    Iterator i = programaciones.iterator();
	    while (i.hasNext()) {
	        JSONObject innerObj = (JSONObject) i.next();
	        ProgramacionDelDia programacion = new ProgramacionDelDia();
	        programacion.setFecha(innerObj.get("fecha").toString());
	        JSONArray pistasArray = (JSONArray) innerObj.get("pistas");
	        ArrayList<String> pistas = new ArrayList<String>();
	        for (Object pista : pistasArray) {
	            pistas.add((String) pista);
	        }
	        programacion.setPistas(pistas);
	        lista.add(programacion);
	    }
	    return lista;
	}

	public static int postJSON(ProgramacionDelDia programacion) throws IOException {
	    if (programacion == null || programacion.getFecha() == null || programacion.getPistas() == null) {
	        throw new IllegalArgumentException("Datos inválidos para ProgramacionDelDia");
	    }

	    URL url = new URL(sitio + "programacionDelDia/guardar");
	    HttpURLConnection http = (HttpURLConnection) url.openConnection();
	    http.setRequestMethod("POST");
	    http.setDoOutput(true);
	    http.setRequestProperty("Accept", "application/json");
	    http.setRequestProperty("Content-Type", "application/json");

	    JSONObject json = new JSONObject();
	    json.put("fecha", programacion.getFecha());

	    JSONArray pistasArray = new JSONArray();
	    for (String nombreCancion : programacion.getPistas()) {
	        if (nombreCancion == null) {
	            throw new IllegalArgumentException("Nombre de canción inválido");
	        }
	        pistasArray.add(nombreCancion);
	    }
	    json.put("pistas", pistasArray);

	    String data = json.toString();
	    byte[] out = data.getBytes(StandardCharsets.UTF_8);
	    try (OutputStream stream = http.getOutputStream()) {
	        stream.write(out);
	    }

	    return http.getResponseCode();
	}
	public static int deleteJSON(String fecha) throws IOException {
		url = new URL(sitio+"ProgramacionDelDia/eliminar/"+fecha);
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();
		http.setRequestMethod("DELETE");
		http.setRequestProperty("Accept", "application/json");
		return http.getResponseCode();
	}
}


