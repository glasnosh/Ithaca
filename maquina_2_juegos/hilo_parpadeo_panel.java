/*
 *  "hilo_parpadeo_panel.java"
 * 	
 *	Hilo que hace que el "marco" de un panel aparezca y desaparezca varias veces para imitar un parpadeo del panel
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */

/*
 *	public hilo_parpadeo_panel(JComponent l1, boolean estado_final)
 *	public void run()
 *	public void start()
 *	private void encendido()	//Coloca el marco/borde
 *	private void apagado()		//Elimina el marco/borde
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import javax.swing.border.*;

import java.awt.image.*;
import java.net.URL;



public class hilo_parpadeo_panel extends Thread {

	private Thread idx_Thread = null;
	private int threadpriority=idx_Thread.MIN_PRIORITY;
	private JComponent l1;
	boolean estado_final;
	
	public hilo_parpadeo_panel(JComponent l1, boolean estado_final){	//estado final dice si al final queda encendido o apagado
		this.l1 = l1;
		this.estado_final = estado_final;
	}//constructor
	
	
	public void run(){	//enciende, espera, apaga, espera, enciende, espera, ..... (10 veces)
		int i=0;
		while(i<10){
			try{
				idx_Thread.sleep(60);
				apagado();
				idx_Thread.sleep(60);
				encendido();
				i++;
			}
			catch (InterruptedException e){}
		}
		if (estado_final == false) apagado();
	}//run
	
	
	public void start() {
        	idx_Thread = new Thread(this);
        	idx_Thread.setPriority(Thread.MIN_PRIORITY);
        	idx_Thread.start();
    	}//start
	
    	private void encendido(){
		l1.setBorder(new LineBorder(Color.blue,10,true));
	}//encendido
	
	private void apagado(){
		l1.setBorder(new EmptyBorder(10,10,10,10));
	}//apagado
	
	
	
}//class