/*
 *  "analizadorFunciones_TABLERO_.java"	
 *	Evaluador de fórmulas clásico de fórmulas atómicas sobre la malla
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */



/*

	public analizadorFunciones_TABLERO_(malla mimalla) 		//COSTRUCTOR
	
	
	public int analizar_TAUTO()
	public int analizar_FALSE()
	
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
	public int analizar_EQUAL(int x1,int y1,int x2,int y2)
	
	public int analizar_ENTRE(int x1, int y1, int x2, int y2, int x3, int y3)
	
	
	
*/


import java.lang.*;
import java.io.*;
import java.util.*;


public class analizadorFunciones_TABLERO_{

	malla mimalla;	//la malla a analizar. Es variable miembro, así que deberá inicializarse con la que se desea aplicar

	
	/********************************/
	/*     	CONSTRUCTOR		*/
	/********************************/
	
	public analizadorFunciones_TABLERO_(malla mimalla){
		this.mimalla = mimalla;	
	}//constructor analizadorFunciones()

	/**********************************************/
	/*	FUNCIONES PRIVADAS DE  LA CLASE	      */
	/**********************************************/
			
	
	/************************************************/
	/*	FUNCIONES 	APARAMETRICAS		*/
	/************************************************/
	
	public int analizar_TAUTO(){return 1;}
	
	public int analizar_FALSE(){return 0;}
	
	/************************************************/
	/*	FUNCIONES 	MONOPARAMETRICAS	*/
	/************************************************/
	
	public int analizar_GRANDE(int x1, int y1){
		int result = 100;	//1 (verdadero), 0(falso), 100 (no evaluable)
		if  (mimalla.devolverCasilla(x1,y1).ocupada){
			if (mimalla.devolverCasilla(x1,y1).tamanho == 1) result=1;
			else if (mimalla.devolverCasilla(x1,y1).tamanho == 2) result=0;
		}//if	
		return result;
	}//analizar_GRANDE()
	
	public int analizar_PEQUENHO(int x1, int y1){
		int result = 100;	//1 (verdadero), 0(falso), 100 (no evaluable)
		if  (mimalla.devolverCasilla(x1,y1).ocupada){
			if (mimalla.devolverCasilla(x1,y1).tamanho == 2) result=1;
			else if (mimalla.devolverCasilla(x1,y1).tamanho == 1) result=0;
		}//if
		return result;
	}//analizar_PEQUENHO()
	
	public int analizar_ROJO(int x1, int y1){
		int result = 100;
		if (mimalla.devolverCasilla(x1,y1).ocupada){
			if (mimalla.devolverCasilla(x1,y1).color == 1) result=1;
			else if (mimalla.devolverCasilla(x1,y1).color != 1) result=0;
		}//if
		return result;
	}//analizar_ROJO
	
	
	public int analizar_AZUL(int x1, int y1){
		int result = 100;
		if (mimalla.devolverCasilla(x1,y1).ocupada){
			if (mimalla.devolverCasilla(x1,y1).color == 2) result=1;
			else if (mimalla.devolverCasilla(x1,y1).color != 2) result=0;
		}//if
		return result;
	}//analizar_AZUL
	
	
	public int analizar_AMARILLO(int x1, int y1){
		int result = 100;
		if (mimalla.devolverCasilla(x1,y1).ocupada){
			if (mimalla.devolverCasilla(x1,y1).color == 3) result=1;
			else if (mimalla.devolverCasilla(x1,y1).color != 3) result=0;
		}//if
		return result;
	}//analizar_AMARILLO
	
	
	public int analizar_ESFERA(int x1, int y1){
		int result = 100;
		if (mimalla.devolverCasilla(x1,y1).ocupada){
			if (mimalla.devolverCasilla(x1,y1).forma == 1) result=1;
			else if (mimalla.devolverCasilla(x1,y1).forma != 1) result=0;
		}//if
		return result;
	}//analizar_ESFERA
	
	public int analizar_CUBO(int x1, int y1){
		int result = 100;
		if (mimalla.devolverCasilla(x1,y1).ocupada){
			if (mimalla.devolverCasilla(x1,y1).forma == 2) result=1;
			else if (mimalla.devolverCasilla(x1,y1).forma != 2) result=0;
		}//if
		return result;
	}//analizar_CUBO
	
	public int analizar_PIRAMIDE(int x1, int y1){
		int result = 100;
		if (mimalla.devolverCasilla(x1,y1).ocupada){
			if (mimalla.devolverCasilla(x1,y1).forma == 3) result=1;
			else if (mimalla.devolverCasilla(x1,y1).forma != 3) result=0;
		}//if
		return result;
	}//analizar_ESFERA
	
