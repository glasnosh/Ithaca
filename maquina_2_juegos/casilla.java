/*
 *  "casilla.java"
 *	Ver documentaci�n en ITHACA
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Mart�nez Garc�a <glasnosh@gmail.com>
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

	
	Casilla de la maya
*/

import java.lang.*;
import java.io.*;
import java.util.*;


public class casilla implements Serializable{
	Boolean ocupada;	//flag que indica si la casilla posee alg�n objeto
	char nombre;
	int forma;		// 1: circular,  2: cuadrada,   3: triangular
	int tamanho;		// 1: grande,   2: peque�a
	int color;		// 1: rojo, 2: azul , 3: amarillo
	
	public casilla(){	//crea casilla vac�a
		ocupada= false;
		nombre = ' ';
		forma = 0;
		tamanho = 0;	
		color = 0;
	}//public casilla()
	
	public casilla(char nombre, int forma, int tamanho, int color){
		ocupada = true;
		this.nombre = nombre;
		this.forma = forma;
		this.tamanho = tamanho;	
		this.color = color;
	}//public casilla(int forma, int tamanho)
	
	public casilla(int forma, int tamanho, int color){
		ocupada = true;
		this.forma = forma;
		this.tamanho = tamanho;	
		this.color = color;
	}//public casilla(int forma, int tamanho)
	
	public Boolean ocupada(){	//indica si la casilla est� ocupada
		 if (ocupada == true) return true;
		 else return false;
	}
	
	public void vaciar(){		//vac�a la casilla
		ocupada = false;
	}
	
	public int forma(){	//devuelve la forma
		return forma;
	}
	
	public int tamanho(){	//devuelve el tama�o
		return tamanho;
	}
	
	public int color(){	//devuelve el color
		return color;
	}
	
	public char nombre(){	//devuelve el nombre
		return nombre;
	}

}//class casilla

