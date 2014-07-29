/*
 *  "tablero_silent.java"
 *      El resultado es un JPanel sobre el que se dibuja un tablero con los elementos de la malla. NO recibe eventos de ratón:
 *	Se basa en "tablero.java" pero eliminando los eventos del ratón
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */

 
 /*
		
	public tablero_silent(malla mimalla)			//Constructor
	public void paint(Graphics g)			
	public Image getImage(String name)
	
	
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

import javax.imageio.*;
import javax.imageio.stream.*;
import java.awt.image.*;
import java.net.URL;
import java.util.Random;

public class tablero_silent extends JPanel {

	int _x0;			//coordenadas del punto (0,0)
	int _y0;
	int pixx;			//ancho de casilla
	int pixy;			//alto de casilla
	Polygon[][] mapa_casillas;	//area poligonal que ocupa cada casilla
	Polygon mapa_papelera;		//area poligonal que ocupa la "papelera"
	malla mimalla;			// la malla que se va a representar
	Boolean flag_marcado;		//indica si hay alguna casilla iluminada (para cambiar su contenido....)
	int x_marcado;			//coordenadas de la casilla qu está iluminada
	int y_marcado;
	Object[][] elementos_tabla;	//se apunta esto a los cuadros de texto (JTextField) de la interfaz para poder "blanquearlos" cuando se modifica algo (estado de "no analizado")

    public tablero_silent(malla mimalla) {this.mimalla = mimalla;}
    
    public void asignar_malla (malla mimalla) {
    	this.mimalla = mimalla;
	repaint();	
    }
	
    public void paint(Graphics g) {		// la más importante
        Graphics2D g2 = (Graphics2D) g;
        Dimension d = getSize();	//pilla lo que mide la ventana. Al redimensionar se readapta
	int x_size = (d.width-10);
	int y_size = (d.height-10);

	boolean medidas_ok = false;
	while (medidas_ok != true){
		if ((x_size / y_size) == 3) medidas_ok = true;		//Esto calcula cual debe ser la medida del dibujo en sí en función
		else if (( x_size/y_size) < 3) {			// de cual es el tamaño de la ventana. Se debe conservar la relación
			x_size = x_size;				// de ( ancho = 3 * largo). De modo que se toma la medida límite
			y_size = (x_size /3);				// de entre las dos en función del tamaño de la ventana y se
									// calcula la otra
		}//else
		else if ((x_size/y_size) > 3){
			x_size = (y_size * 3);
			y_size = y_size;
		}//else
	}//while
	_x0 = (((d.width-10) /2) - (x_size/2));		//punto (0,0) que usaremos como referencia para comenzar a dibujar
	_y0 = (((d.height-10) /2) - (y_size/2));

	
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);		//para que las lineas se dibujen con alisado. Mooooola esta función
	
	g2.setBackground(Color.black);

        g2.clearRect(0, 0, d.width, d.height);				//Un fondo negro para "limpiar" lo que haya debajo
	g2.setColor(Color.orange);
	pixx = x_size /12;
	pixy = y_size /6;
	
	for (int i=0; i<10; i++){	//lineas cuasi-verticales
		g2.drawLine( ((pixx*i)+_x0) , _y0, ((3+i)*pixx)+_x0, (6*pixy)+_y0);	
	}//for
	
	int poquito = ((3*pixx)/6);	//lo que se desplaza el comienzo/final de cada linea horizontal al ir bajando para dar perspectiva
	for (int i=0; i<7; i++){	//lineas cuasi-horizontales
		g2.drawLine( _x0+(i*pixx)-(i*poquito) , _y0+(pixy*i), _x0+((9+i)*pixx)-(poquito*i) , _y0+(pixy*i) );
	}
	
	
	//dibujar las figuras en sus casillas
	Image img_cubo_rojo =		 getImage("png/cubo_rojo.png");	//Cogemos una imagen base de cada y según sea "grande" o 
	Image img_cubo_azul =		 getImage("png/cubo_azul.png");	// "pequeña" la redimensionamos al dibujarla
	Image img_cubo_amarillo =	 getImage("png/cubo_amarillo.png");
	Image img_esfera_roja = 	 getImage("png/esfera_roja.png");
	Image img_esfera_azul = 	 getImage("png/esfera_azul.png");
	Image img_esfera_amarilla = 	 getImage("png/esfera_amarilla.png");
	Image img_piramide_roja = 	 getImage("png/piramide_roja.png");
	Image img_piramide_azul = 	 getImage("png/piramide_azul.png");
	Image img_piramide_amarilla = 	 getImage("png/piramide_amarilla.png");
	
	for (int i=0; i<6; i++){		
		for (int j=0; j<9; j++){
			if (mimalla.devolverCasilla(i,j).ocupada() == true){
				int tamanio = 0; 
				int pos_x = 0;
				int pos_y = 0;
				if (mimalla.devolverCasilla(i,j).tamanho() == 1){	//objeto grande
					tamanio = pixx - (pixx/5);
					pos_x = _x0+(j*pixx)+(i*poquito)+ (pixy/2);
					pos_y = _y0+(i*pixy)- (pixy/2);
					
					if (mimalla.devolverCasilla(i,j).forma() == 1){//esfera
					  if (mimalla.devolverCasilla(i,j).color()==1) g2.drawImage(img_esfera_roja,pos_x,pos_y,tamanio,tamanio,null);
					  if (mimalla.devolverCasilla(i,j).color()==2) g2.drawImage(img_esfera_azul,pos_x,pos_y,tamanio,tamanio,null);
					  if (mimalla.devolverCasilla(i,j).color()==3) g2.drawImage(img_esfera_amarilla,pos_x,pos_y,tamanio,tamanio,null);
					}
					else if (mimalla.devolverCasilla(i,j).forma() == 2){//cubo
					  if (mimalla.devolverCasilla(i,j).color()==1) g2.drawImage(img_cubo_rojo,pos_x,pos_y,tamanio,tamanio,null);
					  if (mimalla.devolverCasilla(i,j).color()==2) g2.drawImage(img_cubo_azul,pos_x,pos_y,tamanio,tamanio,null);
					  if (mimalla.devolverCasilla(i,j).color()==3) g2.drawImage(img_cubo_amarillo,pos_x,pos_y,tamanio,tamanio,null);
					}
					else if (mimalla.devolverCasilla(i,j).forma() == 3){//piramide
					  if (mimalla.devolverCasilla(i,j).color()==1) g2.drawImage(img_piramide_roja,pos_x,pos_y,tamanio,tamanio,null);
					  if (mimalla.devolverCasilla(i,j).color()==2) g2.drawImage(img_piramide_azul,pos_x,pos_y,tamanio,tamanio,null);
					  if (mimalla.devolverCasilla(i,j).color()==3) g2.drawImage(img_piramide_amarilla,pos_x,pos_y,tamanio,tamanio,null);
					}//if
				}
				else if (mimalla.devolverCasilla(i,j).tamanho() == 2){	//objeto pequeño
					tamanio = pixx/2;
					pos_x = _x0+(j*pixx)+(i*poquito)+(pixx/2);
					pos_y = _y0+(i*pixy);
					
					if (mimalla.devolverCasilla(i,j).forma() == 1){//esfera
					  if (mimalla.devolverCasilla(i,j).color()==1) g2.drawImage(img_esfera_roja,pos_x,pos_y,tamanio,tamanio,null);
					  if (mimalla.devolverCasilla(i,j).color()==2) g2.drawImage(img_esfera_azul,pos_x,pos_y,tamanio,tamanio,null);
					  if (mimalla.devolverCasilla(i,j).color()==3) g2.drawImage(img_esfera_amarilla,pos_x,pos_y,tamanio,tamanio,null);
					}
					else if (mimalla.devolverCasilla(i,j).forma() == 2){//cubo
					  if (mimalla.devolverCasilla(i,j).color()==1) g2.drawImage(img_cubo_rojo,pos_x,pos_y,tamanio,tamanio,null);
					  if (mimalla.devolverCasilla(i,j).color()==2) g2.drawImage(img_cubo_azul,pos_x,pos_y,tamanio,tamanio,null);
					  if (mimalla.devolverCasilla(i,j).color()==3) g2.drawImage(img_cubo_amarillo,pos_x,pos_y,tamanio,tamanio,null);
					}
					else if (mimalla.devolverCasilla(i,j).forma() == 3){//piramide
					  if (mimalla.devolverCasilla(i,j).color()==1) g2.drawImage(img_piramide_roja,pos_x,pos_y,tamanio,tamanio,null);
					  if (mimalla.devolverCasilla(i,j).color()==2) g2.drawImage(img_piramide_azul,pos_x,pos_y,tamanio,tamanio,null);
					  if (mimalla.devolverCasilla(i,j).color()==3) g2.drawImage(img_piramide_amarilla,pos_x,pos_y,tamanio,tamanio,null);
					}//if
				}//else
			}//if
		}//for
	}//for
	
	//dibujar los nombres en las figuras que los tengan
	for (int i=0; i<6; i++){		
		for (int j=0; j<9; j++){
			if ( 	(mimalla.devolverCasilla(i,j).ocupada() == true) && 
				( (mimalla.devolverCasilla(i,j).nombre() == 'a' ) ||
				(mimalla.devolverCasilla(i,j).nombre() == 'b' ) ||
				(mimalla.devolverCasilla(i,j).nombre() == 'c' ) ||
				(mimalla.devolverCasilla(i,j).nombre() == 'd' ) ||
				(mimalla.devolverCasilla(i,j).nombre() == 'e' ) ||
				(mimalla.devolverCasilla(i,j).nombre() == 'f' ) ||
				(mimalla.devolverCasilla(i,j).nombre() == 'g' ) ||
				(mimalla.devolverCasilla(i,j).nombre() == 'h' ) )	 ){
					Character _nombre = mimalla.devolverCasilla(i,j).nombre();
					String nombre = _nombre.toString(_nombre);
					int pos_x = _x0+(j*pixx)+(i*poquito)+ (pixy);
					int pos_y = _y0+(i*pixy)+ (pixy/2);
					g2.setColor(Color.white);
					g2.setFont(new Font("Dialog",Font.BOLD,poquito-1));
					g2.drawString(nombre,pos_x,pos_y);
			}//if
		}//for
	}//for
	
	//dibujar el objeto nulo si no hay nada (el fantasmilla flotante)
	//Image img_objeto_nulo =  getImage("png/objeto_nulo.png");
	//if (mimalla.numElementos() == 0) g2.drawImage(img_objeto_nulo,_x0+x_size/2,_y0+y_size/4,x_size/6, x_size/6,null);
	
    }//paint
    
	
    
	public Image getImage(String name) {			//Grácias Google por ayudarme a descubrir esta estupenda función
		URL url = tablero_silent.class.getResource(name);	// simple, funcional....
		Image img = getToolkit().getImage(url);
		try {
			MediaTracker tracker = new MediaTracker(this);
			tracker.addImage(img, 0);
			tracker.waitForID(0);
		} catch (Exception e) {}
		return img;
	}//getImage()

}
