/*
 *  "hilo_parpadeo_tablero.java"
 * 	
 *	idem que el de paneles pero para el tablero
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import javax.swing.border.*;

import java.awt.image.*;
import java.net.URL;



public class hilo_parpadeo_tablero extends Thread {

	private Thread idx_Thread = null;
	private int threadpriority=idx_Thread.MIN_PRIORITY;
	tablero _tablero;
	int x;
	int y;
	boolean estado_final;
	
	public hilo_parpadeo_tablero(tablero _tablero, int x, int y, boolean estado_final){	
		this._tablero = _tablero;
		this.x = x;
		this.y = y;
		this.estado_final = estado_final;
	}//constructor
	
	
	public void run(){
		int i=0;
		while(i<20){
			try{
				idx_Thread.sleep(60);
				_tablero.parpadear(x,y);
				_tablero.repaint();
				idx_Thread.sleep(60);
				_tablero.desparpadear(x,y);
				_tablero.repaint();
				i++;
			}
			catch (InterruptedException e){}
		}
		if (estado_final == false){ 
			_tablero.desparpadear(x,y);
			_tablero.repaint();
		}
	}
	

	
	public void start() {
        	idx_Thread = new Thread(this);
        	idx_Thread.setPriority(Thread.MIN_PRIORITY);
        	idx_Thread.start();
    }
    
	
	
}//class