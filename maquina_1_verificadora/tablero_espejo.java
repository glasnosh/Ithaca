/*
 *  "tablero_espejo.java"
 *     	El resultado de esta clase es un "JPanel" sobre el que se dibuja una malla con proyección de longitud hacia la derecha.
 *      Se usa para el análisis alternativo de la imagen especular de la malla, pero en este caso no se escuchan eventos de 
 * 	 usuario ni nada. Solamente el dibujo en función de lo que tenga la malla, que debería haber sido "dada la vuelta" antes
 * 	 de instanciar/usar a esta clase.
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

import javax.imageio.*;
import javax.imageio.stream.*;
import java.awt.image.*;
import java.net.URL;

public class tablero_espejo extends JPanel{

	int _x0;		//Coordenadas del punto (0,0)
	int _y0;		//
	int pixx;		//ancho de casilla
	int pixy;		//alto de casilla
	Polygon[][] mapa_casillas;
	Polygon mapa_papelera;
	malla mimalla;
	Boolean flag_marcado;
	int x_marcado;
	int y_marcado;
    
    public tablero_espejo(malla _mimalla){ mimalla = _mimalla; }	//CONSTRUCTOR
	
    public void paint(Graphics g) {				//Redefinición de "Paint" para la llamada a "Repaint"
    		/*
			Glasnosh: Para que todo funcione bien según mis pruebas, lo ideal para los dibujos es hacerlo 
				   como se ve aquí. La clase que dibuja hereda de JPanel y en el método "paint" declarar
				   el "Graphics2D" y dibujar sobre él. Finalmente no hace falta incluirlo en el JPanel ni 
				   nada. Regla de oro: "1 JPanel para cada Graphics2D.		
		*/
        Graphics2D g2 = (Graphics2D) g;
        Dimension d = getSize();	//Pilla lo que mide la ventana en ese momento. Si se redimensiona lo "caza" también
	int x_size = (d.width-10);	//Ancho (pixels) de la ventana
	int y_size = (d.height-10);	//Alto (pixels) de la ventana
	
	boolean medidas_ok = false;				//Las medidas del dibujo dentro del JPanel deben ser calculadas
	while (medidas_ok != true){
		if ((x_size / y_size) == 3) medidas_ok = true;		//El alto de la malla debe ser la tercera parte del largo. 
		else if (( x_size/y_size) < 3) {			//Según las medidas de la ventana se calcula las medidas con esa relacion
			x_size = x_size;				// para el dibujo de la malla
			y_size = (x_size /3);
		}
		else if ((x_size/y_size) > 3){				//Según que coordenada marca el límite (ancho o alto), se toma de referencia 
			x_size = (y_size * 3);				// para calcular la otra
			y_size = y_size;
		}							//ahora "x_size"  "y_size" son las medidas que tendrá la malla (el dibujo vamos)
	}//while
	_x0 = (((d.width-10) /2) - (x_size/2));				//offset del punto (0,0)
	_y0 = (((d.height-10) /2) - (y_size/2));

	
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);		//Actualiza las variable del GRAPHICS para que se dibuje con antialiasing
	
	g2.setBackground(Color.black);					//Fondo de la ventana en negro

        g2.clearRect(0, 0, d.width, d.height);				//Se crea el area de dibujo del tamaño de la ventana (se limpia)
	g2.setColor(Color.orange);					//Las lineas se pintan en naranja
	pixx = x_size /12;
	pixy = y_size /6;						//Tamaño de filas (6) y columnas (12)
	
	for (int i=0; i<10; i++){	//lineas cuasi-verticales	
		g2.drawLine( ((3+i)*pixx)+_x0, _y0, ((pixx*i)+_x0) , (6*pixy)+_y0  );
	}//for
	
	int poquito = ((3*pixx)/6);					//lo que se desplaza la linea al ir bajando en cada casilla
	
	for (int i=0; i<7; i++){	//lineas cuasi-horizontales
		g2.drawLine( _x0+((3)*pixx)-(i*poquito) , _y0+(pixy*i), _x0+((12)*pixx)-(poquito*i) , _y0+(pixy*i) );
	}
	
		
	//dibujar las figuras en sus casillas. Primero transformar los "png" en "Image"s utilizables
	Image img_cubo_rojo =		 getImage("png/cubo_rojo.png");
	Image img_cubo_azul =		 getImage("png/cubo_azul.png");
	Image img_cubo_amarillo =	 getImage("png/cubo_amarillo.png");
	Image img_esfera_roja = 	 getImage("png/esfera_roja.png");
	Image img_esfera_azul = 	 getImage("png/esfera_azul.png");
	Image img_esfera_amarilla = 	 getImage("png/esfera_amarilla.png");
	Image img_piramide_roja = 	 getImage("png/piramide_roja.png");
	Image img_piramide_azul = 	 getImage("png/piramide_azul.png");
	Image img_piramide_amarilla = 	 getImage("png/piramide_amarilla.png");
	
	for (int i=0; i<6; i++){					//se recorre la malla 	
		for (int j=0; j<9; j++){
			if (mimalla.devolverCasilla(i,j).ocupada() == true){	//si esa casilla esta ocupada hay que mirar lo que hay y dibujarlo
				int tamanio = 0; 
				int pos_x = 0;
				int pos_y = 0;
				if (mimalla.devolverCasilla(i,j).tamanho() == 1){		//objeto grande
					tamanio = pixx - (pixx/5);				//tamaño del objeto (para alto y para ancho)
					pos_x = _x0+((j-1)*pixx)+((7-i)*poquito)+ (pixy/2);	//los ajustes fueron prueba-error
					pos_y = _y0+(i*pixy)- (pixy/2);				//punto (0,0) del elemento en esas casilla
					
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
				else if (mimalla.devolverCasilla(i,j).tamanho() == 2){		//objeto pequeño
					tamanio = pixx/2;					//tamaño del objeto
					pos_x = _x0+((j-1)*pixx)+((7-i)*poquito)+(pixx/2);
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
	
	
	Image img_objeto_nulo =  getImage("png/objeto_nulo.png");	//Si no hay elementos, dibujar el fantasmas "+- en el medio"
	if (mimalla.numElementos() == 0) g2.drawImage(img_objeto_nulo,_x0+x_size/3,_y0+y_size/4,x_size/6, x_size/6,null);
	
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
					int pos_x = _x0+((j-1)*pixx)+((7-i)*poquito)+ (pixy); //A ojo otra vez. Prueba-error
					int pos_y = _y0+(i*pixy)+ (pixy/2);
					g2.setColor(Color.white);
					g2.setFont(new Font("Dialog",Font.BOLD,poquito-1));
					g2.drawString(nombre,pos_x,pos_y);
			}//if
		}//for
	}//for
	
    }//paint
    
	
    
	public Image getImage(String name) {	//Esta está inspirada en la que aparece en el tutorial Java2D de Sun.
						//Permite crear una "Image" desde cualquier recurso accesible (local o Net)
            URL url = tablero_espejo.class.getResource(name);	
            Image img = getToolkit().getImage(url);
            try {
                MediaTracker tracker = new MediaTracker(this);
                tracker.addImage(img, 0);
                tracker.waitForID(0);
            } catch (Exception e) {}
            return img;
        }//getImage()
	
	
}//class