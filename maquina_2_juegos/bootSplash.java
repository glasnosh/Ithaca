/*
 *  "bootSplash.java"	
 *	Crea una pantalla de "bootSplash" al iniciar la aplicación. Desciende de JWindow, que es una clase semiabstracta
 *       de ventana que no trae botones ni barra superior. Perfectoooo!!!
 *	
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */

import javax.swing.*;
import javax.swing.event.*;


import java.lang.*;
import java.awt.*;
import java.util.*;
import java.io.*;



public class bootSplash extends JWindow{

	public static ImageIcon icono_bootsplash = new ImageIcon("png/bootsplash.png"); //Todo el dibujo a una etiqueta.
	
	public bootSplash(){
	
		try{								//Probamos este UIManager para que luego no 
			UIManager.put("swing.boldMetal", Boolean.FALSE);	// obligue al resto de la aplicación.
		}catch (Exception e){}
		JLabel etiqueta = new JLabel(icono_bootsplash);
		getContentPane().add(etiqueta);
	    	pack();
	}//constructor

}//class