/*
 *	"nodo.java" 
 *		Esta clase define los objetos básicos que se utilizaran en los árboles; los nodos. 
 *		Las variables de clase incluyen la información, sus hijos y también la información sobre estrategias
 *		 que se utilizará más adelante.
 *  
 * Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */


/*
 * 
 * public String _contenido()
 * public int _numero_hijos()
 * public nodo _hijo1()
 * public nodo _hijo2()
 * public nodo _hijo3()
 * public nodo _hijo4()
 * public nodo _hijo5()
 * public nodo _padre()
 * public boolean _rol()
 * public boolean _es_hoja()
 * public boolean _valor_hoja()
 * public int _estado()
 * public String _hijo_izquierdo()
 * public String _hijo_derecho()
 * 
 * public nodo(String contenido, nodo padre, malla mimalla)
 * 
 * 
 * 
 *		Una fórmula del lenguaje se la puede identificar en cuatro estados diferentes; y por lo tanto a cada
 *		 subfórmula de la misma también. Este estado nos lo da el "parser_STATUS":
 *			-Estado '1'. La fórmula, vista desde lo más general, es de la forma: Cuantificador(subfórmula)
 *					Siendo el cuantificador Existe un x o Para todo x
 *					
 *			-Estado '2'. La fórmula está en derivación, de la forma: subfórmula conector subfórmula
 *					Siendo el conector: v,^, -->, <->
 *
 *			-Estado '3'. La fórmula es la negación de una subfórmula que no comienza inmediatamente por un 
 *					cuantificdor:   NO ( subfórmula )
 *
 *			-Estado '4'. La fórmula es la negación de una subfórmula donde la negación afecta inmediatamente 
 *					a un cuantificador:  	NO paratodo x (subformula)    	ó
 								NO existe x (subfórmula)
 *
 *		Toda fórmula del lenguaje se puede identificar en uno de estos cuatro estados siempre. Sino, es que es una 
 * 			"HOJA". Una hoja es una fórmula simple del lenguaje directamente decidible: grande(a), detras(a,b)...
 *
 *
 *
 *		Durante el juego, uno de los juegadores toma el rol de verificador y el otro el de falsador. El objetivo es 
 *			recorrer el árbol e intentar llegar a un nodo hoja cuyo valor decidible (verdadero o falso) coincida 
 *			con el que interesa a cada jugador. En ese caso el jugador que le coincida gana la partida. 
 *			En el juego participa el jugador humano que debera decidir sus propias jugadas y la máquina como 
 *			oponente.
 *		
 *		Estando en "Estado = 1", significa que alguien debe elegir uno de los objetos de la malla para resolver el
 *			cuantificador. Es decir, pasar de "Paratodo x Grande(x)" a algo concreto como "Grande(a)" o "Grande(c)"
 *			Si el cuantificador es universal, la decisión y por tanto el turno de juego recae en el falsador.
 *			Si el cuantificador es existencial, juega el verificador
 *
 *		Estando en "Estado = 2", significa que alguien debe elegir una de las dos subfórmulas para continuar el juego.
 *			Si el conector es: v,-->    juega el verificador.
 *			Si el conector es: ^,<->    juega el falsador
 *
 *		Estando en "Estado = 3" ó "Estado = 4" lo que ocurre es que no juega nadie. Se elimina esa negación y se 
 *			cambian los roles de los jugadores. Falsador pasa a verificador y verificador a falsador.
 *
 *
 */			


import java.awt.*;
import java.awt.event.*;

import java.awt.font.TextLayout;
import java.awt.font.FontRenderContext;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.AWTKeyStroke;
import javax.swing.border.*;
import java.awt.GridBagLayout;

import javax.imageio.*;
import javax.imageio.stream.*;
import java.awt.image.*;
import java.net.URL;
import javax.swing.JFileChooser;

import java.util.Random;

import java.io.*;

public class nodo{
	
	String contenido;	// Fórmula o trozo que almacena ese nodo
	int numero_hijos;	
	nodo hijo1;		// Enlaces a los hijos. Como máximo un nodo tendrá 5 hijos:
	nodo hijo2;		// si es de derivación "-->, v, ^, <->" tendrá 2
	nodo hijo3;		// si es de negación  tendrá 1
	nodo hijo4;		// si es cuantificador tendrá tantos como objetos en la malla, que está limitado a 5
	nodo hijo5;
	nodo padre;		//Para poder ascender del arbol a las hojas
	boolean rol_nodo;	//Según el estado de la fórmula quien jugara en ese nodo, verificador (E,v,->) o falsador
				// (CU,^,<->)
	boolean rol_maquina;	// rol con que llega la máquina en función del anterior
	
	boolean ganador;	//Si es un nodo desde el que ya se gana fijo fijo fijo pase lo que pase. Sirve para crear
				// estrategias ganadoras
				
	int numero_de_hijo;	//es el hijo de su padre número... [1,5]
	
	boolean v1;		//true si hay salida hacia una buena estrategia por el hijo 1
	boolean v2;		// idem por el hijo 2
	boolean v3;		// 	...
	boolean v4;
	boolean v5;
	
	boolean negacion;	// true si el nodo esta en estado 3
	
	boolean es_hoja;	
	boolean valor_hoja;	//verdadera o falsa si es hoja. Si no es hoja no se usa.
	int estado;		//1,2,3 ó 4
	
