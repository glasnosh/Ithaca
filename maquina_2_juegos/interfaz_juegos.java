/*
 *  "interfaz_juegos.java"	
 *	Ventana principal del programa de juegos, con toda su parafernalia añadida
 *	
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */

/*
 *	public interfaz_juegos()
 *	private void iniciar()
 *	private void jugar()
 *	private void cargar()
 *	public void asignar_rol(int rol)
 *	public void terminator()
 *
 */
 
 
import java.awt.*;
import java.awt.event.*;

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

import java.util.Random;
import java.util.*;
import java.lang.*;

import java.io.*;
import javax.swing.tree.*;



class interfaz_juegos extends JFrame implements Runnable{	

	public static ImageIcon icono_ppal =new ImageIcon("png/icono_tux.png");
	
	public static ImageIcon icono_nuevo = new ImageIcon("png/new.png");
	public static ImageIcon icono_salir = new ImageIcon("png/exit.png");
	public static ImageIcon icono_ayuda = new ImageIcon("png/help.png");
	public static ImageIcon icono_info = new ImageIcon("png/info.png");
	public static ImageIcon icono_arbol = new ImageIcon("png/tree.png");
	
	public static ImageIcon icono_ganadas = new ImageIcon("png/ok.png");
	public static ImageIcon icono_perdidas = new ImageIcon("png/cancel.png");
	public static ImageIcon icono_nivel = new ImageIcon("png/level.png");
	
	public static ImageIcon icono_flecha_turno = new ImageIcon("png/flecha_turno.png");
	public static ImageIcon icono_flecha_turno_2 = new ImageIcon("png/flecha_turno_2.png");
	public static ImageIcon icono_noflecha_turno = new ImageIcon("png/flecha_noturno.png");
	
	public static ImageIcon icono_ramas = new ImageIcon("png/ramas.png");
	
	public static ImageIcon icono_tux = new ImageIcon("png/tux.png");
	
	public static ImageIcon icono_blanco = new ImageIcon("png/blanco1.png");
	
	public static JLabel etiqueta_ganadas;
	public static JLabel etiqueta_perdidas;
	public static JLabel etiqueta_nivel;
	public static JLabel etiqueta_ganadas_v;
	public static JLabel etiqueta_perdidas_v;
	public static JLabel etiqueta_nivel_v;
	
	
	
	public static JToolBar barra_herramientas;
	
	
	public static JPanel panel_ppal;	//donde van todos los componentes excepto la barra de herramientas
	public static JPanel panel_ppal_b;
	public static JPanel panel_superior;

	
	public static JButton boton_herramientas_nuevo;
	public static JButton boton_herramientas_salir;
	public static JButton boton_herramientas_ayuda;
	public static JButton boton_herramientas_info;
	public static JButton boton_herramientas_arbol;
	
	public static JPanel panel_norte;
	public static JLabel label_rol_jugador;
	public static JLabel label_rol_maquina;
	public static JLabel label_turno_jugador;
	public static JLabel label_turno_jugador_2;
	public static JLabel label_turno_maquina;
	public static JLabel label_turno_maquina_2;
	public static JPanel panel_fondo_rol_jugador;
	public static JPanel panel_fondo_rol_maquina;
	
	
	public static JButton boton_hijo_izquierdo;
	public static JButton boton_hijo_derecho;
	public static JLabel etiqueta_subarbol_conector;	
	public static JPanel panel_sup_temp_1;
	
	public static JPanel panel_derivacion;
	public static JTextField texto_formula;
	public static JPanel panel_informacion;
	public static JPanel panel_linea_informacion_1;
	public static JPanel panel_linea_informacion_2;
	public static JPanel panel_linea_informacion_3;
	public static JPanel panel_linea_informacion_tux;
	public static JLabel label_informacion_1;
	public static JLabel label_informacion_2;
	public static JLabel label_informacion_3;
	public static JLabel label_informacion_tux;
	public static JPanel panel_informacion_reja;
	
	public static JPanel _tablero_overlay;
	
	public static JComboBox combo_inteligencia;
	
