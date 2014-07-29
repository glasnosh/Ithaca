/* 
 *	seguimiento_arbol.java
 *		Usamos esta clase para hacer el seguimiento del juego. Como de antemano la máquina tiene todo el árbol
 *		 simplemente con esto vamos siguiendo por donde discurre en función de lo que elijan los jugadores y 
 *		 sabiendo así con que fórmula se está jugando en cada momento.
 *
 *		Como el árbol que se tiene ya tiene marcadas las estrategias buenas para la máquina (en caso de que existan)
 *		 se usa también para decidir cual va a ser la siguiente jugada de la máquina.
 *
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */
 
/*
 *	public seguimiento_arbol(arbol arbol1, nodo nodo_raiz)		//CONSTRUCTOR	 
 *	public int[] _recorrido()			// Derivaciones que se han hecho
 *	public void _derivar(int i)			// Hacia que hijo va el juego
 *	public nodo get_nodo_recorrido()		// Referencia hacia el nodo en que se está
 *	public int _obtener_salida()			// Mejor salida posible para la máquina
 *
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;


public class seguimiento_arbol{
	arbol arbol1;			// El árbol en cuestión. Tiene que llevar marcadas ya las estrategias
	nodo nodo_recorrido;		// Una varible interna temporal
	nodo nodo_raiz;			// Nodo raiz del nodo
	
	int[] recorrido; 	//guardamos aquí la sentencia de pasos. ESTA VARIABLE NO SE USA...
	int numero_derivaciones; 	//...que se han hecho
	
	public seguimiento_arbol(arbol arbol1, nodo nodo_raiz){
		this.arbol1 = arbol1;
		this.nodo_raiz = nodo_raiz;
		
		numero_derivaciones = 0;
		recorrido = new int[100];		// Máximo de jugadas que se almacenan. Sobra...
		recorrido[0] = 0;
		
		nodo_recorrido = nodo_raiz;		// Al ppo el nodo que se juega es el raiz.
	}//constructor
	
	public int[] _recorrido() { return recorrido; }		//Implementada pero nunca usada...
	
	public void _derivar(int i){					//Indicamos hacia que hijo vamos.
		numero_derivaciones++;
		if (i == 1){
			nodo_recorrido = nodo_recorrido._hijo1();
			recorrido[numero_derivaciones] = 1;	 
		}
		else if (i == 2){
			nodo_recorrido = nodo_recorrido._hijo2();
			recorrido[numero_derivaciones] = 2;	 
		}
		else if (i == 3){
			nodo_recorrido = nodo_recorrido._hijo3();
			recorrido[numero_derivaciones] = 3;	 
		}
		else if (i == 4){
			nodo_recorrido = nodo_recorrido._hijo4();
			recorrido[numero_derivaciones] = 4;	 
		}
		else if (i == 5){
			nodo_recorrido = nodo_recorrido._hijo5();
			recorrido[numero_derivaciones] = 5;	 
		}
		
	}//heredar
	
	public nodo get_nodo_recorrido(){ return nodo_recorrido; }
	
	public int _obtener_salida(){	// [1,5] según número de hijo
	
		/* 
		 * Criterios para elegir una ruta de salida:
		 * 	1º  Tener salida hacia un nodo final ganador (con rol que coincida con el de la máquina
		 * 	2º  Tener salida hacia un nodo "ganador"
		 *    	3º  Conservar el turno de juego
		 * 	4º  Al azar
		 * 
		 * 	Si hay varios que cumplan lo mismo, pues el primero
		 */

		 int salida = -1; 
		 	//criterio 1º
		 if ((nodo_recorrido._hijo1() != null) && ((nodo_recorrido._hijo1())._es_hoja() == true) && (nodo_recorrido._rol_maquina() == (nodo_recorrido._hijo1())._valor_hoja())) salida = 1;
		 else if ((nodo_recorrido._hijo2() != null) && ((nodo_recorrido._hijo2())._es_hoja() == true) && (nodo_recorrido._rol_maquina() == (nodo_recorrido._hijo2())._valor_hoja())) salida = 2;
		 else if ((nodo_recorrido._hijo3() != null) && ((nodo_recorrido._hijo3())._es_hoja() == true) && (nodo_recorrido._rol_maquina() == (nodo_recorrido._hijo3())._valor_hoja())) salida = 3;
		 else if ((nodo_recorrido._hijo4() != null) && ((nodo_recorrido._hijo4())._es_hoja() == true) && (nodo_recorrido._rol_maquina() == (nodo_recorrido._hijo4())._valor_hoja())) salida = 4;
		 else if ((nodo_recorrido._hijo5() != null) && ((nodo_recorrido._hijo5())._es_hoja() == true) && (nodo_recorrido._rol_maquina() == (nodo_recorrido._hijo5())._valor_hoja())) salida = 5;
		 	
		 	//criterio 2º
		else if ((nodo_recorrido._hijo1() != null) && ((nodo_recorrido._hijo1())._ganador() == true)) salida = 1;
		else if ((nodo_recorrido._hijo2() != null) && ((nodo_recorrido._hijo2())._ganador() == true)) salida = 2;
		else if ((nodo_recorrido._hijo3() != null) && ((nodo_recorrido._hijo3())._ganador() == true)) salida = 3;
		else if ((nodo_recorrido._hijo4() != null) && ((nodo_recorrido._hijo4())._ganador() == true)) salida = 4;
		else if ((nodo_recorrido._hijo5() != null) && ((nodo_recorrido._hijo5())._ganador() == true)) salida = 5;
		
			//criterio 3º
		else if ((nodo_recorrido._hijo1() != null) && (nodo_recorrido._rol_maquina() == (nodo_recorrido._hijo1())._rol_nodo())) salida = 1; 
		else if ((nodo_recorrido._hijo2() != null) && (nodo_recorrido._rol_maquina() == (nodo_recorrido._hijo2())._rol_nodo())) salida = 2; 
		else if ((nodo_recorrido._hijo3() != null) && (nodo_recorrido._rol_maquina() == (nodo_recorrido._hijo3())._rol_nodo())) salida = 3; 
		else if ((nodo_recorrido._hijo4() != null) && (nodo_recorrido._rol_maquina() == (nodo_recorrido._hijo4())._rol_nodo())) salida = 4; 
		else if ((nodo_recorrido._hijo5() != null) && (nodo_recorrido._rol_maquina() == (nodo_recorrido._hijo5())._rol_nodo())) salida = 5; 
		
			//criterio 4
		else {
			Random aleatorio1 = new Random();	
			salida  = 1 + aleatorio1.nextInt(nodo_recorrido._numero_hijos());
			
		}//else 
//System.out.println("Numero de hijos del nodo es >> "+nodo_recorrido._numero_hijos());
//System.out.println("Salida optima para nodo es >> "+salida);
		 
		 return salida; //1, 2, 3, 4 ó 5
		 
	}
}//class