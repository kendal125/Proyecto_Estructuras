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
 * utilizando el Web Service del Banco Central de Costa Rica (BCCR).
 *
 * <p>
 * Esta versión está simplificada para el proyecto, ya que únicamente
 * obtiene el tipo de cambio de venta del día actual.
 * </p>
 *
 * <p>
 * Requiere que el usuario tenga un correo y un token válidos
 * registrados en el servicio del BCCR.
 * </p>
 */
public class BCCR {

    /**
     * Correo electrónico registrado en el BCCR.
     */
    private static final String CORREO = "TU_CORREO";

    /**
     * Token proporcionado por el BCCR.
     */
    private static final String TOKEN = "TU_TOKEN";

    /**
     * Obtiene el tipo de cambio de venta del día actual
     * desde el Web Service del BCCR.
     *
     * @return tipo de cambio de venta como número decimal
     * @throws Exception si ocurre un error en la conexión o en el procesamiento
     */
    public double obtenerTipoCambioVenta() throws Exception {

        // Obtener fecha actual en formato requerido por el BCCR
        String fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

        // Construir la URL con los parámetros necesarios
        String urlStr =
            "https://gee.bccr.fi.cr/Indicadores/Suscripciones/WS/wsindicadoreseconomicos.asmx/ObtenerIndicadoresEconomicosXML"
            + "?Indicador=318"
            + "&FechaInicio=" + fecha
            + "&FechaFinal=" + fecha
            + "&Nombre=BusNovaTech"
            + "&SubNiveles=N"
            + "&CorreoElectronico=" + CORREO
            + "&Token=" + TOKEN;

        // Crear conexión HTTP
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        // Leer la respuesta del servicio
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

        String linea;
        String respuesta = "";

        while ((linea = in.readLine()) != null) {
            respuesta += linea;
        }

        in.close();

        // Extraer el valor entre las etiquetas <NUM_VALOR>
        int inicio = respuesta.indexOf("<NUM_VALOR>");
        int fin = respuesta.indexOf("</NUM_VALOR>");

        if (inicio == -1 || fin == -1) {
            throw new Exception("No se encontró el tipo de cambio en la respuesta del BCCR.");
        }

        String valor = respuesta.substring(inicio + 11, fin);

        return Double.parseDouble(valor);
    }
}
