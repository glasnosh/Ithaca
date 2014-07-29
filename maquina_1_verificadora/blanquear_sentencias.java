/*
 *  "blanquear_sentencias.java"	
 *	Pone todos los (JTextField) donde se escriben las fórmulas con fondo blanco y texto en negro. Así es como debe
 *	 estar cuando no se han analizado.
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


public class blanquear_sentencias implements Runnable{	//lo hacemos como un thread separado por si acaso es un proceso muy pesado

	static Object[][] elementos_tabla;

	public blanquear_sentencias(Object[][] elementos_tabla){	//los JTextField están en el array grande este
		this.elementos_tabla = elementos_tabla;
	}//constructor
	
	
	public void run(){
		for (int i=0; i<100; i++){
			((JTextField)elementos_tabla[i][1]).setForeground(Color.black);
			((JTextField)elementos_tabla[i][1]).setBackground(Color.white);
		}//for
	}//run

}//class