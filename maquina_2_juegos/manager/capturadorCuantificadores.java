/*
 *  "capturadorCuantificadores.java"	
 *	Los objetos de este tipo de utilizan en el an�lisis de m�s bajo nivel. Crean una estructura de datos donde se van 
 *       recogiendo los estados de cuantificaci�n de las variables. (vg. X est� cuantificada existencialmente y negado, T no est�
 *	 cuantificada... etc). No establece un control de errores, de forma que permite recuantificar variables aunque eso 
 *       implique un error de sintaxis. Por lo tanto esto debe controlarse en el nivel superior.
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Mart�nez Garc�a <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */


/*
	public capturadorCuantificadores()		//CONSTRUCTOR

	public void reiniciarCuantificadores()
	public void reiniciar(char variable)
	public void cuantificar(char variable, int cuantificacion, int negacion)
	public int devolverCuantificacion(char variable)
	public void negar(char variable)	
	public boolean negado(char variable)
	public boolean cuantificado(char variable)
	public void imprimirTabla()
	

*/



import java_cup.runtime.*;
import java.lang.*;
import java.io.*;
import java.util.*;


class capturadorCuantificadores{
	
	int tablaCuantificadores[][] = new int[4][2];
	
		//	________________________________________
		//	| variable | cuantificacion | negacion |	Cuantificacion: 0(SIN) 1 (universal)  2 (existencial)	
		//	|----------|----------------|----------|	Negaci�n:   0 (cuantificador)   1 (NO cuantificador)
		//	|    x     |    0/1/2       |   0/1    |	x = [0][*]   
		//	|    y     |    0/1/2       |   0/1    |	y = [1][*]
		//	|    z     |    0/1/2       |   0/1    |	z = [2][*]
		//	|    t     |    0/1/2       |   0/1    |	t = [3][*]
		//	----------------------------------------

	
	public capturadorCuantificadores(){
		for(int i=0; i<4; i++){
			for (int j=0; j<2; j++){
				tablaCuantificadores[i][j] = 0;
			}//for
		}//for
	}//constructor
	
	public void reiniciarCuantificadores(){	//ponerlo todo a cero otra vez
		for(int i=0; i<4; i++){
			for (int j=0; j<2; j++){
				tablaCuantificadores[i][j] = 0;
			}//for
		}//for
	}//reiniciarCuantificadores()
	
	public void reiniciar(char variable){	//reinicia una variable en particular
		switch (variable){
			case 'x':{
				tablaCuantificadores[0][0] = 0;
				tablaCuantificadores[0][1] = 0;
				break;
			}//case 'x'
			case 'y':{
				tablaCuantificadores[1][0] = 0;
				tablaCuantificadores[1][1] = 0;
				break;
			}
			case 'z':{
				tablaCuantificadores[2][0] = 0;
				tablaCuantificadores[2][1] = 0;
				break;
			}
			case 't':{
				tablaCuantificadores[3][0] = 0;
				tablaCuantificadores[3][1] = 0;
				break;
			}
		}//switch
	}
	
	public void cuantificar(char variable, int cuantificacion, int negacion){
		switch (variable){
			case 'x':{
				tablaCuantificadores[0][0] = cuantificacion;
				tablaCuantificadores[0][1] = negacion;
				break;
			}
			case 'y':{
				tablaCuantificadores[1][0] = cuantificacion;
				tablaCuantificadores[1][1] = negacion;
				break;
			}
			case 'z':{
				tablaCuantificadores[2][0] = cuantificacion;
				tablaCuantificadores[2][1] = negacion;
				break;
			}
			case 't':{
				tablaCuantificadores[3][0] = cuantificacion;
				tablaCuantificadores[3][1] = negacion;
				break;
			}
		}//switch
			
	}//cuantificar()
	
