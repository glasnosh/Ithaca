/*
 *  "analizar_espejo.java"	
 *	Crea una ventana de diálogo donde se muestra la imagen especular de la malla y se la analiza
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


/*
 *	La estructura de componentes es:
 *
 *	________________________________	
 *      | ____________________________ |
 *      | |                          | |
 *      | |    Imagen "espejo "      | |
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




class analizar_espejo extends JDialog{

	
	
	
	JTextField campo_texto_formula;
	JTextArea area_texto_explicacion;
	JButton boton_cerrar;
	String formula;
	malla mimalla;

	public analizar_espejo(JFrame f, String formula, malla mimalla){//constructor
		super(f,"Modelo Espejo",true);
		this.formula = formula;
		
		malla malla_espejo = (new intercambiador_malla()).espejo(mimalla);	//le damos la vuelta a la malla (espejo)
		
		tablero_espejo te1 = new tablero_espejo(malla_espejo);		//dibujo del tablero espejo (es un jpanel)
		campo_texto_formula = new JTextField(20);
		campo_texto_formula.setText(formula);
		campo_texto_formula.setMargin(new Insets(10,10,10,10));
		campo_texto_formula.setEditable(false);
		campo_texto_formula.setBackground(Color.white);
		campo_texto_formula.setForeground(Color.black);
		
		area_texto_explicacion = new JTextArea(" \nEste es un mundo espejo del original. \nLa malla es una imagen especular donde en las \n fórmulas han cambiado los valores de verdad \n en relación a las posiciones \"izquierda\" y \"derecha\".");
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
		
		hilo_analizador hilo_x = new hilo_analizador(malla_espejo,campo_texto_formula,1);  //analisis
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
		malla_espejo = (new intercambiador_malla()).espejo(malla_espejo); //me he hecho un pequeño lio con el paso de variables. Esto se hace para que la malla vuelva a estar en su sitio, sino queda del reves en el original al pasar los parametros por referencia
	}//constructor

}//class