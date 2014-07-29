/*
 *  "miniconversor.java"
 * 	Transforma los caracteres que usa la interfaz gr�fica en el lenguaje propio de los parsers.
 * 	 vg.-	masPequenho  -->	MASPEQUENHO
 *			
 * 	Tambi�n hace el arreglo de cambiar el s�mbolo '�' que va antes de un cuatificador por '!' que es el que usa el nucleo
 *	(El nucleo distinque entre s�mbolo '�' -> "no formula"  y '!' -> "no cuantificador", pero en la interfaz solo se usa 
 *	 uno de ellos '�', de modo que hay que hacer la distinci�n y cambar el que corresponda).
 *
 *	Todos los caracteres que no pertenezcan a la cadena:  
 *		 ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghxyzt(),-<>^v=!�<espacio>
 *	 son convertidos a '? para evitar que entren a los parsers y puedan dar una excepci�n no prevista. De esta forma el
 * 	 �nico caracter no reconocido que puede entrar a los parsers es '?'
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Mart�nez Garc�a <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */

/*
	public miniconversor()				//Constructor
	
	public String convertir(String cadena)		//M�todo que hace la conversi�n real
*/
 
import java.lang.*;
import java.io.*;
import java.util.*;


class miniconversor{
	
	String cadena;
	
	Character _IMPLICA = 0x002192;		//S�mbolo real de "implica" en Unicode
	Character _BIMPLICA = 0x2194;		//S�mbolo real de "bimplica" en Unicode
	Character _EXISTEUN = 0x2203;		//S�mbolo real de  "existe" Unicode
	Character _PARATODO = 0x2200;		//S�mbolo real de "para_todo" en Unicode
	Character _Y = 0x2227;			//S�mbolo real de "and" en Unicode
	Character _O = 0x2228;			//S�mbolo real de "or" en Unicode
	Character _T = 0x22A4;			//S�mbolo real de "tautologia" en Unicode
	Character _nT = 0x22A5;			//S�mbolo real de "falsedad" en Unicode
		
	final String IMPLICA = ""+_IMPLICA;		//Se transforman en cadenas para usarlos de forma m�s simple en 
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
			Las siguientes 25-30 lineas de c�digo se usan para cambiar las negaciones de cuantificaciones por el 
			 s�mbolo '!'. Se hace aqu� para evitar que las letras 'E' , 'C' , 'U' puedan dar problemas con las que
			 hacen referencia a funciones at�micas como MASGRANDE
			 						    ^
		*/
		
		int pos = 0;
		int pos_s = 0;
		while((pos =cadena.indexOf(new Character('E'),pos+1)) != -1){
			pos_s = pos;
			boolean fin_ristra = false;
			while (fin_ristra == false){

				if ( ((pos-1) >=0) && (cadena.charAt(pos-1) == '�') ){
					pos = pos-1;
				}
				else{
					fin_ristra = true;
				}
			}
		cadena = cadena.substring(0,pos)+cadena.substring(pos,pos_s).replace('�','!')+cadena.substring(pos_s,cadena.length());
		}
				
		
		int pos_2 = 0;
		int pos_s_2 = 0;
		while((pos_2 =cadena.indexOf(new Character('C'),pos_2+1)) != -1){
			pos_s_2 = pos_2;
			boolean fin_ristra = false;
			while (fin_ristra == false){

				if ( ((pos_2-1) >=0) && (cadena.charAt(pos_2-1) == '�') ){
					pos_2 = pos_2-1;
				}
				else{
					fin_ristra = true;
				}
			}
		cadena = cadena.substring(0,pos_2)+cadena.substring(pos_2,pos_s_2).replace('�','!')+cadena.substring(pos_s_2,cadena.length());
		}
		// Hasta aqu� la traducci�n de negaciones de cuantificadores
		
		
		cadena = cadena.replaceAll(T,"TAUTO()");
		cadena = cadena.replaceAll(nT,"FALSE()");		
		cadena = cadena.replaceAll("grande","GRANDE");
		cadena = cadena.replaceAll("peque�o","PEQUENHO");
		cadena = cadena.replaceAll("rojo","ROJO");
		cadena = cadena.replaceAll("azul","AZUL");
		cadena = cadena.replaceAll("amarillo","AMARILLO");
		cadena = cadena.replaceAll("esfera","ESFERA");
		cadena = cadena.replaceAll("cubo","CUBO");
		cadena = cadena.replaceAll("piramide","PIRAMIDE");
		
		cadena = cadena.replaceAll("masGrande","MASGRANDE");
		cadena = cadena.replaceAll("masPeque�o","MASPEQUENHO");
		cadena = cadena.replaceAll("mismaForma","MISMAFORMA");
		cadena = cadena.replaceAll("mismoColor","MISMOCOLOR");
		cadena = cadena.replaceAll("mismoTama�o","MISMOTAMANHO");
		cadena = cadena.replaceAll("mismaFila","MISMAFILA");
		cadena = cadena.replaceAll("mismaColumna","MISMACOLUMNA");
		cadena = cadena.replaceAll("izquierda","IZQUIERDA");
		cadena = cadena.replaceAll("derecha","DERECHA");
		cadena = cadena.replaceAll("detras","ARRIBA");
		cadena = cadena.replaceAll("delante","ABAJO");
		cadena = cadena.replaceAll("alrededor","ALREDEDOR");
		
		cadena = cadena.replaceAll("entre","ENTRE");
		
		
		//Se hace que todo caracter no perteneciente al lenguaje sea en la pr�ctica el caracter '?'
		
		String cadena_de_caracteres_admitidos = " ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghxyzt(),-<>^v=!�";
		for (int i=0; i< cadena.length(); i++){
			if ( cadena_de_caracteres_admitidos.contains(cadena.charAt(i)+"") == false ){
				cadena = cadena.replace(cadena.charAt(i),'?');	
			}//if
		}//for
				
		vuelta = cadena;
		
		return vuelta;
	}//convertir

}//class