/*
 *  "escuchador_botones_letras.java"
 *      Se crean objetos de este tipo al pulsar en la interfaz sobre botones que contienen variables y nombres.
 *       Colocan la letra en la cadena del (JTextField) que se para como parámetro y recoloca el cursor donde debe ir 
 *       o bien el siguiente parámetro o al final.
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

class escuchador_botones_letras{
	
	JTextField area_texto;
	char caracter;
	
	public escuchador_botones_letras(JTextField area_texto,char caracter, int offset_caracteres){
		this.area_texto = area_texto;
		
		int posicion_cursor = area_texto.getCaretPosition();
		String contenido = area_texto.getText();
		String cadena_final = contenido.substring(0,posicion_cursor)+caracter+contenido.substring(posicion_cursor,contenido.length());
		area_texto.setText(cadena_final);
		area_texto.requestFocus();

		if ((posicion_cursor+2) <= area_texto.getText().length())   area_texto.setCaretPosition(posicion_cursor  +2);
		
	}//constructor

}//class