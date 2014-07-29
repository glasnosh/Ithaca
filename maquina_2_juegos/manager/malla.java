/*
 *  "malla.java"
 *	Estructura de datos que representa la cuadrícula donde se colocan los elementos. Consiste en un array 
 *	 bidimensional de 6 filas y 9 columnas de objetos de la clase "casilla". Además de métotos para manejar
 * 	 los elementos.
 * 
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */


/*
	public malla()				//CONSTRUCTOR

	public void insertar(int casilla_i, int casilla_j, int forma, int tamanho, int color)
	public void insertar(int casilla_i, int casilla_j, char nombre, int forma, int tamanho, int color)
	public void eliminar(int casilla_i, int casilla_j)	
	public void imprimirmalla()				//Mediante System.out.println()
	public int coordenadaX(char nombreElemento)
	public int coordenadaY(char nombreElemento)
	public boolean existeCasilla(char nombreElemento)	//¿Existe una casilla con el nombre "nombreElementos"?
	public casilla devolverCasilla(int i, int j)		//casilla que está en la posición (i,j)
	public casilla[][] devolverCasillas()			//Devuelve la malla completa. Array bidimensional de casillas 
	public int[][] devolverElementos()	//Esta devuelve un array bidimensional con pares de coordenadas de casillas llenas
	public int numElementos()
	public void intercambiar(int casilla1_x, int casilla1_y, int casilla2_x, int casilla2_y)

	malla 6filas x 9columnas de casillas
*/

import java.lang.*;
import java.io.*;
import java.util.*;

public class malla implements Serializable{		//Implementando la interfaz "Serializable" podemos crear flujos tipo
							// ObjectStream para cargar/guardar objetos de este tipo en archivos
	casilla cuadricula[][];
	int numeroElementos;

	public malla(){					//Constructor
		numeroElementos = 0;			//contador del número de elementos que tiene la malla
		cuadricula = new casilla[6][9];		//malla de 6x9 (filas X columnas)
		for(int i=0; i<6; i++){
			for (int j=0; j<9; j++){
				cuadricula[i][j] = new casilla();	//todas las casillas vacias
			}//for 2
		}//for 1	
	}//public malla()
	
	//Los parámetros  tipo "casilla_x, casilla_y, casilla_i, casilla_j" son las coordenadas en la malla de una determinada
	// casilla.
	
	public void intercambiar(int casilla1_x, int casilla1_y, int casilla2_x, int casilla2_y){
		casilla temp;
		temp = cuadricula[casilla1_x][casilla1_y];
		cuadricula[casilla1_x][casilla1_y] = cuadricula[casilla2_x][casilla2_y];
		cuadricula[casilla2_x][casilla2_y] = temp;
	}//intercambiar
	
	public void insertar(int casilla_i, int casilla_j, char nombre, int forma, int tamanho, int color){	//inserta elemento  con nombre en una casilla det.
		if ( (forma!=0) && (tamanho!=0) && (color!=0) ){
			cuadricula[casilla_i][casilla_j] = new casilla(nombre,forma, tamanho, color);
			numeroElementos++;	
		}
	
	}//public insertar
	
	public void insertar(int casilla_i, int casilla_j, int forma, int tamanho, int color){	//inserta elemento sin nombre
		if ( (forma!=0) && (tamanho!=0) && (color!=0) ){
			cuadricula[casilla_i][casilla_j] = new casilla(forma, tamanho, color);
			numeroElementos++;
		}	
	}//public insertar
	
	public void insertar(int casilla_i, int casilla_j, casilla la_casilla){
		if (la_casilla != null) cuadricula[casilla_i][casilla_j] = la_casilla;
	}
	
	public void eliminar(int casilla_i, int casilla_j){	//vacia el contenido de la casilla
		cuadricula[casilla_i][casilla_j] = new casilla();
		numeroElementos--;
	}//public eliminar
	
	public int numElementos(){				//número de elementos que hay 
		return numeroElementos;
	}
	
	
	
	public int[][] devolverElementos(){					//Devuelve el array bidimensional de enteros
		int tabla_elementos[][] = new int[numeroElementos][2];		// formado por pares de coordenadas de las 
		int elemento = 0;						// casillas donde hay un elemento
		for (int i=0; i<6; i++){
			for (int j=0; j<9; j++){
				if (cuadricula[i][j].ocupada() == true) {
					tabla_elementos[elemento][0] = i;
					tabla_elementos[elemento][1] = j;
					elemento++;
				}//if
			}//for
		}//for
		
		return tabla_elementos;
	}
	
	public casilla[][] devolverCasillas(){					//Similar al anterior pero devuelve referencias
		return cuadricula;						// de los objetos casilla de la malla
	}//public devolverCasillas()
	
	public casilla devolverCasilla(int i, int j){				//Devuelve la referencia a una casilla en 
		return cuadricula[i][j];					// particular
	}//public devolverCasilla
	
	public boolean existeCasilla(char nombreElemento){			//¿Existe en la malla un objeto con "nombre"?
		boolean existe = false;
		for(int i=0; i<6; i++){
			for (int j=0; j<9; j++){
				if (cuadricula[i][j].nombre == nombreElemento) existe = true;
			}//for
		}//for
		return existe;
	}
	
	public int coordenadaX(char nombreElemento){				//Coordenada X de la casilla con "nombre"
		int coordenada=100;
		for(int i=0; i<6; i++){
			for(int j=0; j<9; j++){
				if (cuadricula[i][j].nombre == nombreElemento) coordenada = i;
			}//for
		}//for
		return coordenada;
	}

	public int coordenadaY(char nombreElemento){				//Coordenada X de la casilla con "nombre"
		int coordenada=100;
		for(int i=0; i<6; i++){
			for(int j=0; j<9; j++){
				if (cuadricula[i][j].nombre == nombreElemento) coordenada = j;
			}//for
		}//for
		return coordenada;
	}
		
	public void imprimirmalla(){
		System.out.println("    j0   j1   j2   j3   j4   j5   j6   j7   j8\n");
		for(int i=0; i<6; i++){
			System.out.print("i"+i);
			for(int j=0; j<9; j++){
				if (cuadricula[i][j].ocupada == false) System.out.print("  ·  ");
				else if ( (cuadricula[i][j].forma == 1) && (cuadricula[i][j].tamanho == 1) ) System.out.print(" E("+cuadricula[i][j].nombre+")");
				else if ( (cuadricula[i][j].forma == 1) && (cuadricula[i][j].tamanho == 2) ) System.out.print(" e("+cuadricula[i][j].nombre+")");
				else if ( (cuadricula[i][j].forma == 2) && (cuadricula[i][j].tamanho == 1) ) System.out.print(" C("+cuadricula[i][j].nombre+")");
				else if ( (cuadricula[i][j].forma == 2) && (cuadricula[i][j].tamanho == 2) ) System.out.print(" c("+cuadricula[i][j].nombre+")");
				else if ( (cuadricula[i][j].forma == 3) && (cuadricula[i][j].tamanho == 1) ) System.out.print(" P("+cuadricula[i][j].nombre+")");
				else if ( (cuadricula[i][j].forma == 3) && (cuadricula[i][j].tamanho == 2) ) System.out.print(" p("+cuadricula[i][j].nombre+")");
			}//for1
		System.out.println("\n");
		
		}//for2
		System.out.println("NÚMERO DE ELEMENTOS >> "+numeroElementos);
	}//public imprimirmalla
	


}//class malla