/*
 *  "hilo_colocar_objeto_usuario.java"
 * 	
 *	Cuando juega el usuario, este hilo se queda esperando a obtener el objeto sobre el que hace el click para
 *	 pasarlo a la máquina y seguir jugando
 *
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */

/*
 * 		public hilo_colocar_objeto_usuario(					//CONSTRUCTOR
 *						JTextField texto_formula,
 *						int estado,
 *						tablero _tablero,
 *						String _cuantificador)
 *		
 *		public void run()
 *		public void start()
 *
 *
 *
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



public class hilo_colocar_objeto_usuario extends Thread {

	private Thread idx_Thread = null;

	private int threadpriority=idx_Thread.MIN_PRIORITY;
	
	
	tablero _tablero;
	int estado;
	String _cuantificador;
	JTextField texto_formula;
	miniconversor mn1;
	
	
	public hilo_colocar_objeto_usuario(					//CONSTRUCTOR
						JTextField texto_formula,
						int estado,
						tablero _tablero,
						String _cuantificador
		){	
		
		this.texto_formula = texto_formula;
		this.estado = estado;
		this._tablero = _tablero;
		this._cuantificador = _cuantificador;
		
		mn1 = new miniconversor();
	
	}//constructor
	
	
	public void run(){

		if ( (_tablero.get_sensible() == true) && (estado == 1) ){	// ".get_sensible() enciende o apaga el que 
			char objeto_marcado = _tablero.objeto_clickado();	//   se escuche o no lo que hace el usuario
			if (	(objeto_marcado == 'a') ||
			 	(objeto_marcado == 'b') ||
				(objeto_marcado == 'c') ||
				(objeto_marcado == 'd') ||
				(objeto_marcado == 'e') ||
				(objeto_marcado == 'f') ||
				(objeto_marcado == 'g') ||
				(objeto_marcado == 'h') ){
	 				char a_traducir = _cuantificador.charAt(_cuantificador.length()-1);
					String codigo_maquina = mn1.convertir_visual_interno(texto_formula.getText());
				 	eliminador_cosas ec1 = new eliminador_cosas(codigo_maquina);
					texto_formula.setText(mn1.convertir_interno_visual(ec1.eliminar_cuantificador(a_traducir,objeto_marcado)));
					try{idx_Thread.sleep(1500);
					}catch (InterruptedException easdf){}
					
					}//if
						
				}//if
	}//run
	

	
	public void start() {
        	idx_Thread = new Thread(this);
        	idx_Thread.setPriority(Thread.MIN_PRIORITY);
        	idx_Thread.start();
    	}//start()
    
	
	
}//class