	public static Vector vector_formulas_faciles;
	public static Vector vector_modelos_faciles;
	public static Vector vector_formulas_medios;
	public static Vector vector_modelos_medios;
	public static Vector vector_formulas_dificiles;
	public static Vector vector_modelos_dificiles;
	
	//MÁS VARIABLES DE PARTIDA
	int contador_faciles;
	int contador_medios;
	int contador_dificiles;
	
	malla mimalla;
	malla malla_entrada;
	tablero _tablero;
	String formula;
	String formula_entrada;
	int turno;
	miniconversor mn1;
	String _hijo_izquierdo;
	String _hijo_derecho;
	String _trozo;
	boolean rol;
	boolean rol_maquina_inicial;
	boolean _hoja;
	int estado;
	boolean en_juego;
	String _cuantificador;
	hilo_jugar h_j;
	int inteligencia;			//de la maquina. 1. Ñu, 2. Inteligente
	int nivel;				//dificultad del modelo/fórmula. 1, 2, 3
	nodo nodo_raiz;
	arbol miarbol;
	seguimiento_arbol seguidor;
	eliminador_cosas ec1;
	DefaultMutableTreeNode raiz_grafica;

	
	private Thread idx_Thread = null;
	private int threadpriority=idx_Thread.MIN_PRIORITY;
	public void run(){}
	public void start(){
		idx_Thread = new Thread(this);
        	idx_Thread.setPriority(Thread.MIN_PRIORITY);
        	idx_Thread.start();
	}

	public interfaz_juegos(){	//CONSTRUCTOR

			
		//propiedades del JFrame
		setTitle("ITHACA; jugando...");	
		setIconImage(icono_ppal.getImage());
		
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {	public void windowClosing(WindowEvent e) {terminator();	}});
		
		vector_formulas_faciles = new Vector(10,1);
		vector_modelos_faciles = new Vector(10,1);
		vector_formulas_medios = new Vector(10,1);
		vector_modelos_medios = new Vector(10,1);
		vector_formulas_dificiles = new Vector(10,1);
		vector_modelos_dificiles = new Vector(10,1);
		
		contador_faciles = 0;
		contador_medios = 0;
		contador_dificiles = 0;
		
		cargar();	//CARGA LOS ARRAYS DE MODELOS DESDE EL DISCO
		
		//barra de herramientas superior
		barra_herramientas = new JToolBar("BarraHerramientas1",javax.swing.SwingConstants.HORIZONTAL);
		barra_herramientas.setFloatable(false);
		barra_herramientas.setBorderPainted(false);
		
		boton_herramientas_nuevo = new JButton(icono_nuevo);
		boton_herramientas_salir = new JButton(icono_salir);
		boton_herramientas_ayuda = new JButton(icono_ayuda);
		boton_herramientas_info = new JButton(icono_info);
		boton_herramientas_arbol = new JButton(icono_arbol);
		boton_herramientas_arbol.setEnabled(false);
		
		etiqueta_ganadas = new JLabel("Ganadas: ",icono_ganadas,0);
		etiqueta_ganadas_v = new JLabel("0");
		etiqueta_perdidas = new JLabel("Perdidas: ",icono_perdidas,0);
		etiqueta_perdidas_v = new JLabel("0");
		etiqueta_nivel = new JLabel("Nivel: ",icono_nivel,0);
		etiqueta_nivel_v = new JLabel("Facil");
		
		
		
		barra_herramientas.addSeparator();
		barra_herramientas.add(boton_herramientas_nuevo);
		barra_herramientas.addSeparator();
		barra_herramientas.add(boton_herramientas_salir);
		barra_herramientas.addSeparator();
		barra_herramientas.add(boton_herramientas_ayuda);
		barra_herramientas.add(boton_herramientas_info);
		barra_herramientas.addSeparator();
		barra_herramientas.addSeparator();
		barra_herramientas.addSeparator();
		barra_herramientas.add(etiqueta_ganadas);
		barra_herramientas.add(etiqueta_ganadas_v);
		barra_herramientas.addSeparator();
		barra_herramientas.addSeparator();
		barra_herramientas.add(etiqueta_perdidas);
		barra_herramientas.add(etiqueta_perdidas_v);
		barra_herramientas.addSeparator();
		barra_herramientas.addSeparator();
		barra_herramientas.add(etiqueta_nivel);
		barra_herramientas.add(etiqueta_nivel_v);
		barra_herramientas.addSeparator();
		barra_herramientas.addSeparator();
		barra_herramientas.add(boton_herramientas_arbol);
		
