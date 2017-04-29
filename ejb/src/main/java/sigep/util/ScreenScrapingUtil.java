package sigep.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
//import sigep.data.dto.sigep.SimulacionDTO;
import sigep.data.dto.ScreenScrapingDTO;
import sigep.data.dto.SimulacionDTO;
//import sigep.data.dto.sigep.ScreenScrapingDTO;

import java.lang.reflect.Type;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Asus PC on 17/07/2015.
 */
public class ScreenScrapingUtil {

    public static List<SimulacionDTO> findAllMarcasAuto() {
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.autocosmos.com.ar/guiadeprecios").get();
        } catch (IOException e) {
            System.out.println("Error buscando marcas de autos.");
        }
        Elements opcionesDelCombo = doc.select("#marcas option");
        List<SimulacionDTO> simulacionDTOs = new ArrayList<SimulacionDTO>(opcionesDelCombo.size());
        for (Element element : opcionesDelCombo) {
            SimulacionDTO dto = new SimulacionDTO();
            dto.setNombre(element.childNodes().get(0).toString());
            dto.setValue(element.attributes().asList().get(0).getValue());
            simulacionDTOs.add(dto);
        }
        return simulacionDTOs;
    }

    public static List<ScreenScrapingDTO> findModelos(String marca) {

        BufferedReader reader = null;
        marca = marca.replace(" ", "%20");
        String dir = "http://www.autocosmos.com.ar/guiadeprecios/loadmodelos?marca=" + marca;
        StringBuffer buffer = new StringBuffer();
        List<ScreenScrapingDTO> list = null;
        try {
            URL url = new URL(dir);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);
            if (reader != null)
                reader.close();

            Gson gson = new Gson();
            Type datasetListType = new TypeToken<Collection<ScreenScrapingDTO>>() {
            }.getType();
            list = gson.fromJson(buffer.toString(), datasetListType);

        } catch (Exception e) {
        } finally {
            return list;
        }
    }

    public static List<ScreenScrapingDTO> findAnios(String marca, String modelo) {

        marca = marca.replace(" ", "%20");
        modelo = modelo.replace(" ", "%20");
        String dir = "http://www.autocosmos.com.ar/guiadeprecios/loadaniosar?marca=" + marca + "&modelo=" + modelo;
        BufferedReader reader = null;
        StringBuffer buffer = new StringBuffer();
        List<ScreenScrapingDTO> list = null;
        try {
            URL url = new URL(dir);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);
            if (reader != null)
                reader.close();

            Gson gson = new Gson();
            Type datasetListType = new TypeToken<Collection<ScreenScrapingDTO>>() {
            }.getType();
            list = gson.fromJson(buffer.toString(), datasetListType);

        } catch (Exception e) {
        } finally {
            return list;
        }
    }

    public static List<SimulacionDTO> findVersionesYPrecios(String marca, String modelo, String anio) {
        marca = marca.replace(" ", "%20");
        modelo = modelo.replace(" ", "%20");
        Document doc = null;
        try {
            String dir = "http://www.autocosmos.com.ar/guiadeprecios/GetPreciosAr?marca=" + marca
                    + "&modelo=" + modelo + "&anio=" + anio;
            doc = Jsoup.connect(dir).get();
        } catch (IOException e) {
            System.out.println("Error buscando anos y precios de autos.");
        }
        Elements versiones = doc.select("th");
        Elements precios = doc.select(".precio");
        List<SimulacionDTO> simulacionDTOs = new ArrayList<SimulacionDTO>(versiones.size());
        for (int i = 0; i < precios.size(); i++) {
            SimulacionDTO dto = new SimulacionDTO();
            dto.setVersion(versiones.get(i).childNodes().get(1).childNode(0).toString());
            dto.setPrecio(precios.get(i).childNodes().get(0).attr("text"));
            simulacionDTOs.add(dto);
        }
        return simulacionDTOs;
    }


//    public static List<ScreenScrapingDTO> findVersiones(String marca, String modelo){
//
//        BufferedReader reader = null;
//        String dir = "http://www.autocosmos.com.ar/guiadeprecios/loadversionesar?marca=" + marca + "&modelo=" + modelo;
//        StringBuffer buffer = new StringBuffer();
//        List<ScreenScrapingDTO> list = null;
//        try {
//            URL url = new URL(dir);
//            reader = new BufferedReader(new InputStreamReader(url.openStream()));
//            int read;
//            char[] chars = new char[1024];
//            while ((read = reader.read(chars)) != -1)
//                buffer.append(chars, 0, read);
//            if (reader != null)
//                reader.close();
//
//            Gson gson = new Gson();
//            Type datasetListType = new TypeToken<Collection<ScreenScrapingDTO>>() {}.getType();
//            list = gson.fromJson(buffer.toString(), datasetListType);
//
//        }
//        catch(Exception e){
//        }
//        finally        {
//            return list;
//        }
//    }
//
//    public static List<SimulacionDTO> findAnosYPrecios(String marca, String modelo, String version){
//        Document doc = null;
//        try {
//            String dir = "http://www.autocosmos.com.ar/guiadeprecios/GetPreciosAr?marca=" + marca
//            + "&modelo=" + modelo + "&version=" + version;
//            doc = Jsoup.connect(dir).get();
//        } catch (IOException e) {
//            System.out.println("Error buscando anos y precios de autos.");
//        }
//        Elements anios = doc.select(".anio span");
//        Elements precios = doc.select(".precio");
//        List<SimulacionDTO> simulacionDTOs = new ArrayList<SimulacionDTO>(anios.size());
//        for(int i = 0; i<precios.size();i++){
//            SimulacionDTO dto = new SimulacionDTO();
//            dto.setAno(anios.get(i).childNodes().get(0).attr("text"));
//            dto.setPrecio(precios.get(i).childNodes().get(0).attr("text"));
//            simulacionDTOs.add(dto);
//        }
//        return simulacionDTOs;
//    }

}