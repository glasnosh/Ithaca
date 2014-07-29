/*
 *  "help.java"
 *	Crea un cuadro de diálogo modal para mostrar el HTML de ayuda
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

class help extends JDialog{
	
JEditorPane _panel_html;
	
	
public help(JFrame f){
		super(f,"AYUDA",true);
				
		JButton boton = new JButton("Cerrar");
		boton.setMargin(new Insets(0,5,0,5));
		JPanel panel_sur = new JPanel();
		panel_sur.add(boton);
		JPanel _panel_sur = new JPanel(new BorderLayout());
		_panel_sur.add(panel_sur,BorderLayout.EAST);
		
		
		
		try{
			_panel_html = new JEditorPane("file:./txt/Documentacion/index.html");
		}catch (IOException e){System.out.println("help.java: Error creando el JEditorPane;");}
		
		JScrollPane __panel_html = new JScrollPane(_panel_html);
				
		JPanel panel_global = new JPanel(new BorderLayout());
		panel_global.add(__panel_html,BorderLayout.CENTER);
		panel_global.add(_panel_sur, BorderLayout.SOUTH);
		
		
		getContentPane().add(panel_global);
				
		addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){dispose();}});
		boton.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e4){dispose();}});
		
		setSize(800,600);
		setLocationRelativeTo(null); 
		setResizable(false);
		setVisible(true);
	}//fin del constructor



}//fin de la clase