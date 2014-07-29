/*
 *  "analizadorFunciones_O_.java"	
 *	Evaluador de fórmulas donde todas las funciones son falsas salvo la taotológica. Analizador para mallas sin elementos o Nomallas
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */


/*   
	public analizadorFunciones_O_(malla mimalla) 		//COSTRUCTOR
	
	public int analizar_GRANDE(int x1, int y1)
	public int analizar_PEQUENHO(int x1, int y1)
	public int analizar_ROJO(int x1, int y1)
	public int analizar_AZUL(int x1, int y1)
	public int analizar_AMARILLO(int x1, int y1)
	public int analizar_ESFERA(int x1, int y1)
	public int analizar_CUBO(int x1, int y1)
	public int analizar_PIRAMIDE(int x1, int y1)
	
	public int analizar_MASGRANDE (int x1, int y1, int x2, int y2)
	public int analizar_MASPEQUENHO (int x1, int y1, int x2, int y2)
	public int analizar_MISMAFORMA (int x1, int y1, int x2, int y2)
	public int analizar_MISMOCOLOR (int x1, int y1, int x2, int y2)
	public int analizar_MISMOTAMANHO (int x1, int y1, int x2, int y2)
	public int analizar_MISMAFILA (int x1, int y1, int x2, int y2)
	public int analizar_MISMACOLUMNA (int x1, int y1, int x2, int y2)
	public int analizar_IZQUIERDA (int x1, int y1, int x2, int y2)
	public int analizar_DERECHA (int x1, int y1, int x2, int y2)
	public int analizar_ARRIBA (int x1, int y1, int x2, int y2)
	public int analizar_ABAJO (int x1, int y1, int x2, int y2)
	public int analizar_ALREDEDOR (int x1, int y1, int x2, int y2)
	public int analizar_EQUAL (int x1, int y1, int x2, int y2)
	
	public int analizar_ENTRE(int x1, int y1, int x2, int y2, int x3, int y3)
	
	public int analizar_TAUTO()
	public int analizar_FALSE()
	
	
*/


import java.lang.*;
import java.io.*;
import java.util.*;


public class analizadorFunciones_O_{

	malla mimalla;	//la malla a analizar. Es variable miembro, así que deberá inicializarse con la que se desea aplicar

	
	/********************************/
	/*     	CONSTRUCTOR		*/
	/********************************/
	
	public analizadorFunciones_O_(malla mimalla){
		this.mimalla = mimalla;	
	}//constructor analizadorFunciones()

	/**********************************************/
	/*	FUNCIONES PRIVADAS DE  LA CLASE	      */
	/**********************************************/
			
	
	/************************************************/
	/*	FUNCIONES 	APARAMETRICAS   	*/
	/************************************************/
	
	public int analizar_TAUTO() {return 1;}			//esta es la única fórmula atómica que debe devolver verdadero (1)
	
	public int analizar_FALSE() {return 0;}
	
	/************************************************/
	/*	FUNCIONES 	MONOPARAMETRICAS	*/
	/************************************************/
	
	public int analizar_GRANDE(int x1, int y1){return 0;}
	
	public int analizar_PEQUENHO(int x1, int y1){return 0;}
	
	public int analizar_ROJO(int x1, int y1){return 0;}
		
	public int analizar_AZUL(int x1, int y1){return 0;}
	
	public int analizar_AMARILLO(int x1, int y1){return 0;}
		
	public int analizar_ESFERA(int x1, int y1){return 0;}
	
	public int analizar_CUBO(int x1, int y1){return 0;}
	
	public int analizar_PIRAMIDE(int x1, int y1){return 0;}
	
	
	/************************************************/
	/*	FUNCIONES 	BIPARAMETRICAS		*/
	/************************************************/
	
	public int analizar_MASGRANDE (int x1, int y1, int x2, int y2){return 0;}		
	
	public int analizar_MASPEQUENHO (int x1, int y1, int x2, int y2){return 0;}		
	
	public int analizar_MISMAFORMA (int x1, int y1, int x2, int y2){return 0;}
	
	public int analizar_MISMOCOLOR (int x1, int y1, int x2, int y2){return 0;}
	
	public int analizar_MISMOTAMANHO (int x1, int y1, int x2, int y2){return 0;}
		
	public int analizar_MISMAFILA (int x1, int y1, int x2, int y2) {return 0;}
	
	public int analizar_MISMACOLUMNA (int x1, int y1, int x2, int y2) {return 0;}
		
	public int analizar_ARRIBA (int x1, int y1, int x2, int y2) {return 0;}
		
	public int analizar_ABAJO (int x1, int y1, int x2, int y2) {return 0;}
		
	public int analizar_IZQUIERDA (int x1, int y1, int x2, int y2) {return 0;}
	
	public int analizar_DERECHA (int x1, int y1, int x2, int y2) {return 0;}
		
	public int analizar_ALREDEDOR (int x1, int y1, int x2, int y2) {return 0;}	
	
	public int analizar_EQUAL(int x1,int y1,int x2,int y2){		//actualización. Esta conviene que se evalue normalmente
		int result = 100;
		if ( (x1 == x2) && (y1 == y2) ) result = 1;
		else result = 0;
		return result;
	}//analizar_EQUAL
	
	
	
	/************************************************/
	/*	FUNCIONES 	TRIPARAMETRICAS		*/
	/************************************************/
	
	
	public int analizar_ENTRE(int x1, int y1, int x2, int y2, int x3, int y3){return 0;}//(x1,y1) está entre (x2,y2) y (x3,y3)
		
	
	
	
	
}//class analizadorFunciones_O_
