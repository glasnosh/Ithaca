/*
 *  "hilo_analizador.java"
 * 	Esta clase es la que hace de enlace entre la capa de interfaz gráfica y los parsers analizadores. Implementado como     
 * 	 un hilo de ejecución toma sentencias y las parsea mientras las evalua y devuelve el valor de verdad o lo que sea.
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */
 
 /*
 	Toma la malla sobre la que se va a analizar, la fórmula (que viene en el JtextField) y el tipo de análisis.
	El tipo de análisis puede ser: tablero(1), cilindro(2), cubo(3), nada(4)
	
	Ojo. El texto que se toma del (JTextField) está en el lenguaje de frente; es decir, como aparece en la interfaz. 
	Los parsers funcionan con su lenguaje propio, de forma que antes de pasarles nada hay que traducirlo con la 
 	 clase "miniconversor".
 
 
 */
 
 
import java_cup.runtime.*;
import java.lang.*;
import java.io.*;
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class hilo_analizador implements Runnable{
	
	static JTextField area_texto;
	static String _cadena;
	static String cadena;
	static int tipo_analisis;
	static malla mimalla;
	static Yylex_FIRST lexer;		//Yylex_FIRST comprueba la sintaxis y resuelve los cuantificadores
	static Yylex_SECOND lexer2;		//Yylex_SECOND evalua la sentencia
	static int error_sintaxis;

	public hilo_analizador(malla mimalla, JTextField area_texto, int tipo_analisis){	// La sentencia se toma con todo el 
											// JTextField para devolver el valor 
		error_sintaxis = 0;							// de verdad cambiando el color
		this.tipo_analisis = tipo_analisis;
		this.mimalla = mimalla;
		this.area_texto = area_texto;
		_cadena = area_texto.getText();
		
		cadena = (new miniconversor()).convertir(_cadena);	//el miniconversor transforma todo al lenguaje interno del programa
	}//constructor
	
	
	public void run(){
//System.out.println(" -->"+cadena+"<-- ");
		if (area_texto.getText().compareTo("") == 0){	//Si el area esta vacía, simplemente inicializarla en colores
			area_texto.setBackground(Color.white);
			area_texto.setForeground(Color.black);
		}
		else{
			if (mimalla.numElementos() != 0){	//si la malla tiene elementos del usuario
				try{
					lexer = new Yylex_FIRST(cadena);
					parser_FIRST ps1 = new parser_FIRST(lexer,mimalla);
					ps1.parse();
					if (ps1.errorSintaxis() == false){	//la sintaxis es correcta	
						if (ps1.evaluable()){		//si además es evaluable
							area_texto.setBackground(Color.yellow);
							String cadena1 = ps1.cadena_final();
							lexer2 = new Yylex_SECOND(cadena1);	//evaluarla
							parser_SECOND ps2 = new parser_SECOND(lexer2,mimalla,tipo_analisis);
							ps2.parse();
							if (ps2.RESULTADO() == 1) area_texto.setBackground(Color.green);
							else if (ps2.RESULTADO() == 0) area_texto.setBackground(Color.red);
						}//if
						else {
							area_texto.setBackground(Color.yellow);
						}//else
					}else {		//si la sintaxis es incorrecta
						area_texto.setForeground(Color.white);
						area_texto.setBackground(Color.black);
					}
			
				}catch (Exception error){System.out.println("hilo_analizador.java - PARSER: Error de parseo");}
				
			}else if (mimalla.numElementos() == 0){
				// Ojo. Si la malla no tiene elementos el análisis es especial porque queremos que las fórmulas
				//  tautológicas sean ciertas. Para ello, introduciremos en la malla un objeto "abstracto" que 
				//  luego eliminaremos y que responderá "FALSO" a todas las fórmulas atómicas excepto a la 
				//  tautología. De hecho sea cual sea el tipo de malla elegiremos el topo de analisis 4.
				// Ese objeto lo podemos introducir donde queramos y con los atributos que queramos con la 
				//  condición de que nunca se muestre al usuario y luego sea eliminado.
				try{
				
			mimalla.insertar(3,3,1,1,1);						//elemento abstracto
					lexer = new Yylex_FIRST(cadena);			//Se sigue el mismo proceso
					parser_FIRST ps1 = new parser_FIRST(lexer,mimalla);	// de análisis que antes
					ps1.parse();
					if (ps1.errorSintaxis() == false){

						if (ps1.evaluable()){
							area_texto.setBackground(Color.yellow);
							String cadena1 = ps1.cadena_final();
							lexer2 = new Yylex_SECOND(cadena1);
							parser_SECOND ps2 = new parser_SECOND(lexer2,mimalla,4); //peeeeero....
							ps2.parse();
							if (ps2.RESULTADO() == 1) area_texto.setBackground(Color.green);
							else if (ps2.RESULTADO() == 0) area_texto.setBackground(Color.red);
						}//else
						else {
							area_texto.setBackground(Color.yellow);
						}
					}else {
						area_texto.setForeground(Color.white);
						area_texto.setBackground(Color.black);
					}
			mimalla.eliminar(3,3);		//y finalmente lo eliminamos.

			
				}catch (Exception error){System.out.println("hilo_analizador.java - PARSER: Error de parseo");}
			}//else
		}//else
	}//run


}//class