				JRadioButton jrb_azar = new JRadioButton("Azar");
				JRadioButton jrb_inteligente = new JRadioButton("Master");
				ButtonGroup  grupo_inteligencia = new ButtonGroup();	
				grupo_inteligencia.add(jrb_azar);
				grupo_inteligencia.add(jrb_inteligente);
		
				JToolBar barra_herramientas2 = new JToolBar("BarraHerramientas2",javax.swing.SwingConstants.HORIZONTAL);
				barra_herramientas2.setFloatable(false);
				barra_herramientas2.setBorderPainted(false);
				barra_herramientas2.addSeparator();
				barra_herramientas2.add(new JLabel ("Inteligencia de la máquina: "));
				barra_herramientas2.addSeparator();
				barra_herramientas2.addSeparator();
				barra_herramientas2.addSeparator();
				barra_herramientas2.add(jrb_azar);
				barra_herramientas2.add(jrb_inteligente);
				jrb_inteligente.setSelected(true);
				inteligencia = 2;
				
				JRadioButton jrb_facil = new JRadioButton("Facil");
				JRadioButton jrb_medio = new JRadioButton("Medio");
				JRadioButton jrb_dificil = new JRadioButton("Dificil");
				ButtonGroup grupo_nivel = new ButtonGroup();
				grupo_nivel.add(jrb_facil);
				grupo_nivel.add(jrb_medio);
				grupo_nivel.add(jrb_dificil);
				JToolBar barra_herramientas3 = new JToolBar("BarraHerramientas3",javax.swing.SwingConstants.HORIZONTAL);
				barra_herramientas3.setFloatable(false);
				barra_herramientas3.setBorderPainted(false);
				barra_herramientas3.addSeparator();
				barra_herramientas3.add(new JLabel ("Complejidad fórmula/modelo: "));
				barra_herramientas3.addSeparator();
				barra_herramientas3.addSeparator();
				barra_herramientas3.addSeparator();
				barra_herramientas3.add(jrb_facil);
				barra_herramientas3.add(jrb_medio);
				barra_herramientas3.add(jrb_dificil);
				jrb_facil.setSelected(true);
				nivel = 1;
		
		panel_superior = new JPanel(new BorderLayout());	
		panel_ppal_b = new JPanel(new BorderLayout());
		panel_ppal_b.setBorder(new EmptyBorder(10,10,10,10));			//borde general
    		panel_superior.add(barra_herramientas, BorderLayout.NORTH);
		JPanel panel_barras_inferiores = new JPanel(new GridLayout(2,1));
		panel_barras_inferiores.add(barra_herramientas2);
		panel_barras_inferiores.add(barra_herramientas3);
		panel_superior.add(panel_barras_inferiores, BorderLayout.SOUTH);
		
		panel_superior.add(panel_ppal_b, BorderLayout.CENTER);
		
		panel_ppal = new JPanel (new BorderLayout());
		panel_ppal_b.add(panel_ppal, BorderLayout.CENTER);
		
