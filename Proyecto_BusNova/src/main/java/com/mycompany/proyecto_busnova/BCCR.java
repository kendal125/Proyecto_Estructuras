/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_busnova;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase encargada de consultar el tipo de cambio del dólar
 * utilizando el API del Banco Central de Costa Rica (BCCR).
 *
 * <p>
 * Esta versión utiliza el nuevo API del SDDE y obtiene
 * el tipo de cambio de venta del día actual.
 * </p>
 */
public class BCCR {

    /**
     * Token proporcionado por el BCCR.
     */
    private static final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJCQ0NSLVNEREUiLCJzdWIiOiJzdG1vcmFvcnRpekBnbWFpbC5jb20iLCJhdWQiOiJTRERFLVNpdGlvRXh0ZXJubyIsImV4cCI6MjUzNDAyMzAwODAwLCJuYmYiOjE3NzY2NTQzMjYsImlhdCI6MTc3NjY1NDMyNiwianRpIjoiYTU4MDQyNTMtODQ5NS00ZjNiLTg5NGUtNzhhYjU3NjdiOWViIiwiZW1haWwiOiJzdG1vcmFvcnRpekBnbWFpbC5jb20ifQ.m3KwYvQuFMsC3F0tuwSTdZuV3DKinkQTpXACs5NjyjE";

    /**
     * Obtiene el tipo de cambio de venta del día actual
     * desde el API del BCCR.
     *
     * @return tipo de cambio de venta como número decimal
     * @throws Exception si ocurre un error en la conexión o en el procesamiento
     */
    public double obtenerTipoCambioVenta() throws Exception {

        // Fecha actual en formato requerido
        String fecha = new SimpleDateFormat("yyyy/MM/dd").format(new Date());

        // URL del API
        String urlStr =
            "https://apim.bccr.fi.cr/SDDE/api/Bccr.GE.SDDE.Publico.Indicadores.API"
            + "/indicadoresEconomicos/318/series"
            + "?fechaInicio=" + fecha
            + "&fechaFin=" + fecha
            + "&idioma=es";

        URL url = new URL (urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        // Header con token
        con.setRequestProperty("Authorization", "Bearer " + TOKEN);
        con.setRequestProperty("Content-Type", "application/json");

        int responseCode = con.getResponseCode();

        BufferedReader in;
        if (responseCode >= 200 && responseCode < 300) {
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {
            in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }

        String linea;
        String respuesta = "";

        while ((linea = in.readLine()) != null) {
            respuesta += linea;
        }

        in.close();

        if (responseCode < 200 || responseCode >= 300) {
            throw new Exception("HTTP " + responseCode + ": " + respuesta);
        }

        // Extraer valor del JSON
        int inicioCampo = respuesta.indexOf("valorDatoPorPeriodo");

        if (inicioCampo == -1) {
            throw new Exception("No se encontró el tipo de cambio en la respuesta del BCCR.");
        }

        String sub = respuesta.substring(inicioCampo);
        int inicioValor = sub.indexOf(":") + 1;
        int finValor = sub.indexOf("}");

        if (inicioValor <= 0 || finValor <= 0 || inicioValor >= finValor) {
            throw new Exception("No se pudo interpretar el valor del tipo de cambio.");
        }

        String valor = sub.substring(inicioValor, finValor).trim();

        return Double.parseDouble(valor);
    }
    
    public double obtenerTipoCambioCompra() throws Exception {

        // Fecha actual en formato requerido
        String fecha = new SimpleDateFormat("yyyy/MM/dd").format(new Date());

        // URL del API
        String urlStr =
            "https://apim.bccr.fi.cr/SDDE/api/Bccr.GE.SDDE.Publico.Indicadores.API"
            + "/indicadoresEconomicos/317/series"
            + "?fechaInicio=" + fecha
            + "&fechaFin=" + fecha
            + "&idioma=es";

        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        // Header con token
        con.setRequestProperty("Authorization", "Bearer " + TOKEN);
        con.setRequestProperty("Content-Type", "application/json");

        int responseCode = con.getResponseCode();

        BufferedReader in;
        if (responseCode >= 200 && responseCode < 300) {
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {
            in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }

        String linea;
        String respuesta = "";

        while ((linea = in.readLine()) != null) {
            respuesta += linea;
        }

        in.close();

        if (responseCode < 200 || responseCode >= 300) {
            throw new Exception("HTTP " + responseCode + ": " + respuesta);
        }

        // Extraer valor del JSON
        int inicioCampo = respuesta.indexOf("valorDatoPorPeriodo");

        if (inicioCampo == -1) {
            throw new Exception("No se encontró el tipo de cambio en la respuesta del BCCR.");
        }

        String sub = respuesta.substring(inicioCampo);
        int inicioValor = sub.indexOf(":") + 1;
        int finValor = sub.indexOf("}");

        if (inicioValor <= 0 || finValor <= 0 || inicioValor >= finValor) {
            throw new Exception("No se pudo interpretar el valor del tipo de cambio.");
        }

        String valor = sub.substring(inicioValor, finValor).trim();

        return Double.parseDouble(valor);
    }
}