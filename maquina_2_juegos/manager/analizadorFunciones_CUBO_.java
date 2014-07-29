/*
 *  "analizadorFunciones_CUBO_.java"
 *	Evaluador de fórmulas simples en una estructura de la malla similar a un cubo.
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */


/*

	public analizadorFunciones_CUBO_(malla mimalla) 		//COSTRUCTOR
	
	public int analizar_TAUTO()
	public int analizar_FALSE()
	
	public int analizar_GRANDE(int x1, int y1)		//(x1,y1) son las coordenadas de esa casilla en la malla
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
	
	
	  0   1   2   3   4   5   6   7  8
	_______________________________________
   0	|   |   |   ||   |   |   ||   |   |   |			A,B,C,D,E,F,G  son los cuadros centrales de cada cara del cubo.
	|___|___|___||___|___|___||___|___|___|
   1	|   | A |   ||   | B |   ||   | C |   |
	|___|___|___||___|___|___||___|___|___|
   2	|   |   |   ||   |   |   ||   |   |   |
	|___|___|___||___|___|___||___|___|___|
	|-------------------------------------|
   3	|   |   |   ||   |   |   ||   |   |   |
	|___|___|___||___|___|___||___|___|___|
   4	|   | D |   ||   | E |   ||   | F |   |
	|___|___|___||___|___|___||___|___|___|
   5	|   |   |   ||   |   |   ||   |   |   |
	|___|___|___||___|___|___||___|___|___|
	
	
	
	
	
*/


import java.lang.*;
import java.io.*;
import java.util.*;


public class analizadorFunciones_CUBO_{

	malla mimalla;	//la malla a analizar. Es variable miembro, así que deberá inicializarse con la que se desea aplicar

	
	/********************************/
	/*     	CONSTRUCTOR		*/
	/********************************/
	
	public analizadorFunciones_CUBO_(malla mimalla){
		//mimalla = new malla();	//esta linea escojona el constructor. QUITARLA SIEMPRE
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
		int result = 100;
		if ( (mimalla.devolverCasilla(x1,y1).ocupada) && (mimalla.devolverCasilla(x2,y2).ocupada) ){
			if ( (mimalla.devolverCasilla(x1,y1).tamanho() == 1) && (mimalla.devolverCasilla(x1,y1).tamanho() == 2) ) result = 1;
			else result = 0;
		}//if
		return result;
	}//analizar_MASGRANDE
	