	public void negar(char variable){	//cambia (1 -> 0) o (0 -> 1) el valor de negaci�n de un cuantificador
		switch (variable){
			case 'x':{
				if (tablaCuantificadores[0][1] == 1) tablaCuantificadores[0][1] = 0;
				else if (tablaCuantificadores[0][1] == 0) tablaCuantificadores[0][1] = 1;
				break;
			}
			case 'y':{
				if (tablaCuantificadores[1][1] == 1) tablaCuantificadores[1][1] = 0;
				else if (tablaCuantificadores[1][1] == 0) tablaCuantificadores[1][1] = 1;
				break;
			}
			case 'z':{
				if (tablaCuantificadores[2][1] == 1) tablaCuantificadores[2][1] = 0;
				else if (tablaCuantificadores[2][1] == 0) tablaCuantificadores[2][1] = 1;
				break;
			}
			case 't':{
				if (tablaCuantificadores[3][1] == 1) tablaCuantificadores[3][1] = 0;
				else if (tablaCuantificadores[3][1] == 0) tablaCuantificadores[3][1] = 1;
				break;
			}
		}//switch
	}//negar()	

	
	public int devolverCuantificacion(char variable){	//trivial
		int vuelta = 5;
		switch (variable){
			case 'x':{
				vuelta =  tablaCuantificadores[0][0];
				break;
				}
		
			case 'y':{
				vuelta = tablaCuantificadores[1][0];
				break;
				}
			case 'z':{
				vuelta = tablaCuantificadores[2][0];
				break;
				}
			case 't':{
				vuelta = tablaCuantificadores[3][0];
				break;
				}
		}//switch
		return vuelta;
	}//devolverCuantificacion()
	
	public boolean negado(char variable){		//�Est� negado el cuantificador de cierta variable?
		boolean vuelta = false;
		
		switch (variable){
			case 'x':{
				if (tablaCuantificadores[0][1] == 0) vuelta = false;
				else vuelta = true;
				break;
			}//case 'x'
			case 'y':{
				if (tablaCuantificadores[1][1] == 0) vuelta = false;
				else vuelta = true;
				break;
			}
			case 'z':{
				if (tablaCuantificadores[2][1] == 0) vuelta = false;
				else vuelta = true;				
				break;
			}
			case 't':{
				if (tablaCuantificadores[3][1] == 0) vuelta = false;
				else vuelta = true;				
				break;
			}
		}//switch	
		return vuelta;
	}//negado()
	
	public boolean cuantificado(char variable){	//comprueba si una varible est� ya cuantificada
		boolean vuelta = false;
		switch (variable){
			case 'x':{
				if (tablaCuantificadores[0][0] == 0) vuelta = false;
				else vuelta = true;
				break;
			}//case 'x'
			case 'y':{
				if (tablaCuantificadores[1][0] == 0) vuelta = false;
				else vuelta = true;
				break;
			}
			case 'z':{
				if (tablaCuantificadores[2][0] == 0) vuelta = false;
				else vuelta = true;				
				break;
			}
			case 't':{
				if (tablaCuantificadores[3][0] == 0) vuelta = false;
				else vuelta = true;				
				break;
			}
		}//switch	
		return vuelta;
	
	}//cuantificado()
	
	public void imprimirTabla(){
		System.out.println("_________________________________________");
		System.out.println("|VARIABLE |CUANTIFICACION|    NEGADO    |"); 
		System.out.println("-----------------------------------------");
		for(int i=0; i<4; i++){
			switch (i){
			case 0:{ System.out.print("|   x     ");
				break;
			}//case 'x'
			case 1:{ System.out.print("|   y     ");
				break;
			}
			case 2:{ System.out.print("|   z     ");
				break;
			}
			case 3:{ System.out.print("|   t     ");
				break;
			}
		}//switch	
			
			for (int j=0; j<2; j++){
				System.out.print("|       "+tablaCuantificadores[i][j]+"      ");
			}//for
			System.out.print("|\n");
		}//for
		System.out.println("-----------------------------------------");
	}//imprimirTabla

}//class capturadorCuantificadores