		panel_norte = new JPanel(new GridLayout(1,2,1,0));
		label_rol_jugador = new JLabel("Jugador");
		label_rol_maquina = new JLabel("Maquina");
		label_rol_jugador.setHorizontalAlignment(SwingConstants.CENTER);
		label_rol_maquina.setHorizontalAlignment(SwingConstants.CENTER);
		label_turno_jugador = new JLabel(icono_noflecha_turno);
		label_turno_jugador_2 = new JLabel(icono_noflecha_turno);
		label_turno_maquina = new JLabel(icono_noflecha_turno);
		label_turno_maquina_2 = new JLabel(icono_noflecha_turno);
		panel_fondo_rol_jugador = new JPanel(new BorderLayout());
		panel_fondo_rol_maquina = new JPanel(new BorderLayout());
		panel_fondo_rol_jugador.setBackground(Color.LIGHT_GRAY );
		panel_fondo_rol_maquina.setBackground(Color.LIGHT_GRAY);
		panel_fondo_rol_jugador.setBorder(new LineBorder(Color.black,1));
		panel_fondo_rol_maquina.setBorder(new LineBorder(Color.black,1));
		panel_fondo_rol_jugador.add(label_turno_jugador,BorderLayout.WEST);
		panel_fondo_rol_jugador.add(label_turno_jugador_2, BorderLayout.EAST);
		panel_fondo_rol_jugador.add(label_rol_jugador, BorderLayout.CENTER);
		panel_fondo_rol_maquina.add(label_turno_maquina,BorderLayout.WEST);
		panel_fondo_rol_maquina.add(label_turno_maquina_2,BorderLayout.EAST);
		panel_fondo_rol_maquina.add(label_rol_maquina,BorderLayout.CENTER);
		
		panel_norte.add(panel_fondo_rol_jugador);
		panel_norte.add(panel_fondo_rol_maquina);
		panel_norte.setBorder(new EmptyBorder(10,10,10,10));
		
		
		panel_ppal_b.add(panel_norte, BorderLayout.NORTH);
		
		
		//formula y subárboles
		panel_derivacion = new JPanel(new GridLayout(3,1));
		
		texto_formula = new JTextField("No hay ninguna partida en juego");
		texto_formula.setEditable(false);
		texto_formula.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
		texto_formula.setHorizontalAlignment(SwingConstants.CENTER);
		
		boton_hijo_izquierdo = new JButton("------------");
		boton_hijo_derecho = new   JButton("------------");
		
		boton_hijo_izquierdo.setMargin(new Insets(10,10,10,10));
		boton_hijo_derecho.setMargin(new Insets(10,10,10,10));
		etiqueta_subarbol_conector = new JLabel("  ");
		boton_hijo_izquierdo.setEnabled(false);
		boton_hijo_derecho.setEnabled(false);
		etiqueta_subarbol_conector.setFont(new java.awt.Font("Dialog", Font.BOLD, 23));
		etiqueta_subarbol_conector.setForeground(Color.blue);
		panel_sup_temp_1 = new JPanel(new FlowLayout());
		panel_sup_temp_1.add(boton_hijo_izquierdo);
		panel_sup_temp_1.add(etiqueta_subarbol_conector);
		panel_sup_temp_1.add(boton_hijo_derecho);
		panel_sup_temp_1.setBorder(new EmptyBorder(0,0,0,0));	//originalmente 4,4,4,4
		
		panel_derivacion.add(texto_formula);
		panel_derivacion.add(new JLabel(icono_ramas));
		panel_derivacion.add(panel_sup_temp_1);
		panel_derivacion.setBorder(new EmptyBorder(10,10,10,10));
				
		panel_ppal.add(panel_derivacion, BorderLayout.NORTH);
		
		//informassion...
		panel_informacion = new JPanel(new BorderLayout());
		panel_linea_informacion_1 = new JPanel();
		panel_linea_informacion_2 = new JPanel();
		panel_linea_informacion_3 = new JPanel();
		panel_linea_informacion_tux = new JPanel();
		panel_linea_informacion_1.setBackground(Color.orange);
		panel_linea_informacion_2.setBackground(Color.orange);
		panel_linea_informacion_3.setBackground(Color.orange);
		panel_linea_informacion_tux.setBackground(Color.orange);
		
		label_informacion_1 = new JLabel("Inicie una nueva partida",0);
		label_informacion_2 = new JLabel("",0);
		label_informacion_3 = new JLabel("",0);
		label_informacion_tux = new JLabel(icono_tux);
    		label_informacion_1.setFont(new java.awt.Font("DialogInput", Font.BOLD, 18));
		label_informacion_2.setFont(new java.awt.Font("DialogInput", Font.BOLD, 18));
		label_informacion_3.setFont(new java.awt.Font("DialogInput", Font.BOLD, 18));
		
		panel_linea_informacion_1.add(label_informacion_1);
		panel_linea_informacion_2.add(label_informacion_2);
		panel_linea_informacion_3.add(label_informacion_3);
		panel_linea_informacion_tux.add(label_informacion_tux);
		