	/************************************************/
	/*	FUNCIONES 	BIPARAMETRICAS		*/
	/************************************************/
	
	public int analizar_MASGRANDE (int x1, int y1, int x2, int y2){		//(x1,y1) mayor tamanho que (x2,y2)
//System.out.println("Funcion MASGRANDE");
		int result = 100;
		if ( (mimalla.devolverCasilla(x1,y1).ocupada) && (mimalla.devolverCasilla(x2,y2).ocupada) ){
//System.out.println("Tamaño objeto 1 -> "+mimalla.devolverCasilla(x1,y1).tamanho());
//System.out.println("Tamaño objeto 2 -> "+mimalla.devolverCasilla(x2,y2).tamanho());
			if ( (mimalla.devolverCasilla(x1,y1).tamanho() == 1) && (mimalla.devolverCasilla(x2,y2).tamanho() == 2) ) result = 1;
			else result = 0;
		}//if
		return result;
	}//analizar_MASGRANDE
	
	public int analizar_MASPEQUENHO (int x1, int y1, int x2, int y2){		//(x1,y1) menor tamanho que (x2,y2)
		int result = 100;
		if ( (mimalla.devolverCasilla(x1,y1).ocupada) && (mimalla.devolverCasilla(x2,y2).ocupada) ){
			if ( (mimalla.devolverCasilla(x1,y1).tamanho() == 2) && (mimalla.devolverCasilla(x2,y2).tamanho() == 1) ) result = 1;
			else result = 0;
		}//if
		return result;
	}//analizar_MASGRANDE
	
	public int analizar_MISMAFORMA (int x1, int y1, int x2, int y2){
		int result = 100;
		if ( (mimalla.devolverCasilla(x1,y1).ocupada) && (mimalla.devolverCasilla(x2,y2).ocupada) ){
			if ( mimalla.devolverCasilla(x1,y1).forma() == mimalla.devolverCasilla(x2,y2).forma() ) result = 1;
			else result = 0;
		}//if
		return result;
	}//analizar_MISMAFORMA
	
	public int analizar_MISMOCOLOR (int x1, int y1, int x2, int y2){
		int result = 100;
		if ( (mimalla.devolverCasilla(x1,y1).ocupada) && (mimalla.devolverCasilla(x2,y2).ocupada) ){
			if ( mimalla.devolverCasilla(x1,y1).color() == mimalla.devolverCasilla(x2,y2).color() ) result = 1;
			else result = 0;
		}//if
		return result;
	}//analizar_MISMOCOLOR
	
	public int analizar_MISMOTAMANHO (int x1, int y1, int x2, int y2){
		int result = 100;
		if ( (mimalla.devolverCasilla(x1,y1).ocupada) && (mimalla.devolverCasilla(x2,y2).ocupada) ){
			if ( mimalla.devolverCasilla(x1,y1).tamanho() == mimalla.devolverCasilla(x2,y2).tamanho() ) result = 1;
			else result = 0;
		}//if
		return result;
	}//analizar_MISMOTAMANHO
	
	public int analizar_MISMAFILA (int x1, int y1, int x2, int y2) {
		int result = 100;
		if ( (mimalla.devolverCasilla(x1,y1).ocupada) && (mimalla.devolverCasilla(x2,y2).ocupada) ){
			if (x1 == x2) result = 1;
			else result = 0;
		}//if
		return result;	
	}//analizar_MISMAFILA
	
	public int analizar_MISMACOLUMNA (int x1, int y1, int x2, int y2) {
		int result = 100;
		if ( (mimalla.devolverCasilla(x1,y1).ocupada) && (mimalla.devolverCasilla(x2,y2).ocupada) ){
			if (y1 == y2) result = 1;
			else result = 0;
		}//if
		return result;	
	}//analizar_MISMACOLUMNA
	
	public int analizar_ARRIBA (int x1, int y1, int x2, int y2) { // (x1,y1) esta más arriba que (x2,y2)
		int result = 100;
		if ( (mimalla.devolverCasilla(x1,y1).ocupada) && (mimalla.devolverCasilla(x2,y2).ocupada) ){
			if (x1 < x2) result = 1;
			else result = 0;
		}//if
		return result;
	}//analizar_ARRIBA
	
	public int analizar_ABAJO (int x1, int y1, int x2, int y2) { // (x1,y1) esta más  abajo que (x2, y2)
		int result = 100;
		if ( (mimalla.devolverCasilla(x1,y1).ocupada) && (mimalla.devolverCasilla(x2,y2).ocupada) ){
			if (x1 > x2) result = 1;
			else result = 0;
		}//if
		return result;
	}//analizar_ABAJO
	