	public int analizar_MASPEQUENHO (int x1, int y1, int x2, int y2){		//(x1,y1) menor tamanho que (x2,y2)
		int result = 100;
		if ( (mimalla.devolverCasilla(x1,y1).ocupada) && (mimalla.devolverCasilla(x2,y2).ocupada) ){
			if ( (mimalla.devolverCasilla(x1,y1).tamanho() == 2) && (mimalla.devolverCasilla(x1,y1).tamanho() == 1) ) result = 1;
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
	
	public int analizar_MISMAFILA (int x1, int y1, int x2, int y2) {return 0;}

	
	public int analizar_MISMACOLUMNA (int x1, int y1, int x2, int y2) {return 0;}
		
	
	public int analizar_ARRIBA (int x1, int y1, int x2, int y2) {return 0;}
	
	public int analizar_ABAJO (int x1, int y1, int x2, int y2) {return 0;}
	
	public int analizar_IZQUIERDA (int x1, int y1, int x2, int y2) {return 0;}
	
	public int analizar_DERECHA (int x1, int y1, int x2, int y2) {return 0;}
	
	public int analizar_ALREDEDOR (int x1, int y1, int x2, int y2) {	//(x2,y2) esta alrededor de (x1,y1)
		int result = 100;
		if ( (mimalla.devolverCasilla(x1,y1).ocupada) && (mimalla.devolverCasilla(x2,y2).ocupada) ){
			if (	((x1==0) && (y1==0))	||		//ESQUINAS SUPERIORES IZQUIERDAS
				((x1==0) && (y1==3))	||
				((x1==0) && (y1==6))	||
				((x1==3) && (y1==0))	||
				((x1==3) && (y1==3))	||
				((x1==3) && (y1==6))	){
					if ( 	((x2==x1-0) && (y2==y1+1))	||
						((x2==x1+1) && (y2==y1+1))	||
						((x2==x1+1) && (y2==y1-0))	) result = 1;
					else result = 0;
			}
			else if (	((x1==0) && (y1==2))	||	//ESQUINAS SUPERIORES DERECHAS
					((x1==0) && (y1==5))	||	
					((x1==0) && (y1==8))	||
					((x1==3) && (y1==2))	||
					((x1==3) && (y1==5))	||
					((x1==3) && (y1==8))	){
						if (	((x2==x1-0) && (y2==y1-1))	||	
							((x2==x1+1) && (y2==y1-1))	||
							((x2==x1+1) && (y2==y1-0))	) result = 1;
						else result = 0;
			}
			else if (	((x1==2) && (y1==0))	||	//ESQUINAS INFERIORES IZQUIERDAS
					((x1==2) && (y1==3))	||	
					((x1==2) && (y1==6))	||
					((x1==5) && (y1==0))	||
					((x1==5) && (y1==3))	||
					((x1==5) && (y1==6))	){
						if (	((x2==x1-1) && (y2==y1-0))	||
							((x2==x1-1) && (y2==y1+1))	||
							((x2==x1-0) && (y2==y1+1))	) result = 1;
						else result = 0;
			}
			else if (	((x1==2) && (y1==2))	||	//ESQUINAS INFERIORES DERECHAS
					((x1==2) && (y1==5))	||	
					((x1==2) && (y1==8))	||
					((x1==5) && (y1==2))	||
					((x1==5) && (y1==5))	||
					((x1==5) && (y1==8))	){
						if (	((x2==x1-0) && (y2==y1-1))	||
							((x2==x1-1) && (y2==y1-1))	||
							((x2==x1-1) && (y2==y1-0))	) result = 1;
						else result = 0;
			}
			else if (	((x1==1) && (y1==1))	||	//LOS PUNTOS CENTRALES DE CADA CARA
					((x1==1) && (y1==4))	||
					((x1==1) && (y1==7))	||
					((x1==4) && (y1==1))	||
					((x1==4) && (y1==4))	||
					((x1==4) && (y1==7))	){
						if (	((x2==x1-1) && (y2==y1-1))	||
							((x2==x1-1) && (y2==y1-0))	||
							((x2==x1-1) && (y2==y1+1))	||
							((x2==x1-0) && (y2==y1+1))	||
							((x2==x1+1) && (y2==y1+1))	||
							((x2==x1+1) && (y2==y1-0))	||
							((x2==x1+1) && (y2==y1-1))	||
							((x2==x1-0) && (y2==y1-1))	) result = 1;
						else result = 0;
			}
			else if (	((x1==1) && (y1==0))	||	//LADOS IZQUIERDOS
					((x1==1) && (y1==3))	||
					((x1==1) && (y1==6))	||
					((x1==4) && (y1==0))	||
					((x1==4) && (y1==3))	||
					((x1==4) && (y1==6))	){
						if(	((x2==x1-1) && (y2==y1-0))	||
							((x2==x1-1) && (y2==y1+1))	||
							((x2==x1-0) && (y2==y1+1))	||
							((x2==x1+1) && (y2==y1+1))	||
							((x2==x1+1) && (y2==y1+0))	) result =1 ;
						else result = 0;
			}
			else if (	((x1==1) && (y1==2))	||	//LADOS DERECHOS
					((x1==1) && (y1==5))	||
					((x1==1) && (y1==8))	||
					((x1==4) && (y1==2))	||
					((x1==4) && (y1==5))	||
					((x1==4) && (y1==8))	){
						if(	((x2==x1-1) && (y2==y1-0))	||
							((x2==x1-1) && (y2==y1-1))	||
							((x2==x1-0) && (y2==y1-1))	||
							((x2==x1+1) && (y2==y1-1))	||
							((x2==x1+1) && (y2==y1+0))	) result =1 ;
						else result = 0;
			}
			else if (	((x1==0) && (y1==1))	||	//LADOS SUPERIORES
					((x1==0) && (y1==4))	||
					((x1==0) && (y1==7))	||
					((x1==3) && (y1==1))	||
					((x1==3) && (y1==4))	||
					((x1==3) && (y1==7))	){
						if(	((x2==x1-0) && (y2==y1-1))	||
							((x2==x1+1) && (y2==y1-1))	||
							((x2==x1+1) && (y2==y1+0))	||
							((x2==x1+1) && (y2==y1+1))	||
							((x2==x1+0) && (y2==y1+1))	) result =1 ;
						else result = 0;
			}
			else if (	((x1==2) && (y1==1))	||	//LADOS INFERIORES
					((x1==2) && (y1==4))	||
					((x1==2) && (y1==7))	||
					((x1==5) && (y1==1))	||
					((x1==5) && (y1==4))	||
					((x1==5) && (y1==7))	){
						if(	((x2==x1-0) && (y2==y1-1))	||
							((x2==x1-1) && (y2==y1-1))	||
							((x2==x1-1) && (y2==y1+0))	||
							((x2==x1-1) && (y2==y1+1))	||
							((x2==x1+0) && (y2==y1+1))	) result =1 ;
						else result = 0;
			}
			
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
				if ( 	((x1==0) && (y1==0))	||		//LAS ESQUINAS NADA
					((x1==0) && (y1==2))	||
					((x1==0) && (y1==3))	||
					((x1==0) && (y1==5))	||
					((x1==0) && (y1==6))	||
					((x1==0) && (y1==8))	||
					((x1==2) && (y1==0))	||
					((x1==2) && (y1==2))	||
					((x1==2) && (y1==3))	||
					((x1==2) && (y1==5))	||
					((x1==2) && (y1==6))	||
					((x1==2) && (y1==8))	||
					((x1==3) && (y1==0))	||
					((x1==3) && (y1==2))	||
					((x1==3) && (y1==3))	||
					((x1==3) && (y1==5))	||
					((x1==3) && (y1==6))	||
					((x1==3) && (y1==8))	||
					((x1==5) && (y1==0))	||
					((x1==5) && (y1==2))	||
					((x1==5) && (y1==3))	||
					((x1==5) && (y1==5))	||
					((x1==5) && (y1==6))	||
					((x1==5) && (y1==8))	) result = 0;
				else if (	((x1==1) && (y1==1))	||	//LOS PUNTOS CENTRALES DE CADA CARA
						((x1==1) && (y1==4))	||
						((x1==1) && (y1==7))	||
						((x1==4) && (y1==1))	||
						((x1==4) && (y1==4))	||
						((x1==4) && (y1==7))	){
							if (	((x2==x1-0) && (y2==y1-1) && (x3==x1-0) && (y3==y1+1))	||	//cruceta -
								((x3==x1-0) && (y3==y1-1) && (x2==x1-0) && (y2==y1+1))	||
								((x2==x1-1) && (y2==y1-0) && (x3==x1+1) && (y3==y1-0))	||	//cruceta |
								((x3==x1-1) && (y3==y1-0) && (x2==x1+1) && (y2==y1-0))	||
								((x2==x1+1) && (y2==y1-1) && (x3==x1-1) && (y3==y1+1))	||	//cruceta /
								((x3==x1+1) && (y3==y1-1) && (x2==x1-1) && (y2==y1+1))	||
								((x2==x1-1) && (y2==y1-1) && (x3==x1+1) && (y3==y1+1))	||	//cruceta \
								((x3==x1-1) && (y3==y1-1) && (x2==x1+1) && (y2==y1+1))	) result = 1;
							else result = 0;
				}
				else if ( 	((x1==0) && (y1==1))	||	//LADOS CENTRALES SUPERIOR E INFERIORES
						((x1==0) && (y1==4))	||	
						((x1==0) && (y1==7))	||
						((x1==2) && (y1==1))	||
						((x1==2) && (y1==4))	||
						((x1==2) && (y1==7))	||
						((x1==3) && (y1==1))	||
						((x1==3) && (y1==4))	||
						((x1==3) && (y1==7))	||
						((x1==5) && (y1==1))	||
						((x1==5) && (y1==4))	||
						((x1==5) && (y1==7))	){
							if (	((x2==x1-0) && (y2==y1-1) && (x3==x1-0) && (y3==y1+1))	||	
								((x3==x1-0) && (y3==y1-1) && (x2==x1-0) && (y2==y1+1))	)	result = 1;
							else result = 0;
				}
				else if (	((x1==1) && (y1==0))	||	//LADOS CENTRALES LATERALES (IZQ. Y DER.)
						((x1==4) && (y1==0))	||
						((x1==1) && (y1==2))	||
						((x1==4) && (y1==2))	||
						((x1==1) && (y1==3))	||
						((x1==4) && (y1==3))	||
						((x1==1) && (y1==5))	||
						((x1==4) && (y1==5))	||
						((x1==1) && (y1==6))	||
						((x1==4) && (y1==6))	||
						((x1==1) && (y1==8))	||
						((x1==4) && (y1==8))	){
							if (	((x2==x1-1) && (y2==y1-0) && (x3==x1+1) && (y3==x1-0))	||
								((x3==x1-1) && (y3==y1-0) && (x2==x1+1) && (y2==x1-0))	) result = 1;
							else result = 0;
				}
		}//if
		return result;
	}//analizar_ENTRE
	
	
	
	
}//class analizadorFunciones