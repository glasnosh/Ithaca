/*
 *	"interfaz_manager.java"
 *		Interfaz principal del manager. 
 *		Todo se carga y se guarda del archivo: "./models.i"
 *
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


class interfaz_manager extends JFrame{

	public static ImageIcon icono_salir =new ImageIcon("png/exit.png");
	public static ImageIcon icono_guardar = new ImageIcon("png/save.png");
	public static ImageIcon icono_manager =new ImageIcon("png/icono_manager.png");
	public static Container contenedor_ppal;
	public static JPanel panel_superior;
	public static JTabbedPane panel_ppal;

	public static JToolBar barra_herramientas;
	
	public static JPanel panel_faciles;
	public static JPanel panel_medios;
	public static JPanel panel_dificiles;
	public static JPanel panel_norte_faciles;
	public static JPanel panel_norte_medios;
	public static JPanel panel_norte_dificiles;
	public static JPanel panel_botones_facil;
	public static JPanel panel_botones_medio;
	public static JPanel panel_botones_dificil;
	
	public static JButton boton_faciles_anhadir;
	public static JButton boton_faciles_eliminar;
	public static JButton boton_medios_anhadir;
	public static JButton boton_medios_eliminar;
	public static JButton boton_dificiles_anhadir;
	public static JButton boton_dificiles_eliminar;
	
	public static JButton boton_herramientas_salir;
	public static JButton boton_herramientas_guardar;
	
	public static JScrollPane sc1;
	public static JScrollPane sc2;
	public static JScrollPane sc3;
	
	public static Vector vector_formulas_faciles;
	public static Vector vector_modelos_faciles;
	public static Vector vector_formulas_medios;
	public static Vector vector_modelos_medios;
	public static Vector vector_formulas_dificiles;
	public static Vector vector_modelos_dificiles;
	
	public static JList lista_faciles;
	public static JList lista_medios;
	public static JList lista_dificiles;
	
	public static tablero_silent tablero_faciles;
	public static tablero_silent tablero_medios;
	public static tablero_silent tablero_dificiles;
	
	MouseListener mouseListenerFACIL;
	MouseListener mouseListenerMEDIO;
	MouseListener mouseListenerDIFICIL;
	
	KeyListener keyListenerFACIL;
	KeyListener keyListenerMEDIO;
	KeyListener keyListenerDIFICIL;
	
	//
	String[] formulas_faciles;
	String[] formulas_medios;
	String[] formulas_dificiles;
	
	miniconversor_doble mn1;
	
	boolean cambios;	//si se ha cambiado algo o no; flag

public interfaz_manager(){

	cambios = false;
	mn1 = new miniconversor_doble();
	
	vector_formulas_faciles = new Vector(10,1);	//Los pares fórmula/modelo se guardan en Vectores
	vector_modelos_faciles = new Vector(10,1);
	vector_formulas_medios = new Vector(10,1);
	vector_modelos_medios = new Vector(10,1);
	vector_formulas_dificiles = new Vector(10,1);
	vector_modelos_dificiles = new Vector(10,1);
	
	
	cargar();	//Carga los vectores con las fórmulas previamente almacenadas en disco
		
	addWindowListener(new WindowAdapter() {	public void windowClosing(WindowEvent e) {
		salvar_salir();
	}});
	
	try{
		UIManager.put("swing.boldMetal", Boolean.FALSE);
	}catch (Exception e){System.out.println("interfaz.java: Exception UI");}
	
	barra_herramientas = new JToolBar("BarraHerramientas1",javax.swing.SwingConstants.HORIZONTAL);
	barra_herramientas.setFloatable(false);
	barra_herramientas.setBorderPainted(false);
		
		
	boton_herramientas_salir = new JButton(icono_salir);
	boton_herramientas_guardar = new JButton(icono_guardar);
	barra_herramientas.addSeparator();
	barra_herramientas.add(boton_herramientas_salir);
	barra_herramientas.addSeparator();
	barra_herramientas.add(boton_herramientas_guardar);
	
	
	panel_superior = new JPanel(new BorderLayout());
	panel_ppal = new JTabbedPane(SwingConstants.TOP);

	panel_superior.add(BorderLayout.NORTH, barra_herramientas);
	panel_superior.add(BorderLayout.CENTER, panel_ppal);
	
	//FACILES
	panel_faciles = new JPanel(new BorderLayout());
	
	formulas_faciles = new String[vector_formulas_faciles.size()];
	for (int i=0; i< vector_formulas_faciles.size(); i++) formulas_faciles[i] =mn1.convertir_interno_visual ( (String)vector_formulas_faciles.elementAt(i));
	lista_faciles = new JList(formulas_faciles);
	lista_faciles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	lista_faciles.setVisibleRowCount(5);
	sc1 = new JScrollPane(lista_faciles);
	
	boton_faciles_anhadir = new JButton("Agregar nueva");
	boton_faciles_eliminar = new JButton("Eliminar");
	panel_botones_facil = new JPanel(new GridLayout(2,1));
	panel_botones_facil.add(boton_faciles_anhadir);
	panel_botones_facil.add(boton_faciles_eliminar);
	
	tablero_faciles = new tablero_silent(new malla());
	panel_norte_faciles = new JPanel(new BorderLayout());
	panel_norte_faciles.add(BorderLayout.CENTER, sc1);
	panel_norte_faciles.add(BorderLayout.EAST, panel_botones_facil);
	panel_norte_faciles.add(BorderLayout.NORTH, new JPanel());
	panel_norte_faciles.add(BorderLayout.SOUTH, new JPanel());
	panel_norte_faciles.add(BorderLayout.WEST, new JPanel());
	
	panel_faciles.add(BorderLayout.NORTH, panel_norte_faciles);
	panel_faciles.add(BorderLayout.CENTER, tablero_faciles);
	panel_faciles.add(BorderLayout.SOUTH, new JPanel());
	panel_faciles.add(BorderLayout.EAST, new JPanel());
	panel_faciles.add(BorderLayout.WEST, new JPanel());

	//Escuchadores para que la imagen del tablero cambie al modelo de cada fórmula al pulsar con el ratón o 
	// al mover con las teclas de cursor
	
	mouseListenerFACIL = new MouseAdapter() {	public void mouseClicked(MouseEvent e) {
		if (lista_faciles.getSelectedIndex() != -1){
         	 tablero_faciles.asignar_malla ((malla)vector_modelos_faciles.elementAt(lista_faciles.getSelectedIndex()));
     		}
 	}};
	
	
	keyListenerFACIL = new KeyAdapter(){public void keyReleased(KeyEvent e){
		if (lista_faciles.getSelectedIndex() != -1){
		 	tablero_faciles.asignar_malla ((malla)vector_modelos_faciles.elementAt(lista_faciles.getSelectedIndex()));
		}
	}};

 	lista_faciles.addMouseListener(mouseListenerFACIL);
	lista_faciles.addKeyListener(keyListenerFACIL);
		
	boton_faciles_eliminar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent bfe){
		int numero_a_eliminar = lista_faciles.getSelectedIndex();
		if (numero_a_eliminar != -1){
			vector_modelos_faciles.removeElementAt(numero_a_eliminar); 
			vector_formulas_faciles.removeElementAt(numero_a_eliminar); 		
			formulas_faciles = new String[vector_formulas_faciles.size()];
			for (int i=0; i< vector_formulas_faciles.size(); i++){
				formulas_faciles[i] = mn1.convertir_interno_visual((String)vector_formulas_faciles.elementAt(i));
			}
			lista_faciles.setListData(formulas_faciles); 
			cambios = true;
		}//if
	}});
	
	boton_faciles_anhadir.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent bfa){
		interfaz2 i2 = new interfaz2();
		if (i2.ok() == true) {
			String nueva_formula = i2.devolver_formula();

			malla nueva_malla = i2.devolver_malla();

			vector_formulas_faciles.addElement(nueva_formula);
			vector_modelos_faciles.addElement(nueva_malla);
			formulas_faciles = new String[vector_formulas_faciles.size()];
			for (int i=0; i< vector_formulas_faciles.size(); i++){
			formulas_faciles[i] = mn1.convertir_interno_visual((String)vector_formulas_faciles.elementAt(i));
			}
			lista_faciles.setListData(formulas_faciles); 
			cambios = true;
		}
	}});
	
	//MEDIAS
	panel_medios = new JPanel(new BorderLayout());
	
	formulas_medios = new String[vector_formulas_medios.size()];
	for (int i=0; i< vector_formulas_medios.size(); i++) formulas_medios[i] =mn1.convertir_interno_visual ( (String)vector_formulas_medios.elementAt(i));
	lista_medios = new JList(formulas_medios);
	lista_medios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	lista_medios.setVisibleRowCount(5);
	sc2 = new JScrollPane(lista_medios);
	
	boton_medios_anhadir = new JButton("Agregar nueva");
	boton_medios_eliminar = new JButton("Eliminar");
	panel_botones_medio = new JPanel(new GridLayout(2,1));
	panel_botones_medio.add(boton_medios_anhadir);
	panel_botones_medio.add(boton_medios_eliminar);
	
	tablero_medios = new tablero_silent(new malla());
	panel_norte_medios = new JPanel(new BorderLayout());
	panel_norte_medios.add(BorderLayout.CENTER, sc2);
	panel_norte_medios.add(BorderLayout.EAST, panel_botones_medio);
	panel_norte_medios.add(BorderLayout.NORTH, new JPanel());
	panel_norte_medios.add(BorderLayout.SOUTH, new JPanel());
	panel_norte_medios.add(BorderLayout.WEST, new JPanel());
	
	panel_medios.add(BorderLayout.NORTH, panel_norte_medios);
	panel_medios.add(BorderLayout.CENTER, tablero_medios);
	panel_medios.add(BorderLayout.SOUTH, new JPanel());
	panel_medios.add(BorderLayout.EAST, new JPanel());
	panel_medios.add(BorderLayout.WEST, new JPanel());

	
	
	mouseListenerMEDIO = new MouseAdapter() {	public void mouseClicked(MouseEvent e) {
		if (lista_medios.getSelectedIndex() != -1){
         	 tablero_medios.asignar_malla ((malla)vector_modelos_medios.elementAt(lista_medios.getSelectedIndex()));
     		}
 	}};
	
	
	keyListenerMEDIO = new KeyAdapter(){public void keyReleased(KeyEvent e){
		if (lista_medios.getSelectedIndex() != -1){
		 tablero_medios.asignar_malla ((malla)vector_modelos_medios.elementAt(lista_medios.getSelectedIndex()));
		}
	}};

 	lista_medios.addMouseListener(mouseListenerMEDIO);
	lista_medios.addKeyListener(keyListenerMEDIO);
		
	boton_medios_eliminar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent bme){
		int numero_a_eliminar = lista_medios.getSelectedIndex();
		if (numero_a_eliminar != -1){
			vector_modelos_medios.removeElementAt(numero_a_eliminar); 
			vector_formulas_medios.removeElementAt(numero_a_eliminar); 		
			formulas_medios = new String[vector_formulas_medios.size()];
			for (int i=0; i< vector_formulas_medios.size(); i++){
				formulas_medios[i] = mn1.convertir_interno_visual((String)vector_formulas_medios.elementAt(i));
			}
			lista_medios.setListData(formulas_medios); 
			cambios = true;
		}//if
	}});
	
	boton_medios_anhadir.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent bma){
		interfaz2 i2 = new interfaz2();
		if (i2.ok() == true) {
			String nueva_formula = i2.devolver_formula();
			malla nueva_malla = i2.devolver_malla();
			vector_formulas_medios.addElement(nueva_formula);
			vector_modelos_medios.addElement(nueva_malla);
			formulas_medios = new String[vector_formulas_medios.size()];
			for (int i=0; i< vector_formulas_medios.size(); i++){
			formulas_medios[i] = mn1.convertir_interno_visual((String)vector_formulas_medios.elementAt(i));
			}
			lista_medios.setListData(formulas_medios); 
			cambios = true;
		}
	}});
	
	//DIFICILES
	panel_dificiles = new JPanel(new BorderLayout());
	
	formulas_dificiles = new String[vector_formulas_dificiles.size()];
	for (int i=0; i< vector_formulas_dificiles.size(); i++) formulas_dificiles[i] =mn1.convertir_interno_visual ( (String)vector_formulas_dificiles.elementAt(i));
	lista_dificiles = new JList(formulas_dificiles);
	lista_dificiles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	lista_dificiles.setVisibleRowCount(5);
	sc3 = new JScrollPane(lista_dificiles);
	
	boton_dificiles_anhadir = new JButton("Agregar nueva");
	boton_dificiles_eliminar = new JButton("Eliminar");
	panel_botones_dificil = new JPanel(new GridLayout(2,1));
	panel_botones_dificil.add(boton_dificiles_anhadir);
	panel_botones_dificil.add(boton_dificiles_eliminar);
	
	tablero_dificiles = new tablero_silent(new malla());
	panel_norte_dificiles = new JPanel(new BorderLayout());
	panel_norte_dificiles.add(BorderLayout.CENTER, sc3);
	panel_norte_dificiles.add(BorderLayout.EAST, panel_botones_dificil);
	panel_norte_dificiles.add(BorderLayout.NORTH, new JPanel());
	panel_norte_dificiles.add(BorderLayout.SOUTH, new JPanel());
	panel_norte_dificiles.add(BorderLayout.WEST, new JPanel());
	
	panel_dificiles.add(BorderLayout.NORTH, panel_norte_dificiles);
	panel_dificiles.add(BorderLayout.CENTER, tablero_dificiles);
	panel_dificiles.add(BorderLayout.SOUTH, new JPanel());
	panel_dificiles.add(BorderLayout.EAST, new JPanel());
	panel_dificiles.add(BorderLayout.WEST, new JPanel());

	
	
	mouseListenerDIFICIL = new MouseAdapter() {public void mouseClicked(MouseEvent e) {
		if (lista_dificiles.getSelectedIndex() != -1){
         		tablero_dificiles.asignar_malla ((malla)vector_modelos_dificiles.elementAt(lista_dificiles.getSelectedIndex()));
		}//if
     		
 	}};
	
	
	keyListenerDIFICIL = new KeyAdapter(){public void keyReleased(KeyEvent e){
		if (lista_dificiles.getSelectedIndex() != -1){
		 	tablero_dificiles.asignar_malla ((malla)vector_modelos_dificiles.elementAt(lista_dificiles.getSelectedIndex()));
		 }//if
	}};

 	lista_dificiles.addMouseListener(mouseListenerDIFICIL);
	lista_dificiles.addKeyListener(keyListenerDIFICIL);
		
	boton_dificiles_eliminar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent bme){
		int numero_a_eliminar = lista_dificiles.getSelectedIndex();
		if (numero_a_eliminar != -1){
			vector_modelos_dificiles.removeElementAt(numero_a_eliminar); 
			vector_formulas_dificiles.removeElementAt(numero_a_eliminar); 		
			formulas_dificiles = new String[vector_formulas_dificiles.size()];
			for (int i=0; i< vector_formulas_dificiles.size(); i++){
				formulas_dificiles[i] = mn1.convertir_interno_visual((String)vector_formulas_dificiles.elementAt(i));
			}
			lista_dificiles.setListData(formulas_dificiles); 
			cambios = true;
		}//if
	}});
	
	boton_dificiles_anhadir.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent bma){
		interfaz2 i2 = new interfaz2();
		if (i2.ok() == true) {
			String nueva_formula = i2.devolver_formula();
			malla nueva_malla = i2.devolver_malla();
			vector_formulas_dificiles.addElement(nueva_formula);
			vector_modelos_dificiles.addElement(nueva_malla);
			formulas_dificiles = new String[vector_formulas_dificiles.size()];
			for (int i=0; i< vector_formulas_dificiles.size(); i++){
			formulas_dificiles[i] = mn1.convertir_interno_visual((String)vector_formulas_dificiles.elementAt(i));
			}
			lista_dificiles.setListData(formulas_dificiles); 
			cambios = true;
		}
	}});
	
	
	/////////////////////////////////////////////////////////////////
	boton_herramientas_salir.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_salir){
			salvar_salir();
	}});
	
	boton_herramientas_guardar.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent b_guardar){
		if (cambios == true) {
			salvar();
			cambios = false;
		}//if
	}});
	
	//
	boton_herramientas_salir.setToolTipText("Salir salvando los cambios realizados");
	boton_herramientas_guardar.setToolTipText("Salvar los cambios");
	boton_faciles_anhadir.setToolTipText("Añadir Fórmula/Modelo al conjunto de fáciles");
	boton_medios_anhadir.setToolTipText("Añadir Fórmula/Modelo al conjunto de medios");
	boton_dificiles_anhadir.setToolTipText("Añadir Fórmula/Modelo al conjunto de dificiles");
	boton_faciles_eliminar.setToolTipText("Eliminar Fórmula/Modelo marcado del conjunto de fáciles");
	boton_medios_eliminar.setToolTipText("Eliminar Fórmula/Modelo marcado del conjunto de medios");
	boton_dificiles_eliminar.setToolTipText("Eliminar Fórmula/Modelo marcado del conjunto de dificiles");
	
	
	
	//
	panel_ppal.add("Fáciles", panel_faciles);
	panel_ppal.add("Medias", panel_medios);
	panel_ppal.add("Difíciles", panel_dificiles);
		
	contenedor_ppal = getContentPane();
	contenedor_ppal.add(panel_superior);
	
	setTitle("Formula-Manager for ITHACA Games");
	setIconImage(icono_manager.getImage());
	setLocation(100,100);
	setSize(500,420);
	setVisible(true);
	
}//public interfaz()

	private void salvar(){
	
		try{
						FileOutputStream salida = new FileOutputStream("./models.i");
						ObjectOutputStream oos = new ObjectOutputStream(salida);
						oos.writeObject(vector_formulas_faciles);
						oos.writeObject(vector_modelos_faciles);
						oos.writeObject(vector_formulas_medios);
						oos.writeObject(vector_modelos_medios);
						oos.writeObject(vector_formulas_dificiles);
						oos.writeObject(vector_modelos_dificiles);
						oos.close();
						
						(new JOptionPane(null)).showMessageDialog(null, "Guardado correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
		}catch(Exception e){System.out.println("Error E/S");}
				
	}//salvar_salir

	private void salvar_salir(){
		if (cambios == false) System.exit(0);
		else{
			(new JOptionPane(null)).showMessageDialog(null, "Los cambios realizados serán salvados automáticamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			
			try{
						FileOutputStream salida = new FileOutputStream("./models.i");
						ObjectOutputStream oos = new ObjectOutputStream(salida);
						oos.writeObject(vector_formulas_faciles);
						oos.writeObject(vector_modelos_faciles);
						oos.writeObject(vector_formulas_medios);
						oos.writeObject(vector_modelos_medios);
						oos.writeObject(vector_formulas_dificiles);
						oos.writeObject(vector_modelos_dificiles);
						oos.close();
					}catch(Exception e){System.out.println("Error E/S");}
			System.exit(0);
		}//else
	}//salvar_salir
	
	private void cargar(){
		try{
			FileInputStream entrada = new FileInputStream("./models.i");
			ObjectInputStream oos = new ObjectInputStream(entrada);
			vector_formulas_faciles = (Vector) oos.readObject();
			vector_modelos_faciles  = (Vector) oos.readObject();
			vector_formulas_medios = (Vector) oos.readObject();
			vector_modelos_medios  = (Vector) oos.readObject();
			vector_formulas_dificiles = (Vector) oos.readObject();
			vector_modelos_dificiles  = (Vector) oos.readObject();
						
    						oos.close();
		}catch(Exception e){
			(new JOptionPane(null)).showMessageDialog(null, "No se ha encontrado el archivo de fórmulas", "ERROR", JOptionPane.ERROR_MESSAGE);
			System.exit(0);	
		}
	}


public static void main(String[] args) 
	{
		
		new interfaz_manager();
	}

}//class interfaz