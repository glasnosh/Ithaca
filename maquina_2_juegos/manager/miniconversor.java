/*
 *  "miniconversor.java"
 * 	Transforma los caracteres que usa la interfaz gráfica en el lenguaje propio de los parsers.
 * 	 vg.-	masPequenho  -->	MASPEQUENHO
 *			
 * 	También hace el arreglo de cambiar el símbolo '¬' que va antes de un cuatificador por '!' que es el que usa el nucleo
 *	(El nucleo distinque entre símbolo '¬' -> "no formula"  y '!' -> "no cuantificador", pero en la interfaz solo se usa 
 *	 uno de ellos '¬', de modo que hay que hacer la distinción y cambar el que corresponda).
 *
 *	Todos los caracteres que no pertenezcan a la cadena:  
 *		 ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghxyzt(),-<>^v=!¬<espacio>
 *	 son convertidos a '? para evitar que entren a los parsers y puedan dar una excepción no prevista. De esta forma el
 * 	 único caracter no reconocido que puede entrar a los parsers es '?'
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */

/*
	public miniconversor()				//Constructor
	
	public String convertir(String cadena)		//Método que hace la conversión real
*/
 
import java.lang.*;
import java.io.*;
import java.util.*;


class miniconversor{
	
	String cadena;
	
	Character _IMPLICA = 0x002192;		//Símbolo real de "implica" en Unicode
	Character _BIMPLICA = 0x2194;		//Símbolo real de "bimplica" en Unicode
	Character _EXISTEUN = 0x2203;		//Símbolo real de  "existe" Unicode
	Character _PARATODO = 0x2200;		//Símbolo real de "para_todo" en Unicode
	Character _Y = 0x2227;			//Símbolo real de "and" en Unicode
	Character _O = 0x2228;			//Símbolo real de "or" en Unicode
	Character _T = 0x22A4;			//Símbolo real de "tautologia" en Unicode
	Character _nT = 0x22A5;			//Símbolo real de "falsedad" en Unicode
		
	final String IMPLICA = ""+_IMPLICA;		//Se transforman en cadenas para usarlos de forma más simple en 
	final String BIMPLICA = ""+_BIMPLICA;		// comparaciones, concatenaciones....
	final String EXISTEUN = ""+_EXISTEUN;
	final String PARATODO = ""+_PARATODO;
	final String Y = ""+_Y;
	final String O = ""+_O;
	final String T = ""+_T;
	final String nT = ""+_nT;
		

	public miniconversor(){}//constructor
	
	public String convertir(String cadena){
		String vuelta = "";					//cadena traducida
		this.cadena = cadena;

		cadena = cadena.replaceAll(Y,"^");			
		cadena = cadena.replaceAll(O,"v");
		cadena = cadena.replaceAll(IMPLICA,"-->");
		cadena = cadena.replaceAll(BIMPLICA,"<->");
		cadena = cadena.replaceAll(PARATODO,"CU");
		cadena = cadena.replaceAll(EXISTEUN,"E");
	

		/*
			Las siguientes 25-30 lineas de código se usan para cambiar las negaciones de cuantificaciones por el 
			 símbolo '!'. Se hace aquí para evitar que las letras 'E' , 'C' , 'U' puedan dar problemas con las que
			 hacen referencia a funciones atómicas como MASGRANDE
			 						    ^
		*/
		
		int pos = 0;
		int pos_s = 0;
		while((pos =cadena.indexOf(new Character('E'),pos+1)) != -1){
			pos_s = pos;
			boolean fin_ristra = false;
			while (fin_ristra == false){

				if ( ((pos-1) >=0) && (cadena.charAt(pos-1) == '¬') ){
					pos = pos-1;
				}
				else{
					fin_ristra = true;
				}
			}
		cadena = cadena.substring(0,pos)+cadena.substring(pos,pos_s).replace('¬','!')+cadena.substring(pos_s,cadena.length());
		}
				
		
		int pos_2 = 0;
		int pos_s_2 = 0;
		while((pos_2 =cadena.indexOf(new Character('C'),pos_2+1)) != -1){
			pos_s_2 = pos_2;
			boolean fin_ristra = false;
			while (fin_ristra == false){

				if ( ((pos_2-1) >=0) && (cadena.charAt(pos_2-1) == '¬') ){
					pos_2 = pos_2-1;
				}
				else{
					fin_ristra = true;
				}
			}
		cadena = cadena.substring(0,pos_2)+cadena.substring(pos_2,pos_s_2).replace('¬','!')+cadena.substring(pos_s_2,cadena.length());
		}
		// Hasta aquí la traducción de negaciones de cuantificadores
		
		
		cadena = cadena.replaceAll(T,"TAUTO()");
		cadena = cadena.replaceAll(nT,"FALSE()");		
		cadena = cadena.replaceAll("grande","GRANDE");
		cadena = cadena.replaceAll("pequeño","PEQUENHO");
		cadena = cadena.replaceAll("rojo","ROJO");
		cadena = cadena.replaceAll("azul","AZUL");
		cadena = cadena.replaceAll("amarillo","AMARILLO");
		cadena = cadena.replaceAll("esfera","ESFERA");
		cadena = cadena.replaceAll("cubo","CUBO");
		cadena = cadena.replaceAll("piramide","PIRAMIDE");
		
		cadena = cadena.replaceAll("masGrande","MASGRANDE");
		cadena = cadena.replaceAll("masPequeño","MASPEQUENHO");
		cadena = cadena.replaceAll("mismaForma","MISMAFORMA");
		cadena = cadena.replaceAll("mismoColor","MISMOCOLOR");
		cadena = cadena.replaceAll("mismoTamaño","MISMOTAMANHO");
		cadena = cadena.replaceAll("mismaFila","MISMAFILA");
		cadena = cadena.replaceAll("mismaColumna","MISMACOLUMNA");
		cadena = cadena.replaceAll("izquierda","IZQUIERDA");
		cadena = cadena.replaceAll("derecha","DERECHA");
		cadena = cadena.replaceAll("detras","ARRIBA");
		cadena = cadena.replaceAll("delante","ABAJO");
		cadena = cadena.replaceAll("alrededor","ALREDEDOR");
		
		cadena = cadena.replaceAll("entre","ENTRE");
		
		
		//Se hace que todo caracter no perteneciente al lenguaje sea en la práctica el caracter '?'
		
		String cadena_de_caracteres_admitidos = " ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghxyzt(),-<>^v=!¬";
		for (int i=0; i< cadena.length(); i++){
			if ( cadena_de_caracteres_admitidos.contains(cadena.charAt(i)+"") == false ){
				cadena = cadena.replace(cadena.charAt(i),'?');	
			}//if
		}//for
				
		vuelta = cadena;
		
		return vuelta;
	}//convertir

}//class