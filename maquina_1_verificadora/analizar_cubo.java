/*
 *  "analizar_cubo.java"	
 *	Crea una ventana de diálogo donde se muestra la malla en forma de cubo girando y su análisis en esta representación
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
 *      | |    	     CUBO            | |
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




class analizar_cubo extends JDialog{

	JTextField campo_texto_formula;
	JTextArea area_texto_explicacion;
	JButton boton_cerrar;
	String formula;
	static cubo c1;
	malla mimalla;
	private Thread hilo = null;

	public analizar_cubo(JFrame f, String formula, malla mimalla){//constructor
		super(f,"Modelo Cúbico",true);
		this.formula = formula;
		
		c1 = new cubo(mimalla);		
		campo_texto_formula = new JTextField(20);
		campo_texto_formula.setText(formula);
		campo_texto_formula.setMargin(new Insets(10,10,10,10));
		campo_texto_formula.setEditable(false);
		campo_texto_formula.setBackground(Color.white);
		campo_texto_formula.setForeground(Color.black);
		
		area_texto_explicacion = new JTextArea("Este modelo es una representación cúbica del original.\n Los objetos se distribuyen en el modelo cúbico\n en las mismas casillas que en el modelo original,\n pero tanto las posiciones relativas como\n los valores de verdad de oraciones sobre posiciones\n se alteran.");
		area_texto_explicacion.setEditable(false);
		boton_cerrar = new JButton("Cerrar");
		
		JPanel panel_boton_cerrar = new JPanel(new BorderLayout()); //para que  esté a la derecha anidamos dos paneles
		panel_boton_cerrar.add(BorderLayout.EAST, boton_cerrar);
		
		JPanel panel_abajo = new JPanel(new BorderLayout());
		
		panel_abajo.add(BorderLayout.NORTH,campo_texto_formula);
		
		panel_abajo.add(BorderLayout.SOUTH,panel_boton_cerrar);
		panel_abajo.add(BorderLayout.CENTER,area_texto_explicacion);
		
		JPanel panel_ppal = new JPanel(new GridLayout(2,1));
		panel_ppal.add(c1);
		panel_ppal.add(panel_abajo);
				
		hilo_analizador hilo_x = new hilo_analizador(mimalla,campo_texto_formula,3);  //analisis
		hilo_x.run();
		
	
	
	
		addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e){
					c1.stop();
					setVisible(false);
			}});

				
		boton_cerrar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e4)
		{
					c1.stop();
					setVisible(false);
					
				}
			});
	
		c1.start();
		getContentPane().add(panel_ppal);
		setSize(350,600);
		
		setLocationRelativeTo(null); 
		setResizable(false);
		setVisible(true);
			
	}//constructor

}//class