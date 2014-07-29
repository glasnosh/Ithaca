/*
 *  "confirmacion_salir.java"
 *      Crea una ventana modal (extends JDialog) con las opciones "Sí/No" para confirmar si se desea cerrar el programa.
 *       
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

class confirmacion_salir extends JDialog{
	public static ImageIcon iconointerrogacion = new ImageIcon("png/yeah.png");
	JLabel etiquetagif = new JLabel(iconointerrogacion);
	JLabel etiquetatexto = new JLabel("¿Estás seguro?");
	JButton botonsi = new JButton("Sí");
	JButton botonno = new JButton("No");
	JPanel panel = new JPanel();
	JPanel relleno = new JPanel();
	JPanel botones = new JPanel();
	JPanel etiquetas = new JPanel();
	
	public confirmacion_salir(JFrame f){
		super(f,"Cerrar aplicación",true);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout());
		botones.setLayout(new GridLayout(1,2,15,15));
		etiquetas.setLayout(new GridLayout(1,2,15,15));
		etiquetas.add(etiquetagif);
		etiquetas.add(etiquetatexto);
		botonsi.setMargin(new Insets(0,0,0,0));
		botonno.setMargin(new Insets(0,0,0,0));
		botones.add(botonsi);
		botones.add(botonno);
		panel.add("Center",etiquetas);
		panel.add("South",botones);
		
		addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e){	//Si se cierra la ventana, nada
					setVisible(false);
			}});

				
		botonno.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e4)
		{
					setVisible(false);			//si no se quiere salir, se esconde y listo
				}
			});

		botonsi.addActionListener(new ActionListener(){			//si se confirma, exit(0)
			public void actionPerformed(ActionEvent e4)
		{
					System.out.println("Bye!");
					System.exit(0);
				}
			});

		Toolkit tk = Toolkit.getDefaultToolkit();
         	Dimension d = tk.getScreenSize();
				
         	setSize(250,130);
		setLocationRelativeTo(null); 
		setResizable(false);
		setVisible(true);
	}//fin del constructor



}//fin de la clase