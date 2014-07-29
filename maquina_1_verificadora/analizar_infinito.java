/*
 *  "analizar_infinito.java"	
 *	Crea una ventana de diálogo donde se muestra un dibujo que simula ser una malla nula. En esta es como si no hubiera objetos.
 *	 Se analiza aquí la fórmula.
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
 *      | |    Imagen "curvatura"    | |
 *      | |     JLabel con icono     | |
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




class analizar_infinito extends JDialog{

	public static ImageIcon dibujo_curvatura = new ImageIcon("png/curvatura.png");
	
	JLabel etiqueta_dibujo;
	JTextField campo_texto_formula;
	JTextArea area_texto_explicacion;
	JButton boton_cerrar;
	String formula;
	malla mimalla;

	public analizar_infinito(JFrame f, String formula, malla mimalla){//constructor
		super(f,"Modelo Vacío",true);
		this.formula = formula;
		this.mimalla = mimalla;	
	
		etiqueta_dibujo = new JLabel(dibujo_curvatura);
		campo_texto_formula = new JTextField(formula,20);
		campo_texto_formula.setMargin(new Insets(10,10,10,10));
		campo_texto_formula.setEditable(false);
		campo_texto_formula.setBackground(Color.white);
		campo_texto_formula.setForeground(Color.black);
		
		area_texto_explicacion = new JTextArea(" Este modelo presenta una situación en la que no \nhay ningún objeto.\n Hay algo,\n sin embargo, salvaguardando exclusivamente las fórmulas\n válidas expresables en el lenguaje de Ithaca");
		area_texto_explicacion.setEditable(false);
		boton_cerrar = new JButton("Cerrar");
		
		JPanel panel_boton_cerrar = new JPanel(new BorderLayout());
		panel_boton_cerrar.add(BorderLayout.EAST, boton_cerrar);
		
		JPanel panel_abajo = new JPanel(new BorderLayout());
		
		panel_abajo.add(BorderLayout.NORTH,campo_texto_formula);
		
		panel_abajo.add(BorderLayout.SOUTH,panel_boton_cerrar);
		panel_abajo.add(BorderLayout.CENTER,area_texto_explicacion);
		
		JPanel panel_ppal = new JPanel(new GridLayout(2,1));
		panel_ppal.add(etiqueta_dibujo);
		panel_ppal.add(panel_abajo);
		
		hilo_analizador hilo_x = new hilo_analizador(new malla(),campo_texto_formula,0);
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
		pack();
		setLocationRelativeTo(null); 
		setResizable(false);
		setVisible(true);
	}//constructor

}//class