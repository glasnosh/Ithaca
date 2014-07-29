/*
 *  "interfaz.java"
 *	Interfaz de usuario
 * 	Método main()
 *
 *	TODO LIST:
 *	  - limpiar un poco más el código. Mucho código repetido se puede agrupar en una función única.
 *
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

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



class interfaz extends JFrame{
	
	//AVISO.   Se usan indistíntamente los términos   "sentencia" y "fórmula"


	//Aquí se definen todas (y solamente) las variables que tienen que ser static. El resto se declaran en el constructor
	// a medida que se van necesitando. Particularmente hay que declara aquí todos los componentes que se modifican a lo 
	// largo de la clase, como "activarlos/desactivarlos", cambiar su icono....
	
	malla mimalla;						//malla brutal...
	tablero _tablero;					//Dibujo 
	
	int offset_caracteres;					//Para recolocar el cursor 
	
	static int fila;
	
	JPanel[] paneles_filas_tabla;				//Paneles que agrupan una JLabel con el número el JButton y el JtextField
	
	JButton boton_flag_h = new JButton(icono_flag_h);

	static JMenuBar menu_bar;
	static JToolBar barra_herramientas;
	
	static Object[][] elementos_tabla;			//Array bidimensional que representa la ordenacion de los elementos JTextField
								// JButton y JLabel de la zona de análisis. Ojo, se declara como (Object) así
								// que luego al acceder a los componentes hay que hacer el "typecasting" para
								// adaptarlo a lo que sea. vg.- ((JTextField) elementos[a][b])
								
	JComboBox combo_colores;
	JComboBox combo_tamaños;
	
	JButton boton_analizar_cubo;
	JButton boton_analizar_espejo;
	JButton boton_analizar_infinito;
	JButton boton_analizar_magia;
	JButton boton_analizar_centrado;
	
	JButton boton_insertar_piramide;
	JButton boton_insertar_cubo;
	JButton boton_insertar_esfera;
	
	JMenu submenu_aspecto = new JMenu("Aspecto");
	JRadioButtonMenuItem met_lf = (JRadioButtonMenuItem) submenu_aspecto.add(new JRadioButtonMenuItem("Metal_Look"));
	JRadioButtonMenuItem gtk_lf = (JRadioButtonMenuItem) submenu_aspecto.add(new JRadioButtonMenuItem("GTK_Look"));
	JRadioButtonMenuItem mot_lf = (JRadioButtonMenuItem) submenu_aspecto.add(new JRadioButtonMenuItem("Motif_Look"));
	JRadioButtonMenuItem win_lf = (JRadioButtonMenuItem) submenu_aspecto.add(new JRadioButtonMenuItem("Windows_Look"));
	
	
	
	
	public static ImageIcon icono_nuevo_small = new ImageIcon("png/small/filenew.png");	
	public static ImageIcon icono_cargar_small = new ImageIcon("png/small/fileopen.png");
	public static ImageIcon icono_guardar_small = new ImageIcon("png/small/fileexport.png");
	public static ImageIcon icono_salir_small = new ImageIcon("png/small/exit.png");
	public static ImageIcon icono_cortar_small = new ImageIcon("png/small/cut.png");
	public static ImageIcon icono_copiar_small = new ImageIcon("png/small/copy.png");
	public static ImageIcon icono_pegar_small = new ImageIcon("png/small/paste.png");
	public static ImageIcon icono_ayuda_small = new ImageIcon("png/small/help.png");
	public static ImageIcon icono_about_small = new ImageIcon("png/small/info.png");
	public static ImageIcon icono_world_small = new ImageIcon("png/small/world.png");
	
	public static ImageIcon icono_nuevo = new ImageIcon("png/new.png");
	public static ImageIcon icono_cargar = new ImageIcon("png/open.png");
	public static ImageIcon icono_guardar = new ImageIcon("png/save.png");
	public static ImageIcon icono_cortar = new ImageIcon("png/cut.png");
	public static ImageIcon icono_copiar = new ImageIcon("png/copy.png");
	public static ImageIcon icono_pegar = new ImageIcon("png/paste.png");
	
	public static ImageIcon icono_flag_a = new ImageIcon("png/flag_a.png");
	public static ImageIcon icono_flag_b = new ImageIcon("png/flag_b.png");
	public static ImageIcon icono_flag_c = new ImageIcon("png/flag_c.png");
	public static ImageIcon icono_flag_d = new ImageIcon("png/flag_d.png");
	public static ImageIcon icono_flag_e = new ImageIcon("png/flag_e.png");
	public static ImageIcon icono_flag_f = new ImageIcon("png/flag_f.png");
	public static ImageIcon icono_flag_g = new ImageIcon("png/flag_g.png");
	public static ImageIcon icono_flag_h = new ImageIcon("png/flag_h.png");
	
	public static ImageIcon icono_infinito = new ImageIcon("png/infinito.png");
	public static ImageIcon icono_minicubo = new ImageIcon("png/minicubo.png");
	public static ImageIcon icono_espejo = new ImageIcon("png/espejo.png");
	public static ImageIcon icono_magia = new ImageIcon("png/magic.png");
	public static ImageIcon icono_centrado = new ImageIcon("png/centrar.png");
	
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
	
 	
	JPanel panel_ppal = new JPanel(new FlowLayout());	//donde va todo excepto la barra de herramientas.
	
	String current_directory = "";
	
	public interfaz(){		//CONSTRUCTOR
	
		String OS =System.getProperty("os.name");	//Nombre del sistema operativo. Se usa para saber que Look&Feels se pueden aplicar
		
		fila = 0;					//Fila que está activa de las de análisis.
		
		offset_caracteres = 0;
		
		mimalla = new malla();		
		
		setTitle("ITHACA; construyendo y evaluando modelos...");	//título e icono de la ventana principal ("panel_ppal" hereda de JFrame)
		setIconImage(icono_ppal.getImage());
		
		addWindowListener(new WindowAdapter() {	public void windowClosing(WindowEvent e) {	//Evento de cerrar ventana
			new confirmacion_salir(interfaz.this);
		}});
		
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);	//Si no se coloca esto, al pulsar la X de cerrar la ventana muestra el 
										// diálogo de confirmación pero la ventana se cierra igualmente
		
		if (!OS.contains("indows")) win_lf.setEnabled(false);		// El tema "Windows" solo está disponible si se ejecuta sobre windows
		else gtk_lf.setEnabled(false);
			 
		//por defecto se coloca el theme metal con fuentes BOLD (no negrita)
		try{
			UIManager.put("swing.boldMetal", Boolean.FALSE);
		}catch (Exception e){System.out.println("interfaz.java: Exception UI");}
		
			//escuchadores de los menuitems que fuerzan el cambio de Look&Feel
		met_lf.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent qq2){
				try{
					UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
					UIManager.put("swing.boldMetal", Boolean.FALSE);
					SwingUtilities.updateComponentTreeUI(getContentPane());
					SwingUtilities.updateComponentTreeUI(menu_bar);		//La barra de menú va un poco por libre y hay que
				}catch(Exception e_lf){}					// forzarla a cambiar
		}});
		
		gtk_lf.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent aflf){
				try{
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
					SwingUtilities.updateComponentTreeUI(getContentPane());
					SwingUtilities.updateComponentTreeUI(menu_bar);
				}catch(Exception e_lf){}
		}});	
		
		
		mot_lf.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent aflf){
				try{
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
					SwingUtilities.updateComponentTreeUI(getContentPane());
					SwingUtilities.updateComponentTreeUI(menu_bar);


				}catch(Exception e_lf){}
		}});
		
		
		win_lf.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent aflf){
				try{
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					SwingUtilities.updateComponentTreeUI(getContentPane());
					SwingUtilities.updateComponentTreeUI(menu_bar);
				}catch(Exception e_lf){}
		}});
		
		
				
		//Barra de menú
		menu_bar = new JMenuBar();
		menu_bar.updateUI();	
		
		JMenu menu_archivo = new JMenu("Archivo");	//menú archivo
		menu_bar.add(menu_archivo);
		JMenuItem item_nuevo = new JMenuItem("Nuevo",icono_nuevo_small);
		JMenuItem item_salir = new JMenuItem("Salir",icono_salir_small);
		JMenuItem item_cargar = new JMenuItem("Cargar",icono_cargar_small);
		JMenuItem item_guardar = new JMenuItem("Guardar",icono_guardar_small);
		JMenuItem item_barra = new JMenuItem();
		menu_archivo.add(item_nuevo);
		menu_archivo.addSeparator();
		menu_archivo.add(item_cargar);
		menu_archivo.add(item_guardar);
		menu_archivo.addSeparator();
		menu_archivo.add(item_salir);
		
		item_nuevo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent qq2){
				JOptionPane pane = new JOptionPane(null);
 				 String[] options = { "Si", "No" };

				int pane_return = pane.showOptionDialog(null, "Perderá el trabajo no guardado.¿Seguir?", "Nuevo",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, options, options[1]);

				if (pane_return == 0) reiniciar_todo();
		}});
		
		
		item_salir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent qq2){
				new confirmacion_salir(interfaz.this);	 
		}});
		
		JMenu menu_edicion = new JMenu("Edición");	//menú edición
		menu_bar.add(menu_edicion);
		JMenuItem item_cortar = new JMenuItem("Cortar",icono_cortar_small);
		JMenuItem item_copiar = new JMenuItem("Copiar",icono_copiar_small);
		JMenuItem item_pegar = new JMenuItem("Pegar",icono_pegar_small);
		menu_edicion.add(item_cortar);
		menu_edicion.add(item_copiar);
		menu_edicion.add(item_pegar);
		
		JMenu menu_herramientas = new JMenu("Herramientas");	//menú herramientas
		menu_bar.add(menu_herramientas);

		ButtonGroup check_aspecto = new ButtonGroup();		//Los items de temas se agrupan en un grupo de botones de forma que solamente
		check_aspecto.add(met_lf);				// uno de ellos esté seleccionado .
		check_aspecto.add(gtk_lf);
		check_aspecto.add(mot_lf);
		check_aspecto.add(win_lf);
		
		met_lf.setSelected(true);				//Por defecto el seleccionado es el tema metálico
		
		menu_herramientas.add(submenu_aspecto);
		menu_herramientas.addSeparator();
		
		JMenuItem item_analizar_todas = new JMenuItem("Analizar todas las formulas");
		menu_herramientas.add(item_analizar_todas);
		JMenuItem item_blanquear_malla = new JMenuItem("Reiniciar el modelo");
		menu_herramientas.add(item_blanquear_malla);
		
		JMenu menu_ayuda = new JMenu("Ayuda");		//menú ayuda
		menu_bar.add(menu_ayuda);
		JMenuItem item_ayuda = new JMenuItem("Ayuda",icono_ayuda_small);
		JMenuItem item_ayuda_online = new JMenuItem("Documentación en línea",icono_world_small);
		JMenuItem item_info_ithaca = new JMenuItem("Sobre ITHACA",icono_about_small);
		menu_ayuda.add(item_ayuda);
		menu_ayuda.add(item_ayuda_online);
		menu_ayuda.addSeparator();
		menu_ayuda.add(item_info_ithaca);
		
		setJMenuBar(menu_bar);
		
		item_ayuda.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_about){
			new help(interfaz.this);
		}});
		
		item_ayuda_online.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_about){
			//abrir el navegador si estamos en Windows o mostrar la dirección
			String OS2 =System.getProperty("os.name");
			if (OS2.contains("indows")) {
				Runtime r = Runtime.getRuntime();
				Process p = null;
				String comando = "explorer http://ithaca.berlios.de/documentation";
				try {
					p = r.exec( comando );
					p.waitFor();
				} catch( Exception e ) {System.out.println( "interfaz.java: Error ejecutando "+comando );}
			}//if
			else {
				(new JOptionPane(null)).showMessageDialog(null, "Puede encontrar la documentación oficial en: \n http://ithaca.berlios.de/", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			}//else
			
		}});
		
		
		item_info_ithaca.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_about){
			new sucustrupadores(interfaz.this);	//sucustrupadores es la clase que muestra el "AcercaDe"
		}});
		
				
		//barra de herramientas
		barra_herramientas = new JToolBar("BarraHerramientas1",javax.swing.SwingConstants.HORIZONTAL);
		barra_herramientas.setFloatable(false);
		barra_herramientas.setBorderPainted(false);
		
		
		JButton boton_herramientas_nuevo = new JButton(icono_nuevo);
		
		boton_herramientas_nuevo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent qq2){
				JOptionPane pane = new JOptionPane(null);
 				 String[] options = { "Si", "No" };
				int pane_return = pane.showOptionDialog(null, "Perderá el trabajo no guardado.¿Seguir?", "Nuevo",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, options, options[1]);
				if (pane_return == 0) reiniciar_todo();
		}});
		
		JButton boton_herramientas_cargar = new JButton(icono_cargar);
		JButton boton_herramientas_guardar = new JButton(icono_guardar);
		JButton boton_herramientas_cortar = new JButton(icono_cortar);
		JButton boton_herramientas_copiar = new JButton(icono_copiar);
		JButton boton_herramientas_pegar = new JButton(icono_pegar);
		
		JButton boton_flag_a = new JButton(icono_flag_a);
		JButton boton_flag_b = new JButton(icono_flag_b);
		JButton boton_flag_c = new JButton(icono_flag_c);
		JButton boton_flag_d = new JButton(icono_flag_d);
		JButton boton_flag_e = new JButton(icono_flag_e);
		JButton boton_flag_f = new JButton(icono_flag_f);
		JButton boton_flag_g = new JButton(icono_flag_g);
		
		
		
		barra_herramientas.addSeparator();
		barra_herramientas.add(boton_herramientas_nuevo);
		barra_herramientas.addSeparator();
		barra_herramientas.add(boton_herramientas_cargar);
		barra_herramientas.add(boton_herramientas_guardar);
		barra_herramientas.addSeparator();
		barra_herramientas.add(boton_herramientas_cortar);
		barra_herramientas.add(boton_herramientas_copiar);
		barra_herramientas.add(boton_herramientas_pegar);
		barra_herramientas.addSeparator();
		barra_herramientas.add(boton_flag_a);
		barra_herramientas.add(boton_flag_b);
		barra_herramientas.add(boton_flag_c);
		barra_herramientas.add(boton_flag_d);
		barra_herramientas.add(boton_flag_e);
		barra_herramientas.add(boton_flag_f);
		barra_herramientas.add(boton_flag_g);
		barra_herramientas.add(boton_flag_h);
		
		
		//escuchadores de los botones que dan nombre a los objetos
		boton_flag_a.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent bfa1){
				_tablero.dar_nombre('a');
				mimalla = _tablero.devolver_malla();
		}});
		
		boton_flag_b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent bfa1){
				_tablero.dar_nombre('b');
				mimalla = _tablero.devolver_malla();
		}});
		
		boton_flag_c.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent bfa1){
				_tablero.dar_nombre('c');
				mimalla = _tablero.devolver_malla();
		}});
		
		boton_flag_d.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent bfa1){
				_tablero.dar_nombre('d');
				mimalla = _tablero.devolver_malla();
		}});
		boton_flag_e.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent bfa1){
				_tablero.dar_nombre('e');
				mimalla = _tablero.devolver_malla();
		}});
		boton_flag_f.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent bfa1){
				_tablero.dar_nombre('f');
				mimalla = _tablero.devolver_malla();
		}});
		boton_flag_g.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent bfa1){
				_tablero.dar_nombre('g');
				mimalla = _tablero.devolver_malla();
		}});
		boton_flag_h.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent bfa1){
				_tablero.dar_nombre('h');
				mimalla = _tablero.devolver_malla();
		}});
		
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
		
		
		boton_insertar_esfera.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent bip1){
				int color = combo_colores.getSelectedIndex();
				int tamaño = combo_tamaños.getSelectedIndex();
				if (_tablero.insertar_aleatorio(1,color+1,tamaño+1) == 1) new warning_max_elementos(interfaz.this);
				mimalla = _tablero.devolver_malla();
		}});
		
		
		boton_insertar_cubo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent bip1){
				int color = combo_colores.getSelectedIndex();
				int tamaño = combo_tamaños.getSelectedIndex();
				if (_tablero.insertar_aleatorio(2,color+1,tamaño+1) == 1) new warning_max_elementos(interfaz.this);
				mimalla = _tablero.devolver_malla();
		}});
		
		
		boton_insertar_piramide.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent bip1){
				int color = combo_colores.getSelectedIndex();
				int tamaño = combo_tamaños.getSelectedIndex();
				if (_tablero.insertar_aleatorio(3,color+1,tamaño+1) == 1) new warning_max_elementos(interfaz.this);
				mimalla = _tablero.devolver_malla();
		}});
		
			//para que los iconos de los botones de insertar cambien dinámicamente según las características de los combobox
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
		
		JPanel panel_superior = new JPanel(new BorderLayout());	
    		panel_superior.add(barra_herramientas, BorderLayout.NORTH);
		
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
		JButton boton_op_f = new JButton("f");
		JButton boton_op_g = new JButton("g");
		JButton boton_op_h = new JButton("h");
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
		boton_op_f.setMargin(new Insets(0,0,0,0));
		boton_op_g.setMargin(new Insets(0,0,0,0));
		boton_op_h.setMargin(new Insets(0,0,0,0));
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
		panel_teclado_operaciones.add(boton_op_f);
		panel_teclado_operaciones.add(boton_op_g);
		panel_teclado_operaciones.add(boton_op_h);
			//borde de agrupacion	
		panel_teclado_operaciones.setBorder( new TitledBorder("Operadores"));

		panel_ppal.add(panel_teclado_monop);
		panel_ppal.add(panel_teclado_bip);
		panel_ppal.add(panel_teclado_trip);
		panel_ppal.add(panel_teclado_operaciones);
		
		
		//_______________ Aquí comienzan los componentes donde el usuario introduce sentencias y analiza
		elementos_tabla =new Object[100][5];				//Máximo 100 sentencias
		_tablero = new tablero(mimalla, elementos_tabla);

		JPanel temp = new JPanel(new BorderLayout());
	
		JScrollPane panel_ppal_S = new JScrollPane(panel_ppal);
		temp.add(panel_ppal_S,BorderLayout.NORTH);
		temp.add(_tablero,BorderLayout.CENTER);
	
		boton_analizar_cubo = new JButton(icono_minicubo);
		boton_analizar_infinito = new JButton(icono_infinito);
		boton_analizar_espejo = new JButton(icono_espejo);	
		boton_analizar_magia = new JButton(icono_magia);
		boton_analizar_centrado = new JButton(icono_centrado);
	
	
		boton_analizar_infinito.addActionListener(new ActionListener(){		//Si la sentencia está analizada aquí, se puede en el otro mundo
			public void actionPerformed(ActionEvent b_an_inf){
				if ( 	((((JTextField)elementos_tabla[fila][1]).getBackground())  == (Color.red)) 
					||((((JTextField)elementos_tabla[fila][1]).getBackground())  ==(Color.green))
					||((((JTextField)elementos_tabla[fila][1]).getBackground())  == (Color.yellow) )
					){
						String formula = ((JTextField)elementos_tabla[fila][1]).getText();
						new analizar_infinito(interfaz.this,formula,mimalla);
				}
				else (new JOptionPane(null)).showMessageDialog(null, "La fórmula debe estar analizada previamente para eso", "Aviso", JOptionPane.ERROR_MESSAGE);
		}});
	
		boton_analizar_espejo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent b_an_esp){
				if ( 	((((JTextField)elementos_tabla[fila][1]).getBackground())  == (Color.red)) 
					||((((JTextField)elementos_tabla[fila][1]).getBackground())  ==(Color.green))
					||((((JTextField)elementos_tabla[fila][1]).getBackground())  == (Color.yellow) )
					){
						String formula = ((JTextField)elementos_tabla[fila][1]).getText();
						new analizar_espejo(interfaz.this,formula,mimalla);
						_tablero.repaint();
				}
				else (new JOptionPane(null)).showMessageDialog(null, "La fórmula debe estar analizada previamente para eso", "Aviso", JOptionPane.ERROR_MESSAGE);
		}});
		
		
		boton_analizar_cubo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent b_an_cub){
				if ( 	((((JTextField)elementos_tabla[fila][1]).getBackground())  == (Color.red)) 
					||((((JTextField)elementos_tabla[fila][1]).getBackground())  ==(Color.green))
					||((((JTextField)elementos_tabla[fila][1]).getBackground())  == (Color.yellow) )
					){
						String formula = ((JTextField)elementos_tabla[fila][1]).getText();
						new analizar_cubo(interfaz.this,formula,mimalla);
				}
				else (new JOptionPane(null)).showMessageDialog(null, "La fórmula debe estar analizada previamente para eso", "Aviso", JOptionPane.ERROR_MESSAGE);
		}});
		
		boton_analizar_magia.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent b_an_mag){
				if ( 	((((JTextField)elementos_tabla[fila][1]).getBackground())  == (Color.red)) 
					||((((JTextField)elementos_tabla[fila][1]).getBackground())  ==(Color.green))
					||((((JTextField)elementos_tabla[fila][1]).getBackground())  == (Color.yellow) )
					){
						String formula = ((JTextField)elementos_tabla[fila][1]).getText();
						new analizar_aleatorio(interfaz.this,formula);
				}
				else (new JOptionPane(null)).showMessageDialog(null, "La fórmula debe estar analizada previamente para eso", "Aviso", JOptionPane.ERROR_MESSAGE);
		}});
	
		
		boton_analizar_centrado.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent b_an_cent){
				if ( 	((((JTextField)elementos_tabla[fila][1]).getBackground())  == (Color.red)) 
					||((((JTextField)elementos_tabla[fila][1]).getBackground())  ==(Color.green))
					||((((JTextField)elementos_tabla[fila][1]).getBackground())  == (Color.yellow) )
					){
						String formula = ((JTextField)elementos_tabla[fila][1]).getText();
						new analizar_centrado(interfaz.this,mimalla,formula);
				}
				else (new JOptionPane(null)).showMessageDialog(null, "La fórmula debe estar analizada previamente para eso", "Aviso", JOptionPane.ERROR_MESSAGE);
		}});
		

		panel_superior.add(temp,BorderLayout.CENTER);	//la barra de herramientas va al NORTH, y el resto al CENTER
		
		//TABLA DE INSERCCIÓN/ANALISIS DE FÓRMULAS
		paneles_filas_tabla = new JPanel[100];
		for (int i=0; i<100; i++){
			paneles_filas_tabla[i] = new JPanel(new FlowLayout(2,0,0));
		}//for
			
		for (int i=0; i<100; i++){		//Creamos los 100 grupos de componentes y los añadimos
			int _i_mas_1 = i+1;
			String cadena_numero = new String(((Integer)_i_mas_1).toString());	//El JLabel con el número de sentencia
			elementos_tabla[i][0] = new JLabel(cadena_numero);				
			paneles_filas_tabla[i].add((JLabel)elementos_tabla[i][0]);
			
			elementos_tabla[i][1] = new JTextField(25);				//El JTextField
			paneles_filas_tabla[i].add((JTextField)elementos_tabla[i][1]);
			((JTextField)elementos_tabla[i][1]).setText("");
			((JTextField)elementos_tabla[i][1]).setBackground(Color.white);
			((JTextField)elementos_tabla[i][1]).setFont(new Font("times",0,12));
			((JTextField)elementos_tabla[i][1]).setCaretColor(Color.red);
						
			elementos_tabla[i][2] = new JButton("Analizar    ");			//El botón
			((JButton)elementos_tabla[i][2]).setMargin(new Insets(0,0,0,0));
			paneles_filas_tabla[i].add((JButton)elementos_tabla[i][2]);
			
			elementos_tabla[i][3] = new JPanel();					//Un espacio
			
			elementos_tabla[i][4] = new Integer(i);					//Identifica esa fila
		}//for
	
		for (int i=0; i<100; i++){					//Escuchadores de los botones "Analizar" sentencias simples
			((JButton)elementos_tabla[i][2]).addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent an_b_c){
					JButton jb_temp_1 = (JButton) an_b_c.getSource();
					paneles_filas_tabla[fila].setBorder(null);		//Ese grupo para a ser el activo y se le pone borde
					for (int j=0; j<100; j++){
						if (elementos_tabla[j][2] ==jb_temp_1){
						 	fila = (Integer) elementos_tabla[j][4];
						}//if
					}//for
					paneles_filas_tabla[fila].setBorder(new LineBorder(Color.blue,2,true));
				
					mimalla = _tablero.devolver_malla();
					if (  ((JTextField)elementos_tabla[fila][1]).getText().length() > 512  ){
						new warning_max_caracteres(interfaz.this);
					}
					else {
						// Aquí se hace el análisis como tal. Se crea un hilo para ese JTextField
						// Como la malla es la típica, se pasa como tipo de malla el tipo '1'. Si luego no hay elementos
						//  la clase de "hilo_analizador" es la que se encarga de cambiarlo por '4' (malla sin elementos)
						
						hilo_analizador hilo_x = new hilo_analizador(mimalla,((JTextField)elementos_tabla[fila][1]),1);
						hilo_x.run();
					}
					if (	(((JTextField)elementos_tabla[fila][1]).getBackground() == Color.red)
						||(((JTextField)elementos_tabla[fila][1]).getBackground() == Color.green)
						||(((JTextField)elementos_tabla[fila][1]).getBackground() == Color.yellow)
						){
							boton_analizar_cubo.setEnabled(true);
							boton_analizar_espejo.setEnabled(true);
							boton_analizar_infinito.setEnabled(true);
							boton_analizar_magia.setEnabled(true);		
							boton_analizar_centrado.setEnabled(true);
					}//if
					else {
						boton_analizar_cubo.setEnabled(false);
						boton_analizar_espejo.setEnabled(false);
						boton_analizar_infinito.setEnabled(false);
						boton_analizar_magia.setEnabled(false);
						boton_analizar_centrado.setEnabled(false);
					}//else
			}});
		
		}//for
		
		
		for (int i=0; i<100; i++){	//Escuchadores de lo que ocurre al insertar/eliminar texto de un cuadro. En ese casi la casilla
						// debe reiniciarse (fondo a blanco, texto a negro.
			((JTextField)elementos_tabla[i][1]).getDocument().addDocumentListener(new DocumentListener(){
				public void changedUpdate(DocumentEvent h){}
				
				public void removeUpdate(DocumentEvent h){
					((JTextField)elementos_tabla[fila][1]).setBackground(Color.white);
					((JTextField)elementos_tabla[fila][1]).setForeground(Color.black);
					boton_analizar_cubo.setEnabled(false);
					boton_analizar_espejo.setEnabled(false);
					boton_analizar_infinito.setEnabled(false);	
					boton_analizar_magia.setEnabled(false);
					boton_analizar_centrado.setEnabled(false);
				}//removeUpdate()
				public void insertUpdate(DocumentEvent h){ 
					((JTextField)elementos_tabla[fila][1]).setBackground(Color.white);
					((JTextField)elementos_tabla[fila][1]).setForeground(Color.black);
					if ( (  ((JTextField)elementos_tabla[fila][1]).getText().length()  ) >512 ){
						new warning_max_caracteres(interfaz.this);						
					}//if
					boton_analizar_cubo.setEnabled(false);
					boton_analizar_espejo.setEnabled(false);
					boton_analizar_infinito.setEnabled(false);	
					boton_analizar_magia.setEnabled(false);
					boton_analizar_centrado.setEnabled(false);
				}//insertUpdate()
			});//documentListener
		}//for
			
		//inicialmente los botones de análisis raros deben estar desactivados.
		(new interruptor_botones_analisis_raros(boton_analizar_cubo,
			boton_analizar_infinito,
			boton_analizar_espejo,
			boton_analizar_magia,
			boton_analizar_centrado)).set(false);
		
		for (int i = 0; i<100; i++){		//Lo que ocurre cuando se hace click en un area de texto (cambiar borde, botones....)
			((JTextField)elementos_tabla[i][1]).addFocusListener(new FocusAdapter(){public void focusGained(FocusEvent h){
				JTextField jtf_temp_1 = (JTextField) h.getSource();
				paneles_filas_tabla[fila].setBorder(null);
				for (int j=0; j<100; j++){				//Este "for" captura en que fila ha sido el click
					if (elementos_tabla[j][1] ==jtf_temp_1){
						 fila = (Integer) elementos_tabla[j][4];
					}//if
				}//for
				paneles_filas_tabla[fila].setBorder(new LineBorder(Color.blue,2,true));		//Se le pone borde

				if (	(((JTextField)elementos_tabla[fila][1]).getBackground() == Color.red)
					||(((JTextField)elementos_tabla[fila][1]).getBackground() == Color.green)
					||(((JTextField)elementos_tabla[fila][1]).getBackground() == Color.yellow)
					){
						boton_analizar_cubo.setEnabled(true);
						boton_analizar_espejo.setEnabled(true);
						boton_analizar_infinito.setEnabled(true);		
						boton_analizar_magia.setEnabled(true);
						boton_analizar_centrado.setEnabled(true);
				}//if
				else {
					boton_analizar_cubo.setEnabled(false);
					boton_analizar_espejo.setEnabled(false);
					boton_analizar_infinito.setEnabled(false);
					boton_analizar_magia.setEnabled(false);
					boton_analizar_centrado.setEnabled(false);
				}//else
			}});//FocusListener
		}//for
		
		paneles_filas_tabla[fila].setBorder(new LineBorder(Color.blue,2,true));//para que aparezca así al inicializar. Por defecto el activo es el fila=0
		
		JPanel panel_analisis_extranhos = new JPanel();
		
		JButton boton_analizar_todo = new JButton("ANALIZAR TODAS");
		
		JButton boton_borrar_1 = new JButton("BORRAR");
		
		boton_borrar_1.setMargin(new Insets(0,0,0,0));
		boton_analizar_todo.setMargin(new Insets(0,0,0,0));
		boton_analizar_cubo.setMargin(new Insets(0,0,0,0));
		boton_analizar_infinito.setMargin(new Insets(0,0,0,0));
		boton_analizar_espejo.setMargin(new Insets(0,0,0,0));
		boton_analizar_magia.setMargin(new Insets(0,0,0,0));
		boton_analizar_centrado.setMargin(new Insets(0,0,0,0));
		
		panel_analisis_extranhos.add(boton_borrar_1);
		panel_analisis_extranhos.add(boton_analizar_todo);
		panel_analisis_extranhos.add(boton_analizar_cubo);
		panel_analisis_extranhos.add(boton_analizar_infinito);
		panel_analisis_extranhos.add(boton_analizar_espejo);
		panel_analisis_extranhos.add(boton_analizar_magia);
		panel_analisis_extranhos.add(boton_analizar_centrado);
		
		JPanel panel_tabla = new JPanel(new GridLayout(100,1,1,1));
		
		panel_tabla.setBorder( new TitledBorder("Fórmulas"));

		for (int i=0; i<100; i++){
			panel_tabla.add((JPanel)paneles_filas_tabla[i]);
			
		}//for
	
		JScrollPane panel_tabla_S = new JScrollPane(panel_tabla);
		
		JPanel panel_tabla_celdas = new JPanel(new BorderLayout(0,0));
		panel_tabla_celdas.add(panel_tabla_S,BorderLayout.CENTER);
		panel_tabla_celdas.add(panel_analisis_extranhos,BorderLayout.SOUTH);
		
		temp.add(panel_tabla_celdas,BorderLayout.EAST);
		
		//La cosilla de abajo con la leyenda de los colorinos
		JPanel barra_abajo_leyenda = new JPanel(new GridLayout(1,5,0,5) );
		JLabel leyenda_rojo_false = new JLabel("FALSO");
		JLabel leyenda_verde_true = new JLabel("VERDADERO");
		JLabel leyenda_blanco_no_evaluado = new JLabel("No evaluado aún");
		JLabel leyenda_amarillo_no_evaluable = new JLabel("No evaluable");
		JLabel leyenda_negro_no_formula = new JLabel("No es fórmula");
		
		JPanel panel_leyenda_rojo_false = new JPanel();
		panel_leyenda_rojo_false.setBackground(Color.red);
		panel_leyenda_rojo_false.add(leyenda_rojo_false);
		
		JPanel panel_leyenda_verde_true = new JPanel();
		panel_leyenda_verde_true.setBackground(Color.green);
		panel_leyenda_verde_true.add(leyenda_verde_true);
		
		JPanel panel_leyenda_amarillo_no_evaluable = new JPanel();
		panel_leyenda_amarillo_no_evaluable.setBackground(Color.yellow);
		panel_leyenda_amarillo_no_evaluable.add(leyenda_amarillo_no_evaluable);
		
		JPanel panel_leyenda_negro_no_formula = new JPanel();
		panel_leyenda_negro_no_formula.setBackground(Color.black);
		leyenda_negro_no_formula.setForeground(Color.white);
		panel_leyenda_negro_no_formula.add(leyenda_negro_no_formula);
		
		JPanel panel_leyenda_blanco_no_evaluado = new JPanel();
		panel_leyenda_blanco_no_evaluado.setBorder(new LineBorder(Color.black,1));
		panel_leyenda_blanco_no_evaluado.setBackground(Color.white);
		panel_leyenda_blanco_no_evaluado.add(leyenda_blanco_no_evaluado);
		
		barra_abajo_leyenda.add(panel_leyenda_blanco_no_evaluado);
		barra_abajo_leyenda.add(panel_leyenda_rojo_false);
		barra_abajo_leyenda.add(panel_leyenda_verde_true);
		barra_abajo_leyenda.add(panel_leyenda_amarillo_no_evaluable);
		barra_abajo_leyenda.add(panel_leyenda_negro_no_formula);
		barra_abajo_leyenda.setBorder( new LineBorder(Color.white));
		
		panel_superior.add(barra_abajo_leyenda, BorderLayout.SOUTH);
		
		
		
		//escuchadores de la mayoría de botones

		
	//funciones bip
		boton_bip_masGrande.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_masgrande){
			new escuchador_botones_funciones(  ((JTextField)elementos_tabla[fila][1])  ,  "masGrande(,)");
			offset_caracteres = 2;
		}});	
		
		boton_bip_masPequeño.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_maspequenho){
			new escuchador_botones_funciones(  ((JTextField)elementos_tabla[fila][1])  ,  "masPequeño(,)");
			offset_caracteres = 2;
		}});
		
		boton_bip_izquierda.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_izquierda){
			new escuchador_botones_funciones(  ((JTextField)elementos_tabla[fila][1])  ,  "izquierda(,)");
			offset_caracteres = 2;
		}});
		
		boton_bip_derecha.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_derecha){
			new escuchador_botones_funciones(  ((JTextField)elementos_tabla[fila][1])  ,  "derecha(,)");
			offset_caracteres = 2;
		}});
		
		boton_bip_detras.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_detras){
			new escuchador_botones_funciones(  ((JTextField)elementos_tabla[fila][1])  ,  "detras(,)");
			offset_caracteres = 2;
		}});
		
		boton_bip_delante.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_delante){
			new escuchador_botones_funciones(  ((JTextField)elementos_tabla[fila][1])  ,  "delante(,)");
			offset_caracteres = 2;
		}});
		
		boton_bip_alrededor.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_alrededor){
			new escuchador_botones_funciones(  ((JTextField)elementos_tabla[fila][1])  ,  "alrededor(,)");
			offset_caracteres = 2;
		}});
		
		boton_bip_mismoColor.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_mismoColor){
			new escuchador_botones_funciones(  ((JTextField)elementos_tabla[fila][1])  ,  "mismoColor(,)");
			offset_caracteres = 2;
		}});
		
		boton_bip_mismaForma.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_mismaForma){
			new escuchador_botones_funciones(  ((JTextField)elementos_tabla[fila][1])  ,  "mismaForma(,)");
			offset_caracteres = 2;
		}});
		
		boton_bip_mismoTamanho.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_mismoTamanho){
			new escuchador_botones_funciones(  ((JTextField)elementos_tabla[fila][1])  ,  "mismoTamaño(,)");
			offset_caracteres = 2;
		}});
		
		boton_bip_mismaFila.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_mismaFila){
			new escuchador_botones_funciones(  ((JTextField)elementos_tabla[fila][1])  ,  "mismaFila(,)");
			offset_caracteres = 2;
		}});
		
		boton_bip_mismaColumna.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_mismaColumna){
			new escuchador_botones_funciones(  ((JTextField)elementos_tabla[fila][1])  ,  "mismaColumna(,)");
			offset_caracteres = 2;
		}});
		
	//funciones trip
		boton_trip_entre.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_entre){
			new escuchador_botones_funciones(  ((JTextField)elementos_tabla[fila][1])  ,  "entre(,,)");
			offset_caracteres = 3;
		}});
		
	//funciones monop
		boton_monop_grande.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_grande){
			new escuchador_botones_funciones(   ((JTextField)elementos_tabla[fila][1])  ,  "grande()");
			offset_caracteres = 1;
		}});
		
		boton_monop_pequeño.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_pequenho){
			new escuchador_botones_funciones(   ((JTextField)elementos_tabla[fila][1])  ,  "pequeño()");
			offset_caracteres = 1;
		}});
		
		boton_monop_amarillo.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_amarillo){
			new escuchador_botones_funciones(   ((JTextField)elementos_tabla[fila][1])  ,  "amarillo()");
			offset_caracteres = 1;
		}});
		
		boton_monop_rojo.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_rojo){
			new escuchador_botones_funciones(   ((JTextField)elementos_tabla[fila][1])  ,  "rojo()");
			offset_caracteres = 1;
		}});
		
		boton_monop_azul.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_azul){
			new escuchador_botones_funciones(   ((JTextField)elementos_tabla[fila][1])  ,  "azul()");
			offset_caracteres = 1;
		}});
		
		boton_monop_esfera.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_esfera){
			new escuchador_botones_funciones(   ((JTextField)elementos_tabla[fila][1])  ,  "esfera()");
			offset_caracteres = 1;
		}});
		
		boton_monop_cubo.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_cubo){
			new escuchador_botones_funciones(   ((JTextField)elementos_tabla[fila][1])  ,  "cubo()");
			offset_caracteres = 1;
		}});
		
		boton_monop_piramide.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_piramide){
			new escuchador_botones_funciones(   ((JTextField)elementos_tabla[fila][1])  ,  "piramide()");
			offset_caracteres = 1;
		}});
		
	//noparametricas
		boton_op_NT.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_nT){
			
			String subcadena = nT;
			int posicion_cursor = ((JTextField)elementos_tabla[fila][1]).getCaretPosition();
			String contenido = ((JTextField)elementos_tabla[fila][1]).getText();
			String cadena_final = contenido.substring(0,posicion_cursor)+subcadena+contenido.substring(posicion_cursor,contenido.length());
			((JTextField)elementos_tabla[fila][1]).setText(cadena_final);
			((JTextField)elementos_tabla[fila][1]).requestFocus();
			//((JTextField)elementos_tabla[fila][1]).setCaretPosition( posicion_cursor + (subcadena.indexOf (')') +1) );	
			//((JTextField)elementos_tabla[fila][1]).setCaretPosition( posicion_cursor + 2 );	
		
			offset_caracteres = 0;
		}});
		
		boton_op_T.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_T){
			String subcadena = T;
			int posicion_cursor = ((JTextField)elementos_tabla[fila][1]).getCaretPosition();
			String contenido = ((JTextField)elementos_tabla[fila][1]).getText();
			String cadena_final = contenido.substring(0,posicion_cursor)+subcadena+contenido.substring(posicion_cursor,contenido.length());
			((JTextField)elementos_tabla[fila][1]).setText(cadena_final);
			((JTextField)elementos_tabla[fila][1]).requestFocus();
			//((JTextField)elementos_tabla[fila][1]).setCaretPosition( posicion_cursor + (subcadena.indexOf (')') +1) );
			//((JTextField)elementos_tabla[fila][1]).setCaretPosition( posicion_cursor + 2 );	
		
			offset_caracteres = 0;
		}});
		
	//parentesis izquierdo y derecho
	
		boton_op_p_i.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_p_i){
			
			String subcadena = "(";
			int posicion_cursor = ((JTextField)elementos_tabla[fila][1]).getCaretPosition();
			String contenido = ((JTextField)elementos_tabla[fila][1]).getText();
			String cadena_final = contenido.substring(0,posicion_cursor)+subcadena+contenido.substring(posicion_cursor,contenido.length());
			((JTextField)elementos_tabla[fila][1]).setText(cadena_final);
			((JTextField)elementos_tabla[fila][1]).requestFocus();
			((JTextField)elementos_tabla[fila][1]).setCaretPosition( posicion_cursor  +1);	
		
			offset_caracteres = 0;
		}});
	
	
	
	
		boton_op_p_d.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_p_d){
			
			String subcadena = ")";
			int posicion_cursor = ((JTextField)elementos_tabla[fila][1]).getCaretPosition();
			String contenido = ((JTextField)elementos_tabla[fila][1]).getText();
			String cadena_final = contenido.substring(0,posicion_cursor)+subcadena+contenido.substring(posicion_cursor,contenido.length());
			((JTextField)elementos_tabla[fila][1]).setText(cadena_final);
			((JTextField)elementos_tabla[fila][1]).requestFocus();
			((JTextField)elementos_tabla[fila][1]).setCaretPosition( posicion_cursor  +1);	
		
			offset_caracteres = 0;
		}});
	
	//y,o,no,=,-->, <->
		
		boton_op__y.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_p__y){
			
			String subcadena = Y;
			int posicion_cursor = ((JTextField)elementos_tabla[fila][1]).getCaretPosition();
			String contenido = ((JTextField)elementos_tabla[fila][1]).getText();
			String cadena_final = contenido.substring(0,posicion_cursor)+subcadena+contenido.substring(posicion_cursor,contenido.length());
			((JTextField)elementos_tabla[fila][1]).setText(cadena_final);
			((JTextField)elementos_tabla[fila][1]).requestFocus();
			((JTextField)elementos_tabla[fila][1]).setCaretPosition( posicion_cursor  +1);	
		
			offset_caracteres = 0;
		}});
		
		
		boton_op_o.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_p_o){
			
			String subcadena = O;
			int posicion_cursor = ((JTextField)elementos_tabla[fila][1]).getCaretPosition();
			String contenido = ((JTextField)elementos_tabla[fila][1]).getText();
			String cadena_final = contenido.substring(0,posicion_cursor)+subcadena+contenido.substring(posicion_cursor,contenido.length());
			((JTextField)elementos_tabla[fila][1]).setText(cadena_final);
			((JTextField)elementos_tabla[fila][1]).requestFocus();
			((JTextField)elementos_tabla[fila][1]).setCaretPosition( posicion_cursor  +1);	
		
			offset_caracteres = 0;
		}});
		
		boton_op_no.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_p_no){
			
			String subcadena = "¬";
			int posicion_cursor = ((JTextField)elementos_tabla[fila][1]).getCaretPosition();
			String contenido = ((JTextField)elementos_tabla[fila][1]).getText();
			String cadena_final = contenido.substring(0,posicion_cursor)+subcadena+contenido.substring(posicion_cursor,contenido.length());
			((JTextField)elementos_tabla[fila][1]).setText(cadena_final);
			((JTextField)elementos_tabla[fila][1]).requestFocus();
			((JTextField)elementos_tabla[fila][1]).setCaretPosition( posicion_cursor  +1);	
		
			offset_caracteres = 0;
		}});
		
		boton_op_igual.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_p_igual){
			
			String subcadena = "=";
			int posicion_cursor = ((JTextField)elementos_tabla[fila][1]).getCaretPosition();
			String contenido = ((JTextField)elementos_tabla[fila][1]).getText();
			String cadena_final = contenido.substring(0,posicion_cursor)+subcadena+contenido.substring(posicion_cursor,contenido.length());
			((JTextField)elementos_tabla[fila][1]).setText(cadena_final);
			((JTextField)elementos_tabla[fila][1]).requestFocus();
			((JTextField)elementos_tabla[fila][1]).setCaretPosition( posicion_cursor  +1);	
		
			offset_caracteres = 0;
		}});
		
		boton_op_paratodo.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_p_paratodo){
			
			String subcadena = PARATODO;
			int posicion_cursor = ((JTextField)elementos_tabla[fila][1]).getCaretPosition();
			String contenido = ((JTextField)elementos_tabla[fila][1]).getText();
			String cadena_final = contenido.substring(0,posicion_cursor)+subcadena+contenido.substring(posicion_cursor,contenido.length());
			((JTextField)elementos_tabla[fila][1]).setText(cadena_final);
			((JTextField)elementos_tabla[fila][1]).requestFocus();
			((JTextField)elementos_tabla[fila][1]).setCaretPosition( posicion_cursor  +1);	
		
			offset_caracteres = 0;
		}});
		
		boton_op_existe.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_p_existe){
			
			String subcadena = EXISTEUN;
			int posicion_cursor = ((JTextField)elementos_tabla[fila][1]).getCaretPosition();
			String contenido = ((JTextField)elementos_tabla[fila][1]).getText();
			String cadena_final = contenido.substring(0,posicion_cursor)+subcadena+contenido.substring(posicion_cursor,contenido.length());
			((JTextField)elementos_tabla[fila][1]).setText(cadena_final);
			((JTextField)elementos_tabla[fila][1]).requestFocus();
			((JTextField)elementos_tabla[fila][1]).setCaretPosition( posicion_cursor  +1);	
		
			offset_caracteres = 0;
		}});
		
		boton_op_implica.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_p_implica){
			
			String subcadena = IMPLICA;
			int posicion_cursor = ((JTextField)elementos_tabla[fila][1]).getCaretPosition();
			String contenido = ((JTextField)elementos_tabla[fila][1]).getText();
			String cadena_final = contenido.substring(0,posicion_cursor)+subcadena+contenido.substring(posicion_cursor,contenido.length());
			((JTextField)elementos_tabla[fila][1]).setText(cadena_final);
			((JTextField)elementos_tabla[fila][1]).requestFocus();
			((JTextField)elementos_tabla[fila][1]).setCaretPosition( posicion_cursor  +1);	
		
			offset_caracteres = 0;
		}});
		
		boton_op_bimplica.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_p_bimplica){
			
			String subcadena = BIMPLICA;
			int posicion_cursor = ((JTextField)elementos_tabla[fila][1]).getCaretPosition();
			String contenido = ((JTextField)elementos_tabla[fila][1]).getText();
			String cadena_final = contenido.substring(0,posicion_cursor)+subcadena+contenido.substring(posicion_cursor,contenido.length());
			((JTextField)elementos_tabla[fila][1]).setText(cadena_final);
			((JTextField)elementos_tabla[fila][1]).requestFocus();
			((JTextField)elementos_tabla[fila][1]).setCaretPosition( posicion_cursor  +1);	
		
			offset_caracteres = 0;
		}});
		
		
	//letras 
		boton_op_a.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_op_a){
			new escuchador_botones_letras(   ((JTextField)elementos_tabla[fila][1])  , 'a'  , offset_caracteres);
			offset_caracteres--;
		}});
		
		boton_op_b.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_op_b){
			new escuchador_botones_letras(   ((JTextField)elementos_tabla[fila][1])  , 'b'  , offset_caracteres);
			offset_caracteres--;
		}});
		
		boton_op_c.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_op_c){
			new escuchador_botones_letras(   ((JTextField)elementos_tabla[fila][1])  , 'c'  , offset_caracteres);
			offset_caracteres--;
		}});
		
		boton_op_d.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_op_d){
			new escuchador_botones_letras(   ((JTextField)elementos_tabla[fila][1])  , 'd'  , offset_caracteres);
			offset_caracteres--;
		}});
		
		boton_op_e.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_op_e){
			new escuchador_botones_letras(   ((JTextField)elementos_tabla[fila][1])  , 'e'  , offset_caracteres);
			offset_caracteres--;
		}});
		
		boton_op_f.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_op_f){
			new escuchador_botones_letras(   ((JTextField)elementos_tabla[fila][1])  , 'f'  , offset_caracteres);
			offset_caracteres--;
		}});
		
		boton_op_g.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_op_g){
			new escuchador_botones_letras(   ((JTextField)elementos_tabla[fila][1])  , 'g'  , offset_caracteres);
			offset_caracteres--;
		}});
		
		boton_op_h.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_op_h){
			new escuchador_botones_letras(   ((JTextField)elementos_tabla[fila][1])  , 'h'  , offset_caracteres);
			offset_caracteres--;
		}});
		
		
		boton_op_x.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_op_x){
			new escuchador_botones_letras(   ((JTextField)elementos_tabla[fila][1])  , 'x'  , offset_caracteres);
			offset_caracteres--;
		}});
		
		boton_op_y.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_op_y){
			new escuchador_botones_letras(   ((JTextField)elementos_tabla[fila][1])  , 'y'  , offset_caracteres);
			offset_caracteres--;
		}});
		
		boton_op_z.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_op_z){
			new escuchador_botones_letras(   ((JTextField)elementos_tabla[fila][1])  , 'z'  , offset_caracteres);
			offset_caracteres--;
		}});
		
		boton_op_t.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent b_op_t){
			new escuchador_botones_letras(   ((JTextField)elementos_tabla[fila][1])  , 't'  , offset_caracteres);
			offset_caracteres--;
		}});
		
	//movimiento del cursor
		boton_op_izqt.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent izq_t_t){
			((JTextField)elementos_tabla[fila][1]).requestFocus();
			((JTextField)elementos_tabla[fila][1]).setCaretPosition(0);
		}});
		
		boton_op_dert.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent der_t_t){
			((JTextField)elementos_tabla[fila][1]).requestFocus();
			((JTextField)elementos_tabla[fila][1]).setCaretPosition(    (((JTextField)elementos_tabla[fila][1]).getText()).length()    );
		}});
		
		boton_op_del.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent delete_c){
			int posicion_cursor = ((JTextField)elementos_tabla[fila][1]).getCaretPosition();
			if (posicion_cursor > 0){
				String contenido = ((JTextField)elementos_tabla[fila][1]).getText();	
				String cadena_resultado = (contenido.substring(0,posicion_cursor-1)) + (contenido.substring(posicion_cursor,contenido.length()));
				((JTextField)elementos_tabla[fila][1]).setText(cadena_resultado);
				((JTextField)elementos_tabla[fila][1]).requestFocus();
				if (posicion_cursor-1 >0) {((JTextField)elementos_tabla[fila][1]).setCaretPosition(posicion_cursor-1);}
				else if (posicion_cursor-1 <= 0){
					((JTextField)elementos_tabla[fila][1]).setCaretPosition(0);
				}
				
			}//if
			else if (posicion_cursor == 0){
				((JTextField)elementos_tabla[fila][1]).requestFocus();
				((JTextField)elementos_tabla[fila][1]).setCaretPosition(0);
			}
			
		}});
		
		boton_op_izq.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent izquierda_c){
			int posicion_cursor = ((JTextField)elementos_tabla[fila][1]).getCaretPosition();
			if (posicion_cursor != 0){
				((JTextField)elementos_tabla[fila][1]).requestFocus();
				((JTextField)elementos_tabla[fila][1]).setCaretPosition(posicion_cursor-1);
				
			}
			else {
				((JTextField)elementos_tabla[fila][1]).requestFocus();
				((JTextField)elementos_tabla[fila][1]).setCaretPosition(0);
			}
		}});
		
		boton_op_der.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent izquierda_c){
			int posicion_cursor = ((JTextField)elementos_tabla[fila][1]).getCaretPosition();
			//int longiturString contenido = ((JTextField)elementos_tabla[fila][1]).getText();
			if (posicion_cursor != (((JTextField)elementos_tabla[fila][1]).getText()).length() ){
				((JTextField)elementos_tabla[fila][1]).requestFocus();
				((JTextField)elementos_tabla[fila][1]).setCaretPosition(posicion_cursor+1);
				
			}
			else {
				((JTextField)elementos_tabla[fila][1]).requestFocus();
				((JTextField)elementos_tabla[fila][1]).setCaretPosition(  (((JTextField)elementos_tabla[fila][1]).getText()).length()   );
			}
		}});
		
		boton_op_pidt.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent qq2){
			int posicion_cursor = ((JTextField)elementos_tabla[fila][1]).getCaretPosition();
			((JTextField)elementos_tabla[fila][1]).setText("( "+((JTextField)elementos_tabla[fila][1]).getText()+" )");
			((JTextField)elementos_tabla[fila][1]).requestFocus();
			((JTextField)elementos_tabla[fila][1]).setCaretPosition(posicion_cursor+2);
		}});
		
		boton_op_pit.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent qq2){
			int posicion_cursor = ((JTextField)elementos_tabla[fila][1]).getCaretPosition();
			((JTextField)elementos_tabla[fila][1]).setText("( "+((JTextField)elementos_tabla[fila][1]).getText());
			((JTextField)elementos_tabla[fila][1]).requestFocus();
			((JTextField)elementos_tabla[fila][1]).setCaretPosition(posicion_cursor+2);
		}});
		
		boton_op_pdt.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent qq2){
			int posicion_cursor = ((JTextField)elementos_tabla[fila][1]).getCaretPosition();
			((JTextField)elementos_tabla[fila][1]).setText(((JTextField)elementos_tabla[fila][1]).getText()+" )");
			((JTextField)elementos_tabla[fila][1]).requestFocus();
			((JTextField)elementos_tabla[fila][1]).setCaretPosition(posicion_cursor);
		}});
		
		//cortar, copiar, pegar botones barra
		boton_herramientas_cortar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent cortar){
			((JTextField)elementos_tabla[fila][1]).cut();
		}});
		boton_herramientas_copiar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent copiar){
			((JTextField)elementos_tabla[fila][1]).copy();
		}});
		boton_herramientas_pegar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent pegar){
			((JTextField)elementos_tabla[fila][1]).paste();
		}});
		
		//cortar, copiar, pegar botones menu edición
		item_cortar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent cortar){
			((JTextField)elementos_tabla[fila][1]).cut();
		}});
		item_copiar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent copiar){
			((JTextField)elementos_tabla[fila][1]).copy();
		}});
		item_pegar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent pegar){
			((JTextField)elementos_tabla[fila][1]).paste();
		}});
		
		
		boton_borrar_1.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent br_1){
			((JTextField)elementos_tabla[fila][1]).setText("");
			((JTextField)elementos_tabla[fila][1]).setForeground(Color.black);
			((JTextField)elementos_tabla[fila][1]).setBackground(Color.white);
			boton_analizar_cubo.setEnabled(false);
			boton_analizar_espejo.setEnabled(false);
			boton_analizar_infinito.setEnabled(false);
			boton_analizar_magia.setEnabled(false);
			boton_analizar_centrado.setEnabled(false);
		}});
		
		boton_analizar_todo.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent an_todo){
			for (int i=0; i<100; i++){
				if ((((JTextField)elementos_tabla[i][1]).getText()) != ""){
					hilo_analizador hilo_x = new hilo_analizador(mimalla,((JTextField)elementos_tabla[i][1]),1);
					hilo_x.run();
				}//if
			}//for
		}});
		
		
		item_analizar_todas.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent an_todo){
			for (int i=0; i<100; i++){
				if ((((JTextField)elementos_tabla[i][1]).getText()) != ""){
					hilo_analizador hilo_x = new hilo_analizador(mimalla,((JTextField)elementos_tabla[i][1]),1);
					hilo_x.run();
				}//if
			}//for
		}});
		
		item_blanquear_malla.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent reset_malla){
			mimalla = new malla();
			_tablero.actualizar_malla(mimalla);
			_tablero.repaint();	
			offset_caracteres = 0;
			for (int i=0; i<100; i++){
				((JTextField)elementos_tabla[i][1]).setForeground(Color.black);
				((JTextField)elementos_tabla[i][1]).setBackground(Color.white);
			}//for
		}});
		
		
	boton_herramientas_guardar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent assda){
		
				JFileChooser jFileChooser1 = new JFileChooser(new File(current_directory));
				jFileChooser1.setDialogTitle("¿Donde desea guardar el archivo?"); 
				jFileChooser1.setFileFilter(new filtro_archivos(".ITK"));
				int returnVal = jFileChooser1.showSaveDialog(interfaz.this); 
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					File resultFile = jFileChooser1.getSelectedFile();
					String path = new String (resultFile.getPath().toString());
					if (!path.contains(".ITK")) path = path+".ITK";
					current_directory = path;
					try{
						FileOutputStream salida = new FileOutputStream(path);
						ObjectOutputStream oos = new ObjectOutputStream(salida);
						oos.writeObject(mimalla);
						for (int i=0; i<100; i++) {
							String formula_para_guardar = ((JTextField)elementos_tabla[i][1]).getText();
							oos.writeObject(formula_para_guardar);
							
						}//for	
    						oos.close();
					}catch(Exception e){System.out.println("interfaz.java: Error E/S");}
					
				} else {
					jFileChooser1.setVisible(false); 
				} 
	}});	
	
	
	
	
	item_guardar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent assda){
		
				JFileChooser jFileChooser1 = new JFileChooser(new File(current_directory));
				jFileChooser1.setDialogTitle("¿Donde desea guardar el archivo?"); 
				jFileChooser1.setFileFilter(new filtro_archivos(".ITK"));
				int returnVal = jFileChooser1.showSaveDialog(interfaz.this); 
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					File resultFile = jFileChooser1.getSelectedFile();
					String path = new String (resultFile.getPath().toString());
					if (!path.contains(".ITK")) path = path+".ITK";
					current_directory = path;
					try{
						FileOutputStream salida = new FileOutputStream(path);
						ObjectOutputStream oos = new ObjectOutputStream(salida);
						oos.writeObject(mimalla);
						for (int i=0; i<100; i++) {
							String formula_para_guardar = ((JTextField)elementos_tabla[i][1]).getText();
							oos.writeObject(formula_para_guardar);
							
						}//for	
    						oos.close();
					}catch(Exception e){System.out.println("interfaz.java: Error E/S");}
					
				} else {
					jFileChooser1.setVisible(false); 
				} 
	}});	
	
	boton_herramientas_cargar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent assda){
		
				JFileChooser jFileChooser1 = new JFileChooser(new File(current_directory));
				jFileChooser1.setDialogTitle("¿Que archivo desea cargar?"); 
				jFileChooser1.setFileFilter(new filtro_archivos(".ITK"));
				int returnVal = jFileChooser1.showOpenDialog(interfaz.this); 
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					File resultFile = jFileChooser1.getSelectedFile();
					String path = new String (resultFile.getPath().toString());
					current_directory = path;
					try{
						FileInputStream entrada = new FileInputStream(path);
						ObjectInputStream oos = new ObjectInputStream(entrada);
						malla temp_mimalla = (malla) oos.readObject();
						
						for (int i=0; i<100; i++) {
							String formula_cargada = (String) oos.readObject();
							((JTextField)elementos_tabla[i][1]).setText(formula_cargada);
						}//for	
    						oos.close();
						mimalla = temp_mimalla;
						//mimalla.imprimirmalla();
						//temp_mimalla.imprimirmalla();
						_tablero.actualizar_malla(mimalla);
						_tablero.repaint();
					}catch(Exception e){System.out.println("interfaz.java: Error E/S");}
					(new blanquear_sentencias(elementos_tabla)).run();
					
				} else {
					jFileChooser1.setVisible(false); 
				} 
	}});	
	
	
	item_cargar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent assda){
		
				JFileChooser jFileChooser1 = new JFileChooser(new File(current_directory));
				jFileChooser1.setDialogTitle("¿Que archivo desea cargar?"); 
				jFileChooser1.setFileFilter(new filtro_archivos(".ITK"));
				int returnVal = jFileChooser1.showOpenDialog(interfaz.this); 
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					File resultFile = jFileChooser1.getSelectedFile();
					String path = new String (resultFile.getPath().toString());
					current_directory = path;
					try{
						FileInputStream entrada = new FileInputStream(path);
						ObjectInputStream oos = new ObjectInputStream(entrada);
						malla temp_mimalla = (malla) oos.readObject();
						for (int i=0; i<100; i++) {
							String formula_cargada = (String) oos.readObject();
							((JTextField)elementos_tabla[i][1]).setText(formula_cargada);
						}//for	
    						oos.close();
						mimalla = temp_mimalla;
						//mimalla.imprimirmalla();
						//temp_mimalla.imprimirmalla();
						_tablero.actualizar_malla(mimalla);
						_tablero.repaint();
					}catch(Exception e){System.out.println("interfaz.java: Error E/S");}
					(new blanquear_sentencias(elementos_tabla)).run();
					
				} else {
					jFileChooser1.setVisible(false); 
				} 
	}});	
	
	
	
	
		//TODOS LOS ToolTips agrupados aquí
		boton_flag_a.setToolTipText("Selecciona un objeto del modelo y pulsa aquí para asignarle el nombre 'a'");
		boton_flag_b.setToolTipText("Selecciona un objeto del modelo y pulsa aquí para asignarle el nombre 'b'");
		boton_flag_c.setToolTipText("Selecciona un objeto del modelo y pulsa aquí para asignarle el nombre 'c'");
		boton_flag_d.setToolTipText("Selecciona un objeto del modelo y pulsa aquí para asignarle el nombre 'd'");
		boton_flag_e.setToolTipText("Selecciona un objeto del modelo y pulsa aquí para asignarle el nombre 'e'");
		boton_flag_f.setToolTipText("Selecciona un objeto del modelo y pulsa aquí para asignarle el nombre 'f'");
		boton_flag_g.setToolTipText("Selecciona un objeto del modelo y pulsa aquí para asignarle el nombre 'g'");
		boton_flag_h.setToolTipText("Selecciona un objeto del modelo y pulsa aquí para asignarle el nombre 'h'");
		
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
		
		boton_borrar_1.setToolTipText("Borra la fórmula que esté seleccionada");
		boton_analizar_todo.setToolTipText("Analiza todas las fórmulas escritas");
		boton_analizar_cubo.setToolTipText("Evaluación de la fórmula marcada en un modelo cúbico");
		boton_analizar_espejo.setToolTipText("Evaluación de la fórmula marcada en un modelo especular");
		boton_analizar_infinito.setToolTipText("Evaluación de la fórmula marcada en un modelo sin objetos");	
		boton_analizar_magia.setToolTipText("Evaluación de la fórmula marcada en un modelo aleatorio generado por la máquina");
		boton_analizar_centrado.setToolTipText("Evaluación de la fórmula en un modelo en el que los elementos se han ido hacia la parte central");
		
		boton_herramientas_nuevo.setToolTipText("Reiniciar el espacio de trabajo");
		boton_herramientas_cargar.setToolTipText("Cargar de un archivo");
		boton_herramientas_guardar.setToolTipText("Salvar trabajo a un archivo");
		boton_herramientas_cortar.setToolTipText("Cortar selección");
		boton_herramientas_copiar.setToolTipText("Copiar selección");
		boton_herramientas_pegar.setToolTipText("Pegar");
		
		combo_colores.setToolTipText("Color del elemento a insertar");
		combo_tamaños.setToolTipText("Tamaño del elemento a insertar");

		
		//El panel que se muestra es "panel_superior".Esto debería ir al final del todo todo
		getContentPane().add(panel_superior);
		
		
		
		
	}//constructor

	public void reiniciar_todo(){		//Los JTextField  en fondo blanco y las variables a cero. Vuelve al estado inicial
		mimalla = new malla();
//mimalla.imprimirmalla();
		fila = 0;
		offset_caracteres = 0;
		for (int i=0; i<100; i++){
			((JTextField)elementos_tabla[i][1]).setForeground(Color.black);
			((JTextField)elementos_tabla[i][1]).setBackground(Color.white);
			((JTextField)elementos_tabla[i][1]).setText("");
		}//for
		_tablero.actualizar_malla(mimalla);
		_tablero.repaint();
		for (int i=0; i<100; i++) paneles_filas_tabla[i].setBorder(null);
		paneles_filas_tabla[fila].setBorder(new LineBorder(Color.blue,2,true));
	}
	
//*******************************************************************************************************************************
//*******************************************************************************************************************************
//*******************************************************************************************************************************
//*******************************************************************************************************************************
 	public static void main(String argv[]) {
	
		Toolkit tk = Toolkit.getDefaultToolkit();
         	Dimension d = tk.getScreenSize();			//Obtener resolución de la pantalla	
	
		if  ( (d.width <800) || (d.height<600) ) System.out.println("resolucion_baja ("+d.width+","+d.height+")");
		else System.out.println("resolucion_suficiente ("+d.width+","+d.height+")");
System.out.println("Ithaca v0.21 (BETA)");
	        
		final bootSplash splash_screen = new bootSplash();	//Primero el bootsplash
		splash_screen.setLocationRelativeTo(null);	//esto hace que aparezca en el centro justo de la pantalla
		splash_screen.setVisible(true);
		
		final interfaz demo = new interfaz();			//Se crea el resto de la interfaz mientras se muestra el bootsplash
	            
		demo.setSize(d.width,d.height);
		demo.setExtendedState(JFrame.MAXIMIZED_BOTH);		//La ventana aparecerá maximizada (Solo funciona en windows)
		demo.setLocationRelativeTo(null); 
        	demo.setVisible(true);
		splash_screen.setVisible(false);			//Al dibujar la ventana principal esconder el bootsplash
		
System.out.println("Nombre del SO: "+System.getProperty("os.name"));
System.out.println("Arquitectura: "+System.getProperty("os.arch"));
System.out.println("Versión: "+System.getProperty("os.version"));
		
		if  ( (d.width <800) || (d.height<600) ) new warning_low_resolution();	//Si la resolución es baja, se muestra aviso
	
System.out.println("FUNCIONANDO...");
	}//main

}//class


