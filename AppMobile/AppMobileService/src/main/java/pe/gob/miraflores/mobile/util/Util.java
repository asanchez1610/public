package pe.gob.miraflores.mobile.util;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import pe.gob.miraflores.mobile.constantes.MobileConstantes;

public class Util {

	public static String getUrlRemote(String url) {
		URL urlPage;
		String inputLine = null;
		String strJson = "";
		try {
			
			urlPage = new URL(url);
			
			
			BufferedReader in = new BufferedReader(new InputStreamReader(urlPage.openStream(), "UTF-8"));

			while ((inputLine = in.readLine()) != null){
				if(inputLine!=null){
					strJson+=inputLine;
				}
			}
			in.close();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.err.println("Alerta : "+e.getMessage());
			System.err.println("Causa  : "+e.getCause());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Alerta : "+e.getMessage());
			System.err.println("Causa  : "+e.getCause());
		}

		return strJson;

	}
	
	public static int daysBetween(Date fechaIni,Date fechaFin) {
		
		Calendar fechaInicial = new GregorianCalendar();
		fechaInicial.setTime(fechaIni);
		Calendar fechaFinal = new GregorianCalendar();
		fechaFinal.setTime(fechaFin);
		int numdias= 0;
	    while (fechaInicial.before(fechaFinal) || fechaInicial.equals(fechaFinal) ) {
	    	numdias++;
	    	fechaInicial.add(Calendar.DATE, 1);
	    }
	    return numdias;
    }
	
	



	
}