	//las 	que siguen no contienen información sobre el nodo. Se declaran aquí para que sean globales simplemente
	
	boolean _hoja;			
	String hijo_izquierdo;		//Si esta en estado de derivación, que subfórmula es hijo izquierdo
	String hijo_derecho;		// IDEM hijo derecho
	String trozo;			//Si esta en estado de cuantificación, lo que está cuantificado
	String cuantificador;		//El cuantificador si esta en estado de cuantificacion
	
	
	public void _v(int hijo){		//indica que hay una estrategia ganadora saliendo por el hojo indicado
		if (hijo == 1) v1 = true;
		if (hijo == 2) v2 = true;
		if (hijo == 3) v3 = true;
		if (hijo == 4) v4 = true;
		if (hijo == 5) v5 = true;
	}
	public boolean _return_v(int hijo){
		boolean vuelta = false;
		if (hijo == 1) vuelta = v1;
		if (hijo == 2) vuelta = v2;
		if (hijo == 3) vuelta = v3;
		if (hijo == 4) vuelta = v4;
		if (hijo == 5) vuelta = v5;
		return vuelta;
	}
	
	public boolean _ganador() { return ganador; }			// Saber si el nodo es ganador para la máquina
	public void _setGanador(boolean valor) { ganador = valor; }	
	public String _contenido(){ return contenido;}
	public int _numero_hijos(){ return numero_hijos; }
	public nodo _hijo1(){ return hijo1;}
	public nodo _hijo2(){ return hijo2;}
	public nodo _hijo3(){ return hijo3;}
	public nodo _hijo4(){ return hijo4;}				// Casi todas estas para devolver los valores
	public nodo _hijo5(){ return hijo5;}				//  de las variables miembro. Poco misterio
	public nodo _padre(){ return padre;}
	public boolean _rol_nodo(){ return rol_nodo; }
	public boolean _rol_maquina(){ return rol_maquina; }
	public boolean _es_hoja(){ return es_hoja;}
	public boolean _valor_hoja(){ return valor_hoja; }
	public int _estado(){ return estado; }
	public String _hijo_izquierdo(){ return hijo_izquierdo;}
	public String _hijo_derecho(){ return hijo_derecho;}
	public String _cuantificador(){ return cuantificador;}
	public void anhadir_hijo(nodo el_nodo, int numero_hijo){
		if (numero_hijo == 1) hijo1 = el_nodo;
		else if (numero_hijo == 2) hijo2 = el_nodo;
		else if (numero_hijo == 3) hijo3 = el_nodo;
		else if (numero_hijo == 4) hijo4 = el_nodo;
		else if (numero_hijo == 5) hijo5 = el_nodo;
		
	}
	public int _numero_de_hijo() {return numero_de_hijo;}
	public boolean _negacion() { return negacion; }
	
	public nodo(String contenido, nodo padre, malla mimalla, int numero_de_hijo, boolean rol_maquina){	//CONSTRUCTOR


		this.contenido = contenido;
		this.padre = padre;				
		this.numero_de_hijo = numero_de_hijo;		
		this.rol_maquina = rol_maquina;
		
		hijo1 = null;
		hijo2 = null;
		hijo3 = null;
		hijo4 = null;
		hijo5 = null;
		
		v1 = false;
		v2 = false;
		v3 = false;
		v4 = false;
		v5 = false;
		
		ganador = false;
				
		try{								//Explicación: Se crea el nodo con su fórmula.
			Yylex_STATUS lexer = new Yylex_STATUS(contenido);	// se le pasa por el parser_STATUS para saber
			parser_STATUS _ps1 = new parser_STATUS(lexer,contenido);// que tipo de nodo es
			_ps1.parse();						// y se rellenan el resto de sus variables
			estado = _ps1.estado();					// miembro con esa información para saber
			_hoja = _ps1.hoja();					// además como crear sus descendientes
			hijo_izquierdo = _ps1.hijo_izquierdo();
			hijo_derecho = _ps1.hijo_derecho();
			trozo = _ps1.trozo();
			cuantificador = _ps1.cuantificador();
	
		}catch(Exception error){System.out.println("PARSER_STATUS:  Error de parseo ");}
		
		if (estado == 1){	//cuantificacion
			numero_hijos = mimalla.numElementos();
			es_hoja = false;
			if  (cuantificador.charAt(0) == 'E') rol_nodo = true;
			else rol_nodo = false;
		}
		else if (estado == 2){	//Derivación
			numero_hijos = 2;
			es_hoja = false;
			if ((trozo.compareTo("v") == 0) || (trozo.compareTo("-->") == 0)) rol_nodo = true;
			else rol_nodo = false;
		}
		else if (estado == 3){	//NEgación normal de una fórmula
			numero_hijos = 1;
			es_hoja = false;
			negacion = true;
		}
		else if (estado == 4){	//Negación de un cuantificador
			numero_hijos = 1;
			es_hoja = false;
			negacion = true;
		}
		else if ( (_hoja == true) && (estado == 0) ){	//Si es hoja... le buscamos el valor 1 ó 0
			numero_hijos = 0;
			es_hoja = true;
			JTextField texto_formula = new JTextField(contenido);
			hilo_analizador hilo_x = new hilo_analizador(mimalla,texto_formula,1);
			hilo_x.run();
			if ( texto_formula.getBackground() == Color.green) valor_hoja = true;
			else valor_hoja = false;
		}
			
	}//construcotr
	
}//class



