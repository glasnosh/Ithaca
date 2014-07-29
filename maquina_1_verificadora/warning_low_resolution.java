/*
 *  "warning_low_resolucion.java"
 *	Esta clase muestra un cuadro de di�logo con el aviso de que la resoluci�n es insuficiente para visualizar
 * 	 correctamente la aplicaci�n. Al crear esta clase autom�ticamente se muestra el aviso, de modo que se debe 
 * 	 instanciar solamente si es preciso mostrar el aviso. La resoluci�n m�nima deseable es de 800x600 puntos.
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Mart�nez Garc�a <glasnosh@gmail.com>
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

class warning_low_resolution{

	public warning_low_resolution(){
		
		Toolkit tk = Toolkit.getDefaultToolkit();
         	Dimension d = tk.getScreenSize();
	
		String texto_low_resolution = "<html><b> AVISO; RESOLUCI�N BAJA</b><br> Est� utilizando una resoluci�n de pantalla de <b>("+d.width+", "+d.height+")</b>.<br>Para visualizar correctamente este programa deber�a<br> disponer de una resoluci�n m�nima de <b>(800,600)</b>.<br><font color=red>Puede que algunos componentes no se muestren correctamente</font></html>";
		
		JOptionPane jop1 = new JOptionPane();
		jop1.showMessageDialog(null, texto_low_resolution, "AVISO", JOptionPane.WARNING_MESSAGE);
	}//fin del constructor



}//fin de la clase