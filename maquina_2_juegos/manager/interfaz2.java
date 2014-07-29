/*
 *	interfaz2.java
 *		Basada en la interfaz principal de ITHACA. Sirve para introducir las fórmulas en el manager. Botones,
 *		 botones y más botones... Para detalles ver "ithaca.java" en ITHACA
 * 
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */
 
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.PathIterator;
import java.awt.geom.FlatteningPathIterator;
import java.awt.font.TextLayout;
import java.awt.font.FontRenderContext;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.AWTKeyStroke;
import javax.swing.border.*;
import java.awt.GridBagLayout;

import javax.imageio.*;
import javax.imageio.stream.*;
import java.awt.image.*;
import java.net.URL;
import javax.swing.JFileChooser;

import java.io.*;
import java.util.*;

class interfaz2 extends JDialog{

	malla mimalla;						//malla brutal...
	tablero _tablero;					//Dibujo 
	
	int offset_caracteres;					//Para recolocar el cursor 
	
	static JToolBar barra_herramientas;
		
	JComboBox combo_colores;
	JComboBox combo_tamaños;
	
	JButton boton_insertar_piramide;
	JButton boton_insertar_cubo;
	JButton boton_insertar_esfera;
	
	malla malla_vuelta = null;
	String formula_vuelta = null;
	
	boolean _ok = false;
	

	
	public static ImageIcon icono_cortar = new ImageIcon("png/cut.png");
	public static ImageIcon icono_copiar = new ImageIcon("png/copy.png");
	public static ImageIcon icono_pegar = new ImageIcon("png/paste.png");

	
	public static ImageIcon icono_esfera_amarilla_grande = new ImageIcon("png/esfera_amarilla_grande.png");
	public static ImageIcon icono_esfera_amarilla_pequenha = new ImageIcon("png/esfera_amarilla_pequenha.png");
	public static ImageIcon icono_esfera_roja_grande = new ImageIcon("png/esfera_roja_grande.png");
	public static ImageIcon icono_esfera_roja_pequenha = new ImageIcon("png/esfera_roja_pequenha.png");
	public static ImageIcon icono_esfera_azul_grande = new ImageIcon("png/esfera_azul_grande.png");
	public static ImageIcon icono_esfera_azul_pequenha = new ImageIcon("png/esfera_azul_pequenha.png");
	
	public static ImageIcon icono_piramide_amarilla_grande = new ImageIcon("png/piramide_amarilla_grande.png");
	public static ImageIcon icono_piramide_amarilla_pequenha = new ImageIcon("png/piramide_amarilla_pequenha.png");
	public static ImageIcon icono_piramide_roja_grande = new ImageIcon("png/piramide_roja_grande.png");
	public static ImageIcon icono_piramide_roja_pequenha = new ImageIcon("png/piramide_roja_pequenha.png");
	public static ImageIcon icono_piramide_azul_grande = new ImageIcon("png/piramide_azul_grande.png");
	public static ImageIcon icono_piramide_azul_pequenha = new ImageIcon("png/piramide_azul_pequenha.png");
	
	public static ImageIcon icono_cubo_amarillo_grande = new ImageIcon("png/cubo_amarillo_grande.png");
	public static ImageIcon icono_cubo_amarillo_pequenho = new ImageIcon("png/cubo_amarillo_pequenho.png");
	public static ImageIcon icono_cubo_rojo_grande = new ImageIcon("png/cubo_rojo_grande.png");
	public static ImageIcon icono_cubo_rojo_pequenho = new ImageIcon("png/cubo_rojo_pequenho.png");
	public static ImageIcon icono_cubo_azul_grande = new ImageIcon("png/cubo_azul_grande.png");
	public static ImageIcon icono_cubo_azul_pequenho = new ImageIcon("png/cubo_azul_pequenho.png");
	
	public static ImageIcon icono_ppal =new ImageIcon("png/icono.png");
	
 	
	JPanel panel_ppal;
	JPanel panel_superior;
	JPanel panel_teclado;
	JPanel panel_central;

