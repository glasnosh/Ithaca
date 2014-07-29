/*
 *  "hilo_seleccionar_subarbol_azar.java"
 * 	
 *	Hilo que selecciona una derivación del árbol para la máquina cuando esta juega "sin inteligencia" (al azar)
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */
 
/*
 *	public hilo_seleccionar_subarbol_azar(
 *			JTextField texto_formula,
 *			JButton boton_hijo_izquierdo,
 *			JButton boton_hijo_derecho,
 *			//JPanel panel_derivacion;
 *			JLabel label_informacion_2,
 *			seguimiento_arbol seguidor
 *		)
 *
 *	public void run()
 *	public void start()
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import javax.swing.border.*;
import java.awt.image.*;
import java.net.URL;
import java.util.Random;


public class hilo_seleccionar_subarbol_azar extends Thread {

	private Thread idx_Thread = null;

	private int threadpriority=idx_Thread.MIN_PRIORITY;
	
	JTextField texto_formula;
	JButton boton_hijo_izquierdo;
	JButton boton_hijo_derecho;
	JPanel panel_derivacion;
	JLabel label_informacion_2;
	seguimiento_arbol seguidor;
	
	
	public hilo_seleccionar_subarbol_azar(
			JTextField texto_formula,
			JButton boton_hijo_izquierdo,
			JButton boton_hijo_derecho,
			JLabel label_informacion_2,
			seguimiento_arbol seguidor
		){	
		
		this.texto_formula = texto_formula;
		this.boton_hijo_izquierdo = boton_hijo_izquierdo;
		this.boton_hijo_derecho = boton_hijo_derecho;
		this.label_informacion_2 = label_informacion_2;
		this.seguidor = seguidor;
		
	}//constructor
	
	
	
	public void run(){
		Random aleatorio1 = new Random();	
		int subarbol  = (aleatorio1.nextInt(2) + 1);	//1=izquierdo, 2=derecho
		miniconversor mn1 = new miniconversor();
		if (subarbol == 1) {
			texto_formula.setText(mn1.convertir_interno_visual(boton_hijo_izquierdo.getText()));
				(new hilo_parpadeo_panel(boton_hijo_izquierdo,false)).run();
				seguidor._derivar(1);
		}//if
		
		if (subarbol == 2) {
		texto_formula.setText( mn1.convertir_interno_visual(boton_hijo_derecho.getText()));
				(new hilo_parpadeo_panel(boton_hijo_derecho,false)).run();
				seguidor._derivar(2);
		}//if
	}//run
	

	
	public void start() {
        	idx_Thread = new Thread(this);
        	idx_Thread.setPriority(Thread.MIN_PRIORITY);
        	idx_Thread.start();
    	}//start
}//class