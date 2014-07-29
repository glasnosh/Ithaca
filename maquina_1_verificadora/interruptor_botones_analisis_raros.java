/*
 *  "interruptos_botones_analisis.java"
 *	Enable o Disable los botones de analisis alternativos (cubo, espejo, nulo). Permite activarlos o desactivarlos       
 *	 todos de una vez.
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */

 /*	
 	public interruptor_botones_analisis_raros(JButton an_cubo, JButton an_inf, JButton an_esp)	//CONSTRUCTOR
 	public void set(Boolean valor)			//Método intercambiador propiamente dicho
 */
 
import java.lang.*;
import java.io.*;
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class interruptor_botones_analisis_raros{
	JButton an_cubo;			
	JButton an_inf;
	JButton an_esp;
	JButton an_mag;
	JButton an_cen;

	public interruptor_botones_analisis_raros(JButton an_cubo, JButton an_inf, JButton an_esp, JButton an_mag, JButton an_cen){ //los 3 botones
		this.an_cubo = an_cubo;
		this.an_inf = an_inf;
		this.an_esp = an_esp;	
		this.an_mag = an_mag;
		this.an_cen = an_cen;
	}//constructor
	
	public void set(Boolean valor){
		if (valor == true){
			an_cubo.setEnabled(true);
			an_inf.setEnabled(true);
			an_esp.setEnabled(true);		
			an_mag.setEnabled(true);
			an_cen.setEnabled(true);
		}else if (valor == false){
			an_cubo.setEnabled(false);
			an_inf.setEnabled(false);
			an_esp.setEnabled(false);		
			an_mag.setEnabled(false);
			an_cen.setEnabled(false);
		}//else
	}//set


}//class