	public int analizar_IZQUIERDA (int x1, int y1, int x2, int y2) { // (x1,y1) está a la izquierda de (x2,y2)
		int result = 100;
		if ( (mimalla.devolverCasilla(x1,y1).ocupada) && (mimalla.devolverCasilla(x2,y2).ocupada) ){
			if (y1 < y2) result = 1;
			else result = 0;
		}//if
		return result;
	}//analizar_IZQUIERDA
	
	public int analizar_DERECHA (int x1, int y1, int x2, int y2) { // (x1,y1) está a la derecha de (x2,y2)
		int result = 100;
		if ( (mimalla.devolverCasilla(x1,y1).ocupada) && (mimalla.devolverCasilla(x2,y2).ocupada) ){
			if (y1 > y2) result = 1;
			else result = 0;
		}//if
		return result;
	}//analizar_DERECHA
	
	public int analizar_ALREDEDOR (int x1, int y1, int x2, int y2) {	//(x2,y2) esta alrededor de (x1,y1)
System.out.println(" x1 = "+x1);
System.out.println(" y1 = "+y1);
System.out.println(" x2 = "+x2);

System.out.println(" y2 = "+y2);
		int result = 100;
		if ( (mimalla.devolverCasilla(x1,y1).ocupada) && (mimalla.devolverCasilla(x2,y2).ocupada) ){	//zona central
			if  ( (x1>0) && (x1<5) && (y1>0) && (y1<8) ){
System.out.println("zona central");
				if ( 	((x2 == x1-1) && (y2 == y1-1)) ||
					((x2 == x1-1) && (y2 == y1-0)) ||
					((x2 == x1-1) && (y2 == y1+1)) ||
					((x2 == x1-0) && (y2 == y1-1)) ||
					((x2 == x1-0) && (y2 == y1+1)) ||
					((x2 == x1+1) && (y2 == y1-1)) ||
					((x2 == x1+1) && (y2 == y1-0)) ||
					((x2 == x1+1) && (y2 == y1+1)) )	result = 1;
				else result = 0;
			}//if
			else if ( (x1==0) && (y1>0) && (y1<8) ){	//fila superior
System.out.println("fila superior");
				if ( 	((x2 == x1-0) && (y2 == y1-1)) ||
					((x2 == x1-0) && (y2 == y1+1)) ||
					((x2 == x1+1) && (y2 == y1-1)) ||
					((x2 == x1+1) && (y2 == y1-0)) ||
					((x2 == x1+1) && (y2 == y1+1))  )	result = 1;
				else result = 0;
			}//else if
			else if ( (x1==5) && (y1>0) && (y1<8) ){	//fila inferior
System.out.println("fila inferior");
				if ( 	((x2 == x1-1) && (y2 == y1-1)) ||
					((x2 == x1-1) && (y2 == y1-0)) ||
					((x2 == x1-1) && (y2 == y1+1)) ||
					((x2 == x1-0) && (y2 == y1-1)) ||
					((x2 == x1-0) && (y2 == y1+1))  )	result = 1;
				else result = 0;
			}//else if
			else if ( (x1>0) && (x1<5) && (y1==0) ){		//columna izquierda
System.out.println("columna izquierda");
				if (	((x2 == x1-1) && (y2 == y1-0))	||
					((x2 == x1-1) && (y2 == y1+1))	||
					((x2 == x1-0) && (y2 == y1+1))	||
					((x2 == x1+1) && (y2 == y1-0))	||
					((x2 == x1+1) && (y2 == y1+1))	)	result = 1;
				else result = 0;
			}//else if 
			else if ( (x1>0) && (x1<5) && (y1==8) ){		//columna derecha
System.out.println("columna derecha");
				if(	((x2 == x1-1) && (y2 == y1-1))	||
					((x2 == x1-1) && (y2 == y1-0))	||
					((x2 == x1-0) && (y2 == y1-1))	||
					((x2 == x1+1) && (y2 == y1-1))	||
					((x2 == x1+1) && (y2 == y1-0))	)	result = 1;
				else result = 0;
			}//else if
			else if ( (x1==0) && (y1==0) ){			//esquina superior izquierda
System.out.println("esquina superior izquierda");
				if (	((x2==1) && (y2==0)) ||
					((x2==1) && (y2==1)) ||
					((x2==0) && (y2==1)) )	result = 1;
				else result = 0;
			}//else if
			else if ( (x1==0) && (y1==8) ){			//esquina superior derecha
System.out.println("esquina superior derecha");
				if (	((x2==0) && (y2==7)) ||
					((x2==1) && (y2==7)) ||
					((x2==1) && (y2==8))  )	result = 1;
				else result = 0;
			}//else if
			else if ( (x1==5) && (y1==0) ){			//esquina inferior izquierda
System.out.println("esquina superior izquierda");
				if (	((x2==4) && (y2==0)) ||
					((x2==4) && (y2==1)) ||
					((x2==5) && (y2==1)) )	result = 1;
				else result = 0;
			}//else if
			else if ( (x1==5) && (y1==8) ){			//esquina inferior derecha
System.out.println("esquina inferior derecha");
				if (	((x2==4) && (y2==7)) ||
					((x2==4) && (y2==8)) ||
					((x2==5) && (y2==7)) )	result = 1;
				else result = 0;
			}//else if
		}//if
		return result;
	}//analizar_ALREDEDOR
	