		panel_informacion_reja = new JPanel(new GridLayout(2,1));
		panel_informacion_reja.add(panel_linea_informacion_1);
		panel_informacion_reja.add(panel_linea_informacion_2);
		
		panel_informacion.add(panel_linea_informacion_tux,BorderLayout.WEST);
		panel_informacion.add(panel_informacion_reja,BorderLayout.CENTER);
		
		panel_informacion.setBorder(new LineBorder(Color.black,1));
		panel_informacion.setBorder(new EmptyBorder(10,10,10,10));
		
		panel_ppal.add(panel_informacion, BorderLayout.SOUTH);
		
		
		//panel
		mimalla = new malla();
		_tablero = new tablero(mimalla);
		_tablero_overlay = new JPanel(new BorderLayout());
		_tablero_overlay.add(_tablero,BorderLayout.CENTER);
		panel_ppal.add(_tablero_overlay, BorderLayout.CENTER);
		
		JLabel l_blanco = new JLabel(icono_blanco);
		JLabel l_blanco_2 = new JLabel(icono_blanco);
		panel_ppal.add((new JPanel()).add(l_blanco), BorderLayout.EAST);
		panel_ppal.add((new JPanel()).add(l_blanco_2), BorderLayout.WEST);		
		
		_tablero_overlay.setBorder(new EmptyBorder(10,10,10,10));
		
		
		//escuchadores de botones
		boton_herramientas_salir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent qq2){
				terminator();
		}});
				
		boton_herramientas_ayuda.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent qq2){
				new help_NEW(interfaz_juegos.this);
		}});
		
		boton_herramientas_info.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent qq2){
				new sucustrupadores(interfaz_juegos.this);
		}});
		
		boton_herramientas_nuevo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent qq2){
				iniciar();
		}});
		
		boton_herramientas_arbol.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent qq2){
				arbol_grafico ag1 = new arbol_grafico(interfaz_juegos.this);
				ag1.sons(nodo_raiz, raiz_grafica);
				ag1.mostrar_arbol(raiz_grafica);
		}});
		
		
		jrb_azar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent qq2){
				inteligencia = 1;
		}});
				
		jrb_inteligente.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent qq2){
				inteligencia = 2;
		}});
		
		jrb_facil.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent aee1){
				nivel = 1;
				etiqueta_nivel_v.setText("Facil");
		}});
		
		jrb_medio.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent aee1){
				nivel = 2;
				etiqueta_nivel_v.setText("Medio");
		}});
		
		jrb_dificil.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent aee1){
				nivel = 3;
				etiqueta_nivel_v.setText("Dificil");
		}});
		
		//ToolTips y fin
		boton_herramientas_nuevo.setToolTipText("Nueva partida");
		boton_herramientas_salir.setToolTipText("Salir");
		boton_herramientas_ayuda.setToolTipText("Ayuda");
		boton_herramientas_info.setToolTipText("Info");
		boton_herramientas_arbol.setToolTipText("Mostrar árbol de derivación de la fórmula");
		jrb_inteligente.setToolTipText("La máquina juega de forma inteligente");
		jrb_azar.setToolTipText("La máquina realiza sus jugadas al azar");
		jrb_facil.setToolTipText("La complicación de las fórmulas es mínima");
		jrb_medio.setToolTipText("La complicación de las fórmulas es media");
		jrb_dificil.setToolTipText("La complicación de las fórmulas es dificil");
		
		getContentPane().add(panel_superior);
		
		boton_hijo_izquierdo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent bhi){
				texto_formula.setText(mn1.convertir_interno_visual(boton_hijo_izquierdo.getText()));
				panel_derivacion.setBorder(new EmptyBorder(10,10,10,10));
				seguidor._derivar(1);
				h_j = new hilo_jugar(
							panel_derivacion,
							panel_norte,
							panel_fondo_rol_jugador,
							panel_fondo_rol_maquina,
							_tablero_overlay,
							etiqueta_ganadas_v,
							etiqueta_perdidas_v,
							etiqueta_nivel_v,
							label_rol_jugador,
							label_rol_maquina,
							label_turno_jugador_2,
							label_turno_jugador,
							label_turno_maquina,
							label_turno_maquina_2,
							label_informacion_1,
							label_informacion_2,
							boton_hijo_izquierdo,
							boton_hijo_derecho,
							etiqueta_subarbol_conector,
							texto_formula,
							mimalla,
							_tablero,
							inteligencia,
							miarbol,
							nodo_raiz,
							seguidor,
							boton_herramientas_nuevo
						);
				h_j.start();
		}});
		
		boton_hijo_derecho.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent bhd){
				texto_formula.setText(mn1.convertir_interno_visual(boton_hijo_derecho.getText()));
				panel_derivacion.setBorder(new EmptyBorder(10,10,10,10));
				seguidor._derivar(2);
				h_j = new hilo_jugar(
							panel_derivacion,
							panel_norte,
							panel_fondo_rol_jugador,
							panel_fondo_rol_maquina,
							_tablero_overlay,
							etiqueta_ganadas_v,
							etiqueta_perdidas_v,
							etiqueta_nivel_v,
							label_rol_jugador,
							label_rol_maquina,
							label_turno_jugador_2,
							label_turno_jugador,
							label_turno_maquina,
							label_turno_maquina_2,
							label_informacion_1,
							label_informacion_2,
							boton_hijo_izquierdo,
							boton_hijo_derecho,
							etiqueta_subarbol_conector,
							texto_formula,
							mimalla,
							_tablero,
							inteligencia,
							miarbol,
							nodo_raiz,
							seguidor,
							boton_herramientas_nuevo
						);
				h_j.start();
		}});
		
		_tablero.addMouseListener(new MouseListener(){public void mousePressed(MouseEvent e){
			if (_tablero.get_sensible() == true){

				char objeto_marcado = _tablero.objeto_clickado();
				if (	(objeto_marcado == 'a') ||
			 		(objeto_marcado == 'b') ||
					(objeto_marcado == 'c') ||
					(objeto_marcado == 'd') ||
					(objeto_marcado == 'e') ){
_tablero.sensible_off();
						if (objeto_marcado == 'a') seguidor._derivar(1);;
						if (objeto_marcado == 'b') seguidor._derivar(2);
						if (objeto_marcado == 'c') seguidor._derivar(3);
						if (objeto_marcado == 'd') seguidor._derivar(4);
						if (objeto_marcado == 'e') seguidor._derivar(5);
						_cuantificador = h_j.pasame_cuantificador();
		 				char a_traducir = _cuantificador.charAt(_cuantificador.length()-1);
						String codigo_maquina = mn1.convertir_visual_interno(texto_formula.getText());
				 		ec1 = new eliminador_cosas(codigo_maquina);
						texto_formula.setText(mn1.convertir_interno_visual(ec1.eliminar_cuantificador(a_traducir,objeto_marcado)));
						try{idx_Thread.sleep(1500);
						}catch (InterruptedException easdf){}
					
				
						
				h_j = new hilo_jugar(
							panel_derivacion,
							panel_norte,
							panel_fondo_rol_jugador,
							panel_fondo_rol_maquina,
							_tablero_overlay,
							etiqueta_ganadas_v,
							etiqueta_perdidas_v,
							etiqueta_nivel_v,
							label_rol_jugador,
							label_rol_maquina,
							label_turno_jugador_2,
							label_turno_jugador,
							label_turno_maquina,
							label_turno_maquina_2,
							label_informacion_1,
							label_informacion_2,
							boton_hijo_izquierdo,
							boton_hijo_derecho,
							etiqueta_subarbol_conector,
							texto_formula,
							mimalla,
							_tablero,
							inteligencia,
							miarbol,
							nodo_raiz,
							seguidor,
							boton_herramientas_nuevo
						);
				h_j.start();
			   }//if
			}//if
			}//mouseListener
			public void mouseExited(MouseEvent e){}
			public void mouseMoved(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
		});
		
	
		
	}//CONSTRUCTOR
	
	private void iniciar(){
	
	  if (	((nivel == 1) && (vector_formulas_faciles.size() == 0)) ||
	  	((nivel == 2) && (vector_formulas_medios.size() == 0)) ||
	  	((nivel == 3) && (vector_formulas_dificiles.size() == 0)) ){
			(new JOptionPane(null)).showMessageDialog(null, "No hay fórmulas disponibles en la base de datos para ese nivel", "ERROR", JOptionPane.ERROR_MESSAGE);
	  }
	  else{
	  	
		//nivel
		if (contador_faciles >= vector_formulas_faciles.size()) contador_faciles = 0;
		if (contador_medios >= vector_formulas_medios.size()) contador_medios = 0;
		if (contador_dificiles >= vector_formulas_dificiles.size()) contador_dificiles = 0;
		
		if (nivel == 1){	//facil
			formula_entrada = (String) vector_formulas_faciles.elementAt(contador_faciles);
			malla_entrada = (malla) vector_modelos_faciles.elementAt(contador_faciles);
			contador_faciles++;
		}//if
		else if (nivel == 2){	//medios
			formula_entrada = (String) vector_formulas_medios.elementAt(contador_medios);
			malla_entrada = (malla) vector_modelos_medios.elementAt(contador_medios);
			contador_medios++;
		}//else
		else if (nivel == 3){	//dificiles
			formula_entrada = (String) vector_formulas_dificiles.elementAt(contador_dificiles);
			malla_entrada = (malla) vector_modelos_dificiles.elementAt(contador_dificiles);
			contador_dificiles++;
		}//else
		
		for (int i=0; i<6; i++){
			for (int j=0; j<9; j++){
				if ((mimalla.devolverCasilla(i,j)).ocupada()) mimalla.eliminar(i,j);
				if ((malla_entrada.devolverCasilla(i,j)).ocupada()){
					int forma = (malla_entrada.devolverCasilla(i,j)).forma();
					int tamanho = (malla_entrada.devolverCasilla(i,j)).tamanho();
					int color = (malla_entrada.devolverCasilla(i,j)).color();
					char nombre = (malla_entrada.devolverCasilla(i,j)).nombre();
					mimalla.insertar(i,j,nombre,forma,tamanho,color);
				}
				
				
			}//for
		}//for
		mn1 = new miniconversor();
		formula = mn1.convertir_visual_interno(formula_entrada);
		
		boton_herramientas_nuevo.setEnabled(false);
		turno = 0;		//no se está en juego
		
		eliminador_cosas ec1 = new eliminador_cosas(formula);
		
		raiz_grafica = new DefaultMutableTreeNode(mn1.convertir_interno_visual( formula));
		
		boton_hijo_izquierdo.setText("------------");
		boton_hijo_derecho.setText("------------");
		etiqueta_subarbol_conector.setText("  ");
		
		texto_formula.setText(mn1.convertir_interno_visual(formula));
		texto_formula.setBackground(Color.white);
		_tablero.repaint();
		boton_herramientas_arbol.setEnabled(true);
		
		JOptionPane pane = new JOptionPane(null);
 		String[] options = { "A Verificar", "A Falsar" };
		int pane_return = pane.showOptionDialog(null, "¿A que desea jugar?", "Nueva Partida",JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, options[1]);
		if (pane_return == 0){	//Jugador a verificar
			 asignar_rol(1);
			 rol_maquina_inicial = false;
		}//if
		else if (pane_return == 1){
			asignar_rol(2);
			rol_maquina_inicial = true;
		}//else
		else System.exit(0);//Si se cierra este cuadro de dialogo cerrar programa. Sino se pone a jugar sola. EStán locos estos romanos...
		//crear el arbol de derivación para la fórmula elegida
		nodo_raiz = new nodo(formula, null, mimalla,0, rol_maquina_inicial);	//nodo raiz
		miarbol = new arbol();
		miarbol.hijos(nodo_raiz, mimalla);		//ESto crea la estructura de datos arbol completa
		miarbol.crear_estrategia(nodo_raiz);	//Se escribe sobre el arbol la estrategia.
		seguidor = new seguimiento_arbol(miarbol, nodo_raiz);
//miarbol.recorrer(nodo_raiz);
		//nodo_recorrido = nodo_raiz;

		jugar();
	   }//else
	}//iniciar
	
	private void jugar(){
	
	
	h_j = new hilo_jugar(
			panel_derivacion,
			panel_norte,
			panel_fondo_rol_jugador,
			panel_fondo_rol_maquina,
			_tablero_overlay,
			etiqueta_ganadas_v,
			etiqueta_perdidas_v,
			etiqueta_nivel_v,
			label_rol_jugador,
			label_rol_maquina,
			label_turno_jugador_2,
			label_turno_jugador,
			label_turno_maquina,
			label_turno_maquina_2,
			label_informacion_1,
			label_informacion_2,
			boton_hijo_izquierdo,
			boton_hijo_derecho,
			etiqueta_subarbol_conector,
			texto_formula,
			mimalla,
			_tablero,
			inteligencia,
			miarbol,
			nodo_raiz,
			seguidor,
			boton_herramientas_nuevo
	);
	h_j.start();
	
	}//jugar
	
	
	private void cargar(){
		try{
			FileInputStream entrada = new FileInputStream("./manager/models.i");
			ObjectInputStream oos = new ObjectInputStream(entrada);
			vector_formulas_faciles = (Vector) oos.readObject();
			vector_modelos_faciles  = (Vector) oos.readObject();
			vector_formulas_medios = (Vector) oos.readObject();
			vector_modelos_medios  = (Vector) oos.readObject();
			vector_formulas_dificiles = (Vector) oos.readObject();
			vector_modelos_dificiles  = (Vector) oos.readObject();
			oos.close();
		}
		 catch (ClassNotFoundException a1){ System.out.println("ClassNotFoundException");}
		 catch (InvalidClassException a2){ System.out.println("InvalidClassException: "+a2.getMessage());}
		 catch (StreamCorruptedException a3){ System.out.println("StreamCorruptedException");}
		 catch (OptionalDataException a4){ System.out.println("OptionalDataException");}
		 catch (IOException a5){ System.out.println("IOException");}
		     
	}//cargar
	
	public void asignar_rol(int rol){ //asigna rol al jugador y pone a la máquina el otro. 1-Verificador, 2-Falsador
		if (rol == 1){
			panel_fondo_rol_jugador.setBackground(Color.GREEN );
			panel_fondo_rol_maquina.setBackground(Color.RED);
			label_rol_jugador.setText("Jugador es VERIFICADOR");
			label_rol_maquina.setText("Maquina es FALSEADOR");			
		}//if
		else if (rol == 2){
			panel_fondo_rol_jugador.setBackground(Color.RED );
			panel_fondo_rol_maquina.setBackground(Color.GREEN);
			label_rol_jugador.setText("Jugador es FALSADOR");
			label_rol_maquina.setText("Maquina es VERIFICADOR");
		}//else
		
	}//asignar_rol
	

	
	public void terminator(){		//confirmación de salir
		JOptionPane pane = new JOptionPane(null);
 		String[] options = { "Si", "No" };
		int pane_return = pane.showOptionDialog(null, "¿Está seguro?", "Salir",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, options, options[1]);
		if (pane_return == 0) System.exit(0);	
	}//terminator

	public static void main(String argv[]) {
	
		final bootSplash bs = new bootSplash();
		bs.setLocationRelativeTo(null);
		bs.setVisible(true);
		 
		final interfaz_juegos demo = new interfaz_juegos();		
		
		Toolkit tk = Toolkit.getDefaultToolkit();
         	Dimension d = tk.getScreenSize();			//Obtener resolución de la pantalla	
	
		if  ( (d.width <800) || (d.height<600) ) (new JOptionPane(null)).showMessageDialog(null, "La resolución de su pantalla es inferior a 800 x 600. Algunos componentes podrían no verse correctamente", "AVISO", JOptionPane.INFORMATION_MESSAGE);
		else {
			if (d.height >= 640) demo.setSize(600,640);
			else demo.setSize(600,600);
		}
		demo.setLocationRelativeTo(null); 
		demo.setResizable(false);
        	demo.setVisible(true);
		bs.setVisible(false);
		System.out.println("Funcionando...");
		

	}//main

}//class interfaz_juegos

