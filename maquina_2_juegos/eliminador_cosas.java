/*
 *  "eliminador_cosas.java"
 *	
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Mart�nez Garc�a <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */


/*
 *	(c) 2006 Jose Manuel Mart�nez Garc�a <glasnosh@yahoo.es>
 *
 *	<18/01/2006>	
 *	Esta clase se utiliza como peque�o "transformador" de las f�rmulas cuando se pasa entre los diferentes estados del juego.
 *	Por ejemplo elimina la cuantificaci�n que acaba de ser particularizada o la negaci�n.
 *	Solo implementa m�todos de ayuda.
 *	No tiene mucho misterio. Simplemente es �til sacar definiciones de m�todos a otras clases para no aturullarse con tanto c�digo
 *
 *
 *
 */


import java.io.*;
import java.lang.*;


public class eliminador_cosas{

	String cadena;		//la cadena sobre la que va a trabajar	

	public eliminador_cosas(String _cadena){
		
		cadena = _cadena.substring(0,_cadena.length());
		eliminar_blancos();	//Nos quitamos los espacios en blanco de en medio paporsi...

	}//constructor
	
	public String eliminar_cuantificador(char variable_cuantificada, char caracter_particularizado){
		String cadena_final = "";
		
		//buscar el cuantificador:   CUx  Ex
		int primera_ocurrencia = cadena.indexOf(variable_cuantificada); //la primera ocurrencia es la de la cuantificacion
		
		int numero_caracteres_a_eliminar = 0;	//si es CUx, ser�n 3. Si es Ex, ser�n 2
		if ((cadena.charAt(primera_ocurrencia - 1)) == 'E') numero_caracteres_a_eliminar = 2;
		else if ((cadena.charAt(primera_ocurrencia -1)) == 'U') numero_caracteres_a_eliminar = 3;
		else System.out.println("Hay un error, porque no encuentro la cuantificaci�n");	//error a estudiar... (en caso de que se d�)
		
		//eliminarlo
		cadena = 	cadena.substring(0, (primera_ocurrencia - numero_caracteres_a_eliminar +1)) +
				cadena.substring(primera_ocurrencia + 1 , cadena.length());
		
		//cambiar las variables por nombres				
		cadena = cadena.replace(variable_cuantificada, caracter_particularizado);
		cadena_final = cadena;
		
		return cadena_final;
	}//eliminar_cuantificador
	
	public String eliminar_negacion(){	
		String cadena_final = "";
		
			int primera_ocurrencia_normal = cadena.indexOf('�');	//eliminar la primera ocurrencia del s�mbolo '�'
			int primera_ocurrencia_cuanti = cadena.indexOf('!');	// o de '!', lo que est� primero.
			
			int primera_ocurrencia;
			if (primera_ocurrencia_normal < primera_ocurrencia_cuanti) primera_ocurrencia = primera_ocurrencia_normal;
			else primera_ocurrencia = primera_ocurrencia_cuanti;
			
			if (primera_ocurrencia_normal == -1) primera_ocurrencia = primera_ocurrencia_cuanti;
			if (primera_ocurrencia_cuanti == -1) primera_ocurrencia = primera_ocurrencia_normal;
			cadena =	cadena.substring(0, (primera_ocurrencia))+
			cadena.substring((primera_ocurrencia+1), cadena.length());
			cadena_final = cadena;
		
		return cadena_final;
	}//eliminar_negacion
	
	private void eliminar_blancos(){	//por si acaso aqu� eliminamos los espacios en blanco 
		cadena = cadena.replaceAll(" ","");
	}//eliminar_blancos
	
	
}//class eliminador_cosas