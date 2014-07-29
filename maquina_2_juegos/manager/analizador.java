/*
 *  "analizador.java"	
 *	Esta clase es similar a la de "hilo_analizador" de ITHACA pero no funciona como Thread. Para poder saber cuando termina
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */
 
/*
 *	public analizador(malla mimalla, JTextField area_texto, int tipo_analisis)	// CONSTRUCTOR
 *	public int analizar()		
 */
 
import java_cup.runtime.*;
import java.lang.*;
import java.io.*;
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class analizador{
	
	static String _cadena;
	static String cadena;
	static int tipo_analisis;
	static malla mimalla;
	static Yylex_FIRST lexer;		
	static Yylex_SECOND lexer2;
	static int error_sintaxis;
	JTextField area_texto;

	public analizador(malla mimalla, JTextField area_texto, int tipo_analisis){	// La sentencia se toma con todo el 
											// JTextField para devolver el valor 
		error_sintaxis = 0;							// de verdad cambiando el color
		this.tipo_analisis = tipo_analisis;
		this.mimalla = mimalla;
		this.area_texto = area_texto;
		_cadena = area_texto.getText();
		
		cadena = (new miniconversor()).convertir(_cadena);	//el miniconversor transforma todo al lenguaje interno del programa
	}//constructor
	
	
	public int analizar(){		//1. ok    2. no
		int vuelta = 100;
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
							vuelta = 0;
							String cadena1 = ps1.cadena_final();
							lexer2 = new Yylex_SECOND(cadena1);	//evaluarla
							parser_SECOND ps2 = new parser_SECOND(lexer2,mimalla,tipo_analisis);
							ps2.parse();
							if (ps2.RESULTADO() == 1){
								 area_texto.setBackground(Color.green);
								 vuelta = 1;
							}
							else if (ps2.RESULTADO() == 0){
							 	area_texto.setBackground(Color.red);
								vuelta = 1;
							}
						}//if
						else {
							area_texto.setBackground(Color.yellow);
							vuelta = 0;
						}//else
					}else {		//si la sintaxis es incorrecta
						area_texto.setForeground(Color.white);
						area_texto.setBackground(Color.black);
						vuelta = 0;
					}
			
				}catch (Exception error){System.out.println("hilo_analizador.java - PARSER: Error de parseo");}
				
			}else if (mimalla.numElementos() == 0){
				vuelta = 0;
			}//else
		}//else
		return vuelta;
	}//analizar


}//class