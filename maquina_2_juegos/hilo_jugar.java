/*
 *  "hilo_jugar.java"
 * 	
 *	Este hilo mueve casi todo el juego.
 *
 *
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */
 
/*	
 *	public hilo_jugar(		//Constructor. Toma casi todas las variables que intervienen en el juego
 *				JPanel panel_derivacion,
 *				JPanel panel_norte,
 *				JPanel panel_fondo_rol_jugador,
 *				JPanel panel_fondo_rol_maquina,
 *				JPanel _tablero_overlay,
 *				JLabel etiqueta_ganadas_v,
 *				JLabel etiqueta_perdidas_v,
 *				JLabel etiqueta_nivel_v,
 *				JLabel label_rol_jugador,
 *				JLabel label_rol_maquina,
 *				JLabel label_turno_jugador_2,
 *				JLabel label_turno_jugador,
 *				JLabel label_turno_maquina,
 *				JLabel label_turno_maquina_2,
 *				JLabel label_informacion_1,
 *				JLabel label_informacion_2,
 *				JButton boton_hijo_izquierdo,
 *				JButton boton_hijo_derecho,
 *				JLabel etiqueta_subarbol_conector,
 *				JTextField texto_formula,
 *				malla mimalla,
 *				tablero _tablero,
 *				int inteligencia,
 *				arbol miarbol,
 *				nodo nodo_raiz,
 *				seguimiento_arbol seguidor,
 *				JButton boton_herramientas_nuevo
 *			)
 *
 *	public void run()
 *	public void start()
 *	public void jugar()
 *	public void poner_flecha(int jugador)
 *	private void sumar_acierto()
 *	private void sumar_fallo()
 *	private void activar_botones_derivacion()
 *	private void desactivar_botones_derivacion()
 *	private void cambiar_rol()
 *	public String pasame_cuantificador()
 *
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import javax.swing.border.*;

import java.awt.image.*;
import java.net.URL;



public class hilo_jugar extends Thread {

	private Thread idx_Thread = null;
	private hilo_parpadeo_panel h1 = null;
	private hilo_parpadeo_panel h2 = null;
	private int threadpriority=idx_Thread.MIN_PRIORITY;

	public static ImageIcon icono_flecha_turno = new ImageIcon("png/flecha_turno.png");
	public static ImageIcon icono_flecha_turno_2 = new ImageIcon("png/flecha_turno_2.png");
	public static ImageIcon icono_noflecha_turno = new ImageIcon("png/flecha_noturno.png");

	public static JLabel etiqueta_ganadas_v;
	public static JLabel etiqueta_perdidas_v;
	public static JLabel etiqueta_nivel_v;
	
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
	
	public static JPanel panel_derivacion;
	public static JTextField texto_formula;
	
	public static JLabel label_informacion_1;
	public static JLabel label_informacion_2;
	
	public static JPanel _tablero_overlay;
	
	public static JButton boton_herramientas_nuevo;
	
	miniconversor mn1;
	malla mimalla;
	tablero _tablero;
	String _hijo_izquierdo;
	String _hijo_derecho;
	String _trozo;
	boolean rol;
	boolean _hoja;
	int estado;
	boolean en_juego;
	String _cuantificador;
	int inteligencia;
	arbol miarbol;
	nodo nodo_raiz;
	seguimiento_arbol seguidor;
	
	public hilo_jugar(		//Constructor. Toma casi todas las variables que intervienen en el juego
				JPanel panel_derivacion,
				JPanel panel_norte,
				JPanel panel_fondo_rol_jugador,
				JPanel panel_fondo_rol_maquina,
				JPanel _tablero_overlay,
				JLabel etiqueta_ganadas_v,
				JLabel etiqueta_perdidas_v,
				JLabel etiqueta_nivel_v,
				JLabel label_rol_jugador,
				JLabel label_rol_maquina,
				JLabel label_turno_jugador_2,
				JLabel label_turno_jugador,
				JLabel label_turno_maquina,
				JLabel label_turno_maquina_2,
				JLabel label_informacion_1,
				JLabel label_informacion_2,
				JButton boton_hijo_izquierdo,
				JButton boton_hijo_derecho,
				JLabel etiqueta_subarbol_conector,
				JTextField texto_formula,
				malla mimalla,
				tablero _tablero,
				int inteligencia,
				arbol miarbol,
				nodo nodo_raiz,
				seguimiento_arbol seguidor,
				JButton boton_herramientas_nuevo
	
			){	//estado final dice si al final queda encendido o apagado
		this.panel_derivacion = panel_derivacion;
		this.panel_norte = panel_norte;
		this.panel_fondo_rol_jugador = panel_fondo_rol_jugador;
		this.panel_fondo_rol_maquina = panel_fondo_rol_maquina;
		this._tablero_overlay = _tablero_overlay;
		this.etiqueta_ganadas_v = etiqueta_ganadas_v;
		this.etiqueta_perdidas_v = etiqueta_perdidas_v;
		this.etiqueta_nivel_v = etiqueta_nivel_v;
		this.label_rol_jugador = label_rol_jugador;
		this.label_rol_maquina = label_rol_maquina;
		this.label_turno_jugador = label_turno_jugador;
		this.label_turno_jugador_2 = label_turno_jugador_2;
		this.label_turno_maquina = label_turno_maquina;
		this.label_turno_maquina_2 = label_turno_maquina_2;
		this.label_informacion_1 = label_informacion_1;
		this.label_informacion_2 = label_informacion_2;
		this.boton_hijo_izquierdo = boton_hijo_izquierdo;
		this.boton_hijo_derecho = boton_hijo_derecho;
		this.etiqueta_subarbol_conector = etiqueta_subarbol_conector;
		this.texto_formula = texto_formula;
		this.mimalla = mimalla;
		this._tablero = _tablero;
		this.inteligencia = inteligencia;
		this.miarbol = miarbol;
		this.nodo_raiz = nodo_raiz;
		this.seguidor = seguidor;
		this.boton_herramientas_nuevo = boton_herramientas_nuevo;
	
		
		
		
	}//constructor
	
	
	public void run(){
		mn1 = new miniconversor();
		jugar();
	}
	
	public void start() {
        	idx_Thread = new Thread(this);
        	idx_Thread.setPriority(Thread.MIN_PRIORITY);
        	idx_Thread.start();
    	}
    
	
	//_________________________________________________
	//---- FUNCION ES ACCESORIAS ----
	//_________________________________________________
	
	public void jugar(){

		desactivar_botones_derivacion();
		_tablero.sensible_off();
		_tablero_overlay.setBorder(new EmptyBorder(10,10,10,10));
		boton_hijo_izquierdo.setBorder(new EmptyBorder(10,10,10,10));
		boton_hijo_derecho.setBorder(new EmptyBorder(10,10,10,10));
		panel_derivacion.setBorder(new EmptyBorder(10,10,10,10));
		String formula2 = mn1.convertir_visual_interno(texto_formula.getText());

		try{
			Yylex_STATUS lexer = new Yylex_STATUS(formula2);
			parser_STATUS _ps1 = new parser_STATUS(lexer,formula2);
			_ps1.parse();
			estado = _ps1.estado();
			_hoja = _ps1.hoja();
			_hijo_izquierdo = _ps1.hijo_izquierdo();
			_hijo_derecho = _ps1.hijo_derecho();
			_trozo = _ps1.trozo();
			_cuantificador = _ps1.cuantificador();
	
		}catch(Exception error){System.out.println("TERMINATOR: Error de parseo ");}
		
		if (estado == 1){	//IF CUANTIFICADOR
	_tablero.sensible_off();
			//encontrar el cuantificador. Será la primera construcción igual a "CU" o a "E"
			//con "CU" juega falsador. Con "E" juega verificador.
			if (	((_cuantificador.charAt(0) == 'E') && (panel_fondo_rol_jugador.getBackground() == Color.green)) ||
				((_cuantificador.charAt(0) == 'C') && (panel_fondo_rol_jugador.getBackground() == Color.red)) ){
				//juega el jugador
				desactivar_botones_derivacion();
				poner_flecha(1);
				(new hilo_parpadeo_panel(_tablero_overlay,true)).run();
				_tablero.sensible_on();
				label_informacion_1.setText("Elija un objeto para resolver");
				label_informacion_2.setText(" el cuantificador \" "+mn1.convertir_interno_visual(_cuantificador)+"\"");
			
			}
			else{	//juega la maquina
				//Seleccionar un objeto de la malla
				_tablero.sensible_off();
				desactivar_botones_derivacion();
				poner_flecha(2);
				hilo_parpadeo_panel hp2 = new hilo_parpadeo_panel(_tablero_overlay,false);
				hp2.run();
				label_informacion_1.setText("La máquina está pensando en un objeto");
				label_informacion_2.setText(" para resolver el cuantificador \" "+mn1.convertir_interno_visual(_cuantificador)+"\"");
				try{ hp2.join();}catch (InterruptedException iie2){}
				try{
					idx_Thread.sleep(1500);
				}catch (InterruptedException e){}
				
				
				
				
				if (inteligencia == 1){	//tonto. juega al azar
					hilo_seleccionar_objeto_azar hso1;
					hso1 = new hilo_seleccionar_objeto_azar(	texto_formula,
											mimalla,
											_tablero,
											_cuantificador,
											seguidor
										);
					hso1.run();
					try{
						hso1.join();
					}catch (InterruptedException ie1){System.out.println("no puedo esperar hilo objeto");}
					
					//nodo_recorrido = hso1.get_nodo();
				}
				else if (inteligencia == 2){ //listo. sigue la estrategia
					hilo_seleccionar_objeto_master hso1;
					hso1 = new hilo_seleccionar_objeto_master(	texto_formula,
											mimalla,
											_tablero,
											_cuantificador,
											seguidor
										);
					hso1.run();
					try{
						hso1.join();
					}catch (InterruptedException ie1){System.out.println("no puedo esperar hilo objeto");}
				}
				jugar();
			}//else
			 
		}//if CUANTIFICADOR
		

		if (estado == 2){	//IF DERIVACION
	_tablero.sensible_off();
		boton_hijo_izquierdo.setText( mn1.convertir_interno_visual(_hijo_izquierdo) );
		boton_hijo_derecho.setText( mn1.convertir_interno_visual(_hijo_derecho) );
		//etiqueta_subarbol_conector.setText(mn1.convertir_interno_visual(_trozo));
		etiqueta_subarbol_conector.setText("  ");

		  if((((_trozo.compareTo("v") == 0) || (_trozo.compareTo("-->") == 0)) && (panel_fondo_rol_jugador.getBackground() == Color.green))  ||
		     (((_trozo.compareTo("^") == 0) || (_trozo.compareTo("<->") == 0)) && (panel_fondo_rol_jugador.getBackground() == Color.red))){
		     	//JUEGA EL JUGADOR
			activar_botones_derivacion();
			poner_flecha(1);
			_tablero.sensible_off();
			(new hilo_parpadeo_panel(panel_derivacion,true)).run();
			label_informacion_1.setText("Elija un subárbol");
			label_informacion_2.setText("");
		     
		     }//if juega jugador
		   else {
		   	//JUEGA LA MAQUINA
			//Seleccionar subarbol
			desactivar_botones_derivacion();
			poner_flecha(2);
			_tablero.sensible_off();
			hilo_parpadeo_panel hp1 = new hilo_parpadeo_panel(panel_derivacion,false);
			label_informacion_1.setText("La máquina está pensando en un subárbol");
			label_informacion_2.setText(" con el que jugar");
			hp1.run();
			try{ hp1.join();}catch (InterruptedException iie1){}
			
			try{
					idx_Thread.sleep(1500);
			}catch (InterruptedException e){}
			
			if (inteligencia == 1){	//tonto
				hilo_seleccionar_subarbol_azar hsa1;
				hsa1 = new hilo_seleccionar_subarbol_azar(	texto_formula,
										boton_hijo_izquierdo,
										boton_hijo_derecho,
										label_informacion_2,
										seguidor
										);
				hsa1.run();
				try{
					hsa1.join();
				}catch (InterruptedException ie1){System.out.println("no puedo esperar hilo subarbol");}
				
				//nodo_recorrido = hsa1.get_nodo();
			}//if
			else if (inteligencia == 2){ //listo
				hilo_seleccionar_subarbol_master hsa1;
				hsa1 = new hilo_seleccionar_subarbol_master(	texto_formula,
										boton_hijo_izquierdo,
										boton_hijo_derecho,
										label_informacion_2,
										seguidor
										);
				hsa1.run();
				try{
					hsa1.join();
				}catch (InterruptedException ie1){System.out.println("no puedo esperar hilo subarbol");}
			}//else
			
			jugar();
							
		
		   }//if juega maquina
		}//derivacion
		
		if (estado == 3){	//IF NEGACION NORMAL
	_tablero.sensible_off();
			//parpadear_panel_norte
			String formula3 = mn1.convertir_visual_interno(texto_formula.getText());
			eliminador_cosas ec1 = new eliminador_cosas(formula3);
			formula3 = ec1.eliminar_negacion();
			texto_formula.setText(mn1.convertir_interno_visual(_trozo));
			label_informacion_1.setText(" ¡AVISO! La negación ha cambiado los roles");
			label_informacion_2.setText("");
			cambiar_rol();
			seguidor._derivar(1);
			(new hilo_parpadeo_panel(panel_norte,false)).run();
			
			jugar();
		}//negacion
		
		if (estado == 4){	//IF NEGACION CUANTI
	_tablero.sensible_off();
			String formula3 = mn1.convertir_visual_interno(texto_formula.getText());
			eliminador_cosas ec1 = new eliminador_cosas(formula3);
			formula3 = ec1.eliminar_negacion();
			texto_formula.setText(mn1.convertir_interno_visual(formula3));
			label_informacion_1.setText(" ¡AVISO! La negación ha cambiado los roles");
			label_informacion_2.setText("");
			cambiar_rol();
			seguidor._derivar(1);
			(new hilo_parpadeo_panel(panel_norte,false)).run();
			
			jugar();
		}//negacion	
			
		else if ( (_hoja == true) && (estado == 0) ){	//es final
	_tablero.sensible_off();
			hilo_analizador hilo_x = new hilo_analizador(mimalla,texto_formula,1);
			hilo_x.run();
			try{
				hilo_x.join();
			}catch (InterruptedException ie1){System.out.println("no puedo esperar hilo analiador de hoja");}	
			if ( (texto_formula.getBackground()) == (panel_fondo_rol_jugador.getBackground()) ){
				sumar_acierto();
				label_informacion_1.setText("Has ganado la partida");
				label_informacion_2.setText("");
			boton_herramientas_nuevo.setEnabled(true);
			}//if gana
			else{
				sumar_fallo();
				label_informacion_1.setText("Has perdido la partida");
				label_informacion_2.setText("");
			boton_herramientas_nuevo.setEnabled(true);
			}//if pierde
		}//if nodo_hoja
	}//jugar
	
	
	
	public void poner_flecha(int jugador){	//pone la flecha en la interfaz. 1-Jugador, 2-Maquina
		if (jugador == 1){
			label_turno_jugador.setIcon(icono_flecha_turno);
			label_turno_jugador_2.setIcon(icono_flecha_turno_2);
			label_turno_maquina.setIcon(icono_noflecha_turno);
			label_turno_maquina_2.setIcon(icono_noflecha_turno);
			
		}//if
		else if (jugador == 2){
			label_turno_maquina.setIcon(icono_flecha_turno);
			label_turno_maquina_2.setIcon(icono_flecha_turno_2);
			label_turno_jugador.setIcon(icono_noflecha_turno);
			label_turno_jugador_2.setIcon(icono_noflecha_turno);
		}//else
	}//poner_flecha
	
	
	private void sumar_acierto(){		//En la barra de herramientas de la interfaz
		String aciertos = etiqueta_ganadas_v.getText();
		Integer valor = -100;
		valor = valor.parseInt(aciertos, 10);
		valor = valor + 1;
		aciertos = valor.toString();
		etiqueta_ganadas_v.setText(aciertos);
		
	}
	
	private void sumar_fallo(){		//IDEM
		String fallos = etiqueta_perdidas_v.getText();
		Integer valor = -100;
		valor = valor.parseInt(fallos, 10);
		valor = valor + 1;
		fallos = valor.toString();
		etiqueta_perdidas_v.setText(fallos);
	}
	
	
		private boolean rol(int jugador){	//calcula del rol del jugador(1) o de la maquina(2)
		boolean result = true;
		if (jugador == 1) { //jugador humano
			if (panel_fondo_rol_jugador.getBackground() == Color.GREEN) result = true;
			if (panel_fondo_rol_jugador.getBackground() == Color.RED) result = false;
		}
		else if (jugador == 2){	//maquina
			if (panel_fondo_rol_maquina.getBackground() == Color.GREEN) result = true;
			if (panel_fondo_rol_maquina.getBackground() == Color.RED) result = false;	
		}
		return result;
	}//rol()
	
	
	
	private void activar_botones_derivacion(){
		boton_hijo_izquierdo.setEnabled(true);
		boton_hijo_derecho.setEnabled(true);
	}//activar_botones_derivacion
	
	
	private void desactivar_botones_derivacion(){
		boton_hijo_izquierdo.setEnabled(false);
		boton_hijo_derecho.setEnabled(false);
	}//desactivar_botones_derivacion
	
	
	private void cambiar_rol(){
		if (panel_fondo_rol_jugador.getBackground() == Color.GREEN) {	//jugador es VERIFICADOR
			panel_fondo_rol_jugador.setBackground(Color.RED );
			panel_fondo_rol_maquina.setBackground(Color.GREEN);
			label_rol_jugador.setText("Jugador es FALSADOR");
			label_rol_maquina.setText("Maquina es VERIFICADOR");		
		}//if
		else if (panel_fondo_rol_jugador.getBackground() == Color.RED){	//jugador es FALSADOR
			panel_fondo_rol_jugador.setBackground(Color.GREEN );
			panel_fondo_rol_maquina.setBackground(Color.RED);
			label_rol_jugador.setText("Jugador es VERIFICADOR");
			label_rol_maquina.setText("Maquina es FALSEADOR");					
		}//else
	}
	
	public String pasame_cuantificador(){ return _cuantificador;}
	
	
	
	
}//class