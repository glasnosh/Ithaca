/*
 *  "warning_max_elementos.java"
 *	Clase que hereda de JDialog para mostrar un aviso si se trata de introducir m�s de 15 elementos en la malla.
 * 
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

class warning_max_elementos extends JDialog{
	public static ImageIcon iconowarning = new ImageIcon("png/warning.png");
	JLabel etiquetagif = new JLabel(iconowarning);
	JLabel etiquetatexto = new JLabel("No se pueden insertar m�s de 15 elementos");
	JButton botonaceptar = new JButton("Aceptar");
	
	JPanel panel = new JPanel();
	JPanel centro = new JPanel(new FlowLayout());
	JPanel abajo = new JPanel();
	
	public warning_max_elementos(JFrame f){
		super(f,"AVISO",true);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout());
		
		centro.add(etiquetagif);
		centro.add(etiquetatexto);
		botonaceptar.setMargin(new Insets(0,0,0,0));
		abajo.add(botonaceptar);
		panel.add("Center",centro);
		panel.add("South",abajo);
		
		addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e){
					setVisible(false);
			}});
				
		botonaceptar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e4)
		{
					setVisible(false);
				}
			});
		Toolkit tk = Toolkit.getDefaultToolkit();
         	Dimension d = tk.getScreenSize();
				
		pack();
		setLocationRelativeTo(null); 
		setResizable(false);
		setVisible(true);
	}//fin del constructor



}//fin de la clase