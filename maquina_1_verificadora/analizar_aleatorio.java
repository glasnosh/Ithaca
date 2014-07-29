/*
 *  "analizar_aleatorio.java"	
 * 	Se crea una nueva malla con elementos aleatorios y se analiza la sentencia en ella.
 *	Esta clase crea una ventana de diálogo donde se muestra la imagen de la malla y el análisis
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
 *      | |    Imagen "aleat. "      | |
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




class analizar_aleatorio extends JDialog{

	
	
	tablero_silent te1;
	JTextField campo_texto_formula;
	JTextArea area_texto_explicacion;
	JButton boton_cerrar;
	String formula;
	malla mimalla;

	public analizar_aleatorio(JFrame f, String formula){//constructor
		super(f,"Modelo Aleatorio",true);
		this.formula = formula;
		
		mimalla = new malla();						// La malla es nueva
										// Tendrá un número aleatorio de elementos y estos
										//serán de forma, tamaño y color aleatorios
										
		te1 = new tablero_silent(mimalla);
		campo_texto_formula = new JTextField(20);
		campo_texto_formula.setText(formula);
		campo_texto_formula.setMargin(new Insets(10,10,10,10));
		campo_texto_formula.setEditable(false);
		campo_texto_formula.setBackground(Color.white);
		campo_texto_formula.setForeground(Color.black);
										
		//Construimos aleatoriamente la malla
		Random aleatorio1 = new Random();
		//Número de elementos
		int nElementos = 1 + aleatorio1.nextInt(15);			// [1,15] intervalo CERRADO
		int al_forma = 0;
		int al_tamanho = 0;
		int al_color = 0;
		int al_res = 0;		//aquí esto no nos sirve para nada. Es para recoger el valor de "insertar_aleatorio"
		int al_pos_x = 0;
		int al_pos_y = 0;
		int al_nombre = 0;
		char al_nombre_char = '?';
		boolean nombre_si_no = false;
		int[] nombres_cogidos = {0,0,0,0,0,0,0,0};
				//      {a,b,c,d,e,f,g,h}
		
		for (int i=1; i<=nElementos; i++){
			//características de los elementos
			al_forma = 1 + aleatorio1.nextInt(3);
			al_tamanho = 1 + aleatorio1.nextInt(2);		//Forma, Tamaño y Color aleatorios (¿Cuanto?, solo 
			al_color = 1 + aleatorio1.nextInt(3);		// Java (tm) lo sabe, of course
	
		//Mucho trabajar y poco jugar, hacen de Bart un niño aburrido.
		//Mucho trabajar y poco jugar, hacen de Bart un niño aburrido.
		//Mucho trabajar y poco jugar, hacen de Bart un niño aburrido.
		//Mucho trabajar y poco jugar, hacen de Bart un niño aburrido.
		//Mucho programar y poco reir, hacen de Pep un niño abatido.
		//En el mismo día que descubrí que había sido despojado de aquel rincón... de aquella isla en medio del océano
		// conocida como el Valle Cuadrado, descubrí también que ni siquiera hay llave que encierre para siempre esa 
		// posibilidad de hacer algo por el simple hecho de no arrepentirse de no haberlo hecho. O simplemente, y esto
		// es lo más curioso, por a**r. (Amor: def. Enajenación mental transitoria. def. Estado de atontamiento similar
		// a la infancia pero que suele aparecer a partir de la adolescencia).
		//Glasnoshito niño... vamos a currar ya.
			
			//Nombre. La probabilidad de que un objeto tenga un nombre va a ser 1/4
			if ( (1 + aleatorio1.nextInt(4)) < 4 ) nombre_si_no = false;
			else nombre_si_no = true;
			
			if (nombre_si_no){	//buscarle un nombre que no esté cogido
				al_nombre = aleatorio1.nextInt(8);
				if ((nombres_cogidos[al_nombre]) != 0) nombre_si_no = false;
				else{
					nombres_cogidos[al_nombre] = 1;
					if (al_nombre == 0) al_nombre_char = 'a';
					if (al_nombre == 1) al_nombre_char = 'b';
					if (al_nombre == 2) al_nombre_char = 'c';
					if (al_nombre == 3) al_nombre_char = 'd';
					if (al_nombre == 4) al_nombre_char = 'e';
					if (al_nombre == 5) al_nombre_char = 'f';
					if (al_nombre == 6) al_nombre_char = 'g';
					if (al_nombre == 7) al_nombre_char = 'h';
				}
			}//if
		
		
			//posición aleatoria donde insertarlo
			al_pos_x = aleatorio1.nextInt(6);
			al_pos_y = aleatorio1.nextInt(9);
			if (nombre_si_no == false) mimalla.insertar(al_pos_x, al_pos_y,al_forma, al_tamanho, al_color);
			else mimalla.insertar(al_pos_x, al_pos_y,al_nombre_char,al_forma, al_tamanho, al_color);
		}//for
		//____________________________________
		
		
		
		area_texto_explicacion = new JTextArea(" \nEste mundo ha sido generado aleatoriamente\npor la máquina");
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
		
		hilo_analizador hilo_x = new hilo_analizador(mimalla,campo_texto_formula,1);  //analisis
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