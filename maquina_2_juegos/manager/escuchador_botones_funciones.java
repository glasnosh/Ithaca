/*
 *  "escuchador_botones_funciones.java"
 *      Se usa cuando se hace click sobre un boton de función atómica en la interfaz. Escribe el texto correspondiente en el 
 *       area de texto seleccionada y recoloca el cursor en el lugar donde debe ir el primer parámetro entre paréntesis       
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */


import java.lang.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class escuchador_botones_funciones{
	
	JTextField area_texto;
	String subcadena;
	
	public escuchador_botones_funciones(JTextField area_texto,String subcadena){ //constructor. Area de texto y el texto
	
		this.area_texto = area_texto;
		
		int posicion_cursor = area_texto.getCaretPosition();
		String contenido = area_texto.getText();
		String cadena_final = contenido.substring(0,posicion_cursor)+subcadena+contenido.substring(posicion_cursor,contenido.length());
		area_texto.setText(cadena_final);
		area_texto.requestFocus();
		area_texto.setCaretPosition( posicion_cursor + (subcadena.indexOf ('(') +1) );
	}//constructor

}//class