	JTextField jtf_formula;
	JButton boton_insertar;
	
	
	
	
	public interfaz2(){//CONSTRUCTOR
		setModal(true);
		offset_caracteres = 0;
		
		mimalla = new malla();
		_tablero = new tablero(mimalla);
		
		try{
			UIManager.put("swing.boldMetal", Boolean.FALSE);
		}catch (Exception e){System.out.println("interfaz.java: Exception UI");}
		
		
		
		panel_ppal = new JPanel(new BorderLayout());
		panel_superior = new JPanel(new BorderLayout());
		panel_teclado = new JPanel(new GridLayout(4,1));
		
		
		barra_herramientas = new JToolBar("BarraHerramientas1",javax.swing.SwingConstants.HORIZONTAL);
		barra_herramientas.setFloatable(false);
		barra_herramientas.setBorderPainted(false);
		
		
		JButton boton_herramientas_cortar = new JButton(icono_cortar);
		JButton boton_herramientas_copiar = new JButton(icono_copiar);
		JButton boton_herramientas_pegar = new JButton(icono_pegar);
	
		barra_herramientas.addSeparator();
		barra_herramientas.add(boton_herramientas_cortar);
		barra_herramientas.add(boton_herramientas_copiar);
		barra_herramientas.add(boton_herramientas_pegar);
		barra_herramientas.addSeparator();
		
		boton_insertar_piramide = new JButton(icono_piramide_roja_grande);
		boton_insertar_cubo = new JButton(icono_cubo_rojo_grande);
		boton_insertar_esfera = new JButton(icono_esfera_roja_grande);
		boton_insertar_piramide.setMargin(new Insets(0,0,0,0));
		boton_insertar_cubo.setMargin(new Insets(0,0,0,0));
		boton_insertar_esfera.setMargin(new Insets(0,0,0,0));
		
		combo_colores = new JComboBox();
		combo_tamaños = new JComboBox();
				
		combo_colores.setEditable(false);
		combo_tamaños.setEditable(false);
		
		
		//Inserción de elementos
		boton_insertar_piramide = new JButton(icono_piramide_roja_grande);
		boton_insertar_cubo = new JButton(icono_cubo_rojo_grande);
		boton_insertar_esfera = new JButton(icono_esfera_roja_grande);
		boton_insertar_piramide.setMargin(new Insets(0,0,0,0));
		boton_insertar_cubo.setMargin(new Insets(0,0,0,0));
		boton_insertar_esfera.setMargin(new Insets(0,0,0,0));
		
		combo_colores = new JComboBox();
		combo_tamaños = new JComboBox();
				
		combo_colores.setEditable(false);
		combo_tamaños.setEditable(false);
		
		combo_colores.addItem("Rojo");		//item 0
		combo_colores.addItem("Azul");		//item 1
		combo_colores.addItem("Amarillo");	//item 2
		
		combo_tamaños.addItem("Grande");	//item 0
		combo_tamaños.addItem("Pequeño");	//item 1
		
		barra_herramientas.addSeparator();
		barra_herramientas.add(combo_colores);
		barra_herramientas.add(combo_tamaños);
		barra_herramientas.add(boton_insertar_piramide);
		barra_herramientas.add(boton_insertar_cubo);
		barra_herramientas.add(boton_insertar_esfera);
		barra_herramientas.addSeparator();
		
		combo_colores.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent qq2){
				int color = combo_colores.getSelectedIndex();
				int tamaño = combo_tamaños.getSelectedIndex();
				
				if ( (color == 0) && (tamaño == 0) ){	//rojo grande
					 boton_insertar_piramide.setIcon(icono_piramide_roja_grande);
					 boton_insertar_esfera.setIcon(icono_esfera_roja_grande);
					 boton_insertar_cubo.setIcon(icono_cubo_rojo_grande);
				}
				else if ( (color == 0) && (tamaño == 1) ){//rojo pequeño
					boton_insertar_piramide.setIcon(icono_piramide_roja_pequenha);
					boton_insertar_esfera.setIcon(icono_esfera_roja_pequenha);
					boton_insertar_cubo.setIcon(icono_cubo_rojo_pequenho);
				}
				else if ( (color == 1) && (tamaño == 0) ){//azul grande
					boton_insertar_piramide.setIcon(icono_piramide_azul_grande);
					boton_insertar_esfera.setIcon(icono_esfera_azul_grande);
					boton_insertar_cubo.setIcon(icono_cubo_azul_grande);
				}
				else if ( (color == 1) && (tamaño == 1) ){//azul pequeño
					boton_insertar_piramide.setIcon(icono_piramide_azul_pequenha);
					boton_insertar_esfera.setIcon(icono_esfera_azul_pequenha);
					boton_insertar_cubo.setIcon(icono_cubo_azul_pequenho);
				}
				else if ( (color == 2) && (tamaño == 0) ){//amarillo grande
					boton_insertar_piramide.setIcon(icono_piramide_amarilla_grande);
					boton_insertar_esfera.setIcon(icono_esfera_amarilla_grande);
					boton_insertar_cubo.setIcon(icono_cubo_amarillo_grande);
				}
				else if ( (color == 2) && (tamaño == 1) ){//amarillo pequeño
					boton_insertar_piramide.setIcon(icono_piramide_amarilla_pequenha);
					boton_insertar_esfera.setIcon(icono_esfera_amarilla_pequenha);
					boton_insertar_cubo.setIcon(icono_cubo_amarillo_pequenho);
				}
		}});
		
		combo_tamaños.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent qq2){
				int color = combo_colores.getSelectedIndex();
				int tamaño = combo_tamaños.getSelectedIndex();
				
				if ( (color == 0) && (tamaño == 0) ){	//rojo grande
					 boton_insertar_piramide.setIcon(icono_piramide_roja_grande);
					 boton_insertar_esfera.setIcon(icono_esfera_roja_grande);
					 boton_insertar_cubo.setIcon(icono_cubo_rojo_grande);
				}
				else if ( (color == 0) && (tamaño == 1) ){//rojo pequeño
					boton_insertar_piramide.setIcon(icono_piramide_roja_pequenha);
					boton_insertar_esfera.setIcon(icono_esfera_roja_pequenha);
					boton_insertar_cubo.setIcon(icono_cubo_rojo_pequenho);
				}
				else if ( (color == 1) && (tamaño == 0) ){//azul grande
					boton_insertar_piramide.setIcon(icono_piramide_azul_grande);
					boton_insertar_esfera.setIcon(icono_esfera_azul_grande);
					boton_insertar_cubo.setIcon(icono_cubo_azul_grande);
				}
				else if ( (color == 1) && (tamaño == 1) ){//azul pequeño
					boton_insertar_piramide.setIcon(icono_piramide_azul_pequenha);
					boton_insertar_esfera.setIcon(icono_esfera_azul_pequenha);
					boton_insertar_cubo.setIcon(icono_cubo_azul_pequenho);
				}
				else if ( (color == 2) && (tamaño == 0) ){//amarillo grande
					boton_insertar_piramide.setIcon(icono_piramide_amarilla_grande);
					boton_insertar_esfera.setIcon(icono_esfera_amarilla_grande);
					boton_insertar_cubo.setIcon(icono_cubo_amarillo_grande);
				}
				else if ( (color == 2) && (tamaño == 1) ){//amarillo pequeño
					boton_insertar_piramide.setIcon(icono_piramide_amarilla_pequenha);
					boton_insertar_esfera.setIcon(icono_esfera_amarilla_pequenha);
					boton_insertar_cubo.setIcon(icono_cubo_amarillo_pequenho);
				}
		}});
		
		
		//funciones monop
		JPanel panel_teclado_monop = new JPanel(new GridLayout(4,2,5,5));	//4 filas, 2 columnas, 5 pixels de separación
		JButton boton_monop_grande = new JButton("grande");
		JButton boton_monop_pequeño = new JButton("pequeño");
		JButton boton_monop_amarillo = new JButton("amarillo");
		JButton boton_monop_rojo = new JButton("rojo");
		JButton boton_monop_azul = new JButton("azul");
		JButton boton_monop_esfera = new JButton("esfera");
		JButton boton_monop_cubo = new JButton("cubo");
		JButton boton_monop_piramide = new JButton("piramide");
		
			//eliminar espacios sobrantes en el boton
		boton_monop_grande.setMargin(new Insets(0,0,0,0));
		boton_monop_pequeño.setMargin(new Insets(0,0,0,0));
		boton_monop_amarillo.setMargin(new Insets(0,0,0,0));
		boton_monop_rojo.setMargin(new Insets(0,0,0,0));
		boton_monop_azul.setMargin(new Insets(0,0,0,0));
		boton_monop_esfera.setMargin(new Insets(0,0,0,0));
		boton_monop_cubo.setMargin(new Insets(0,0,0,0));
		boton_monop_piramide.setMargin(new Insets(0,0,0,0));
		
		panel_teclado_monop.add(boton_monop_grande);
		panel_teclado_monop.add(boton_monop_pequeño);
		panel_teclado_monop.add(boton_monop_amarillo);
		panel_teclado_monop.add(boton_monop_esfera);
		panel_teclado_monop.add(boton_monop_rojo);
		panel_teclado_monop.add(boton_monop_cubo);
		panel_teclado_monop.add(boton_monop_azul);
		panel_teclado_monop.add(boton_monop_piramide);
			//borde de agrupacion
		panel_teclado_monop.setBorder( new TitledBorder("f(1)"));

		//funciones bip
		JPanel panel_teclado_bip = new JPanel(new GridLayout(6,2,5,5));	//4 filas, 2 columnas, 5 pixels de separación
		
		JButton boton_bip_masGrande = new JButton("masGrande");
		JButton boton_bip_masPequeño = new JButton("masPequeño");
		JButton boton_bip_izquierda = new JButton ("izquierda");
		JButton boton_bip_derecha = new JButton ("derecha");
		JButton boton_bip_detras = new JButton ("detras");
		JButton boton_bip_delante = new JButton ("delante");
		JButton boton_bip_alrededor = new JButton ("alrededor");
		JButton boton_bip_mismoColor = new JButton("mismoColor");
		JButton boton_bip_mismaForma = new JButton("mismaForma");
		JButton boton_bip_mismoTamanho = new JButton("mismoTamaño");
		JButton boton_bip_mismaColumna = new JButton("mismaColumna");
		JButton boton_bip_mismaFila = new JButton("mismaFila");
				
			//eliminar espacios sobrantes en el boton
		boton_bip_masGrande.setMargin(new Insets(0,0,0,0));
		boton_bip_masPequeño.setMargin(new Insets(0,0,0,0));
		boton_bip_izquierda.setMargin(new Insets(0,0,0,0));
		boton_bip_derecha.setMargin(new Insets(0,0,0,0));
		boton_bip_detras.setMargin(new Insets(0,0,0,0));
		boton_bip_delante.setMargin(new Insets(0,0,0,0));
		boton_bip_alrededor.setMargin(new Insets(0,0,0,0));
		boton_bip_mismoColor.setMargin(new Insets(0,0,0,0));
		boton_bip_mismaForma.setMargin(new Insets(0,0,0,0));
		boton_bip_mismoTamanho.setMargin(new Insets(0,0,0,0));
		boton_bip_mismaColumna.setMargin(new Insets(0,0,0,0));
		boton_bip_mismaFila.setMargin(new Insets(0,0,0,0));
		
		panel_teclado_bip.add(boton_bip_masGrande);
		panel_teclado_bip.add(boton_bip_masPequeño);
		panel_teclado_bip.add(boton_bip_izquierda);
		panel_teclado_bip.add(boton_bip_derecha);
		panel_teclado_bip.add(boton_bip_detras);
		panel_teclado_bip.add(boton_bip_delante);
		panel_teclado_bip.add(boton_bip_alrededor);
		panel_teclado_bip.add(boton_bip_mismoColor);
		panel_teclado_bip.add(boton_bip_mismaForma);
		panel_teclado_bip.add(boton_bip_mismoTamanho);
		panel_teclado_bip.add(boton_bip_mismaColumna);
		panel_teclado_bip.add(boton_bip_mismaFila);
	
			//borde de agrupacion
		panel_teclado_bip.setBorder( new TitledBorder("f(1,2)"));

		//funciones trip
		JPanel panel_teclado_trip = new JPanel(new GridLayout(1,1,5,5));
		JButton boton_trip_entre = new JButton("entre");
		boton_trip_entre.setSize(140,140);
		boton_trip_entre.setMargin(new Insets(0,30,0,30));
		panel_teclado_trip.add(boton_trip_entre);
			//borde de agrupacion
		panel_teclado_trip.setBorder( new TitledBorder("f(1,2,3)"));

		
		//operaciones, variables y constantes....
		JPanel panel_teclado_operaciones = new JPanel(new GridLayout(4,8,5,5));
		
		Character _IMPLICA = 0x002192;		//símbolo unicode de -->
		Character _BIMPLICA = 0x2194;		//símbolo unicode de <->
		Character _EXISTEUN = 0x2203;		//símbolo unicode de E
		Character _PARATODO = 0x2200;		//símbolo unicode de CU
		Character _Y = 0x2227;			//símbolo unicode de ^
		Character _O = 0x2228;			//símbolo unicode de v 
		Character _T = 0x22A4;			//símbolo unicode de T
		Character _nT = 0x22A5;			//símbolo unicode de nT
		
		final String IMPLICA = ""+_IMPLICA;	//Los transformamos en String para que no de problemas de concatenar y buscar y eso
		final String BIMPLICA = ""+_BIMPLICA;
		final String EXISTEUN = ""+_EXISTEUN;
		final String PARATODO = ""+_PARATODO;
		final String Y = ""+_Y;
		final String O = ""+_O;
		final String T = ""+_T;
		final String nT = ""+_nT;
		
		JButton boton_op_a = new JButton("a");
		JButton boton_op_b = new JButton("b");
		JButton boton_op_c = new JButton("c");
		JButton boton_op_d = new JButton("d");
		JButton boton_op_e = new JButton("e");
		JButton boton_op_x = new JButton("x");
		JButton boton_op_y = new JButton("y");
		JButton boton_op_z = new JButton("z");
		JButton boton_op_t = new JButton("t");
		JButton boton_op_implica = 	new JButton(IMPLICA);
		JButton boton_op_bimplica = 	new JButton(BIMPLICA);
		JButton boton_op_existe = 	new JButton(EXISTEUN);
		JButton boton_op_paratodo = 	new JButton(PARATODO);
		JButton boton_op__y = 		new JButton(Y);
		JButton boton_op_o = 		new JButton(O);
		JButton boton_op_T = 		new JButton(T);
		JButton boton_op_NT = 		new JButton(nT);
		JButton boton_op_no = 		new JButton("¬");
		JButton boton_op_igual = 	new JButton("=");
		JButton boton_op_p_i = 		new JButton("(");
		JButton boton_op_p_d = 		new JButton(")");
		
		JButton boton_op_pit = 		new JButton("(...");
		JButton boton_op_pdt = 		new JButton("...)");
		JButton boton_op_pidt = 	new JButton("(. .)");
		JButton boton_op_del =		new JButton("DEL");
		JButton boton_op_izq = 		new JButton("<-");
		JButton boton_op_der = 		new JButton("->");
		JButton boton_op_izqt = 	new JButton("|<-");
		JButton boton_op_dert = 	new JButton("->|");
		
			//eliminar los border de los botones
		boton_op_a.setMargin(new Insets(0,0,0,0));
		boton_op_b.setMargin(new Insets(0,0,0,0));
		boton_op_c.setMargin(new Insets(0,0,0,0));
		boton_op_d.setMargin(new Insets(0,0,0,0));
		boton_op_e.setMargin(new Insets(0,0,0,0));
		boton_op_x.setMargin(new Insets(0,0,0,0));
		boton_op_y.setMargin(new Insets(0,0,0,0));
		boton_op_z.setMargin(new Insets(0,0,0,0));
		boton_op_t.setMargin(new Insets(0,0,0,0));
		boton_op_implica.setMargin(new Insets(0,0,0,0));
		boton_op_bimplica.setMargin(new Insets(0,0,0,0));
		boton_op_existe.setMargin(new Insets(0,0,0,0));
		boton_op_paratodo.setMargin(new Insets(0,0,0,0));
		boton_op__y.setMargin(new Insets(0,0,0,0));
		boton_op_o.setMargin(new Insets(0,0,0,0));
		boton_op_no.setMargin(new Insets(0,0,0,0));
		boton_op_igual.setMargin(new Insets(0,0,0,0));
		boton_op_p_i.setMargin(new Insets(0,0,0,0));
		boton_op_p_d.setMargin(new Insets(0,0,0,0));
		boton_op_pit.setMargin(new Insets(0,0,0,0));
		boton_op_pdt.setMargin(new Insets(0,0,0,0));
		boton_op_pidt.setMargin(new Insets(0,0,0,0));
		boton_op_del.setMargin(new Insets(0,0,0,0));
		boton_op_izq.setMargin(new Insets(0,0,0,0));
		boton_op_der.setMargin(new Insets(0,0,0,0));
		boton_op_izqt.setMargin(new Insets(0,0,0,0));
		boton_op_dert.setMargin(new Insets(0,0,0,0));
		boton_op_T.setMargin(new Insets(0,0,0,0));
		boton_op_NT.setMargin(new Insets(0,0,0,0));
		
		panel_teclado_operaciones.add(boton_op_pidt);
		panel_teclado_operaciones.add(boton_op_pit);
		panel_teclado_operaciones.add(boton_op_pdt);
		panel_teclado_operaciones.add(boton_op_izq);
		panel_teclado_operaciones.add(boton_op_der);
		panel_teclado_operaciones.add(boton_op_izqt);
		panel_teclado_operaciones.add(boton_op_dert);
		panel_teclado_operaciones.add(boton_op_del);
		
		panel_teclado_operaciones.add(boton_op__y);
		panel_teclado_operaciones.add(boton_op_o);
		panel_teclado_operaciones.add(boton_op_implica);
		panel_teclado_operaciones.add(boton_op_bimplica);
		panel_teclado_operaciones.add(boton_op_no);
		panel_teclado_operaciones.add(boton_op_igual);
		panel_teclado_operaciones.add(boton_op_p_i);
		panel_teclado_operaciones.add(boton_op_p_d);
		
		panel_teclado_operaciones.add(boton_op_paratodo);
		panel_teclado_operaciones.add(boton_op_existe);
		panel_teclado_operaciones.add(boton_op_x);
		panel_teclado_operaciones.add(boton_op_y);
		panel_teclado_operaciones.add(boton_op_z);
		panel_teclado_operaciones.add(boton_op_t);
		panel_teclado_operaciones.add(boton_op_T);
		panel_teclado_operaciones.add(boton_op_NT);
	
		panel_teclado_operaciones.add(boton_op_a);
		panel_teclado_operaciones.add(boton_op_b);
		panel_teclado_operaciones.add(boton_op_c);
		panel_teclado_operaciones.add(boton_op_d);
		panel_teclado_operaciones.add(boton_op_e);
		panel_teclado_operaciones.add(new JPanel());
		panel_teclado_operaciones.add(new JPanel());
		panel_teclado_operaciones.add(new JPanel());
			//borde de agrupacion	
		panel_teclado_operaciones.setBorder( new TitledBorder("Operadores"));

		
		JPanel pt1 = new JPanel(new BorderLayout());
		JPanel pt2 = new JPanel(new BorderLayout());
		JPanel pt3 = new JPanel(new BorderLayout());
		JPanel pt4 = new JPanel(new BorderLayout());
		pt1.add(BorderLayout.NORTH, panel_teclado_monop);
		pt2.add(BorderLayout.CENTER, panel_teclado_bip);
		pt3.add(BorderLayout.NORTH, panel_teclado_trip);
		pt4.add(BorderLayout.NORTH, panel_teclado_operaciones);
		panel_teclado.add(pt1);
		panel_teclado.add(pt2);
		panel_teclado.add(pt3);
		panel_teclado.add(pt4);
		//FIN TECLADO
		panel_ppal.add(BorderLayout.WEST, panel_teclado);
		
		panel_central = new JPanel(new BorderLayout());
		
		jtf_formula = new JTextField(20);
		jtf_formula.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		jtf_formula.setHorizontalAlignment(SwingConstants.CENTER);
		jtf_formula.setCaretColor(Color.red);
		
		boton_insertar = new JButton(" I N S E R T A R");
		boton_insertar.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		
		JPanel panel_sur = new JPanel(new GridLayout(5,1));
		panel_sur.add(new JPanel());
		panel_sur.add(jtf_formula);
		panel_sur.add(new JPanel());
		panel_sur.add(boton_insertar);
		panel_sur.add(new JPanel());
		
		panel_central.add(BorderLayout.SOUTH, panel_sur);
		panel_central.add(BorderLayout.CENTER, _tablero);
		panel_central.add(BorderLayout.NORTH, new JPanel());
		panel_central.add(BorderLayout.EAST, new JPanel());
		panel_central.add(BorderLayout.WEST, new JPanel());
		
		
		panel_ppal.add(BorderLayout.CENTER, panel_central);
		
		
		
		boton_insertar_esfera.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent bip1){
				int color = combo_colores.getSelectedIndex();
				int tamaño = combo_tamaños.getSelectedIndex();
				//if (_tablero.insertar_aleatorio(1,color+1,tamaño+1) == 1) new warning_max_elementos(interfaz.this);
				//mimalla = _tablero.devolver_malla();
		}});
		
		
		boton_insertar_cubo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent bip1){
				int color = combo_colores.getSelectedIndex();
				int tamaño = combo_tamaños.getSelectedIndex();
				//if (_tablero.insertar_aleatorio(2,color+1,tamaño+1) == 1) new warning_max_elementos(interfaz.this);
				//mimalla = _tablero.devolver_malla();
		}});
		
		
		boton_insertar_piramide.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent bip1){
				int color = combo_colores.getSelectedIndex();
				int tamaño = combo_tamaños.getSelectedIndex();
				//if (_tablero.insertar_aleatorio(3,color+1,tamaño+1) == 1) new warning_max_elementos(interfaz.this);
				//mimalla = _tablero.devolver_malla();
		}});
		
		//funciones bip
		boton_bip_masGrande.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_masgrande){
			new escuchador_botones_funciones(  jtf_formula  ,  "masGrande(,)");
			offset_caracteres = 2;
		}});	
		
		boton_bip_masPequeño.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_maspequenho){
			new escuchador_botones_funciones(  jtf_formula  ,  "masPequeño(,)");
			offset_caracteres = 2;
		}});
		
		boton_bip_izquierda.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_izquierda){
			new escuchador_botones_funciones(  jtf_formula  ,  "izquierda(,)");
			offset_caracteres = 2;
		}});
		
		boton_bip_derecha.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_derecha){
			new escuchador_botones_funciones(  jtf_formula  ,  "derecha(,)");
			offset_caracteres = 2;
		}});
		
		boton_bip_detras.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_detras){
			new escuchador_botones_funciones(  jtf_formula  ,  "detras(,)");
			offset_caracteres = 2;
		}});
		
		boton_bip_delante.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_delante){
			new escuchador_botones_funciones(  jtf_formula  ,  "delante(,)");
			offset_caracteres = 2;
		}});
		
		boton_bip_alrededor.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_alrededor){
			new escuchador_botones_funciones(  jtf_formula  ,  "alrededor(,)");
			offset_caracteres = 2;
		}});
		
		boton_bip_mismoColor.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_mismoColor){
			new escuchador_botones_funciones(  jtf_formula  ,  "mismoColor(,)");
			offset_caracteres = 2;
		}});
		
		boton_bip_mismaForma.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_mismaForma){
			new escuchador_botones_funciones(  jtf_formula  ,  "mismaForma(,)");
			offset_caracteres = 2;
		}});
		
		boton_bip_mismoTamanho.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_mismoTamanho){
			new escuchador_botones_funciones(  jtf_formula  ,  "mismoTamaño(,)");
			offset_caracteres = 2;
		}});
		
		boton_bip_mismaFila.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_mismaFila){
			new escuchador_botones_funciones(  jtf_formula  ,  "mismaFila(,)");
			offset_caracteres = 2;
		}});
		
		boton_bip_mismaColumna.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_mismaColumna){
			new escuchador_botones_funciones(  jtf_formula  ,  "mismaColumna(,)");
			offset_caracteres = 2;
		}});
		
	//funciones trip
		boton_trip_entre.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_entre){
			new escuchador_botones_funciones(  jtf_formula  ,  "entre(,,)");
			offset_caracteres = 3;
		}});
		
	//funciones monop
		boton_monop_grande.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_grande){
			new escuchador_botones_funciones(   jtf_formula  ,  "grande()");
			offset_caracteres = 1;
		}});
		
		boton_monop_pequeño.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_pequenho){
			new escuchador_botones_funciones(   jtf_formula  ,  "pequeño()");
			offset_caracteres = 1;
		}});
		
		boton_monop_amarillo.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_amarillo){
			new escuchador_botones_funciones(   jtf_formula  ,  "amarillo()");
			offset_caracteres = 1;
		}});
		
		boton_monop_rojo.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_rojo){
			new escuchador_botones_funciones(   jtf_formula  ,  "rojo()");
			offset_caracteres = 1;
		}});
		
		boton_monop_azul.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_azul){
			new escuchador_botones_funciones(   jtf_formula  ,  "azul()");
			offset_caracteres = 1;
		}});
		
		boton_monop_esfera.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_esfera){
			new escuchador_botones_funciones(   jtf_formula  ,  "esfera()");
			offset_caracteres = 1;
		}});
		
		boton_monop_cubo.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_cubo){
			new escuchador_botones_funciones(   jtf_formula  ,  "cubo()");
			offset_caracteres = 1;
		}});
		
		boton_monop_piramide.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_piramide){
			new escuchador_botones_funciones(   jtf_formula  ,  "piramide()");
			offset_caracteres = 1;
		}});
		
		
		boton_op_NT.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_nT){
			
			String subcadena = nT;
			int posicion_cursor = jtf_formula.getCaretPosition();
			String contenido = jtf_formula.getText();
			String cadena_final = contenido.substring(0,posicion_cursor)+subcadena+contenido.substring(posicion_cursor,contenido.length());
			jtf_formula.setText(cadena_final);
			jtf_formula.requestFocus();
			//jtf_formula.setCaretPosition( posicion_cursor + (subcadena.indexOf (')') +1) );	
			//jtf_formula.setCaretPosition( posicion_cursor + 2 );	
		
			offset_caracteres = 0;
		}});
		
		boton_op_T.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_T){
			String subcadena = T;
			int posicion_cursor = jtf_formula.getCaretPosition();
			String contenido = jtf_formula.getText();
			String cadena_final = contenido.substring(0,posicion_cursor)+subcadena+contenido.substring(posicion_cursor,contenido.length());
			jtf_formula.setText(cadena_final);
			jtf_formula.requestFocus();
			//jtf_formula.setCaretPosition( posicion_cursor + (subcadena.indexOf (')') +1) );
			//jtf_formula.setCaretPosition( posicion_cursor + 2 );	
		
			offset_caracteres = 0;
		}});
		
	//parentesis izquierdo y derecho
	
		boton_op_p_i.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_p_i){
			
			String subcadena = "(";
			int posicion_cursor = jtf_formula.getCaretPosition();
			String contenido = jtf_formula.getText();
			String cadena_final = contenido.substring(0,posicion_cursor)+subcadena+contenido.substring(posicion_cursor,contenido.length());
			jtf_formula.setText(cadena_final);
			jtf_formula.requestFocus();
			jtf_formula.setCaretPosition( posicion_cursor  +1);	
		
			offset_caracteres = 0;
		}});
	
	
	
	
		boton_op_p_d.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_p_d){
			
			String subcadena = ")";
			int posicion_cursor = jtf_formula.getCaretPosition();
			String contenido = jtf_formula.getText();
			String cadena_final = contenido.substring(0,posicion_cursor)+subcadena+contenido.substring(posicion_cursor,contenido.length());
			jtf_formula.setText(cadena_final);
			jtf_formula.requestFocus();
			jtf_formula.setCaretPosition( posicion_cursor  +1);	
		
			offset_caracteres = 0;
		}});
	
	//y,o,no,=,-->, <->
		
		boton_op__y.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_p__y){
			
			String subcadena = Y;
			int posicion_cursor = jtf_formula.getCaretPosition();
			String contenido = jtf_formula.getText();
			String cadena_final = contenido.substring(0,posicion_cursor)+subcadena+contenido.substring(posicion_cursor,contenido.length());
			jtf_formula.setText(cadena_final);
			jtf_formula.requestFocus();
			jtf_formula.setCaretPosition( posicion_cursor  +1);	
		
			offset_caracteres = 0;
		}});
		
		
		boton_op_o.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_p_o){
			
			String subcadena = O;
			int posicion_cursor = jtf_formula.getCaretPosition();
			String contenido = jtf_formula.getText();
			String cadena_final = contenido.substring(0,posicion_cursor)+subcadena+contenido.substring(posicion_cursor,contenido.length());
			jtf_formula.setText(cadena_final);
			jtf_formula.requestFocus();
			jtf_formula.setCaretPosition( posicion_cursor  +1);	
		
			offset_caracteres = 0;
		}});
		
		boton_op_no.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_p_no){
			
			String subcadena = "¬";
			int posicion_cursor = jtf_formula.getCaretPosition();
			String contenido = jtf_formula.getText();
			String cadena_final = contenido.substring(0,posicion_cursor)+subcadena+contenido.substring(posicion_cursor,contenido.length());
			jtf_formula.setText(cadena_final);
			jtf_formula.requestFocus();
			jtf_formula.setCaretPosition( posicion_cursor  +1);	
		
			offset_caracteres = 0;
		}});
		
		boton_op_igual.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_p_igual){
			
			String subcadena = "=";
			int posicion_cursor = jtf_formula.getCaretPosition();
			String contenido = jtf_formula.getText();
			String cadena_final = contenido.substring(0,posicion_cursor)+subcadena+contenido.substring(posicion_cursor,contenido.length());
			jtf_formula.setText(cadena_final);
			jtf_formula.requestFocus();
			jtf_formula.setCaretPosition( posicion_cursor  +1);	
		
			offset_caracteres = 0;
		}});
		
		boton_op_paratodo.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_p_paratodo){
			
			String subcadena = PARATODO;
			int posicion_cursor = jtf_formula.getCaretPosition();
			String contenido = jtf_formula.getText();
			String cadena_final = contenido.substring(0,posicion_cursor)+subcadena+contenido.substring(posicion_cursor,contenido.length());
			jtf_formula.setText(cadena_final);
			jtf_formula.requestFocus();
			jtf_formula.setCaretPosition( posicion_cursor  +1);	
		
			offset_caracteres = 0;
		}});
		
		boton_op_existe.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_p_existe){
		
			String subcadena = EXISTEUN;
			int posicion_cursor = jtf_formula.getCaretPosition();
			String contenido = jtf_formula.getText();
			String cadena_final = contenido.substring(0,posicion_cursor)+subcadena+contenido.substring(posicion_cursor,contenido.length());
			jtf_formula.setText(cadena_final);
			jtf_formula.requestFocus();
			jtf_formula.setCaretPosition( posicion_cursor  +1);	
		
			offset_caracteres = 0;
		}});
		
		boton_op_implica.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_p_implica){
			
			String subcadena = IMPLICA;
			int posicion_cursor = jtf_formula.getCaretPosition();
			String contenido = jtf_formula.getText();
			String cadena_final = contenido.substring(0,posicion_cursor)+subcadena+contenido.substring(posicion_cursor,contenido.length());
			jtf_formula.setText(cadena_final);
			jtf_formula.requestFocus();
			jtf_formula.setCaretPosition( posicion_cursor  +1);	
		
			offset_caracteres = 0;
		}});
		
		boton_op_bimplica.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_p_bimplica){
			
			String subcadena = BIMPLICA;
			int posicion_cursor = jtf_formula.getCaretPosition();
			String contenido = jtf_formula.getText();
			String cadena_final = contenido.substring(0,posicion_cursor)+subcadena+contenido.substring(posicion_cursor,contenido.length());
			jtf_formula.setText(cadena_final);
			jtf_formula.requestFocus();
			jtf_formula.setCaretPosition( posicion_cursor  +1);	
		
			offset_caracteres = 0;
		}});
		
		//letras 
		boton_op_a.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_op_a){
			new escuchador_botones_letras(   jtf_formula  , 'a'  , offset_caracteres);
			offset_caracteres--;
		}});
		
		boton_op_b.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_op_b){
			new escuchador_botones_letras(   jtf_formula  , 'b'  , offset_caracteres);
			offset_caracteres--;
		}});
		
		boton_op_c.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_op_c){
			new escuchador_botones_letras(   jtf_formula  , 'c'  , offset_caracteres);
			offset_caracteres--;
		}});
		
		boton_op_d.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_op_d){
			new escuchador_botones_letras(   jtf_formula  , 'd'  , offset_caracteres);
			offset_caracteres--;
		}});
		
		boton_op_e.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_op_e){
			new escuchador_botones_letras(   jtf_formula  , 'e'  , offset_caracteres);
			offset_caracteres--;
		}});
		
		
		boton_op_x.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_op_x){
			new escuchador_botones_letras(   jtf_formula  , 'x'  , offset_caracteres);
			offset_caracteres--;
		}});
		
		boton_op_y.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_op_y){
			new escuchador_botones_letras(   jtf_formula  , 'y'  , offset_caracteres);
			offset_caracteres--;
		}});
		
		boton_op_z.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_op_z){
			new escuchador_botones_letras(   jtf_formula  , 'z'  , offset_caracteres);
			offset_caracteres--;
		}});
		
		boton_op_t.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_op_t){
			new escuchador_botones_letras(   jtf_formula  , 't'  , offset_caracteres);
			offset_caracteres--;
		}});
		
	//movimiento del cursor
		boton_op_izqt.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent izq_t_t){
			jtf_formula.requestFocus();
			jtf_formula.setCaretPosition(0);
		}});
		
		boton_op_dert.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent der_t_t){
			jtf_formula.requestFocus();
			jtf_formula.setCaretPosition(    (jtf_formula.getText()).length()    );
		}});
		
		boton_op_del.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent delete_c){
			int posicion_cursor = jtf_formula.getCaretPosition();
			if (posicion_cursor > 0){
				String contenido = jtf_formula.getText();	
				String cadena_resultado = (contenido.substring(0,posicion_cursor-1)) + (contenido.substring(posicion_cursor,contenido.length()));
				jtf_formula.setText(cadena_resultado);
				jtf_formula.requestFocus();
				if (posicion_cursor-1 >0) {jtf_formula.setCaretPosition(posicion_cursor-1);}
				else if (posicion_cursor-1 <= 0){
					jtf_formula.setCaretPosition(0);
				}
				
			}//if
			else if (posicion_cursor == 0){
				jtf_formula.requestFocus();
				jtf_formula.setCaretPosition(0);
			}
			
		}});
		
		boton_op_izq.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent izquierda_c){
			int posicion_cursor = jtf_formula.getCaretPosition();
			if (posicion_cursor != 0){
				jtf_formula.requestFocus();
				jtf_formula.setCaretPosition(posicion_cursor-1);
				
			}
			else {
				jtf_formula.requestFocus();
				jtf_formula.setCaretPosition(0);
			}
		}});
		
		boton_op_der.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent izquierda_c){
			int posicion_cursor = jtf_formula.getCaretPosition();
			//int longiturString contenido = jtf_formula.getText();
			if (posicion_cursor != (jtf_formula.getText()).length() ){
				jtf_formula.requestFocus();
				jtf_formula.setCaretPosition(posicion_cursor+1);
				
			}
			else {
				jtf_formula.requestFocus();
				jtf_formula.setCaretPosition(  (jtf_formula.getText()).length()   );
			}
		}});
		
		boton_op_pidt.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent qq2){
			int posicion_cursor = jtf_formula.getCaretPosition();
			jtf_formula.setText("( "+jtf_formula.getText()+" )");
			jtf_formula.requestFocus();
			jtf_formula.setCaretPosition(posicion_cursor+2);
			
		}});
		
		boton_op_pit.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent qq2){
			int posicion_cursor = jtf_formula.getCaretPosition();
			jtf_formula.setText("( "+jtf_formula.getText());
			jtf_formula.requestFocus();
			jtf_formula.setCaretPosition(posicion_cursor+2);
			
		}});
		
		boton_op_pdt.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent qq2){
			int posicion_cursor = jtf_formula.getCaretPosition();
			jtf_formula.setText(jtf_formula.getText()+" )");
			jtf_formula.requestFocus();
			jtf_formula.setCaretPosition(posicion_cursor);
		
		}});
		
		//cortar, copiar, pegar botones barra
		boton_herramientas_cortar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent cortar){
			jtf_formula.cut();
		}});
		boton_herramientas_copiar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent copiar){
			jtf_formula.copy();
		}});
		boton_herramientas_pegar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent pegar){
			jtf_formula.paste();
		}});
		
		
		boton_insertar_esfera.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent bip1){
				int color = combo_colores.getSelectedIndex();
				int tamaño = combo_tamaños.getSelectedIndex();
				//if (_tablero.insertar_aleatorio(1,color+1,tamaño+1) == 1) new warning_max_elementos(interfaz2.this);
				if (_tablero.insertar_aleatorio(1,color+1,tamaño+1) == 1) (new JOptionPane(null)).showMessageDialog(null, "El límite de objetos es de 5", "Aviso", JOptionPane.ERROR_MESSAGE);
				mimalla = _tablero.devolver_malla();
		}});
		
		
		boton_insertar_cubo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent bip1){
				int color = combo_colores.getSelectedIndex();
				int tamaño = combo_tamaños.getSelectedIndex();
				if (_tablero.insertar_aleatorio(2,color+1,tamaño+1) == 1) (new JOptionPane(null)).showMessageDialog(null, "El límite de objetos es de 5", "Aviso", JOptionPane.ERROR_MESSAGE);
				mimalla = _tablero.devolver_malla();
		}});
		
		
		boton_insertar_piramide.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent bip1){
				int color = combo_colores.getSelectedIndex();
				int tamaño = combo_tamaños.getSelectedIndex();
				if (_tablero.insertar_aleatorio(3,color+1,tamaño+1) == 1) (new JOptionPane(null)).showMessageDialog(null, "El límite de objetos es de 5", "Aviso", JOptionPane.ERROR_MESSAGE);
				mimalla = _tablero.devolver_malla();
		}});
		
		
		boton_insertar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent bins){
			
//System.out.println("jtf_formula = ''");
				String cadena_Formula = null;
				cadena_Formula = jtf_formula.getText();
				
				analizador an1 = new analizador(mimalla, jtf_formula, 1);
				if (an1.analizar() == 0) (new JOptionPane(null)).showMessageDialog(null, "La sintaxis no es correcta o no hay elementos en el modelo", "Aviso", JOptionPane.ERROR_MESSAGE);
				else if (cadena_Formula.compareTo("") == 0) (new JOptionPane(null)).showMessageDialog(null, "Escriba una fórmula válida", "Aviso", JOptionPane.ERROR_MESSAGE);
				else {
					malla_vuelta = mimalla;
					
					String fonmula = jtf_formula.getText();
					String cadena2 = (new miniconversor()).convertir(fonmula);
					//formula_vuelta = cadena2;
					formula_vuelta = jtf_formula.getText();
					
					setVisible(false);
					_ok = true;
				}
		}});
		
		//Tooltips
		boton_insertar_piramide.setToolTipText("Inserta un elemento pirámide en el modelo");
		boton_insertar_cubo.setToolTipText("Inserta un elemento cubo en el modelo");
		boton_insertar_esfera.setToolTipText("Inserta un elemento esfera en el modelo");

		boton_monop_grande.setToolTipText("grande(a). 'a' es grande");
		boton_monop_pequeño.setToolTipText("pequeño(a). 'a' es pequeño");
		boton_monop_amarillo.setToolTipText("amarillo(a). 'a' es de color amarillo");
		boton_monop_rojo.setToolTipText("rojo(a). 'a' es de color rojo");
		boton_monop_azul.setToolTipText("azul(a). 'a' es de color azul");
		boton_monop_esfera.setToolTipText("esfera(a). 'a' tiene forma de esfera");
		boton_monop_cubo.setToolTipText("cubo(a). 'a' tiene forma de cubo");
		boton_monop_piramide.setToolTipText("piramide(a). 'a' tiene forma de pirámide");
		
		boton_bip_masGrande.setToolTipText("masGrande(a,b). 'a' es más grande que 'b'");
		boton_bip_masPequeño.setToolTipText("masPequeño(a,b). 'a' es más pequeño que 'b'");
		boton_bip_izquierda.setToolTipText("izquierda(a,b). 'a' está colocado a la izquierda de 'b'");
		boton_bip_derecha.setToolTipText("derecha(a,b). 'a' está colocado a la derecha de 'b'");
		boton_bip_detras.setToolTipText("detras(a,b). 'a' está colocado por detrás de 'b'");
		boton_bip_delante.setToolTipText("delante(a,b). 'a' está colocado por delante de 'b'");
		boton_bip_alrededor.setToolTipText("alrededor(a,b). 'a' está en alguna casilla contigüa a 'b'");
		boton_bip_mismoColor.setToolTipText("mismoColor(a,b). 'a' tiene el mismo color que 'b'");
		boton_bip_mismaForma.setToolTipText("mismaForma(a,b). 'a' tiene la misma forma que 'b'");
		boton_bip_mismoTamanho.setToolTipText("mismoTamaño(a,b). 'a' tiene el mismo tamaño que 'b'");
		boton_bip_mismaColumna.setToolTipText("mismaColumna(a,b). 'a' no está ni a la izquierda ni a la derecha de 'b'");
		boton_bip_mismaFila.setToolTipText("mismaFila(a,b). 'a' no está ni por detrás ni por delante de 'b'");
		
		boton_trip_entre.setToolTipText("entre(a,b,c). 'a' está entre 'b' y 'c'");
		
		boton_op_pit.setToolTipText("Coloca un paréntesis al principio de la fórmula");
		boton_op_pdt.setToolTipText("Coloca un paréntesis al final de la fórmula");
		boton_op_pidt.setToolTipText("Encierra la fórmula completa entre paréntesis");
		boton_op_del.setToolTipText("Borra un caracter hacia atrás");
		boton_op_izq.setToolTipText("Mueve el cursor un caracter a la izquierda");
		boton_op_der.setToolTipText("Mueve el cursor un caracter a la derecha");
		boton_op_izqt.setToolTipText("Mueve el cursor al principio de la fórmula");
		boton_op_dert.setToolTipText("Mueve el cursor al final de la fórmula");
		
		boton_op_T.setToolTipText("Función tautológica");
		boton_op_NT.setToolTipText("Función falsedad");
		boton_herramientas_cortar.setToolTipText("Cortar selección");
		boton_herramientas_copiar.setToolTipText("Copiar selección");
		boton_herramientas_pegar.setToolTipText("Pegar");
		combo_colores.setToolTipText("Color del elemento a insertar");
		combo_tamaños.setToolTipText("Tamaño del elemento a insertar");
		
		
		//
		panel_superior.add(BorderLayout.NORTH, barra_herramientas);
		panel_superior.add(BorderLayout.CENTER, panel_ppal);
		getContentPane().add(panel_superior);
		
		setSize(800,600);
		setTitle("Nueva Fórmula/Modelo");
		setVisible(true);
		
		
	}//CONSTRUCTOR
	
	public malla devolver_malla() { return malla_vuelta;}
	
	public String devolver_formula() { return formula_vuelta; }
	
	public boolean ok()	{ return _ok; }
	
}//class