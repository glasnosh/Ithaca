/*
 *  "casilla.java"
 *      Implementa la  estructura de datos más simple de la aplicación. Una casilla tiene: nombre, forma, tamanho, color
 *       y puede o no estar ocupada.
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */

/*

	public casilla()
	public casilla(char nombre, int forma, int tamanho, int color)
	
	public casilla(int forma, int tamanho, int color)
	public Boolean ocupada()
	public void vaciar()
	public int forma()
	public int tamanho()
	public int color()
	public char nombre()

	
	Casilla de la malla
*/

import java.lang.*;
import java.io.*;
import java.util.*;


public class casilla implements Serializable{	//La interfaz "Serializable" permitirá crear un ObjectOutputStream para almacenar
					 	// objetos de este tipo en disco directamente.
						
	Boolean ocupada;	//flag que indica si la casilla posee algún objeto
	char nombre;		// a,b,c,d,e,f,g,h
	int forma;		// 1: circular,  2: cuadrada,   3: triangular
	int tamanho;		// 1: grande,   2: pequeña
	int color;		// 1: rojo, 2: azul , 3: amarillo
	
	public casilla(){	//crea casilla vacía
		ocupada= false;
		nombre = ' ';
		forma = 0;
		tamanho = 0;	
		color = 0;
	}//public casilla()
	
	public casilla(char nombre, int forma, int tamanho, int color){		//casilla con algo dentro y nombre
		ocupada = true;
		this.nombre = nombre;
		this.forma = forma;
		this.tamanho = tamanho;	
		this.color = color;
	}//public casilla(int forma, int tamanho)
	
	public casilla(int forma, int tamanho, int color){			//casilla con algo dentro y nombre
		ocupada = true;
		this.forma = forma;
		this.tamanho = tamanho;	
		this.color = color;
	}//public casilla(int forma, int tamanho)
	
	public Boolean ocupada(){	//indica si la casilla está ocupada
		 if (ocupada == true) return true;
		 else return false;
	}
	
	public void vaciar(){		//vacía la casilla
		ocupada = false;
	}
	
	public int forma(){	//devuelve la forma
		return forma;
	}
	
	public int tamanho(){	//devuelve el tamaño
		return tamanho;
	}
	
	public int color(){	//devuelve el color
		return color;
	}
	
	public char nombre(){	//devuelve el nombre
		return nombre;
	}

}//class casilla

