/*
 *  "hilo_seleccionar_objeto_master.java"
 * 	
 *	Hilo que selecciona un objeto del modelo para la máquina cuando esta juega siguendo las estrategias creadas
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */
 
/*
 *	public hilo_seleccionar_objeto_master(
 *						JTextField texto_formula,
 *						malla mimalla,
 *						tablero _tablero,
 *						String _cuantificador,
 *						seguimiento_arbol seguidor
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



public class hilo_seleccionar_objeto_master extends Thread {

	private Thread idx_Thread = null;

	private int threadpriority=idx_Thread.MIN_PRIORITY;
	
	
	JTextField texto_formula;
	malla mimalla;
	tablero _tablero;
	String _cuantificador;
	seguimiento_arbol seguidor;
	
	
	public hilo_seleccionar_objeto_master(
						JTextField texto_formula,
						malla mimalla,
						tablero _tablero,
						String _cuantificador,
						seguimiento_arbol seguidor
		){	
		
		this.texto_formula = texto_formula;
		this.mimalla = mimalla;
		this._tablero = _tablero;
		this._cuantificador = _cuantificador;
		this.seguidor = seguidor;
	
	}//constructor
	
	
	
	public void run(){

		int objeto  = seguidor._obtener_salida();	//saca el mejor hijo del arbol y sigue por ahí

		
		char nombre_objeto = '&';
		if (objeto == 1) nombre_objeto = 'a';
		else if (objeto == 2) nombre_objeto = 'b';
		else if (objeto == 3) nombre_objeto = 'c';
		else if (objeto == 4) nombre_objeto = 'd';
		else if (objeto == 5) nombre_objeto = 'e';

		if (objeto == 1) seguidor._derivar(1);
		else if (objeto == 2) seguidor._derivar(2);
		else if (objeto == 3) seguidor._derivar(3);
		else if (objeto == 4) seguidor._derivar(4);
		else if (objeto == 5) seguidor._derivar(5);

		
		int x = mimalla.coordenadaX(nombre_objeto);
		int y = mimalla.coordenadaY(nombre_objeto);
		hilo_parpadeo_tablero hpt1 = new hilo_parpadeo_tablero(_tablero, x, y, false);
		hpt1.run();
		try{
			hpt1.join();
		}catch (InterruptedException ie1){System.out.println("no puedo esperar hilo parpadeo_objeto");}

		miniconversor mn1 = new miniconversor();
		char a_traducir = _cuantificador.charAt(_cuantificador.length()-1);
		String codigo_maquina = mn1.convertir_visual_interno(texto_formula.getText());
	 	eliminador_cosas ec1 = new eliminador_cosas(codigo_maquina);
		texto_formula.setText(mn1.convertir_interno_visual(ec1.eliminar_cuantificador(a_traducir,nombre_objeto)));
	}//run
	

	
	public void start() {
        	idx_Thread = new Thread(this);
        	idx_Thread.setPriority(Thread.MIN_PRIORITY);
        	idx_Thread.start();
    	}//start
    
	
	
}//class