	public int analizar_EQUAL(int x1,int y1,int x2,int y2){
		int result = 100;
		if ( (x1 == x2) && (y1 == y2) ) result = 1;
		else result = 0;
		return result;
	}//analizar_EQUAL
	
	
	/************************************************/
	/*	FUNCIONES 	TRIPARAMETRICAS		*/
	/************************************************/
	
	
	public int analizar_ENTRE(int x1, int y1, int x2, int y2, int x3, int y3){//(x1,y1) está entre (x2,y2) y (x3,y3)
		int result = 100;
		if (	(mimalla.devolverCasilla(x1,y1).ocupada) &&
			(mimalla.devolverCasilla(x2,y2).ocupada) &&
			(mimalla.devolverCasilla(x3,y3).ocupada) ){
				if  ( (x1>0) && (x1<5) && (y1>0) && (y1<8) ){	//ZONA DE EN MEDIO
					if ( 	((x2==x1-0) && (y2==y1-1) && (x3==x1-0) && (y3==y1+1))	||	//cruceta  -
						((x3==x1-0) && (y3==y1-1) && (x2==x1-0) && (y2==y1+1))	||	
						((x2==x1-1) && (y2==y1-0) && (x3==x1+1) && (y3==y1-0))	||	//cruceta |
						((x3==x1-1) && (y3==y1-0) && (x2==x1+1) && (y2==y1-0))	||	
						((x2==x1+1) && (y2==y1-1) && (x3==x1-1) && (y3==y1+1))	||	//cruceta /
						((x3==x1+1) && (y3==y1-1) && (x2==x1-1) && (y2==y1+1))	||	
						((x2==x1-1) && (y2==y1-1) && (x3==x1+1) && (y3==y1+1))	||	//cruceta \
						((x3==x1-1) && (y3==y1-1) && (x2==x1+1) && (y2==y1+1))	) result = 1;
					else result = 0;
				}
				else if ( 	((x1==0) && (y1==0)) ||		//ESQUINAS. SIEMPRE '0'
						((x1==0) && (y1==8)) ||
						((x1==5) && (y1==0)) ||
						((x1==5) && (y1==8))	) result = 0;
				else if ( (x1==0) && (y1>0) && (y1<8) ) {	//FILA SUPERIOR	
					if ( 	((x2==x1-0) && (y2==y1-1) && (x3==x1-0) &&(y3==y1+1))	||
						((x3==x1-0) && (y3==y1-1) && (x2==x1-0) &&(y2==y1+1))	) result = 1;
					else result = 0;
				}
				else if ( (x1==5) && (y1>0) && (y1<8) ) {	//FILA INFERIOR
					if ( 	((x2==x1-0) && (y2==y1-1) && (x3==x1-0) &&(y3==y1+1))	||
						((x3==x1-0) && (y3==y1-1) && (x2==x1-0) &&(y2==y1+1))	) result = 1;
					else result = 0;
				}
				else if ( (x1>0) && (x1<5) && (y1==0) )	{	//COLUMNA IZQUIERDA
					if ( 	((x2==x1-1) && (y2==y1-0) && (x3==x1+1) &&(y3==y1+0))	||
						((x3==x1-1) && (y3==y1-0) && (x2==x1+1) &&(y2==y1+0))	) result = 1;
					else result = 0;
				}
				else if ( (x1>0) && (x1<5) && (y1==8) ) {	//COLUMNA DERECHA
					if ( 	((x2==x1-1) && (y2==y1-0) && (x3==x1+1) &&(y3==y1+0))	||
						((x3==x1-1) && (y3==y1-0) && (x2==x1+1) &&(y2==y1+0))	) result = 1;
					else result = 0;
				}
		}//if
		return result;
	}//analizar_ENTRE
	
	
	
	
}//class analizadorFunciones