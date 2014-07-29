/*
 *  "analizar_centrado.java"	
 *
 *	Tomando los elementos de la malla original, se recolocan en la parte central y se analiza.
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


/*
 *	La estructura de componentes es:
 *
 *	________________________________	
 *      | ____________________________ |
 *      | |                          | |
 *      | |    Imagen "centr. "      | |
 *      | |          JPanel          | |
 *      | |                          | |
 *      | |                          | |
 *      | |__________________________| |
 *      | ___________________________| |
 *      | | JTextField - Formula     | |
 *      | |__________________________| |
 *      | ____________________________ |
 *      | |                          | |
 *      | |  JTextArea explicacion   | |
 *      | |    del mundo             | |
 *      | |__________________________| |
 *      |            _________________ |
 *      |            |JButton cerrar | |
 *      |______________________________|
 *
 *
*/




class analizar_centrado extends JDialog{

	
	
	tablero_silent te1;
	JTextField campo_texto_formula;
	JTextArea area_texto_explicacion;
	JButton boton_cerrar;
	String formula;
	malla mimalla_2;

	public analizar_centrado(JFrame f,malla mimalla, String formula){//constructor
		super(f,"Modelo \"Centrado\"",true);
		this.formula = formula;
		
		mimalla_2 = new malla();						// La malla es nueva
										
		te1 = new tablero_silent(mimalla_2);
		campo_texto_formula = new JTextField(20);
		campo_texto_formula.setText(formula);
		campo_texto_formula.setMargin(new Insets(10,10,10,10));
		campo_texto_formula.setEditable(false);
		campo_texto_formula.setBackground(Color.white);
		campo_texto_formula.setForeground(Color.black);
										
		
		//____________________________________
		//Se recorre la malla original y se van colocando elementos iguales en esta en posiciones predefinidas
		//posiciones predefinidas
		int[][] casillas_centrales = {	{2,4},
						{2,3},
						{2,5},
						{1,4},
						{3,4},
						{1,3},
						{1,5},
						{3,3},
						{3,5},
						{2,2},
						{2,6},
						{3,2},
						{3,6},
						{1,2},
						{1,6},
					     };
		
		int nElementos = mimalla.numElementos();
		int[][] casillas_originales = mimalla.devolverElementos();
		int c_forma;
		int c_tamanho;
		int c_color;
		char c_nombre;
		int pos_x_original;
		int pos_y_original;
		int pos_x_centrado;
		int pos_y_centrado;
		
		for (int i=1; i<=nElementos; i++){
			pos_x_original = casillas_originales[i-1][0];
			pos_y_original = casillas_originales[i-1][1];
			c_forma = (mimalla.devolverCasilla(pos_x_original, pos_y_original)).forma();
			c_tamanho = (mimalla.devolverCasilla(pos_x_original, pos_y_original)).tamanho();
			c_color = (mimalla.devolverCasilla(pos_x_original, pos_y_original)).color();
			c_nombre = (mimalla.devolverCasilla(pos_x_original, pos_y_original)).nombre();
			pos_x_centrado = casillas_centrales[i-1][0];
			pos_y_centrado = casillas_centrales[i-1][1];
			
			if ((c_nombre) != ' ') mimalla_2.insertar(pos_x_centrado, 
								pos_y_centrado, 
								c_nombre,
								c_forma,
								c_tamanho,
								c_color	);
								
			else mimalla_2.insertar(	pos_x_centrado,
						pos_y_centrado,
						c_forma,
						c_tamanho,
						c_color);
			
		}//for
		//______________________________________
		area_texto_explicacion = new JTextArea(" \nEste mundo tiene los mismos elementos que\nel original, pero distribuidos en la\nparte central.");
		area_texto_explicacion.setEditable(false);
		boton_cerrar = new JButton("Cerrar");
		
		JPanel panel_boton_cerrar = new JPanel(new BorderLayout()); //para que  este a la derecha anidamos dos paneles
		panel_boton_cerrar.add(BorderLayout.EAST, boton_cerrar);
		
		JPanel panel_abajo = new JPanel(new BorderLayout());
		
		panel_abajo.add(BorderLayout.NORTH,campo_texto_formula);
		
		panel_abajo.add(BorderLayout.SOUTH,panel_boton_cerrar);
		panel_abajo.add(BorderLayout.CENTER,area_texto_explicacion);
		
		JPanel panel_ppal = new JPanel(new GridLayout(2,1));
		panel_ppal.add(te1);
		panel_ppal.add(panel_abajo);
		
		hilo_analizador hilo_x = new hilo_analizador(mimalla_2,campo_texto_formula,1);  //analisis
		hilo_x.run();
	
	
	
		addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e){
					setVisible(false);
			}});

				
		boton_cerrar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e4)
		{
					setVisible(false);
				}
			});
	
	
		getContentPane().add(panel_ppal);
		setSize(470,400);
		setLocationRelativeTo(null); 
		setResizable(false);
		setVisible(true);
		
	}//constructor

}//class