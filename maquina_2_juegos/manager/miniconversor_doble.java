/*
 *  "miniconversor_doble.java"
 * 
 *	Hay dos versiones de miniconversores. Una que solamente cambia entre visual e interno y otra que hace ambas cosas. 
 *	Como normalmente solo se usa una, se la llamaba fuera la que fuera "miniconversor". Aqu� como se usan ambas, para 
 *	 diferenciarlas a esta que hace ambas cosas se la llama "miniconversor_doble".
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Mart�nez Garc�a <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */


 
import java.lang.*;
import java.io.*;
import java.util.*;


class miniconversor_doble{
	
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
		

	public miniconversor_doble(){}//constructor
	
	public String convertir_visual_interno(String cadena){
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
	
	
	
	
	public String convertir_interno_visual(String cadena){
		String vuelta = "";					//cadena traducida
		this.cadena = cadena;

		

		/*
			Las siguientes 25-30 lineas de c�digo se usan para cambiar las negaciones de cuantificaciones por el 
			 s�mbolo '!'. Se hace aqu� para evitar que las letras 'E' , 'C' , 'U' puedan dar problemas con las que
			 hacen referencia a funciones at�micas como MASGRANDE
			 						    ^
		*/
		
		cadena = cadena.replace('^',_Y);	
		// Hasta aqu� la traducci�n de negaciones de cuantificadores
		
		
		cadena = cadena.replaceAll("TAUTO()",T);
		cadena = cadena.replaceAll("FALSE()",nT);		
		cadena = cadena.replaceAll("GRANDE","grande");
		cadena = cadena.replaceAll("PEQUENHO","peque�o");
		cadena = cadena.replaceAll("ROJO","rojo");
		cadena = cadena.replaceAll("AZUL","azul");
		cadena = cadena.replaceAll("AMARILLO","amarillo");
		cadena = cadena.replaceAll("ESFERA","esfera");
		cadena = cadena.replaceAll("CUBO","cubo");
		cadena = cadena.replaceAll("PIRAMIDE","piramide");
		
		cadena = cadena.replaceAll("MASGRANDE","masGrande");
		cadena = cadena.replaceAll("MASPEQUENHO","masPeque�o");
		cadena = cadena.replaceAll("MISMAFORMA","mismaForma");
		cadena = cadena.replaceAll("MISMOCOLOR","mismoColor");
		cadena = cadena.replaceAll("MISMOTAMANHO","mismoTama�o");
		cadena = cadena.replaceAll("MISMAFILA","mismaFila");
		cadena = cadena.replaceAll("MISMACOLUMNA","mismaColumna");
		cadena = cadena.replaceAll("IZQUIERDA","izquierda");
		cadena = cadena.replaceAll("DERECHA","derecha");
		cadena = cadena.replaceAll("ARRIBA","detras");
		cadena = cadena.replaceAll("ABAJO","delante");
		cadena = cadena.replaceAll("ALREDEDOR","alrededor");
		
		cadena = cadena.replaceAll("ENTRE","entre");
		
		
		
		cadena = cadena.replaceAll("v",O);
		cadena = cadena.replaceAll("-->",IMPLICA);
		cadena = cadena.replaceAll("<->",BIMPLICA);
		cadena = cadena.replaceAll("CU",PARATODO);
		cadena = cadena.replaceAll("E",EXISTEUN);
		cadena = cadena.replaceAll("!","�");
		
		
		
		vuelta = cadena;
		
		return vuelta;
	}//convertir_visual_interno
	
	
	

}//class